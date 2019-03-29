# Hadoop on XSEDE

* bridges path: ```/pylon5/mc5fr5p/hbagheri/09_Hadoop/RefSeq```

### New Hadoop on bridges

```
# 1. Connect to a login node
ssh bridges.psc.edu

# 2. Connect to r383 (the namenode)
ssh r383

# 3. Load a special module:
module load  /opt/packages/hadoop-testing/hadoop-envmod.txt

```

#### Hadoop specification
* 5 nodes Hadoop version 2.7.2
* The Hadoop infrastructure is shared therefore we need to run the queries 3 times and take the average as a running time for different mappers configuration.
* Data set path on HDFS:  ``` /user/hbagheri/dataset/  ```

### Run queries on Hadoop

##### Default confing without mappers and split size definition
```./runHadoop.sh query.boa /output/path/on/hadoop/```


##### Change the spit size and the mappers numbers

```
# last parameter is the split size. In this example is 3MB=3000000 which would be the 32 mappers
bash runHadoopRefSeq.sh query.boa /output/path/on/hadoop/ 3000000

```

## Hadoop useful commands
* Hadoop job list: ```hadoop job -list all```
* kill job: ```hadoop job –kill <JobID>```
* see job status: ``` hadoop job -status <JobID>```
* use Yarn to check status: ```yarn application -status <ApplicationID>```

## Additional files in the 09_Hadoop folder
* yarnTime.sh file that gives the run time of a given application_id
   * ``` bash yarnTime.sh application_id ```
