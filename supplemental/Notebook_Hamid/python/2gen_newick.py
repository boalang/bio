#!/usr/bin/pthon

'''
    @author: Hamid Bagheri
    
    This script generates Newick tree format 
    It creates internal nodes by calling get_lineage
    First parameter is CSV file
    Second parameter is the generated Newick file

'''


import sys
import random
from ete3 import Tree,NCBITaxa, Phyloxml, phyloxml
from IPython.display import Image, display
import pandas as pd
import numpy as np


ncbi = NCBITaxa()


internal_nodes=set()


def get_internal_node():
    #Load all internal nodes, all parents
    for taxid in taxid_list:
        try:
            internal_nodes.update(ncbi.get_lineage(taxid))
        except ValueError:
            print ("error in getting get_lineage()")

    

def get_leaves_taxid(nodeset):
    df=pd.read_csv("boacsv_8_17.txt", names=['taxid','CDS','CDS_Mean','exon','exon_Mean','gene','gene_Mean','mRNA','mRNA_Mean'])
    # external nodes or GFF files tax id (species)
    taxid_list=df['taxid']
    gff_set=set()
    
    
    for nodeid in nodeset:
        for taxid in taxid_list:
            try:
                if nodeid in ncbi.get_lineage(taxid):
                    gff_set.add(taxid)
            except ValueError:
                print ("error in getting get_lineage()")
    return gff_set




def layout(node): 
    node_Name= ncbi.translate_to_names([int(node.name)])
    if node.is_leaf():
        info=df.loc[df['taxid'] == int(node.name)].values.tolist()
        row=info[0]
        F= faces.BarChartFace([row[1],row[3],row[5],row[7]], min_value=0,
                              colors=COLOR_SCHEMES["spectral"],
                              labels = ['CDS','exon','gene','mRNA'],
                              label_fsize=10, scale_fsize=10)
        faces.add_face_to_node(F,node, 0, position="branch-top")
        F.background.color = "#eee"
        node.add_features(CDS=row[1])
        node.add_features(exon=row[3])
        node.add_features(gene=row[5])
        node.add_features(mRNA=row[7])
        node.add_features(tax2name=  str(get_name(int(node.name)) ))

        
    else:
        nodeset=set()
        nodeset.add(int(node.name))
        taxid_list=get_leaves_taxid(nodeset)
        node.add_features(GFF_No=len(taxid_list))
        info= df[df['taxid'].isin(taxid_list)]['CDS'].mean()
        node.add_features(avg_CDS=info)
        info= df[df['taxid'].isin(taxid_list)]['exon'].mean()
        node.add_features(avg_exon=info)
        info= df[df['taxid'].isin(taxid_list)]['gene'].mean()
        node.add_features(avg_gene=info)
        info= df[df['taxid'].isin(taxid_list)]['mRNA'].mean()
        node.add_features(avg_mRNA=info)


def build_tree():
    
    # set all internal nodes tax id
    get_internal_node()
    
    node_list=set()
    node_list.update(internal_nodes)
    node_list.update(taxid_list)



    t = ncbi.get_topology(node_list,intermediate_nodes=True)
    
    #ts = TreeStyle()
    # ts.layout_fn = layout
    # ts.mode = "r"
    # ts.show_leaf_name = True
#    for leaf in t:
#        info=df.loc[df['taxid'] == int(leaf.name)].values.tolist()
#        row=info[0]
#        leaf.add_features(CDS=row[1])
#        leaf.add_features(exon=row[3])
#        leaf.add_features(gene=row[5])
#        leaf.add_features(mRNA=row[7])
    return t

if __name__ == '__main__':
    if len(sys.argv)>2:

        df = pd.read_csv(sys.argv[1], names=['taxid', 'CDS', 'CDS_Mean', 'exon', 'exon_Mean', 'gene', 'gene_Mean', 'mRNA',
                                'mRNA_Mean'])
        taxid_list = df['taxid']

        newick_file=sys.argv[2]
        t = build_tree()

        #t.render("barchart.png", w=400, tree_style=ts)
        #display(Image(filename='barchart.png'))
        #t.show(tree_style=ts)
        #print(t)
        #t.show()

        # write tree as a newick format
        t.write(format=1, outfile=newick_file)

        #project = Phyloxml()
        # TODO which format?
        #phylo = phyloxml.PhyloxmlTree(newick=t.write())
        #phylo = phyloxml.PhyloxmlTree(newick=t.write(format=1, features=["CDS","mRNA","gene"]))
        #phylo.show()

        #project.add_phylogeny(phylo)
        #print project.get_phylogeny()[0]

        # Add the tree to the phyloxml project
        #project.add_phylogeny(phylo)
        #print project.get_phylogeny()[0]


        # Export the project as phyloXML format
        #project.export()

        #phylo.export()



