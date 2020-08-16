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