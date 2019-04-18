
# Instruction on run the Boa script on the VritualBox.

* You should install the Virtual Box first: https://www.virtualbox.org/wiki/Downloads
* Then double download the Ubuntu18.04.ova file and double click on it.
You need following  user and pass to login to the Linux.

  ```
  User: boa
  Pass: boag2018
  ```
* Open the Firefox browser. It should open the following url by default.
  * URL: http://127.0.0.1/boa/run
  * User: boa
  * Password: rocks

* You can see on the job lists some examples that have been tested on the Non-Redundant protein database.
  * NB: Since it is  one node Hadoop cluster queries may take few minutes to run.

* Copy past the following Boa script to test the interface:

```
c: Cluster = input;
counts: output sum[string][string][string] of int;

ann := getAnnotations(c.seqid);

if (match (`unnamed protein`, ann))
     counts[c.id][c.seqid][ann] << 1;

```
