#!/usr/bin/python

import  sys
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
        print(line)
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


        # if line.startswith('Assembler'):  # FIXME for one genome we may have different assembler, it is not unique.
        #     # assemblers[taxid]=value
        #     taxid = line[line.index('[') + 1:line.index(']')]
        #     value = line[line.index('][') + 2:line.index('] =')]
        #     assemblerfile.write(str(taxid) + "," + str(value) + "\n")
        # else:
        #     refseq = line[line.index('[') + 1:line.index(']')]
        #     taxid = line[line.index('][') + 2:line.index('] =')]
        #     refseqlist.add(refseq)
        #     taxlist[refseq] = taxid
        #     value = line[line.index('=') + 1:].rstrip()
        #
        #     if line.startswith('geneCounts'):
        #         geneCounts[refseq]=value
        #     elif line.startswith('exonCounts'):
        #         exonCounts[refseq]=value
        #     elif line.startswith('mRNACounts'):
        #         mRNACounts[refseq]=value
        #     elif line.startswith('CDSCounts'):
        #         CDSCounts[refseq]=value
        #     elif line.startswith('geneMean'):
        #         geneMean[refseq]=value
        #     elif line.startswith('exonMean'):
        #         exonMean[refseq]=value
        #     elif line.startswith('mRNAMean'):
        #         mRNAMean[refseq]=value
        #     elif line.startswith('CDSMean'):
        #         CDSMean[refseq]=value

        
# print(len(refseqlist), len(assemblers), len(geneCounts), len(geneMean), len(exonCounts), len(exonMean), len(mRNACounts), len(mRNAMean), len(CDSCounts), len(CDSMean))

# with open("boacsv_09_17.csv",'w') as out:
#     for refseq in refseqlist:
#         if refseq not in geneCounts:
#             geneCounts[refseq]=0
#             geneMean[refseq]=0
#         if refseq not in exonCounts:
#             exonCounts[refseq]=0
#             exonMean[refseq]=0
#         if refseq not in mRNACounts:
#             mRNACounts[refseq]=0
#             mRNAMean[refseq]=0
#         if refseq not in CDSCounts:
#             CDSCounts[refseq]=0
#             CDSMean[refseq]=0
#
#
#         out.write(str(refseq) + ","+ str(taxlist[refseq]) + "," + str(geneCounts[refseq]) + "," + str(geneMean[refseq]) + "," + str(exonCounts[refseq]) + "," + str(exonMean[refseq]) + "," + str(mRNACounts[refseq]) + "," + str(mRNAMean[refseq]) + "," + str(CDSCounts[refseq]) + "," + str(CDSMean[refseq]) + "\n")
#         #assemblerfile.write(str(taxid) + ","+ str(geneCounts[taxid])+ ","+ str(geneMean[taxid])+ ","+ str(exonCounts[taxid])+ ","+ str(exonMean[taxid])+ ","+ str(mRNACounts[taxid])+ ","+ str(mRNAMean[taxid])+ ","+ str(CDSCounts[taxid])+ ","+ str(CDSMean[taxid])+"\n")


        