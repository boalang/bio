
#!/usr/bin/python

import pandas as pd
import sys


'''
 This script gets Boa output and generates the 2 csv files:
 one for features stats and another csv file for assembly programs
 ID is Refseq not taxid, bacause taxid is not unique
'''
geneCounts={}
exonCounts={}
mRNACounts={}
CDSCounts={}
geneMean={}
exonMean={}
mRNAMean={}
CDSMean={}
assemblers={}
refseqlist=set()
taxlist={}

'''
 input:
    AssemblerCounts[GCF_000001215.4][7227][143726002][1152978][0][0][2442][21485538] = 1
    AssemblerCounts[GCF_000001405.36][9606][3241953429][161368251][837][59364414][1497][56413054] = 1



 output:
    GCF_000001215.4,7227,143726002,1152978,0,0,2442,21485538
    GCF_000001405.36,9606,3241953429,161368251,837,59364414,1497,56413054

'''
boa_outupt =sys.argv[1]
boa_outupt_converted = boa_outupt + "_converted"
with open(boa_outupt) as f, open(boa_outupt_converted, "w") as assemblerfile:
    for line in f:
        dataline=line.split('[')
        refseq=dataline[1][:-1]
        taxid=dataline[2][:-1]
        total_length=dataline[3][:-1]
        total_gap_length=dataline[4][:-1]
        scaffold_count=dataline[5][:-1]
        scaffold_N50=dataline[6][:-1]
        contig_count=dataline[7][:-1]
        contig_N50=dataline[8][:-6]

        assemblerfile.write (str(refseq) + "," + taxid + "," + total_length
                             + "," + total_gap_length + "," + scaffold_count
                             + "," + scaffold_N50 + "," + contig_count + "," + contig_N50 + "\n")



def get_stats(assembly_file, intereseted_taxids_file):
    df_assemblystats=pd.read_csv(assembly_file, names=['refseq','taxid','total_length','total_gap_length','scaffold_count', 'scaffold_N50','contig_count','contig_N50'])
    df_assemblystats = df_assemblystats[['refseq','taxid','total_length','scaffold_count', 'scaffold_N50','contig_count','contig_N50']]
    # df_assemblystats.set_index("taxid", inplace=True)

    #list of taxids that we are interested is in the intereseted_taxids_file
    intereseted_taxids = {}
    with open(intereseted_taxids_file) as f:
        for line in f:
            words = line.split()
            (key, val) = words[0], list(words[1:len(words)])
            intereseted_taxids[key] = val

    for tax in intereseted_taxids:
        if int(tax) in [2,4751,2157,33090, 33208,71240, 4447]:
            print("#############################################")
            print("tax id: " + tax)

            tax_list = intereseted_taxids[tax]
            #convert list of string to list of int
            tax_list = list(map(int, tax_list))
            # remove duplicate taxids
            tax_list = sorted(set(tax_list))

            ''' 
            if a specific GFF file is in a subtree of a interested tax id, e.g taxid=2 for Bacteria, then 
            add it to the list to calculate the summary stats.
            so iterate over the df_assemblystats
            '''

            df_interested = df_assemblystats[df_assemblystats['taxid'].isin(tax_list)] # list of all subtree taxids is here

            # print("shape before : " + str(df_interested.shape))
            # df_interested = df_interested[~(df_interested == 0).any(axis=1)]
            # df_interested.to_csv(intereseted_taxids_file+"_"+tax, sep='\t', encoding='utf-8')
            # print("shape after : " + str(df_interested.shape))

            for item in ['total_length','scaffold_count', 'scaffold_N50','contig_count','contig_N50']:
                df_tmp = df_interested[item]
                # print(df_tmp)
                # df_tmp = df_tmp[~(df_tmp == 0).any(axis=1)]
                df_tmp = df_tmp.iloc[df_tmp.nonzero()[0]]
                print(df_tmp.shape)

                print(df_tmp.describe()[['mean','std']])

# first argument is the assembly file output from Boa
# second argument is the interested tax ids with all its sub_tree
get_stats(boa_outupt_converted, sys.argv[2])
