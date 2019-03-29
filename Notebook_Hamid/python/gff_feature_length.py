import os
import sys
import  random
from BCBio.GFF import GFFExaminer
''' 
This is the task A1 calculates the number of features for each GFF file.
'''
def parse_gff(in_file):
    features_no=0
    examiner = GFFExaminer()
    in_handle = open(in_file)
    gff = examiner.available_limits(in_handle)
    gff_features = gff['gff_type']
    #TODO: find the feauter length not count
    for feature in gff_features:
        features_no +=gff_features[feature]
    return (features_no)

files_list=list()
sample_size= 10
for path, subdirs, files in os.walk(sys.argv[1]):
    for name in files:
        if name.endswith('.gff'):
            # print (name, os.path.join(path, name))
            files_list.append(os.path.join(path, name))
print(len(files_list)) # prints the nubmer of GFF files

# Take a sample of data and run it few times to get the estimated run time
randIndex = random.sample(range(len(files_list)), sample_size)
randIndex.sort()
sample_files=[files_list[i] for i in randIndex]


for f in sample_files:
    with open(f, "r") as in_file:
        print(f)
        taxtid = ""
        refseq_id = ""
        parent_list = list()

        for line in in_file:
            fields= line.split()
            if line.startswith("##species"): # get tax id
                taxid=line[line.index("=")+1:]
                # print(taxtid)
            if line.startswith("#!genome-build-accession"):
                refseq_id=line[line.index(":")+1:] # get refseq id of the gff file
    feature_count = parse_gff(f)

    print(refseq_id.rstrip("\n")+ "," + taxid.rstrip("\n") + "," + str("%0.2f" % feature_count )+ "," )
