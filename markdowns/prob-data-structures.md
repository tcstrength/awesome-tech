# PROBABILISTIC DATA STRUCTURES (PDS for short)

PDS can't tell you a definate answer, instead they provide you with a reasonable approximation of the answer and a way to approximate this estimation.
They keep the size constant and ignore all collisions, because of that, they have some advantages:
    - The size in memory is constant (use small amount of memory)
    - They can be easily parallelizable (hashes are independent, so that, we can hash a list of items independtly)
    - The constant query time 

There is some PDS we can follow:
    - Bloom filter
    - Cuckoo filter
    - Count min sketch, abbreviated CMS
    - Hyperloglog

## 1. Bloom filter

Bloom filter is an PDS algorithm to check if an element is already in the list of not, and the result is an evaluation (because all PDF using hash algorithm and the problem of hashing is conflicts, PDF ignore conflicts, so the result will not be 100% correct at all).

The filter consists of an array of __m__ bits and __k__ hash functions.

Here is how the bits array of m bits is represented:

0|1|2|3|4|5|...|m
-|-|-|-|-|-|-|-
0|1|0|0|0|1|...|0

### a) Insert an element

Now to get deeper on Bloom, we can take an example of inserting element, and we assume that we already implemented 2 hash functions __h1__ and __h2__, the array of 8 bits.

``` bash
h1("geeks") = 1
h2("geeks") = 4
```

After the hash, the bits array will be enabled at bit 1 and bit 4

0|1|2|3|4|5|6|7|8
-|-|-|-|-|-|-|-|-
0|1|0|0|1|0|0|0|0

Next we insert another string "nerd"

```bash
h1("nerd") = 3
h2("nerd") = 4
```

0|1|2|3|4|5|6|7|8
-|-|-|-|-|-|-|-|-
0|1|0|1|1|0|0|0|0

### b) Check an element existing or not

```C
bool check(string str)
    i1 = h1(str) % m
    i2 = h2(str) % m
    if (!bits[i1] or !bits[i2]):
        return false
    return true
```

### d) Delete an element

In Bloom filter, delete is impossible.
Assume that, we want to delete the string "nerd", we will disable the bit 3 and 4 in the array, but how about the "geeks" inserted before?

```scala
package algo

import scala.math
import scala.util.hashing.MurmurHash3

class BloomFilter(count: Int, prob: Float) {
    val arrSize = getArraySize();
    val hashSize = getHashSize();
    var bitArr = Array.fill[Boolean](arrSize){false}

    def insert(str: String) {
        var i = 0;
        var ind = 0;
        for (i <- 0 until hashSize) {
            ind = math.abs(MurmurHash3.stringHash(str, i) % arrSize);
            println("Index" + ind.toString())
            bitArr(ind) = true;
        }
    }

    def check(str: String): Boolean = {
        var i = 0;
        var ind = 0;
        for (i <- 0 until hashSize) {
            ind = math.abs(MurmurHash3.stringHash(str, i) % arrSize);
            if (bitArr(ind) == false) {
                return false;
            }
        }

        return true;
    }

    def getArraySize(): Int = {
        val a = -(count * math.log(prob))
        val b = math.log(2)
        return (a / (b * b)).toInt;
    }

    def getHashSize(): Int = {
        val k = (arrSize / count) * math.log(2)
        return k.toInt;
    }
}
```

## 2. Cuckoo filter

Cuckoo hashing applies the idea of multiple-choice and relocation together and guarantees O(1) worst case lookup time.

```scala
package algo

import scala.util.hashing.MurmurHash3
import scala.math

class CuckooFilter(numNests: Int, numCols: Int) {
    val buckets = Array.ofDim[String](numNests, numCols)
    val dummy = null;
    var pos = new Array[Int](numNests)

    def _insert(key: String, tabIndex: Int, count: Int) {
        if (count == numCols) {
            println("Hash table insert failed, cycle present!", key)
            return;
        }

        for (i <- 0 until numNests) {
            pos(i) = math.abs(MurmurHash3.stringHash(key, i) % numCols)
            if (buckets(i)(pos(i)) == key) {
                return;
            }
        }

        if (buckets(tabIndex)(pos(tabIndex)) != dummy) {
            val temp = buckets(tabIndex)(pos(tabIndex))
            buckets(tabIndex)(pos(tabIndex)) = key
            _insert(temp, (tabIndex + 1) % numNests, count + 1)
        } else {
            buckets(tabIndex)(pos(tabIndex)) = key
        }
    }

    def insert(key: String) {
        _insert(key, 0, 0)
    }

    def delete(key: String) {

    }

    def lookup(key: String): (Int, Int) = {
        for (i <- 0 until numNests) {
            pos(i) = math.abs(MurmurHash3.stringHash(key, i) % numCols)
            if (buckets(i)(pos(i)) == key) {
                return (i, pos(i));
            }
        }

        return (-1, -1);
    }

    def print() {
        for (i <- 0 until numNests) {
            println("Table ", i)
            for (j <- 0 until numCols) {
                if (buckets(i)(j) != null)
                    println(buckets(i)(j))
            }
        }
    }
}

```

### 3. Count min sketch

The count min sketch is like cuckoo hashing but the value they store is the number of one particular key has been inserting to our storage.

### 4. Hyperloglog

Hyperloglog is an algorithm to solve count distinct problem. Calculating the exact cardinality of a multiset requires an amount of memory proportional to the cardinality, which is impractical for very large data sets.