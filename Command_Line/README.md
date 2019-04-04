## Run Boa<sub>g</sub> in a command line

* Clone the Github repository.
* Prerequisite to run scripts on command line is to install Java. It can be downloaded [here](https://www.oracle.com/technetwork/java/javase/downloads/index.html).
* Go to the command line folder and run the following command:
```
bash runBoaG.sh assemblyALL.boa dataset/ output
```
  * ```assemblyALL.boa``` is a Boa<sub>g</sub> code in the current directory.
  * ```dataset``` is a folder that has the Boa<sub>g</sub> database which is a small fungi-only subset.
  * ```output``` is a folder that the output will be placed.
* Output of this query for this small dataset is as follows:

  ```
  AsmStats[Arachne] = 3
  AsmStats[HGAP] = 1
  AsmStats[N/A] = 43
  AsmStats[Newbler] = 2
  AsmStats[Unknown] = 1
  ```
