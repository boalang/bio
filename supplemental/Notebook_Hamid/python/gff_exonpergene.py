import os
import sys
from BCBio.GFF import GFFExaminer

# Uncomment following line for actual analysis
# sys.stdout=open("exonpergene2018.txt", "w+")

# with open('exonpergene.txt',"w") as outf:

def parse_gff(in_file):
    exon_no=0
    geneNo_no =0
    examiner = GFFExaminer()
    in_handle = open(in_file)

    gff = examiner.available_limits(in_handle)
    gff_features = gff['gff_type']
    # print(gff_features)
    for feature in gff_features:
        if 'exon' in feature:
            # print(feature.sub_features)
            exon_no=gff_features[feature]

        if 'gene' in feature:
            geneNo_no = gff_features[feature]

    in_handle.close()
    return exon_no,geneNo_no

files_list=list()
for path, subdirs, files in os.walk(sys.argv[1]):
    for name in files:
        if name.endswith('.gff'):
            # print (name, os.path.join(path, name))
            files_list.append(os.path.join(path, name))

# list all gff files
# files_list=[f for f in os.listdir(".") if f.endswith('.gff')]

# prints the nubmer of GFF files
print(len(files_list))


for f in files_list:
    with open(f, "r") as in_file:
        # print(f)
        taxtid = ""
        refseq_id = ""
        parent_list = list()

        for line in in_file:
            fields= line.split()
            if (len(fields)>10 and fields[2]=="exon"):
                sub_features=fields[8].split(";")
                parent_list.append(sub_features[1][7:]) # ParentID=rna
                # print(fields[2] ,sub_features[1][7:])
            if line.startswith("##species"):
                taxid=line[line.index("=")+1:]
                # print(taxtid)
            if line.startswith("#!genome-build-accession"):
                refseq_id=line[line.index(":")+1:]
                # print(refseq_id, taxtid)
    exon_count,gene_count = parse_gff(f)
    # print("geneNo, exonNo",gene_count, exon_count)
    parent_set=set(parent_list)
    with open(f, "r") as in_file:
        gene_set = set()

        for line in in_file:
            fields= line.split()
            if (len(fields)>10 and (fields[2]=="mRNA" or fields[2]=="transcript") ):
                sub_features=fields[8].split(";")
                parentID=sub_features[1][7:]
                featureID=sub_features[0][3:]
                # print(featureID,parentID)
                if featureID in parent_set:
                    gene_set.add(parentID)

    print(refseq_id.rstrip("\n")+ "," + taxid.rstrip("\n") + "," +
          str("%0.2f" % gene_count )+ "," +
          str("%0.2f" %(gene_count+(exon_count-len(gene_set))))+ "," +\
          str("%0.2f" %((gene_count+(exon_count-len(gene_set)))/gene_count)))


    # avglist=list()
    # #print(list1)
    # parent_set=set(parent_list)
    # print(parent_set)
    # print(gene_set)
    # for parent in parent_set:
    #     print (parent, parent_list.count(parent))
    #     avglist.append(parent_list.count(parent))
    # if len(avglist) > 0:
    #     print(refseq_id.rstrip("\n")+ "," + taxid.rstrip("\n") + "," +str("%0.2f" %(sum(avglist) / float(len(avglist)))))
    # else:
    #     print(refseq_id.rstrip("\n")+ "," + taxid.rstrip("\n")+","+ "0")


