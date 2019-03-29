#!/usr/bin/python

import sys
import pandas as pd


'''
gets the Boa output and generate the csv file for top assembler study

inputs are as follows: 
    AsmStats[GCF_000001405.36][9606][3241953429][161368251][837][59364414][1497][56413054][N/A] = 1
    AsmStats[GCF_000001515.7][9598][3231170666][98551029][45511][26972556][72226][384816][DiscoVar] = 1
    AsmStats[GCF_000001515.7][9598][3231170666][98551029][45511][26972556][72226][384816][PBJelly] = 1

outputs are as follows:
    GCF_000001405.36,9606,3241953429,161368251,837,59364414,1497,56413054,N/A
    GCF_000001515.7,9598,3231170666,98551029,45511,26972556,72226,384816,DiscoVar
    GCF_000001515.7,9598,3231170666,98551029,45511,26972556,72226,384816,PBJelly
'''

def convert_file(boa_output):
    # find the list of all assemblies
    assembly_set =set()
    AllPaths_list =['AllPaths','Allpaths', 'Allpath-lg', 'Allpath-LG', 'AllPaths-LG' , 'allpaths-lg', 'Allpaths/Velvet', 'ALLPATHS-LG', 'allpaths']
    Velvet_list = ['Velveth', 'Velvet', 'Allpaths/Velvet', 'VelvetOptimiser', 'Velevt', 'Velent', 'MetaVelvet', 'VelvetOptimizer']
    Newbler_list =['Newbler', 'newbler']
    CLC_list = ['clc','CLC', 'CLCbio-De', 'CLCBio', 'CLC-Genomics', 'CLC_Genomics_Workbench','CLCbio']
    SPAdes_list =['SPADES', 'spades', 'MetaSPAdes', 'plasmidspades', 'SPAdes', 'SPAdes','SPADes', 'SPAdes-Linux', 'SPADEs', 'iMetAMOS-SPAdes']

    file_converted = boa_output + "_converted"
    with open(boa_output) as f, open(file_converted, "w") as modified_output:
        for line in f:
            dataline = line.split('[')
            refseq = dataline[1][:-1]
            taxid = dataline[2][:-1]
            total_length = dataline[3][:-1]
            total_gap_length = dataline[4][:-1]
            scaffold_count = dataline[5][:-1]
            scaffold_N50 = dataline[6][:-1]
            contig_count = dataline[7][:-1]
            contig_N50 = dataline[8][:-1]
            assembly_program = dataline[9][:-6]

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


            modified_output.write(str(refseq) + "," + taxid + "," + total_length
                                + "," + total_gap_length + "," + scaffold_count
                                + "," + scaffold_N50 + "," + contig_count + "," + contig_N50 + "," + assembly_program + "\n")

            assembly_set.add(assembly_program)

    print(assembly_set)
    return (file_converted)

def get_stats(assembly_file, intereseted_taxids_file):

    df_assemblystats=pd.read_csv(assembly_file, names=['refseq','taxid','total_length','total_gap_length','scaffold_count', 'scaffold_N50','contig_count','contig_N50', 'assembly'])
    df_assemblystats = df_assemblystats[['refseq','taxid','total_length','scaffold_count', 'scaffold_N50','contig_count','contig_N50', 'assembly']]


    # df_assemblystats.set_index("taxid", inplace=True)

    # list of taxids that we are interested is in the intereseted_taxids_file
    intereseted_taxids = {}
    with open(intereseted_taxids_file) as f:
        for line in f:
            words = line.split()
            (key, val) = words[0], list(words[1:len(words)])
            intereseted_taxids[key] = val

    for tax in intereseted_taxids:
        if int(tax) in [33208]: # list of kingdoms tax id: 2,4751,2157,33090, 33208,71240, 4447
            print("#########################################")
            print("tax id: " + tax)

            tax_list = intereseted_taxids[tax]
            print(" len tax list before sort:" + str(len(tax_list)))

            # convert list of string to list of int
            tax_list = list(map(int, tax_list))
            # remove duplicate taxids
            tax_list = sorted(set(tax_list))
            print(" len tax list:" + str(len(tax_list)))

            df_assemblystats_tax = df_assemblystats[df_assemblystats['taxid'].isin(tax_list)] # list of all subtree taxids is here


            assembly_programs = ['SOAPdenovo','AllPaths', 'Velvet', 'Newbler', 'CLC', 'SPAdes']
            for asm in assembly_programs:
                print("###########################")
                print("assembly : " + asm)

                asm_list = [asm]

                df_interested = df_assemblystats_tax[df_assemblystats_tax['assembly'].isin(asm_list)]
                # df_interested = df_interested[~(df_interested == 0).any(axis=1)]
                print(df_interested.shape)

                for item in ['total_length', 'scaffold_count', 'scaffold_N50', 'contig_count', 'contig_N50']:
                    df_tmp = df_interested[item]
                    # print(df_tmp)
                    # df_tmp = df_tmp[~(df_tmp == 0).any(axis=1)]
                    df_tmp = df_tmp.iloc[df_tmp.nonzero()[0]]
                    print(df_tmp.shape)

                    print(df_tmp.describe()[['mean', 'std']])


modified_output = convert_file(sys.argv[1])

get_stats(modified_output, sys.argv[2])