# Containerized version of Boa

## Docker container
### We created a repository that users can clone and run Boa queries.
  * Hadoop version is 2.7.7
  * Hadoop cluster with 3 nodes
  * Ubuntu vesion: 18.04
  * subset of RefSeq project, i.e., fungi to test boa queries.

#### Instruction on how to run a Docker image
* [Three nodes cluster in Docker](https://github.com/boalang/bio/tree/master/Docker/hadoop-cluster-docker)

## Monitor Hadoop and submitted tasks
* Go to this url:
[http://0.0.0.0:8088/cluster](http://0.0.0.0:8088/cluster)
* to see the HDF go to : [http://0.0.0.0:50070/](http://0.0.0.0:50070/)

## Singularity container
* The container created by Docker can be converted to a Singularity image.
* quick start: https://singularity.lbl.gov/quickstart
* Link: https://github.com/ISUgenomics/bioinformatics-workbook/blob/master/Appendix/HPC/Containers/Intro_Singularity.md


* Singularity on XSEDE
 * https://www.psc.edu/user-resources/software/singularity


## convert Docker to Singularity
* use **docker2singularity**
 * ref: https://github.com/singularityware/docker2singularity
 *
   ```
   docker run -v /var/run/docker.sock:/var/run/docker.sock -v ~/Documents/:/output --privileged -t --rm singularityware/docker2singularity docker-hadoop-1.2.1:latest
   ```
