package algo.hashtable
class DoubleHashTable(size: Int) extends HashTable {
    var _ht = new Array[Int](size)

    for (i <- 0 until _ht.size) {
        _ht(i) = Int.MinValue
    }

    def hash(value: Int, key: Int): Int = {
        if (key == 0) {
            return value % size
        }

        return (value + 11) % size
    }

    def insert(value: Int) {
        var key1 = hash(value, 0)
        var key2 = hash(value, 1)
        if (_ht(key1) == Int.MinValue) {
            _ht(key1) = value
        } else if (_ht(key2) == Int.MinValue) {
            _ht(key2) = value
        } else {
            println("Failed to insert " + value)
        }
    }

    def delete(value: Int) {
        var key1 = hash(value, 0)
        var key2 = hash(value, 1)
        if (_ht(key1) == value) {
            _ht(key1) = Int.MinValue
        } else if (_ht(key2) == value) {
            _ht(key2) = Int.MinValue
        }
    }

    def contains(value: Int): Boolean = {
        var key1 = hash(value, 0)
        var key2 = hash(value, 1)
        if (_ht(key1) == value) {
            return true
        } else if (_ht(key2) == value) {
            return true
        }

        return false
    }
}
