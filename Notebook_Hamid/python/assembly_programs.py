#!/usr/bin/python

import sys
import pandas as pd


'''
gets the Boa output and generate the csv file for top assembler study

inputs are as follows: 
    AsmStats[2019][GCF_004137355.1][2509717][Velvet] = 1
    AsmStats[2019][GCF_004137365.1][83334][Canu] = 1
    AsmStats[2019][GCF_004137375.1][633][SPAdes] = 1
    AsmStats[2019][GCF_004137425.1][446][Unicycler] = 1

outputs are as follows:
    
'''

def convert_file(boa_output):
    # find the list of all assemblies
    assembly_set =set()
    AllPaths_list =['AllPaths','Allpaths', 'Allpath-lg', 'Allpath-LG', 'AllPaths-LG' , 'allpaths-lg', 'Allpaths/Velvet', 'ALLPATHS-LG', 'allpaths']
    Velvet_list = ['Velveth', 'Velvet', 'Allpaths/Velvet', 'VelvetOptimiser', 'Velevt', 'Velent', 'MetaVelvet', 'VelvetOptimizer']
    Newbler_list =['Newbler', 'newbler']
    CLC_list = ['clc','CLC', 'CLCbio-De', 'CLCBio', 'CLC-Genomics', 'CLC_Genomics_Workbench','CLCbio']
    SPAdes_list =['SPADES', 'spades', 'MetaSPAdes', 'plasmidspades', 'SPAdes', 'SPAdes','SPADes', 'SPAdes-Linux', 'SPADEs', 'iMetAMOS-SPAdes']
    ABySS_list =['ABySS', 'ABYSS']

    file_converted = boa_output + "_converted"
    with open(boa_output) as f, open(file_converted, "w") as modified_output:
        for line in f:
            dataline = line.split('[')
            asYear = dataline[1][:-1]
            refseq = dataline[2][:-1]
            taxid = dataline[3][:-1]
            assembly_program = dataline[4][:-6]

            if assembly_program in Velvet_list:
                assembly_program = "Velvet"
            elif assembly_program in CLC_list:
                assembly_program = "CLC"
            elif assembly_program in AllPaths_list:
                assembly_program = "AllPaths"
            elif assembly_program in Newbler_list:
                assembly_program = "Newbler"
            elif assembly_program in SPAdes_list:
                assembly_program = "SPAdes"
            elif assembly_program in ABySS_list:
                assembly_program = "ABySS"



            modified_output.write(str(asYear) + "," + str(refseq) + "," + taxid + "," + assembly_program + "\n")

            assembly_set.add(assembly_program)

    # following prints list of all assembly programs
    # print(assembly_set)
    return (file_converted)

def get_stats(assembly_file, intereseted_taxids_file):
    df_assemblystats=pd.read_csv(assembly_file, names=['asYear','refseq','taxid', 'assembly'])
    # df_assemblystats = df_assemblystats[['refseq','taxid','total_length','scaffold_count', 'scaffold_N50','contig_count','contig_N50', 'assembly']]

    # list of taxids that we are interested is in the intereseted_taxids_file
    intereseted_taxids = {}
    with open(intereseted_taxids_file) as f:
        for line in f:
            words = line.split()
            (key, val) = words[0], list(words[1:len(words)])
            intereseted_taxids[key] = val

    for tax in intereseted_taxids:
        if int(tax) in [4751 ]: # list of kingdoms tax id: 2,4751,2157,33090, 33208,71240, 4447
            print("#########################################")
            print("tax id: " + tax)

            tax_list = intereseted_taxids[tax]
            # convert list of string to list of int
            tax_list = list(map(int, tax_list))
            # remove duplicate taxids
            tax_list = sorted(set(tax_list))

            df_assemblystats_tax = df_assemblystats[df_assemblystats['taxid'].isin(tax_list)] # list of all subtree taxids is here


            assembly_programs = ['AllPaths', 'Velvet', 'Newbler', 'CLC', 'SPAdes','SOAPdenovo','HGAP','Celera', ]
            for asm in assembly_programs:
                print("###########################")
                print("assembly : " + asm)

                asm_list = [asm]

                df_tmp = df_assemblystats_tax[df_assemblystats_tax['assembly'].isin(asm_list)]
                # df_interested = df_interested[~(df_interested == 0).any(axis=1)]
                # print(df_interested.shape)

                for year in range(2010,2019):
                    year_list = [year]
                    df_tmp_year = df_tmp[df_tmp['asYear'].isin(year_list)]

                    print(str(year) + " "  + str(df_tmp_year.shape))

                    print(df_tmp_year.describe())


modified_output = convert_file(sys.argv[1])

get_stats(modified_output, sys.argv[2])