#!/usr/bin/python

'''

 Goal of this script is to get the subtree(list of taxonomies) for all tax ids in the NCBI taxonomy database
 we want to check for a given tax_id or tax_name what are the tax names that appear in the clusters.

'''
from ete3 import NCBITaxa
import pandas as pd
import sys

'''
 first argument should be cd-hit tax list,i.e., we get this from parsing XML file
 second argument should be the ncbi database that has all the taxonomy list
'''
ncbi = NCBITaxa()

'''
 the following function gets set of nodes(tax ids) and finds all the tax_ids that appear in the subtree 
 the taxid_list data frame has all the tax ids from CD-HIT output that appears in different clusters.
 we want to know what are the tax ids that are children of each node in the nodeset
'''

taxLineage = dict()

def getLineage():
    tax_file = sys.argv[1]
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



# if we want to claculate the lineage undoc following command
getLineage()

#follwoing gets the list of taxids and returns their tax names