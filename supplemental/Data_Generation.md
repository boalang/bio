
# Data generation steps

## Download from NCBI
* Python script to download all the gff, txt, and fna files
* Time to download:  about a week. This could be done in parallel but NCBI might block your IP if you run more threads at the same time. The most time consuming one was Bacteria because it has more than 280K files for GFF and Assembly stats files.


```Python

#!/usr/bin/python

import os
import subprocess


my_dic={}


def dl_from_ncbi(assembly_summary):
    # download fna, gff and Assembly_stats files from ftp
    with open (assembly_summary) as f:
        next(f)
        for line in f:
            info=line.split('\t')
            fna_url=info[19]+'/'+info[19].split('/')[-1]+'_genomic.fna.gz'
            fnafileName=info[19].split('/')[-1]+'_genomic.fna.gz'

            assembly_url=info[19]+'/'+info[19].split('/')[-1]+'_assembly_stats.txt'
            assembly_stat= info[19].split('/')[-1]+'_assembly_stats.txt'

            gff_url=info[19]+'/'+info[19].split('/')[-1]+'_genomic.gff.gz'
            gfffileName=info[19].split('/')[-1]+'_genomic.gff.gz'

            if not os.path.isfile(gfffileName):
                #os.system('curl -o ' + fnafileName + ' '  + fna_url)
                os.system('curl -o ' + gfffileName + ' '  + gff_url)
                os.system('curl ' + assembly_url + '>>' + assembly_stat )
                print (" ################### "+ gfffileName)



data=subprocess.check_output("curl ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/", shell=True)
# print(data)


with open("data.txt", 'w') as f:
    for line in data.strip().decode().splitlines():
        f.write(line + "\n")

directory_list=[]
with open("data.txt") as f:
    for line in f:
        folders = line.split()
        if "." not in folders[8]:
            directory_list.append(folders[8])


for directory in directory_list:
    if not os.path.exists(directory):
        os.makedirs(directory)
        os.chdir(directory)
        try:
            assembly_summary = subprocess.check_output(
            "curl ftp://ftp.ncbi.nlm.nih.gov/genomes/refseq/"+ directory + "/assembly_summary.txt", shell=True)
            with open("assembly_summary.txt", 'w') as outf:
                # outf.write(assembly_summary)
                for line in assembly_summary.strip().decode().splitlines():
                    outf.write(line + "\n")
            dl_from_ncbi("assembly_summary.txt")

        except subprocess.CalledProcessError:
            print ("############## No Assembly_summary file ")

        os.chdir("..")
```


## Data generation
* After downloading the dataset from NCBI you can rub the following command in the Command_Line folder in this repository:

```
 bash runbash.sh output_path  raw_data
```
*  The first command is the output location and the second command is the folder of the raw data. It can be the full RefSeq that has different folders or any subset of the RefSeq.
* Full dataset can be obtained from the [Google Drive Link](https://drive.google.com/drive/folders/1u-APb-clMbPNpHXhalthPWEDsNT-OtnX?usp=sharing)


### System specification
```
iMac (Retina 5K, 27-inch, Late 2015)
processor: 4 GHz Intel Core i7
Memory: 32 GB 1867 MHz DDR3
```

### Full dataset on iMac
* raw data: 379G
* BoaG dataset:64GB
* time to generate the dataset: 14 hours
*

#### Raw data size in each folder

```
du -ch /Users/hbagheri/Documents/NCBI/Data2019/

1.1G	/Users/hbagheri/Documents/NCBI/Data2019//archaea
265G	/Users/hbagheri/Documents/NCBI/Data2019//bacteria
5.4G	/Users/hbagheri/Documents/NCBI/Data2019//fungi
 18G	/Users/hbagheri/Documents/NCBI/Data2019//invertebrate
 18G	/Users/hbagheri/Documents/NCBI/Data2019//plant
1.7G	/Users/hbagheri/Documents/NCBI/Data2019//protozoa
 36G	/Users/hbagheri/Documents/NCBI/Data2019//vertebrate_mammalian
 34G	/Users/hbagheri/Documents/NCBI/Data2019//vertebrate_other
185M	/Users/hbagheri/Documents/NCBI/Data2019//viral
379G	/Users/hbagheri/Documents/NCBI/Data2019/
379G	total


```

## Subset of dataset
### Fungi
* run time: 13 minutes
* Hadoop sequence file size: 548 MB
* Raw Data size: 5.4 GB
* Json file size and run time:

### Archaea
* run time: 2 min
* location on my system: ```/Users/hbagheri/Documents/BoaG/Refseq/CommandLine/archaea```
* Hadoop sequence file size: 194MB
* Raw Data size: 1.1 GB
* Json file size and run time: 2.66 GB and 5 minutes


### invertebrate
* run time:  46m
* location on my system: ```/Users/hbagheri/Documents/BoaG/Refseq/CommandLine/invertebrate```
* Hadoop sequence file size: 2.2 GB
* Raw Data size: 18 GB
* Json file size and run time: 34 GB


### plant
* run time:  53m
* location on my system: ```/Users/hbagheri/Documents/BoaG/Refseq/CommandLine/plant```
* Hadoop sequence file size: 1.9   GB
* Raw Data size:  18 GB
* Json file size and run time: 33 GB


### protozoa
* run time:  5m
* location on my system: ```/Users/hbagheri/Documents/BoaG/Refseq/CommandLine/protozoa```
* Hadoop sequence file size: 248M   GB
* Raw Data size:  1.7 GB
* Json file size and run time: 4.25 GB



### Data schema

#### Protobuf schema
* Boa<sub>g</sub> database is written in binary based on the following schema.

```
package boa.types;

option optimize_for =SPEED;

message Genome{
	required string refseq=1;
    required string taxid=2;
	repeated Sequence sequence=3;
	optional AssemblerRoot assemblerRoot=4;

}

message Sequence
{
	required string accession=1;
	required string header=2;
	optional FeatureRoot featureRoot=3;
}


message AssemblerRoot{
	optional string refseq=1;
	repeated Assembler assembler=2;      
	optional int64 total_length=3;
	optional int64 scaffold_count=4;  
	optional int64 scaffold_N50=5;
	optional int64 contig_N50=6;
	optional int64 contig_count=7;
	optional int64 total_gap_length=8;
	optional uint64 assembly_date = 9;
}

message Assembler{
   	required string name=1;
	required string desc=2;

}

message FeatureRoot{

    optional string refseq=1;
    repeated Feature feature=2;
}

message Feature {
    required string accession=1;
	required string seqid=2;
	required string source=3;
	required string ftype=4;
	required int64 start=5;
	required int64 end=6;
	required string score=7;
	required string strand=8;
	required string phase=9;
	repeated Attribute attribute=10;
	optional string parent=11;

}

message Attribute{
	required string tag=1;
	required string value=2;

}


```
