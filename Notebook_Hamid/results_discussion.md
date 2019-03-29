
# To be added to the results section in the BMC paper

## TODOs:
* RefSeq studies, what are related works?
* add troubleshooting in the repo or website: for some queries that have compilation error. Or database in not available, etc.



## Comparison between MongoDB and BoaG
* MongoDB is an open source NoSQL database that also supports many features of traditional databases like sorting, grouping, aggregating, indexing, etc. MongoDB has been used to handle large scale semi-structured or NoSQL data. Datasets are stored in a flexible JSON format and therefore can support data schemas that evolve over time. MapReduce is a framework that has been used for scalable analysis in scientific data. Hadoop is an open source implementation of MapReduce. There are organizations that have used the power of MongoDB and Hadoop framework [together](https://www.mongodb.com/hadoop-and-mongodb) to address challenges in Big Data. [Genomics England](https://www.genomicsengland.co.uk/) runs the 100,000 Genomes Project using [MongoDB](https://www.mongodb.com/press/genomics-england-uses-mongodb-to-power-the-data-science-behind-the-100000-genomes-project) to harness huge amount of data in bioinformatics.


* Compare in terms of data generation:  more I/O in json generation
* Output file size: JSON format as it can be seen in the (TODO: file) is much larger than the raw file simple because it stores the schema along with data. Therefore the JSON file is much larger. On average the JSON file is 2.7 times of the raw data while the Hadoop binary sequence file is about 4 time smaller.
* query Line of Code (LOC) BoaG vs MongoDB & Python: The BoaG scripts has less number of lines.
* post processing in python is needed to aggregate data output. While it is possible that an expert in python and MongoDB write a more efficient query, the query on the left is an indication that the number of lines of codes is more for  average bioinformaticians.
 * the domain specific language and infrastructure abstracts a way these details. For example the reducers summarize the data.
   * TODO: figure list of reducers and aggregators
   * while the list shows a list of predefined aggregators the BoaG language, that are  also available in traditional RDBS and MongoDB, is flexible enough to define new aggregators.
   * BoaG language is designed to put the complexity on the backend and hide details of how to analyze genomics datasets and provides an easy to use interface for average bioinformaticians to explore large amount of data.

* TODO: add one section about map-reduce programs. Cite: the work that reviews map-reduce in bioinformaticians.

## TODO: add to paper compare and contrast Hadoop and MongoDB
* paper: Performance Evaluation of a MongoDB and Hadoop Platform for Scientific Data Analysis
* MongoDB and Hadoop performance analysis has been studied. Read/write from/to MongoDB has more overhead that read/write from/to in HDFS.
* MongoDB is for storage while Hadoop is for analysis. The best combination for different scenarios are shown in this paper.
* list of companies that use MongoDB with Hadoop: https://www.mongodb.com/hadoop-and-mongodb
* TODO: Hadoop Vs. MongoDB: Which Platform is Better for Handling Big Data?: https://aptude.com/blog/entry/hadoop-vs-mongodb-which-platform-is-better-for-handling-big-data/
  * NoSql???
* Many time-consuming analyses of next-generation
sequencing data can be addressed with modern cloud computing.
The Apache Hadoop-based solutions have become popular in genomics because of their scalability in a cloud infrastructure
   * paper ref: "SparkSeq: fast, scalable and cloud-ready tool for the interactive genomic data analysis with nucleotide precision"


##  TODO:compare with intermine? apache solr?
http://intermine.org/
* can boa be itegrated with InterMine?
* BoaG can be integrated with Galaxy.


| |MongoDB|BoaG |
|--|--| --|
|Lines of Code|--|--|
|Data generation time|--|--|
|Data file (json vs Sequence file)|--|--|
|Run time|--|--|
|Schema Flexibility|--|--|


* here is an example in BoaG vs MongoDB. While experts in MongoDB may write this query more efficiently and reduce the size
but query on the left is an indication that how an average bioinformaticians would write the query.

## compare the map-reduce in MongoDB vs BoaG
 * MongoDB reference: https://docs.mongodb.com/manual/aggregation/

#### Query with defining map and reducer as a separate function

```javascript
> var mapFunc1 = function(){
    emit (this.taxid, 1);
  };
> var reduceFunc1 = function(key, values){
    return Array.sum(values);
 };
> db.refseq1.mapReduce(
                      mapFunc1,
                      reduceFunc1,      
                      {        
                        query : {"feature.ftype":"region"},        
                        out: "sum_taxid"     
                      }
 ).find()
```
While the boa query would be smaller and easy to use:
```
g: Genome = input;
counts: output sum[string] of int;
counts [g.taxid]<< 1;
```
As we can see the BoaG language abstracts the details of map-reduce tasks, therefore
the number of lines of code would be smaller than the MongoDB query.


## Compare with Python
* If we write the number of assembler programs without mapreduce approach the code in python would be as follows:

```python
#!/usr/bin/python

def get_db():
    from pymongo import MongoClient
    client = MongoClient('localhost:27017')
    db = client.test
    return db


def insert_data(db):
    db.refseq1.insert({"seq_id": "WP_12124"})

def get_data(db, arg):

    assembly_out = {}
    data_out= db.refseq1.find({"taxid": arg})
    # parse feature list
    for item in data_out:
        # parse assembly information
        assembly_data = item["assembler"]
        assembly_programs = assembly_data["assembler"]
        for asm in assembly_programs:
            print("assembly program: " + asm["name"])
            if asm["name"] in assembly_out:
                assembly_out[asm["name"]] +=1
            else:
                assembly_out[asm["name"]] =1

    print(assembly_out)
    return data_out

if __name__ == "__main__":
    db = get_db()
    # insert_data(db)
    acc_id  = "NZ_NCQP01000001.1"
    tax_id = "1673428"
    ret= get_data(db, tax_id)
    print(ret)
```    

## Discussions about the dataset
* RefSeq is just an example of dataset that demonstrates the language and infrastructure features that BoaG would have. While RefSeq is a small dataset we see lots of benefits of using genomics specific language that could be utilized to address much larger dataset that will remain for the future work. We already have started working on Non-Redundant protein database, taxonomic assignment, and CDHIT clustering output and have found interesting results. One practical application we have found was to identify proteins that may have been taxonomically mis-annotated due to contamination in the sample.


## TODO: cite papers that talks about usage of Hadoop and Spark in recent bioinformatics works
* dataset is not our contribution

## Bring programs script and define as a DSL function in BoaG
* Bringing external implementation to Boa is another advantages of the infrastructure over traditional languages. Users can bring their own implementation from Perl, Bash, Python, etc. But we have to address the security issues as well. Not everyone can run any arbitrary scripts on the infrastructure. Scripts could be converted to a DSL function.

## cite literature the difference and applications of Hadoop vs MongoDB
