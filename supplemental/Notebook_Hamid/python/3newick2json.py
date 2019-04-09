#!/usr/bin/python

'''
    @author: Hamid Bagheri

     Convert nwk format to json
     the tree has annotated with list of leaves 
     First argument is the Newick file format
     Second Argument will be the generated JSON file (Equivalent to the Newick format)
     It needs boacsv data and assembler data to annotate each node in the tree
     
     Example call: python 3newick2json.py tol_8_17.nwk tol_12_17.json 
'''

import sys
from ete3 import Tree, NCBITaxa
import random
import pandas as pd
import numpy as np
import json
import csv


lineage={}
nodes_stats={}
nodes_stats['nodes']=[]

nodes_assembly_stats={}
nodes_assembly_stats['nodes']=[]


ncbi = NCBITaxa()
df=pd.read_csv("boacsv_12_17.csv", names=['refseq','taxid','gene','gene_length','exon','exon_length','mRNA','mRNA_length','CDS','CDS_length','exon_per_gene'])
taxid_list=df['taxid']
df_assemblers=pd.read_csv("assemblerdata_09_17.csv", names=['taxid','assembler'])
df_assemblystats=pd.read_csv("assemblystats2_09_17.csv", names=['refseq','taxid','total_length','total_gap_length','scaffold_count', 'scaffold_N50','contig_count','contig_N50'])

print(df)

for taxid in taxid_list:
    try:
        lineage[taxid]=ncbi.get_lineage(taxid)
    except Exception as e:
        print(e)

  # call get_leaves_taxid
  #  nodeset=set()
  #   nodeset.add(int(node.name))
  #   taxid_list=get_leaves_taxid(nodeset)

def get_leaves_taxid(nodeset):

    gff_set = set()

    for nodeid in nodeset:
        for taxid in taxid_list:
            try:
                if nodeid in lineage[taxid]:
                    gff_set.add(taxid)
            except ValueError:
                print ("error in getting get_lineage()")
    return gff_set



def get_json(node):
    # Read ETE tag for duplication or speciation events
    if not hasattr(node, 'evoltype'):
        dup = random.sample(['N','Y'], 1)[0]
    elif node.evoltype == "S":
        dup = "N"
    elif node.evoltype == "D":
        dup = "Y"


    node.name = node.name.replace("'", '')

    node_Name=""
    if node.name != "" :
        node_Name= ncbi.translate_to_names([int(node.name)])
        node_Name=str(node_Name[0])
        print("node name ------" + str(node_Name))

    nodeset=set()
    leaves_list=[]
    assembler_frame=pd.DataFrame()
    leaves_frame=pd.DataFrame()
    assemblystats_frame=pd.DataFrame()
    if node.name!="" and node.children:
        taxid=int(node.name)
        nodeset.add(taxid)
        leaves_list=get_leaves_taxid(nodeset)
        leaves_frame=df.loc[df['taxid'].isin(leaves_list)]
        print(leaves_frame)
        assembler_frame=df_assemblers.loc[df_assemblers['taxid'].isin(leaves_list)]
        assemblystats_frame = df_assemblystats.loc[df_assemblystats['taxid'].isin(leaves_list)]

    json_tree = {"name": node_Name.replace('\n','').replace("'",""),
            "taxid": node.name,

#              "leaves":[ {"taxid":str(int(row['taxid'])),
#                         "gene":str(row['gene']),
#                         "gene_length":str(row['gene_length']),
#                         "exon": str(row['exon']),
#                         "exon_length": str(row['exon_length']),
#                         "mRNA": str(row['mRNA']),
#                         "mRNA_length": str(row['mRNA_length']),
#                         "CDS": str(row['CDS']),
#                         "CDS_length": str(row['CDS_length'])
#                         } for index, row in leaves_frame.iterrows()],  #  this format "leaves": ["L1", "L2", "L3"]
            "leaves": [[#str(int(row['taxid'])),
                        # str(row['refseq']),
                        str(row['gene'])+","+
                        str(int(float(row['gene_length'])))+","+
                        str(row['exon'])+","+
                        str(int(float(row['exon_length'])))+","+
                        str(row['mRNA'])+","+
                        str(int(float(row['mRNA_length'])))+","+
                        str(row['CDS'])+","+
                        str(int(float(row['CDS_length']))) + "," +
                        str(float(row['exon_per_gene']))
                        ] for index, row in leaves_frame.iterrows()],  # this format "leaves": ["L1", "L2", "L3"]
            "assemblers":[str(row['assembler']) for index, row in assembler_frame.iterrows()],
            #              "leaves": [str(leaf) for leaf in leaves_list],  # this format "leaves": ["L1", "L2", "L3"]
            #              "type": "node" if node.children else "leaf",
            #             "leaves": [str(row) for index,row in leaves_frame.iterrows()],  # this format "leaves": ["L1", "L2", "L3"]
            #             "type": "node" if node.children else "leaf",
            #             "uniprot_name": "Unknown",
            }

    nodes_stats['nodes'].append({
        "name": node_Name.replace('\n', '').replace("'", ""),
        "taxid": node.name,
        "leaves": [[  # str(int(row['taxid'])),
            # str(row['refseq']),
            str(row['gene']) + "," +
            str(int(float(row['gene_length']))) + "," +
            str(row['exon']) + "," +
            str(int(float(row['exon_length']))) + "," +
            str(row['mRNA']) + "," +
            str(int(float(row['mRNA_length']))) + "," +
            str(row['CDS']) + "," +
            str(int(float(row['CDS_length'])))  + "," +
            str(float(row['exon_per_gene']))

        ] for index, row in leaves_frame.iterrows()],
        "assemblers": [str(row['assembler']) for index, row in assembler_frame.iterrows()],

    })

    nodes_assembly_stats['nodes'].append({
        "name": node_Name.replace('\n', '').replace("'", ""),
        "taxid": node.name,
        "leaves": [[
            str(row['refseq']) + "," + str(row['taxid']) + "," + str(row['total_length'])
            + "," + str(row['total_gap_length']) + "," + str(row['scaffold_count'])
            + "," + str(row['scaffold_N50']) + "," + str(row['contig_count']) + "," + str(row['contig_N50'])
        ] for index, row in assemblystats_frame.iterrows()],

    })

    if node.children:
        json_tree["children"] = []
        for ch in node.children:
            json_tree["children"].append(get_json(ch))
    return json_tree


if __name__ == '__main__':
    if len(sys.argv) > 2:
        t = Tree(sys.argv[1], format=1)

    else:
        # create a random example tree
        t = Tree()

        t.populate(100, random_branches=True)

#    t=Tree()
#    t.populate(10000, random_branches=True)
#    print(t)

    # TreeWidget seems to fail with simple quotes

    json_tree=str(get_json(t))
    json_tree=json_tree.replace("'", '"')

    with open('nodes_stats1217.txt','w') as outjson:
        json.dump(nodes_stats,outjson)
    with open('nodes_assembly_stats1217.txt', 'w') as outjsonassembly:
            json.dump(nodes_assembly_stats, outjsonassembly)

    with open (sys.argv[2], 'w') as f:
        f.write(json_tree)

