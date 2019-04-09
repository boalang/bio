## Run Hadoop Cluster within Docker Containers
* NB: this Docker version is forked from this GitHub repository: https://github.com/kiwenlau/hadoop-cluster-docker

### Three Nodes Hadoop Cluster

##### 1. Clone the repository

```
git clone https://github.com/boalang/bio.git
```

##### 2. Move to the Docker folder  and build the container

```
cd Docker/hadoop-cluster-docker/

docker build -t "boa/hadoop" .

```

##### 3. create hadoop network

```
sudo docker network create --driver=bridge hadoop
```

##### 4. start container

```
sudo ./start-container.sh
```

**output:**

```
start hadoop-master container...
start hadoop-slave1 container...
start hadoop-slave2 container...
root@hadoop-master:~#
```
- start 3 containers with 1 master and 2 slaves
- you will get into the /root directory of hadoop-master container

##### 5. start hadoop

```
./start-hadoop.sh
```

##### 6. Test Boa<sub>g</sub> query
```
cd RefSeq/

bash dsmaster.sh maxMin.boa output1
```



**output**

```
MaxGenome[] = GCF_000149925.1, 88724376.0
MinGenome[] = GCF_000146465.1, 2216898.0

```
