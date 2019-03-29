#!/usr/bin/python
import os
import subprocess
import sys
import numpy as np
import operator

assembly_stats={}
def get_assembly(file):
    assembly_program=None
    assembly_date = ""
    with open(file, 'r') as f:
        for line in f:
            if line.startswith('# Assembly method:'):
                data=line.split(':')
                assembly_program = data[1].strip()
                print(data[1].strip())
            if line.startswith('# Date:'): # check the date of assembly
                data = line.split(':')
                assembly_date = data[1].strip()


    return assembly_program, assembly_date

#all assembly_stats.txt in the current directory
#files_list=[f for f in os.listdir(".") if f.endswith('.txt')]

files_list=list()
for path, subdirs, files in os.walk("."):
    for name in files:
        if name.endswith('.txt'):
            # print (name, os.path.join(path, name))
            files_list.append(os.path.join(path, name))
for f in files_list:
    # print(os.path.abspath(f))
    assembly_program, assembly_date=get_assembly(f)
    #TODO: split for years > =2016 and Years <2016
    if assembly_program in assembly_stats:
        assembly_stats[assembly_program] +=1
    else:
        assembly_stats[assembly_program] = 1
sorted_assemblers = sorted(assembly_stats.items(), key=operator.itemgetter(1))
#print(sorted_assemblers)
print(sorted_assemblers[-2:])