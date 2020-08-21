import algo.hashtable.OpenAddrHashTable

object Hello extends App {
    var ht = new OpenAddrHashTable(10)
    ht.insert(10)
    ht.insert(20)
    ht.insert(30)
    ht.insert(11)
    ht.htprint()
}