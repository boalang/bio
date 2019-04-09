
#!/usr/bin/python

import pandas as pd
import sys


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
get_stats(sys.argv[1], sys.argv[2])
