#!/usr/bin/python

import  csv
'''
 This script gets Boa output and generates the 2 csv files:
 one for features stats and another csv file for assembly programs
 ID is Refseq not taxid, bacause taxid is not unique
 
 one line output is as follows:
 GCF_001560115.1,2285, 2338, 834.6561163387511, 74, 120.83783783783784,0,0, 2283, 850.6903197547088,1
 where order of fields are:
 refseqid, taxid, geneCounts, geneMean, exonCounts, exonMean, mRNACounts, mRNAMean, CDSCounts, CDSMean, exonPerGene

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
 inputs are as follows:
    AssemblerCounts[100053][Velvet] = 5
    AssemblerCounts[1000561][N/A] = 1
    AssemblerCounts[1000562][CAP3] = 1
 and (exonpergene stats): 
    GCF_000836235.1,8.93
    GCF_000949405.1,8.61
    GCF_000956235.1,7.97
    GCF_000980195.1,8.69
    GCF_001015335.1,6.3

 outputs are as follows:
    1000562,CLC
    1000562,Newbler
    1000565,Newbler
 And:
    GCF_002143675.1,470, 4032, 888.6974206349206, 61, 160.63934426229508, 1, 360.0, 3971, 899.881390078066,1
    GCF_900037445.1,1313, 2318, 809.8188093183779, 53, 168.75471698113208, 1, 348.0, 2265, 824.8194260485651,1
    GCF_001997665.1,1334084, 4279, 954.0051413881748, 51, 179.5686274509804, 1, 368.0, 4228, 963.3467360454115,1
'''

with open("boa_output") as f, open("assemblerdata2018.csv", "w") as assemblerfile:
    for line in f:


        if line.startswith('Assembler'):  # FIXME for one genome we may have different assembler, it is not unique.
            # assemblers[taxid]=value
            taxid = line[line.index('[') + 1:line.index(']')]
            value = line[line.index('][') + 2:line.index('] =')]
            assemblerfile.write(str(taxid) + "," + str(value) + "\n")
        else:
            refseq = line[line.index('[') + 1:line.index(']')]
            taxid = line[line.index('][') + 2:line.index('] =')]
            refseqlist.add(refseq)
            taxlist[refseq] = taxid
            value = line[line.index('=') + 1:].rstrip()

            if line.startswith('geneCounts'):
                geneCounts[refseq]=value
            elif line.startswith('exonCounts'):
                exonCounts[refseq]=value
            elif line.startswith('mRNACounts'):
                mRNACounts[refseq]=value
            elif line.startswith('CDSCounts'):
                CDSCounts[refseq]=value
            elif line.startswith('geneMean'):
                geneMean[refseq]=value
            elif line.startswith('exonMean'):
                exonMean[refseq]=value
            elif line.startswith('mRNAMean'):
                mRNAMean[refseq]=value
            elif line.startswith('CDSMean'):
                CDSMean[refseq]=value

        
print(len(refseqlist), len(assemblers), len(geneCounts), len(geneMean),
      len(exonCounts), len(exonMean), len(mRNACounts), len(mRNAMean), len(CDSCounts), len(CDSMean))

reader = csv.reader(open('exonGene2018.csv', 'r'))
exon_per_gene = {}
for row in reader:
    k, v = row
    exon_per_gene[k] = v

with open("boacsv_12_17.csv",'w') as out:
    for refseq in refseqlist:
        if refseq not in geneCounts:
            geneCounts[refseq]=0
            geneMean[refseq]=0
        if refseq not in exonCounts:
            exonCounts[refseq]=0
            exonMean[refseq]=0
        if refseq not in mRNACounts:
            mRNACounts[refseq]=0
            mRNAMean[refseq]=0
        if refseq not in CDSCounts:
            CDSCounts[refseq]=0
            CDSMean[refseq]=0

        exonPerGene=0
        if refseq in exon_per_gene:
            exonPerGene=exon_per_gene[refseq]
            print("###refseq, exon per gene")
            print (refseq, exonPerGene)


        out.write(str(refseq) + ","+ str(taxlist[refseq]) + "," + str(geneCounts[refseq]) + "," +
                  str(geneMean[refseq]) + "," + str(exonCounts[refseq]) + "," + str(exonMean[refseq]) +
                  "," + str(mRNACounts[refseq]) + "," + str(mRNAMean[refseq]) + "," + str(CDSCounts[refseq]) +
                  "," + str(CDSMean[refseq]) + "," + str(exonPerGene) + "\n")
        #assemblerfile.write(str(taxid) + ","+ str(geneCounts[taxid])+ ","+ str(geneMean[taxid])+ ","+ str(exonCounts[taxid])+ ","+ str(exonMean[taxid])+ ","+ str(mRNACounts[taxid])+ ","+ str(mRNAMean[taxid])+ ","+ str(CDSCounts[taxid])+ ","+ str(CDSMean[taxid])+"\n")


        