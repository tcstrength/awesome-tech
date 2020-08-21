package algo.hashtable

import scala.collection.mutable.ListBuffer

class ChainingHashTable(size: Int) extends HashTable {
    class HtItem {
        var value = Int.MinValue
        var chain = new ListBuffer[Int]()
    }

    var _ht = new Array[HtItem](size)

    def hash(value: Int): Int = {
        return value % _ht.size
    }

    def insert(value: Int) {
        var key = hash(value)

        if (_ht(key) == null) {
            _ht(key) = new HtItem()
            println("new htitem")
        }

        if (_ht(key).value == Int.MinValue) {
            _ht(key).value = value
            println(_ht(key).value)
        } else if (contains(value) == false) {
            _ht(key).chain += value
            println("Conflict, add " + value + " to chain! " + _ht(key).value)
        }
    }

    def delete(value: Int) {
        var key = hash(value)
        if (_ht(key).value == value) {
            _ht(key).value = Int.MinValue
        }

        for (data <- _ht(key).chain) {
            if (data == value) {
                _ht(key).chain -= value
                return;
            }
        }
    }

    def contains(value: Int) : Boolean = {
        var key = hash(value)
        if (_ht(key).value == value) {
            return true
        }

        for (data <- _ht(key).chain) {
            if (data == value) {
                return true
            }
        }

        return false;
    }

    def htprint() {
        for (item <- _ht) {
            if (item != null) {
                print(item.value)
                print(" ")
                for (child <- item.chain) {
                    print(child)
                    print(" ")
                }
                println()
            }
        }
    }
}