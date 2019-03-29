#!/usr/bin/python

import sys
import  pandas as pd
from ete3 import NCBITaxa

taxLineage = dict()
ncbi = NCBITaxa()

'''
 input:
    geneCounts[GCF_900481115.1][562] = 5401
    exonCounts[GCF_900138005.1][1185650] = 53
    
 output:
    refseqid,taxid,gene,exon,exon/gene
    GCF_000007565.2,160488, 5786, 165,0.028517110266159697  
'''
def parse_file(BoaOutput):
    exon_dic ={}
    gene_dic = {}
    geneMean_dic = {}

    with open(BoaOutput, "r") as boa_file, open("tax_list", "w") as  tax_list_file:
        for line in boa_file:
            if line.startswith('geneCounts'):
                dataline = line.split('[')
                refseq = dataline[1][:-1]
                taxid = dataline[2][:dataline[2].index("]")]

                # write tax id to the disk
                tax_list_file.write(taxid+"\n")
                gene_count = line[line.index("=")+1:]
                gene_dic[refseq+"," + taxid] = gene_count.rstrip()

            if line.startswith('geneMean'):
                dataline = line.split('[')
                refseq = dataline[1][:-1]
                taxid = dataline[2][:dataline[2].index("]")]
                gene_mean = line[line.index("=")+1:]
                geneMean_dic[refseq+"," + taxid] = gene_mean.rstrip()

            if line.startswith('exonCounts'):
                dataline = line.split('[')
                refseq = dataline[1][:-1]
                taxid = dataline[2][:dataline[2].index("]")]
                exon_count = line[line.index("=")+1:]
                exon_dic[refseq+"," + taxid] = exon_count.rstrip()
    print(geneMean_dic)
    with open(BoaOutput+ "_modified", "w") as fouput:
        for key, value in gene_dic.items():
            if key in exon_dic:
                words = key.split(",")
                tmp_refseq = words[0]
                tmp_taxid = words [1]
                fouput.write(tmp_refseq+","+tmp_taxid+","+ value+","+ exon_dic[key]+","+ str(geneMean_dic[key]) +"," + str(int(exon_dic[key])/int(value))[:5] + "\n")



def get_stats(assembly_file, intereseted_taxids_file):
    df_assemblystats=pd.read_csv(assembly_file, names=['refseq','taxid','gene','exon','genelength','exonpergene'])
    # df_assemblystats.set_index("taxid", inplace=True)

    #list of taxids that we are interested is in the intereseted_taxids_file
    intereseted_taxids = {}
    with open(intereseted_taxids_file) as f:
        for line in f:
            words = line.split()
            (key, val) = words[0], list(words[1:len(words)])
            intereseted_taxids[key] = val

    for tax in intereseted_taxids:
        if int(tax) in [2,4751,2157,33090, 33208,71240, 4447, 4890]:
            print("#############################################")
            print("tax id: " + str(ncbi.get_taxid_translator([tax]) ))

            tax_list = intereseted_taxids[tax]
            #convert list of string to list of int
            tax_list = list(map(int, tax_list))
            # remove duplicate taxids
            tax_list = sorted(set(tax_list))

            df_interested = df_assemblystats[df_assemblystats['taxid'].isin(tax_list)] # list of all subtree taxids is here

            print(df_interested.shape)

            print(df_interested.describe())


def getLineage(tax_file):
    lineage_file = tax_file + "_lineage"
    xml_ncbi_df = pd.read_csv(tax_file, names=['taxid'])

    xml_tax_list = xml_ncbi_df['taxid']
    subTree_taxs = set()

    for taxid in xml_tax_list:
        try:
            lineage = ncbi.get_lineage(taxid)
            if lineage:
                for tax_lin in lineage:
                    if tax_lin in taxLineage :
                        taxLineage [tax_lin] += str(taxid) + " "
                    else:
                        taxLineage[tax_lin] = str(taxid) + " "
        except ValueError:
            print("error in getting get_lineage()")

    with open(lineage_file, "w") as output:
        for key in taxLineage:
          taxid2name = ncbi.get_taxid_translator([int(key)])
          strLine = str(key) + "\t" + str(taxLineage[key])
          output.write(strLine + "\n")


''' 
    gets list of tax ids and use ncbi to get the tax name 
    this file has the tax count as well
    NB: we use this output for annotating the tree of life
'''
parse_file(sys.argv[1])
getLineage("tax_list")
get_stats(sys.argv[1]+"_modified", "tax_list_lineage")