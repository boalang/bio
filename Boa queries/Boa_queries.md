# Boa Queries

#### What is the smallest and largest genome in RefSeq?

```
g: Genome=input;
MaxGenome: output maximum(1) of string weight int;
MinGenome: output minimum(1) of string weight int;

adata:= getAssembler(g.refseq);
MaxGenome << g.refseq weight adata.total_length;
if(adata.total_length>0)
  MinGenome << g.refseq weight adata.total_length;

```
* Output

```
MaxGenome[] = GCF_000298275.1, 4444080527.0
MinGenome[] = GCF_000839085.1, 220.0

```

#### Exon and Gene counts for the full dataset

* run time on a single machine:
```
# command
time bash dsmaster1.sh exonpergene2016.boa all2019/ exonpergene2016OUTPUT
# run time for the years >= 2016
real	217m31.746s
user	98m22.679s
sys	13m49.539s
```

* Boa query

```
# Gene count, exon count, exon/gene
g: Genome = input;
geneCounts: output sum[string][string] of int;
exonCounts: output sum[string][string] of int;
adata := getAssembler(g.refseq);
asYear :=yearof(adata.assembly_date);

if(asYear >= 2016){

        foreach(i:int; def(g.sequence[i])){
          fdata := getFeature(g.refseq, g.sequence[i].accession);

          foreach(j:int; def(fdata.feature[j])){
                  if (match("gene",fdata.feature[j].ftype)){
                        geneCounts [g.refseq][g.taxid]<< 1;

                  }     
                  if (match("exon",fdata.feature[j].ftype)){
                        exonCounts [g.refseq][g.taxid]<< 1;

                  }     
          }
        }  
}       
```

* output

```
exonCounts[GCF_002110465.1][1981054] = 46
exonCounts[GCF_002110505.1][595] = 126
exonCounts[GCF_002110515.1][595] = 123
exonCounts[GCF_002110545.1][656397] = 109
exonCounts[GCF_002110555.1][656387] = 97
....
....
geneCounts[GCF_900639345.1][1352] = 3151
geneCounts[GCF_900639355.1][1352] = 3279
geneCounts[GCF_900639365.1][1352] = 3314

```

* output after post processing ``` python exon_gene.py part-r-00000 ```to calculate the exon per gene for >=2016:

```
tax id: {33208: 'Metazoa'}
(185, 6)
              taxid          gene          exon    genelength  exonpergene
count  1.850000e+02    185.000000  1.850000e+02    185.000000   185.000000
mean   1.286600e+05  24970.945946  4.629668e+05  23048.622193    17.788108
std    2.510577e+05  10352.248301  2.802082e+05  11845.182033     6.449821
min    6.565000e+03  10654.000000  5.183400e+04   3230.715775     4.465000
25%    8.790000e+03  16797.000000  2.179570e+05  13131.857908    13.000000
50%    3.488200e+04  24482.000000  4.470030e+05  19713.367727    17.220000
75%    1.161530e+05  30358.000000  6.209190e+05  33206.410679    22.480000
max    1.737458e+06  84199.000000  1.947098e+06  62342.449498    32.670000
#############################################
tax id: {33090: 'Viridiplantae'}
(46, 6)
               taxid           gene           exon    genelength  exonpergene
count      46.000000      46.000000      46.000000     46.000000    46.000000
mean    37085.000000   43228.739130  385118.934783   4108.790991     9.270826
std     50487.568932   21144.739107  155879.670633   1309.713711     1.991143
min      3218.000000   20744.000000  215945.000000   2058.901195     5.794000
25%      3774.000000   31384.750000  292696.250000   3622.609362     8.210000
50%      4567.500000   36032.500000  338508.500000   3954.452418     9.100000
75%     59504.000000   47907.000000  440657.750000   4421.179149     9.770250
max    206008.000000  119540.000000  857006.000000  11007.512836    19.120000
#############################################
tax id: {71240: 'eudicotyledons'}
(37, 6)
               taxid           gene           exon   genelength  exonpergene
count      37.000000      37.000000      37.000000    37.000000    37.000000
mean    34664.405405   45408.189189  397647.297297  3845.030198     9.016135
std     43772.985563   22495.104131  167962.512534   688.173733     1.367830
min      3469.000000   20744.000000  215945.000000  2058.901195     5.794000
25%      3708.000000   31841.000000  293087.000000  3584.831188     7.958000
50%      4081.000000   38318.000000  339834.000000  3952.133035     9.059000
75%     59895.000000   47911.000000  460637.000000  4352.277828     9.776000
max    158383.000000  119540.000000  857006.000000  4884.889993    12.550000
#############################################
tax id: {4447: 'Liliopsida'}
(8, 6)
               taxid          gene           exon    genelength  exonpergene
count       8.000000      8.000000       8.000000      8.000000     8.000000
mean    52513.625000  35583.750000  318533.125000   5351.575176     9.217625
std     77880.840727  11210.026618   67362.363011   2540.473618     1.288733
min      4558.000000  25125.000000  241743.000000   3618.890859     6.911000
25%      4605.500000  29946.000000  289298.500000   3826.223214     8.972250
50%     10027.000000  31882.500000  297235.500000   4271.599007     9.100000
75%     63915.250000  37053.000000  322449.750000   5586.628351     9.527250
max    206008.000000  56364.000000  450368.000000  11007.512836    11.640000
#############################################
tax id: {2: 'Bacteria'}
(92287, 6)
              taxid          gene          exon    genelength   exonpergene
count  9.228700e+04  92287.000000  92287.000000  92287.000000  92287.000000
mean   3.728350e+05   4319.914647     78.320186    890.162926      0.019416
std    6.978808e+05   1574.570137     29.059968     64.587150      0.008199
min    9.000000e+00    188.000000      1.000000    667.626639      0.001000
25%    5.730000e+02   2949.500000     57.000000    851.443547      0.014000
50%    1.496000e+03   4494.000000     72.000000    886.614283      0.019000
75%    2.168160e+05   5481.000000     95.000000    934.162371      0.023000
max    2.501515e+06  12171.000000   1860.000000   1286.844917      0.320000
#############################################
tax id: {4751: 'Fungi'}
(90, 6)
              taxid          gene           exon   genelength  exonpergene
count  9.000000e+01     90.000000      90.000000    90.000000    90.000000
mean   8.857097e+05  10327.800000   32346.888889  1696.645557     2.980200
std    5.698087e+05   3523.038318   18167.920114   171.943340     1.355471
min    4.909000e+03   4544.000000    5146.000000  1357.491561     1.039000
25%    2.416848e+05   7444.250000   19218.250000  1577.657195     2.428500
50%    9.844865e+05  10839.500000   33260.500000  1701.120889     3.041500
75%    1.392254e+06  12326.500000   39786.750000  1808.550167     3.258250
max    1.835702e+06  26245.000000  102619.000000  2137.082559     7.090000
#############################################
tax id: {4890: 'Ascomycota'}
(70, 6)
              taxid          gene          exon   genelength  exonpergene
count  7.000000e+01     70.000000     70.000000    70.000000    70.000000
mean   8.828509e+05  10429.028571  28451.242857  1660.273778     2.541486
std    5.664382e+05   3124.646429  13715.211287   142.958349     0.848397
min    4.909000e+03   4740.000000   5146.000000  1357.491561     1.039000
25%    2.762090e+05   7494.750000  14135.500000  1554.726520     1.610000
50%    9.844855e+05  11432.500000  32205.500000  1665.986741     2.808000
75%    1.446600e+06  12346.500000  38506.500000  1787.561800     3.148750
max    1.835702e+06  18659.000000  57178.000000  1959.703104     4.646000
#############################################
tax id: {2157: 'Archaea'}
(338, 6)
              taxid         gene        exon   genelength  exonpergene
count  3.380000e+02   338.000000  338.000000   338.000000   338.000000
mean   9.086289e+05  2990.183432   60.076923   851.046592     0.021408
std    8.453045e+05   945.928747    9.636417    36.993530     0.006766
min    2.161000e+03   566.000000   22.000000   723.447478     0.011000
25%    4.781800e+04  2283.250000   54.000000   831.325020     0.015000
50%    6.605205e+05  2963.500000   59.000000   851.412293     0.020000
75%    1.679095e+06  3744.250000   67.750000   871.276181     0.028000
max    2.500550e+06  5545.000000   92.000000  1072.272809     0.056000


```
* for years < 2016

```
tax id: {33208: 'Metazoa'}
(262, 6)
              taxid          gene          exon    genelength  exonpergene
count  2.620000e+02    262.000000  2.620000e+02    262.000000   262.000000
mean   1.035838e+05  22384.003817  3.149116e+05  22182.734581    13.412126
std    2.125405e+05   9674.638395  2.113446e+05  12980.705546     5.451054
min    6.087000e+03  10189.000000  3.378200e+04   1345.342769     2.714000
25%    9.366500e+03  15104.500000  1.489215e+05  10958.927076     9.391000
50%    3.052950e+04  20918.000000  2.497935e+05  18223.620654    12.510000
75%    1.043590e+05  27242.750000  4.375592e+05  33545.443962    16.797500
max    1.720309e+06  81768.000000  1.313909e+06  57549.478109    30.690000
#############################################
tax id: {4751: 'Fungi'}
(194, 6)
              taxid          gene           exon   genelength  exonpergene
count  1.940000e+02    194.000000     194.000000   194.000000   194.000000
mean   6.960367e+05   9243.634021   29140.000000  1645.857670     2.881088
std    4.011491e+05   3575.468454   20982.363897   254.975276     1.542941
min    4.896000e+03   1879.000000    1909.000000   773.947320     0.911000
25%    3.707082e+05   6084.750000    9555.000000  1491.274240     1.766750
50%    6.547430e+05   9373.000000   28011.000000  1613.181606     2.745000
75%    1.042655e+06  11875.750000   35634.750000  1808.667209     3.339500
max    1.664694e+06  21354.000000  100216.000000  2348.006710     6.620000
#############################################
tax id: {4890: 'Ascomycota'}
(143, 6)
              taxid          gene          exon   genelength  exonpergene
count  1.430000e+02    143.000000    143.000000   143.000000   143.000000
mean   6.847486e+05   9589.083916  25257.881119  1637.723478     2.476657
std    4.132776e+05   3156.765751  14388.871054   205.835385     1.021194
min    4.896000e+03   3675.000000   4855.000000  1030.276232     0.911000
25%    3.431375e+05   6468.000000  10746.000000  1487.803216     1.873500
50%    6.193000e+05   9974.000000  27094.000000  1600.606585     2.645000
75%    1.067136e+06  11876.000000  33286.500000  1764.662900     2.996000
max    1.664694e+06  21354.000000  81522.000000  2263.850915     6.009000
#############################################
tax id: {33090: 'Viridiplantae'}
(61, 6)
               taxid          gene           exon    genelength  exonpergene
count      61.000000     61.000000      61.000000     61.000000    61.000000
mean    90901.557377  32187.163934  273552.557377   4164.226509     8.142525
std    145966.719538  17323.939176  153787.779308   2303.182088     2.521628
min      2711.000000   7016.000000    9437.000000   1301.685733     1.184000
25%      3988.000000  22743.000000  202670.000000   2895.676453     7.641000
50%     29730.000000  30115.000000  268528.000000   3767.092080     8.387000
75%     88036.000000  39724.000000  347770.000000   4640.484500     9.475000
max    574566.000000  98741.000000  823272.000000  14295.837694    13.990000
#############################################
tax id: {2: 'Bacteria'}
(51537, 6)
              taxid          gene          exon    genelength   exonpergene
count  5.153700e+04  51537.000000  51537.000000  51537.000000  51537.000000
mean   6.384716e+05   3891.818480     70.258591    885.629574      0.019472
std    6.208866e+05   1550.473733     26.297256     65.212792      0.008415
min    3.500000e+01    165.000000      4.000000    627.402062      0.002000
25%    1.313000e+03   2500.000000     51.000000    829.866861      0.014000
50%    4.864080e+05   3896.000000     65.000000    879.537523      0.019000
75%    1.286084e+06   5015.000000     85.000000    934.986224      0.024000
max    2.364150e+06  12119.000000    428.000000   1239.532828      0.334000
#############################################
tax id: {71240: 'eudicotyledons'}
(41, 6)
               taxid          gene           exon    genelength  exonpergene
count      41.000000     41.000000      41.000000     41.000000    41.000000
mean    56439.658537  38539.975610  328268.390244   4057.347907     8.671146
std     89945.512601  16382.447094  133612.345518   1485.453488     1.325277
min      2711.000000  20339.000000  186283.000000   2223.981550     5.201000
25%      3885.000000  28134.000000  242803.000000   3303.544526     7.999000
50%      4432.000000  32519.000000  309114.000000   3919.451535     8.631000
75%     75702.000000  43323.000000  369473.000000   4560.024473     9.301000
max    412675.000000  98741.000000  823272.000000  10786.723327    10.720000
#############################################
tax id: {2157: 'Archaea'}
(474, 6)
              taxid         gene        exon   genelength  exonpergene
count  4.740000e+02   474.000000  474.000000   474.000000   474.000000
mean   7.712317e+05  2930.369198   59.869198   855.908299     0.021681
std    5.170191e+05   879.511993    9.076593    40.565598     0.007006
min    2.162000e+03  1314.000000   37.000000   697.878650     0.009000
25%    3.399205e+05  2060.500000   54.000000   835.143939     0.017000
50%    7.972095e+05  3015.500000   61.000000   859.464388     0.020000
75%    1.227484e+06  3572.750000   65.000000   879.131267     0.025000
max    1.769296e+06  5227.000000  122.000000  1004.071053     0.047000
#############################################
tax id: {4447: 'Liliopsida'}
(7, 6)
               taxid          gene           exon    genelength  exonpergene
count       7.000000      7.000000       7.000000      7.000000     7.000000
mean    62406.857143  29944.142857  288974.571429   6608.098889     9.621000
std     72077.877999   4865.999638   62523.970088   3862.735378     1.185533
min      4533.000000  21940.000000  203585.000000   3583.372253     7.935000
25%     22251.000000  27710.500000  235065.500000   3875.512069     8.837000
50%     42345.000000  30687.000000  327819.000000   5096.863006     9.808000
75%     65390.500000  33166.000000  336413.500000   7764.797565    10.445000
max    214687.000000  35229.000000  348460.000000  14295.837694    11.04000

```

####  Feature stats, counts, length, etc

```
# different features stats count and average length
g: Genome = input;
counts: output sum [string][string][string]  of int;
featureLength: output mean[string][string][string] of int;

foreach(i:int; def(g.sequence[i])){
   fdata := getFeature(g.refseq, g.sequence[i].accession);
   foreach(j:int; def(fdata.feature[j])){
		flength := fdata.feature[j].end-fdata.feature[j].start;
		counts [g.refseq][g.taxid][fdata.feature[j].ftype] << 1;
		featureLength[g.refseq][g.taxid][fdata.feature[j].ftype]<< flength;  	

   }		  

 }
 ```
* sample output

```


```



#### Summary statistics for full dataset used for the Tree of Life

```
g: Genome = input;
geneCounts: output sum[string][string] of int;
exonCounts: output sum[string][string] of int;
mRNACounts: output sum[string][string] of int;
CDSCounts: output sum[string][string] of int;

geneMean: output mean[string][string] of int;
exonMean: output mean[string][string] of int;
mRNAMean: output mean[string][string] of int;
CDSMean: output mean[string][string] of int;
AssemblerCounts: output sum[string][string] of int;

foreach(i:int; def(g.sequence[i])){
    # iterate over all features for a this sequence
    # we need to load the feature info from the features.seq
    fdata := getFeature(g.refseq, g.sequence[i].accession);
	foreach(j:int; def(fdata.feature[j])){
		flength := fdata.feature[j].end-fdata.feature[j].start;
		if (match("gene",fdata.feature[j].ftype)){
		  	geneCounts [g.refseq][g.taxid]<< 1;
		  	geneMean[g.refseq][g.taxid]<< flength;  	
		}
	    if (match("exon",fdata.feature[j].ftype)){
		  	exonCounts [g.refseq][g.taxid]<< 1;
		  	exonMean [g.refseq][g.taxid]<< flength;  	
		}
		if (match("mRNA",fdata.feature[j].ftype)){
		  	mRNACounts [g.refseq][g.taxid]<< 1;
		  	mRNAMean [g.refseq][g.taxid]<< flength;  	
		  	}

	    if (match("CDS",fdata.feature[j].ftype)){
		  	CDSCounts [g.refseq][g.taxid]<< 1;
		  	CDSMean [g.refseq][g.taxid]<< flength;  	
		  	}

	}

  	# Assemblers
  	adata := getAssembler(g.refseq);
	foreach(k:int; def(adata.assembler[k])){
	  	 AssemblerCounts [g.taxid][adata.assembler[k].name]<< 1;
	}

}

```

* sample Output

	```
	geneCounts[GCF_000281695.1][406552] = 3768
	geneMean[GCF_000337255.1][795797] = 828.1449893390192
	exonMean[GCF_000955905.1][1603555] = 156.33962264150944
	exonCounts[GCF_000980175.1][2209] = 63
	CDSMean[GCF_000970145.1][1434118] = 897.6280213903743
	AssemblerCounts[797209][Newbler] = 32
	AssemblerCounts[797209][Phrap] = 6
	AssemblerCounts[797209][Velvet] = 6

	```




#### List of sequence reads for each refseq
```
# list of sequence reads for each refseq
g: Genome = input;
counts: output sum[string][string] of int;
foreach(i:int; def(g.sequence[i]))
	counts [g.refseq][g.taxid]<< 1;
```
* sample output
```
counts[GCF_000001985.1][441960] = 452
counts[GCF_000002495.2][242507] = 53
counts[GCF_000002515.2][28985] = 7
counts[GCF_000002525.2][4952] = 7
counts[GCF_000002545.3][284593] = 14
counts[GCF_000002655.1][330879] = 8
counts[GCF_000002715.2][344612] = 143
```
* run time: 30 seconds

##### Top 3 most used assembly programs
```
g: Genome = input;
counts: output top(3) of string weight int;
assemblerData :=getAssembler(g.refseq);
foreach(i:int; def(assemblerData.assembler[i]))
  	 counts << assemblerData.assembler[i].name weight 1;
```

* Output on subset of data(i.e. Fungi)

```
counts[] = N/A, 87.0
counts[] = Newbler, 44.0
counts[] = allpaths, 23.0

```

##### assemblers based on tax id

```
g: Genome = input;
counts: output sum[string][string] of int;

asm :=getAssembler(g.refseq);
foreach(i:int; def(asm.assembler[i])){
  	 counts [g.taxid][asm.assembler[i].name]<< 1;
}
```

* sample Output on fungi dataset

```
counts[1182541][allpaths] = 1
counts[1182542][allpaths] = 1
counts[1182543][allpaths] = 1
counts[1182544][allpaths] = 1
counts[1182545][allpaths] = 1
counts[1186058][GS] = 1
counts[1213859][SOAPdenovo] = 1
counts[1220924][allpaths] = 1
counts[1229662][Newbler] = 1
counts[1230383][N/A] = 1

```

#### number of GFF files for each taxonomy id
```
g: Genome = input;
counts: output sum[string] of int;
counts [g.taxid]<< 1;
```
* sample Output
```
counts[429572] = 1
counts[43687] = 6
counts[43928] = 1
counts[439386] = 1
counts[439481] = 2
counts[444158] = 1
```

### number of times each assembly program have been utilized?

```
# counts over the years for  statistics of all assembly programs
g: Genome = input;
AsmStats: output sum [string]  of int;

# We need to load Assemblers info from disk
adata := getAssembler(g.refseq);

foreach(k:int; def(adata.assembler[k])){
     AsmStats [adata.assembler[k].name] <<1;  
}

```

* output

```
AsmStats[A5-Miseq] = 66
AsmStats[A5-assembler] = 1
AsmStats[A5-assembly] = 1
AsmStats[A5-miseeq] = 7
AsmStats[A5-miseq] = 714
AsmStats[ABYSS] = 1857
AsmStats[ABySS,] = 2
AsmStats[ABySS-PE] = 1
AsmStats[ABySS-pe] = 9
AsmStats[ABySS] = 1880
AsmStats[CANU] = 83
AsmStats[Celera] = 2606
AsmStats[Geneious] = 629
AsmStats[IDBA-UD] = 202
AsmStats[IDBA_UD] = 234
AsmStats[MaSuRCA] = 1118
AsmStats[Newbler] = 6164
AsmStats[Platanus] = 494
AsmStats[SOAPdenovo] = 5539


```

### top 40 assembler programs

```
g: Genome = input;
counts: output top(40) of string weight int;
data :=getAssembler(g.refseq);

foreach(i:int;def(data.assembler[i]))
        counts << data.assembler[i].name weight 1;
```

* output

```
counts[] = N/A, 49745.0
counts[] = SPAdes, 29769.0
counts[] = CLC, 13998.0
counts[] = Velvet, 8868.0
counts[] = allpaths, 8344.0
counts[] = Newbler, 6164.0
counts[] = SOAPdenovo, 5539.0
counts[] = HGAP, 3783.0
counts[] = Celera, 2606.0
counts[] = ABySS, 1880.0
counts[] = ABYSS, 1857.0
counts[] = A5, 1833.0
counts[] = software, 1641.0
counts[] = MaSuRCA, 1118.0
counts[] = SMRT, 1036.0
counts[] = Trinityrnaseq, 984.0
counts[] = MIRA, 967.0
counts[] = Unknown, 905.0
counts[] = Unicycler, 876.0
counts[] = ALLPATHS, 825.0
counts[] = GS, 728.0
counts[] = A5-miseq, 714.0
counts[] = Geneious, 629.0
counts[] = Spades, 589.0
counts[] = Ray, 525.0
counts[] = Platanus, 494.0
counts[] = newbler, 492.0
counts[] = IDBA, 415.0
counts[] = AllPaths, 406.0
counts[] = Canu, 351.0
counts[] = Phrap, 336.0
counts[] = PacBio, 307.0
counts[] = SPADES, 280.0
counts[] = SGI, 279.0
counts[] = HGAP3, 263.0
counts[] = IDBA_UD, 234.0
counts[] = velvet, 223.0
counts[] = Edena, 219.0
counts[] = Allpaths, 208.0
counts[] = CLCbio, 203.0

```
