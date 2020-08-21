package algo
import scala.util.hashing.MurmurHash3

class CountMinSketch(numNests: Int, numItemsPN: Int) {
    val buckets = Array.ofDim[Int](numNests, numItemsPN)

    def hash(key: String, i: Int): Int = {
        return math.abs(MurmurHash3.stringHash(key, i) % numItemsPN)
    }
    
    def insert(key: String) {
        for (i <- 0 until numNests) {
            val ind = hash(key, i)
            buckets(i)(ind) += 1
        }
    }

    def count(key: String): Int = {
        var min = Int.MaxValue

        for (i <- 0 until numNests) {
            val ind = hash(key, i)
            if (buckets(i)(ind) < min)
                min = buckets(i)(ind)
        }

        return min
    }
}