# Assembly statistics
* Hypothesis that we want to test: **We expect N50s to increase and number of scaffolds to decrease**
* We should see an improvement in assemblies in later years

#### Background about assembly stats:
* N50: https://en.wikipedia.org/wiki/N50,_L50,_and_related_statistics
* scaffolds: https://en.wikipedia.org/wiki/Scaffolding_(bioinformatics)

## schema changes to address stats over time.

## BoaG Queries

#### Summary statistics for years <= 2015
* Boa query:

```
g: Genome = input;
AssemblerCounts: output sum[string][int][string][string] of int;
AsmStats: output collection [string][string][int][int][int][int][int][int] of int;

# We need to load Assemblers info from disk
adata := getAssembler(g.refseq);
asYear :=yearof(adata.assembly_date);

#foreach(k:int; def(adata.assembler[k])){
#	  	 AssemblerCounts [g.refseq][asYear][g.taxid][adata.assembler[k].name]<< 1;
#}

# stats of contigs, etc
if(asYear <= 2015)
	AsmStats [g.refseq][g.taxid][adata.total_length][adata.total_gap_length][adata.scaffold_count]
          [adata.scaffold_N50][adata.contig_count][adata.contig_N50] <<1;
```

* call ```boaAssembly2csv.py``` to convert the Boa output to the CSV format.
* get list of all taxids in the previous output ```awk -F',' '{print $2}' part-r-00000_converted > tax_list```
* call a python script to generate the lineage file: ``` python getLineage.py tax_list ```  
* call ```python assembly_stats.py assmbler2015Output/part-r-00000_converted  assmbler2015Output/tax_list_lineage``` to generate the summary statistics
  * we removed all the rows that have missing values in Contigs or Scaffolds.
* following are list of tax ids that we are interested in ```[2,4751,2157,33090, 33208,71240, 4447]```

* the outputs for the above taxids are as follows, using pandas:

```
#############################################
tax id: 33208
(295,)
mean    1.336857e+09
std     1.046491e+09
Name: total_length, dtype: float64
(272,)
mean    37429.713235
std     64265.820171
Name: scaffold_count, dtype: float64
(272,)
mean    7.216597e+06
std     1.401510e+07
Name: scaffold_N50, dtype: float64
(294,)
mean    118643.010204
std     119063.501360
Name: contig_count, dtype: float64
(294,)
mean    1.384035e+05
std     1.261944e+06
Name: contig_N50, dtype: float64
#############################################
tax id: 33090
(67,)
mean    6.201964e+08
std     6.860794e+08
Name: total_length, dtype: float64
(54,)
mean    22967.444444
std     47621.697685
Name: scaffold_count, dtype: float64
(54,)
mean    1.479494e+07
std     2.491472e+07
Name: scaffold_N50, dtype: float64
(65,)
mean    52542.030769
std     71621.688274
Name: contig_count, dtype: float64
(65,)
mean    4.757087e+05
std     1.834412e+06
Name: contig_N50, dtype: float64
#############################################
tax id: 71240
(46,)
mean    7.543746e+08
std     7.509117e+08
Name: total_length, dtype: float64
(38,)
mean    26336.631579
std     53541.740032
Name: scaffold_count, dtype: float64
(38,)
mean    1.719973e+07
std     2.712146e+07
Name: scaffold_N50, dtype: float64
(46,)
mean    58899.152174
std     74059.532469
Name: contig_count, dtype: float64
(46,)
mean    3.075266e+05
std     1.642158e+06
Name: contig_N50, dtype: float64
#############################################
tax id: 4751
(202,)
mean    2.968321e+07
std     1.799870e+07
Name: total_length, dtype: float64
(130,)
mean    341.307692
std     699.421520
Name: scaffold_count, dtype: float64
(130,)
mean    2.041492e+06
std     1.729535e+06
Name: scaffold_N50, dtype: float64
(196,)
mean     858.091837
std     1433.239208
Name: contig_count, dtype: float64
(196,)
mean    550179.153061
std     751540.547584
Name: contig_N50, dtype: float64
#############################################
tax id: 2
(51962,)
mean    3.892013e+06
std     1.628174e+06
Name: total_length, dtype: float64
(11023,)
mean    45.308900
std     82.857411
Name: scaffold_count, dtype: float64
(11023,)
mean    1.318928e+06
std     1.570599e+06
Name: scaffold_N50, dtype: float64
(47762,)
mean    126.596122
std     177.390290
Name: contig_count, dtype: float64
(47762,)
mean    272878.520937
std     553217.419445
Name: contig_N50, dtype: float64
#############################################
tax id: 4447
(8,)
mean    6.175527e+08
std     4.500042e+08
Name: total_length, dtype: float64
(7,)
mean    31516.857143
std     39186.985371
Name: scaffold_count, dtype: float64
(7,)
mean    1.874103e+07
std     2.505071e+07
Name: scaffold_N50, dtype: float64
(8,)
mean    74281.375000
std     88109.509699
Name: contig_count, dtype: float64
(8,)
mean    1.791528e+06
std     3.269385e+06
Name: contig_N50, dtype: float64
#############################################
tax id: 2157
(470,)
mean    2.996759e+06
std     1.039213e+06
Name: total_length, dtype: float64
(24,)
mean    17.000000
std     16.357421
Name: scaffold_count, dtype: float64
(24,)
mean    1.350876e+06
std     1.177856e+06
Name: scaffold_N50, dtype: float64
(269,)
mean    110.665428
std     126.609340
Name: contig_count, dtype: float64
(269,)
mean    382578.888476
std     708645.595241
Name: contig_N50, dtype: float64

```


#### Summary statistics for years >= 2016
* the same method as above
* Boa query

```
g: Genome = input;
AssemblerCounts: output sum[string][int][string][string] of int;
AsmStats: output collection [string][string][int][int][int][int][int][int] of int;

# We need to load Assemblers info from disk
adata := getAssembler(g.refseq);
asYear :=yearof(adata.assembly_date);

if(asYear >= 2016)
	AsmStats [g.refseq][g.taxid][adata.total_length][adata.total_gap_length][adata.scaffold_count]
          [adata.scaffold_N50][adata.contig_count][adata.contig_N50] <<1;

```

* output

```
tax id: 33208
(185,)
mean    1.283843e+09
std     9.591313e+08
Name: total_length, dtype: float64
(68,)
mean    20643.985294
std     43795.739403
Name: scaffold_count, dtype: float64
(68,)
mean    2.246786e+07
std     3.679030e+07
Name: scaffold_N50, dtype: float64
(185,)
mean    53837.459459
std     77939.073163
Name: contig_count, dtype: float64
(185,)
mean    2.546505e+06
std     7.906700e+06
Name: contig_N50, dtype: float64
#############################################
tax id: 33090
(46,)
mean    9.760618e+08
std     8.862322e+08
Name: total_length, dtype: float64
(18,)
mean     9170.611111
std     18301.374870
Name: scaffold_count, dtype: float64
(18,)
mean    3.123573e+07
std     4.955433e+07
Name: scaffold_N50, dtype: float64
(46,)
mean    38017.913043
std     43402.005810
Name: contig_count, dtype: float64
(46,)
mean    1.879339e+06
std     4.973195e+06
Name: contig_N50, dtype: float64
#############################################
tax id: 71240
(37,)
mean    9.153932e+08
std     7.677289e+08
Name: total_length, dtype: float64
(12,)
mean     6489.00000
std     10645.44881
Name: scaffold_count, dtype: float64
(12,)
mean    2.666612e+07
std     5.025851e+07
Name: scaffold_N50, dtype: float64
(37,)
mean    40455.891892
std     44544.918768
Name: contig_count, dtype: float64
(37,)
mean    1.611671e+06
std     4.380495e+06
Name: contig_N50, dtype: float64
#############################################
tax id: 4447
(8,)
mean    1.319652e+09
std     1.351721e+09
Name: total_length, dtype: float64
(6,)
mean    14533.833333
std     28943.139839
Name: scaffold_count, dtype: float64
(6,)
mean    4.037494e+07
std     5.139825e+07
Name: scaffold_N50, dtype: float64
(8,)
mean    31119.250000
std     40546.758742
Name: contig_count, dtype: float64
(8,)
mean    3.294085e+06
std     7.573567e+06
Name: contig_N50, dtype: float64
#############################################
tax id: 2
(92290,)
mean    4.328610e+06
std     1.629071e+06
Name: total_length, dtype: float64
(1600,)
mean    66.011250
std     78.431879
Name: scaffold_count, dtype: float64
(1600,)
mean    9.170268e+05
std     1.471367e+06
Name: scaffold_N50, dtype: float64
(84034,)
mean    132.374670
std     176.994369
Name: contig_count, dtype: float64
(84034,)
mean    390090.731954
std     866589.562347
Name: contig_N50, dtype: float64
#############################################
tax id: 4751
(90,)
mean    2.979904e+07
std     1.592715e+07
Name: total_length, dtype: float64
(48,)
mean    139.4375
std     159.7426
Name: scaffold_count, dtype: float64
(48,)
mean    1.338551e+06
std     9.025016e+05
Name: scaffold_N50, dtype: float64
(87,)
mean    360.183908
std     688.075586
Name: contig_count, dtype: float64
(87,)
mean    7.861642e+05
std     1.101625e+06
Name: contig_N50, dtype: float64
#############################################
tax id: 2157
(338,)
mean    2.949804e+06
std     9.846644e+05
Name: total_length, dtype: float64
(5,)
mean    52.400000
std     40.887651
Name: scaffold_count, dtype: float64
(5,)
mean    385202.600000
std     434599.265975
Name: scaffold_N50, dtype: float64
(260,)
mean     74.938462
std     121.007850
Name: contig_count, dtype: float64
(260,)
mean    539667.323077
std     712641.647002
Name: contig_N50, dtype: float64

```


#### Top assembler analysis
##### Boa query for <= 2015

```
g: Genome = input;
AsmStats: output collection [string][string][int][int][int][int][int][int][string] of int;

# We need to load Assemblers info from disk
adata := getAssembler(g.refseq);
asYear :=yearof(adata.assembly_date);

foreach(k:int; def(adata.assembler[k])){
	if(asYear <= 2015)
		AsmStats [g.refseq][g.taxid][adata.total_length][adata.total_gap_length][adata.scaffold_count]
	          [adata.scaffold_N50][adata.contig_count][adata.contig_N50][adata.assembler[k].name] <<1;  
}
```
* outputs after running ```python top_assembly_stats.py TopAssembler2015OUtput/part-r-00000  ```

### Assembly programs for Metazoa

##### Years < 2016
```
tax id: 33208
 len tax list before sort:295
 len tax list:292
###########################
assembly : SOAPdenovo
(98, 8)
(98,)
mean    1.293417e+09
std     7.726936e+08
Name: total_length, dtype: float64
(97,)
mean    40355.484536
std     38810.730660
Name: scaffold_count, dtype: float64
(97,)
mean    4.548386e+06
std     1.357361e+07
Name: scaffold_N50, dtype: float64
(98,)
mean    116914.132653
std      79001.952191
Name: contig_count, dtype: float64
(98,)
mean    42029.622449
std     48982.849762
Name: contig_N50, dtype: float64
###########################
assembly : AllPaths
(54, 8)
(54,)
mean    1.555009e+09
std     1.138207e+09
Name: total_length, dtype: float64
(49,)
mean    11252.959184
std     13512.431739
Name: scaffold_count, dtype: float64
(49,)
mean    7.492583e+06
std     9.773952e+06
Name: scaffold_N50, dtype: float64
(54,)
mean    119972.925926
std      97953.358826
Name: contig_count, dtype: float64
(54,)
mean    38979.00000
std     32040.70744
Name: contig_N50, dtype: float64
###########################
assembly : Velvet
(2, 8)
(2,)
mean    1.497574e+09
std     1.941164e+09
Name: total_length, dtype: float64
(1,)
mean    1395.0
std        NaN
Name: scaffold_count, dtype: float64
(1,)
mean    14986627.0
std            NaN
Name: scaffold_N50, dtype: float64
(2,)
mean    42836.000000
std     46472.471873
Name: contig_count, dtype: float64
(2,)
mean    276196.000000
std     248526.820384
Name: contig_N50, dtype: float64
###########################
assembly : Newbler
(18, 8)
(18,)
mean    9.072074e+08
std     9.080688e+08
Name: total_length, dtype: float64
(14,)
mean     87588.285714
std     117229.623144
Name: scaffold_count, dtype: float64
(14,)
mean    2.113500e+06
std     2.387713e+06
Name: scaffold_N50, dtype: float64
(18,)
mean    133594.166667
std     157720.934440
Name: contig_count, dtype: float64
(18,)
mean    34081.888889
std     27324.846416
Name: contig_N50, dtype: float64


```
##### Years < 2016

```
assembly : SOAPdenovo
(21, 8)
(21,)
mean    1.041167e+09
std     8.485851e+08
Name: total_length, dtype: float64
(14,)
mean    38383.071429
std     49409.632232
Name: scaffold_count, dtype: float64
(14,)
mean    7.887689e+06
std     1.177750e+07
Name: scaffold_N50, dtype: float64
(21,)
mean    86615.857143
std     66221.456981
Name: contig_count, dtype: float64
(21,)
mean     98464.666667
std     208816.307456
Name: contig_N50, dtype: float64
###########################
assembly : AllPaths
(48, 8)
(48,)
mean    9.068708e+08
std     7.776643e+08
Name: total_length, dtype: float64
(24,)
mean    7151.416667
std     7075.082632
Name: scaffold_count, dtype: float64
(24,)
mean    4.336875e+06
std     1.435511e+07
Name: scaffold_N50, dtype: float64
(48,)
mean    33465.354167
std     38064.079153
Name: contig_count, dtype: float64
(48,)
mean    188477.541667
std     335602.684977
Name: contig_N50, dtype: float64

###########################
assembly : Newbler
(7, 8)
(7,)
mean    8.654051e+08
std     9.747398e+08
Name: total_length, dtype: float64
(2,)
mean    3323.500000
std     2202.637623
Name: scaffold_count, dtype: float64
(2,)
mean    877464.500000
std     910264.923382
Name: scaffold_N50, dtype: float64
(7,)
mean    56059.000000
std     80266.428208
Name: contig_count, dtype: float64
(7,)
mean    75043.142857
std     60618.500893
Name: contig_N50, dtype: float64

```




### for the full dataset?
```
assembly : AllPaths
(8244,)
mean    1.420000e+07
std     1.552883e+08
Name: total_length, dtype: float64
(7223,)
mean     103.675758
std     1437.405442
Name: scaffold_count, dtype: float64
(7223,)
mean    1.565003e+06
std     1.909007e+06
Name: scaffold_N50, dtype: float64
(8244,)
mean      840.158176
std     12481.528499
Name: contig_count, dtype: float64
(8244,)
mean    441092.875425
std     529528.366391
Name: contig_N50, dtype: float64
###########################
assembly : Velvet
(3847,)
mean    4.900231e+06
std     4.629614e+07
Name: total_length, dtype: float64
(1471,)
mean    119.626785
std     204.060849
Name: scaffold_count, dtype: float64
(1471,)
mean    8.020274e+05
std     1.558862e+06
Name: scaffold_N50, dtype: float64
(3847,)
mean     215.868989
std     1254.940180
Name: contig_count, dtype: float64
(3847,)
mean    280553.052508
std     729208.364410
Name: contig_N50, dtype: float64
###########################
assembly : Newbler
(3321,)
mean    1.069689e+07
std     9.961114e+07
Name: total_length, dtype: float64
(654,)
mean     2243.062691
std     21184.578669
Name: scaffold_count, dtype: float64
(654,)
mean    2.270857e+06
std     1.981444e+06
Name: scaffold_N50, dtype: float64
(3321,)
mean     1032.611563
std     15645.054660
Name: contig_count, dtype: float64
(3321,)
mean    306778.546522
std     717786.278599
Name: contig_N50, dtype: float64
###########################
assembly : CLC
(4064,)
mean    5.574782e+06
std     4.499240e+07
Name: total_length, dtype: float64
(42,)
mean    200.023810
std     448.139868
Name: scaffold_count, dtype: float64
(42,)
mean    2.215168e+06
std     3.158099e+06
Name: scaffold_N50, dtype: float64
(4064,)
mean     189.910433
std     1199.085738
Name: contig_count, dtype: float64
(4064,)
mean    180565.118356
std     292222.393778
Name: contig_N50, dtype: float64
###########################
assembly : SPAdes
(1379,)
mean    4.555091e+06
std     1.508023e+06
Name: total_length, dtype: float64
(12,)
mean    75.250000
std     85.350747
Name: scaffold_count, dtype: float64
(12,)
mean    1.186751e+06
std     1.369431e+06
Name: scaffold_N50, dtype: float64
(1379,)
mean    106.113125
std     112.600822
Name: contig_count, dtype: float64
(1379,)
mean    286704.187092
std     353943.384068
Name: contig_N50, dtype: float64


```

##### Boa query for >= 2016
```
# Summary statistics of all assembly programs
g: Genome = input;
AsmStats: output collection [string][string][int][int][int][int][int][int][string] of int;

# We need to load Assemblers info from disk
adata := getAssembler(g.refseq);
asYear :=yearof(adata.assembly_date);

foreach(k:int; def(adata.assembler[k])){
	if(asYear >= 2016)
		AsmStats [g.refseq][g.taxid][adata.total_length][adata.total_gap_length][adata.scaffold_count]
	          [adata.scaffold_N50][adata.contig_count][adata.contig_N50][adata.assembler[k].name] <<1;  
}
```
* run time: 20 seconds
* output:

```
assembly : AllPaths
(586,)
mean    8.122799e+07
std     3.328792e+08
Name: total_length, dtype: float64
(194,)
mean     965.664948
std     3513.485838
Name: scaffold_count, dtype: float64
(194,)
mean    2.016453e+06
std     5.121621e+06
Name: scaffold_N50, dtype: float64
(554,)
mean     3164.427798
std     14868.156805
Name: contig_count, dtype: float64
(554,)
mean    288613.036101
std     351601.041030
Name: contig_N50, dtype: float64
###########################
assembly : Velvet
(4899,)
mean    3.771146e+06
std     9.771672e+06
Name: total_length, dtype: float64
(766,)
mean    82.486945
std     75.594527
Name: scaffold_count, dtype: float64
(766,)
mean    221413.861619
std     348403.258557
Name: scaffold_N50, dtype: float64
(4747,)
mean    180.404466
std     627.590034
Name: contig_count, dtype: float64
(4747,)
mean    156706.363387
std     314856.102208
Name: contig_N50, dtype: float64
###########################
assembly : Newbler
(2097,)
mean    7.607865e+06
std     7.426879e+07
Name: total_length, dtype: float64
(84,)
mean    136.238095
std     564.718260
Name: scaffold_count, dtype: float64
(84,)
mean    2.070688e+06
std     2.574218e+06
Name: scaffold_N50, dtype: float64
(1545,)
mean     408.303560
std     6284.187238
Name: contig_count, dtype: float64
(1545,)
mean    219022.494498
std     430348.063761
Name: contig_N50, dtype: float64
###########################
assembly : CLC
(9913,)
mean    4.409805e+06
std     1.582855e+06
Name: total_length, dtype: float64
(54,)
mean    200.222222
std     135.458783
Name: scaffold_count, dtype: float64
(54,)
mean    4.388680e+05
std     1.072887e+06
Name: scaffold_N50, dtype: float64
(9547,)
mean    173.916204
std     192.564112
Name: contig_count, dtype: float64
(9547,)
mean    176345.685556
std     260613.926928
Name: contig_N50, dtype: float64
###########################
assembly : SPAdes
(28755,)
mean    4.543324e+06
std     1.637144e+06
Name: total_length, dtype: float64
(36,)
mean    100.611111
std     107.464354
Name: scaffold_count, dtype: float64
(36,)
mean    1.213182e+06
std     1.651847e+06
Name: scaffold_N50, dtype: float64
(28104,)
mean    141.659266
std     171.955625
Name: contig_count, dtype: float64
(28104,)
mean    278928.895460
std     502513.150623
Name: contig_N50, dtype: float64


```


### all Assembly programs over the years

```
# counts over the years for  statistics of all assembly programs
g: Genome = input;
AsmStats: output collection [int][string][string][string] of int;

# We need to load Assemblers info from disk
adata := getAssembler(g.refseq);
asYear :=yearof(adata.assembly_date);

foreach(k:int; def(adata.assembler[k])){
     AsmStats [asYear][g.refseq][g.taxid][adata.assembler[k].name] <<1;  
}


```



## List of all assembly programs in the RefSeq database
```
{'CG', 'iCORN', 'HGAP2', 'CA', 'PBJelly2', 'reapr', 'IMAGE', 'allpaths-lg', 'Soapdenovo', 'platanus', 'CLB', 'stitch', 'REAPR', 'PILON', 'PacBio', 'CLCbio-De', 'GS', 'CONTIGuator', 'QUAST', 'Burrows-Wheeler', 'Bowtie', 'RS_HGAP_Assembly.3', 'edena', 'Pacific', 'SMRT', 'Prodigal', 'GapFiller', 'SSPACE2', 'Quast', 'Spades', 'METASSEMBLER', 'G-finisher', 'VelvetOptimiser', 'Trimmonatic', 'Velevt', 'L_RNA_Scaffolder', 'RS_AHA_Scaffolding', 'SSPACE-LongRead', 'MicrobeTrakr', 'CodonCode', 'SPADES', 'SSPACE-STANDARD', 'Allpath-lg', 'HGAP3', 'integration', 'SeqMan', 'clc', 'A5pipeline', 'NSilico', 'Ray0Meta', 'Allpath-LG', 'IDBA-U', 'SOAPdenovo', 'SPAdes,', 'ALLPATHS-LG', 'A5_miseq', 'additional', 'SMRTpipe', 'Platanus', 'ClC', 'SGI', 'Mosaik', 'AllPaths-LG', 'LYNX', 'iMetAMOS-SPAdes', 'CIrclator', 'ABySS-pe', 'a5_miseq_linux', 'Lachesis', 'Arrow', 'Minimus', 'DNASTAR-SeqMan', 'Redundans', 'RS', 'Mauve', 'Maq', 'MyPro', 'CLS', 'Metassembler', 'HaploMerger', 'SOAPdenovo2-63mer', 'plasmidspades', 'SPADes', 'SeqManPro', 'FLASH', 'Allpaths', 'Edena', 'Consed', 'spades', 'CLC_Bio', 'Allpaths-LG', 'ATLAS-LINK', 'Platanas', 'other', 'Platanus_B', 'Dovetail', 'SMRTanalysis', 'ABySS', 'SPAdes', 'DBG2OLC', 'DNAstar', 'velvet', 'Reapr', 'ARGO', 'A5-pipeline', 'CAP3', 'SoftGenetics', 'GapCloser', 'Sequencher', 'CABOT', 'Velveth', 'DISCOVAR', 'CISA', 'Unknown', 'Velvet', 'SoapDenovo', 'BWA', 'a5_miseq', 'RAST', 'minimus', 'Spaded', 'DiscoVar', 'Assembler', 'NGEN', 'AIST', 'DNAStar', 'PBJelly', 'Microbial', 'Ragout', 'NextGene', 'MetAmos', 'SparseAssembler', 'miniasm', 'L_RNA_scaffolder', 'Geneious-Assembler', 'PRJNA278440', 'SPades', 'Arachne', 'LINKS', 'CABOG', 'post-assembly', 'A5-MiSeq', 'SOAPdenvo', 'SOAPdenoco', 'MaSuRCA', 'software', 'Phrap', 'unicycler', 'SPAdes-Linux', 'bowtie2', 'AHA', 'Minia', 'MIX', 'Mira4', 'ABYSS', 'Bowtie2', 'hgap', 'MSR-CA', 'A5-Miseq', 'sspace', 'Bridgegapper', 'BGI', 'A5MiSeq', 'HGAP3.0', 'soapdenovo', 'TAIPAN', 'Velent', 'SOAPdenovo-63mer', 'iMetAMOS', 'A5_MISeq', '2015-07-17', 'NextGENe', 'GOBOND', 'Falcon', 'A5Miseq', 'IDBA_UD', 'CLC', 'FastQC', 'IDBA', 'NGen', 'Pipeline', 'Mix', 'Chromonomer', 'AlignGraph', 'Sspace', 'allpaths', 'ALLPATHS', 'CLC-Genomics', 'SeqManNGen', 'Hierarchical', 'INVIEW', 'AllPaths_LG', 'UMD', 'a5-Miseq', 'FALCON', 'A5_pipeline', 'Spade', 'gsAssembler', 'Patric', 'PBjelly', 'A5_MiSeq', 'Ray-Meta', 'ID', 'MetaSPAdes', 'HiRise', 'CLCbio', 'N/A', 'Ray', 'MIRA', 'Hybrid', 'HGA', 'MetaQUAST', 'a5', 'IDBA-UD', 'Kmerfreq', 'SPADEs', 'a5-miseq', 'PAGIT', 'SOAPdenovo2', 'phredPhrap', 'BESST', 'IrysView', 'SSAKE', 'MHAP/PBcR', 'SKESA', 'Assemblez', 'HGAPv3', 'Prokka', 'Meraculous', 'gsMapper', 'Phred', 'A5-pipepline', 'De', 'minimus2', 'Allpaths/Velvet', 'Discovar', 'A5_miseq_pipeline', 'SGA', 'idba-ud', 'GENEIOUS', 'SOAP', 'Celera', 'CutAdapt', 'SynaDNovo', 'AMOScmp', 'A5-miseq', 'Bio', 'Velvet,', 'ABACAS', 'Anytag', 'Genious', 'MegaHit', 'DraftDoctor', 'breseq', 'OPERA', 'Ngen', 'ARACHNE', 'MetaVelvet', 'EDGE', 'AllPathsLG', 'Mira', 'Trimmomatic', 'CLC-Bio', 'Glimmer', 'AMOS-Package', 'samtools', 'Circulator', 'CLC_Genomics_Workbench', 'Roche', 'Geneious', 'VelvetOptimizer', 'MGAP', 'DNA', 'Pilon', 'Kbase', 'CANU', 'elvet', 'Manual', 'HGPA', 'AS-miseq', 'Trimnonatic', 'phrap', 'newbler', 'ARACHNE_modified', 'Lasergene', 'JGI', 'Newbler', 'DNASTAR', 'unknown', 'idba', 'SSpace', 'BWA-MEM', 'SOAPdenove', 'IonTorrent', 'As', 'A', 'Atlas', 'a', 'Sparse/proovread/Sparc', 'A52', 'A5_Miseq', 'AllPaths', 'BioNano', 'A5', 'SSPACE', 'pilon', 'RS_HGAP_Assembly', 'Edge', 'FastaAlternateReferenceMaker', 'Abyss', 'SPADE', 'CLCBio', 'ITMO', 'EDENA', 'MG', 'HGAP', 'Canu'}
```
* Data cleaning for assembly programs name
  * some assembly programs have typo of different version as a name. Following are examples of Velvet
    * Velvet: ```Velveth, Velvet, Allpaths/Velvet, VelvetOptimiser, Velevt, Velent, MetaVelvet, VelvetOptimizer```
    * CLC: ```clc,CLC, CLCbio-De, CLCBio, CLC-Genomics, CLC_Genomics_Workbench,CLCbio ```
    * AllPaths: ```Allpaths, Allpath-lg, Allpath-LG, AllPaths-LG , allpaths-lg, Allpaths/Velvet, ALLPATHS-LG```
    * Newbler : ``` Newbler, newbler```
    * Spades: ``` SPADES, spades, MetaSPAdes, plasmidspades, SPAdes, SPAdes,SPADes, SPAdes-Linux, SPADEs, iMetAMOS-SPAdes ```

### Missing values in assembly stats files

* Missing Scaffolds values
![missing values](https://bitbucket.org/_boa/genomics-sdsi/src/master/img/Missingvalues.png)

## post-processing
