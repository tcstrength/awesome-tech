package algo.hashtable

import scala.util.control.Breaks._
import scala.util.control.Breaks

class OpenAddrHashTable(size: Int) extends HashTable {
    var _ht = new Array[Int](size)
    for (i <- 0 until _ht.size) {
        _ht(i) = Int.MinValue
    }

    def hash(value: Int, key: Int): Int = {
        return (value + key) % size
    }

    def insert(value: Int) {
        var key = hash(value, 0)
        if (_ht(key) == Int.MinValue) {
            _ht(key) = value
        } else {
            var first = key
            var break = new Breaks
            var count = 1

            do {
                key = hash(value, count)

                if (_ht(key) == Int.MinValue) {
                    _ht(key) = value
                    return
                }

                count += 1
            } while (key != first)

            println("Table full " + value)
        }
    }

    def contains(value: Int): Boolean = {
        return false
    }

    def delete(value: Int): Unit = {

    }

    def htprint() {
        for (i <- 0 until _ht.size) {
            if(_ht(i) != Int.MinValue)
                println("Index " + i + " " + _ht(i))
        }
    }
}