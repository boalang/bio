# MongoDB


#### Java file to generate the JSON version is JsonGen.java located in the ```package boa.bio.datagen``` package.

```
bash jsongen.sh /path/to/the/raw/data/
```
* System specification
```
iMac (Retina 5K, 27-inch, Late 2015)
processor: 4 GHz Intel Core i7
Memory: 32 GB 1867 MHz DDR3
```



## Json format example
```json
{
    "sedid":"GCF_002194565.1",
    "taxid":"1273541",
    "feature":[
        {
            "id":"id0",
            "accession":"NZ_NCQP01000001.1",
            "source":"RefSeq",
            "ftype":"region",
            "start":"0",
            "end":557338,
            "score":"0.0",
            "phase":".",
            "strand":" NZ_NCQP01000001.1",
            "attribute":[
                {
                    "id":" id0",
                    "tag":" collection-date",
                    "value":"15-May-2004"
                },
                {
                    "id":" id0",
                    "tag":" country",
                    "value":"Pacific Ocean: Juan de Fuca Ridgek"
                },
                {
                    "id":" id0",
                    "tag":" ID",
                    "value":"id0"
                },
                {
                    "id":" id0",
                    "tag":" Dbxref",
                    "value":"taxon:1273541"
                }
            ]
        },
        {
            "id":"gene0",
            "accession":"NZ_NCQP01000001.1",
            "source":"RefSeq",
            "ftype":"gene",
            "start":"0",
            "end":691,
            "score":"0.0",
            "phase":".",
            "strand":" NZ_NCQP01000001.1",
            "attribute":[
                {
                    "id":" gene0",
                    "tag":" old_locus_tag",
                    "value":"Pdsh_00005"
                },
                {
                    "id":" gene0",
                    "tag":" ID",
                    "value":"gene0"
                },
                {
                    "id":" gene0",
                    "tag":" locus_tag",
                    "value":"Pdsh_RS00005"
                },
                {
                    "id":" gene0",
                    "tag":" start_range",
                    "value":".,1"
                },
                {
                    "id":" gene0",
                    "tag":" gene_biotype",
                    "value":"pseudogene"
                },
                {
                    "id":" gene0",
                    "tag":" gbkey",
                    "value":"Gene"
                },
                {
                    "id":" gene0",
                    "tag":" partial",
                    "value":"true"
                },
                {
                    "id":" gene0",
                    "tag":" pseudo",
                    "value":"true"
                },
                {
                    "id":" gene0",
                    "tag":" Name",
                    "value":"Pdsh_RS00005"
                }
            ]
        }
    ],
    "assembler":{
        "contig_count":0,
        "contig_N50":0,
        "scaffold_count":0,
        "scaffold_N50":0,
        "total_gap_length":0,
        "total_length":0,
        "assembler":[
            {
                "name":" Velvet",
                "desc":" Velvet"
            }
        ]
    }
}
```

## queries using MongoDB
### Number of features for each GFF file

```json
> db.refseq1.aggregate([ {    $project: {  seqid: 1,  numberofFeatures: {$size  : "$feature"} } } ] )
{ "_id" : ObjectId("5c572e8d5e2c8ac3beab6180"), "seqid" : "GCF_002194565.1", "numberofFeatures" : 2 }
{ "_id" : ObjectId("5c572e8d5e2c8ac3beab6183"), "seqid" : "GCF_900090055.1", "numberofFeatures" : 8 }

```

### using mapreduce to get the number of GFF files per each taxonomy id
```javascript
> db.refseq1.mapReduce(      
                    function(){emit (this.taxid, 1);},      
                    function(key,values){return Array.sum(values)},      
                    {        
                      query : {"feature.ftype":"region"},        
                      out: "sum_taxid"     
                    }
)
{
	"result" : "sum_taxid",
	"timeMillis" : 390,
	"counts" : {
		"input" : 4,
		"emit" : 4,
		"reduce" : 2,
		"output" : 2
	},
	"ok" : 1
}

> db.refseq1.mapReduce(      
                    function(){emit (this.taxid, 1);},      
                    function(key,values){return Array.sum(values)},      
                    {        
                      query : {"feature.ftype":"region"},        
                      out: "sum_taxid"     
                    }
).find()
{ "_id" : "1273541", "value" : 2 }
{ "_id" : "1673428", "value" : 2 }

```
#### the same query with defining map and reducer as a separate function

```javascript
> var mapFunc1 = function(){
... emit (this.taxid, 1);};
> var reduceFunc1 = function(key, values){
...  return Array.sum(values);
... };
> db.refseq1.mapReduce(
                      mapFunc1,
                      reduceFunc1,      
                      {        
                        query : {"feature.ftype":"region"},        
                        out: "sum_taxid"     
                      }
 ).find()
```
