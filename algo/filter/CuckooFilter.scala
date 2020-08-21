package algo

import scala.util.hashing.MurmurHash3
import scala.math

class CuckooFilter(numNests: Int, numCols: Int) extends BaseFilter {
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

	def check(key: String): Boolean = {
		return lookup(key)._1 != -1;
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
