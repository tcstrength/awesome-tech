import mmh3

class CountMinSketch:

    def __init__(self, size = 16, numHash = 4):
        self.hashTable = []
        self.size = size
        self.numHash = numHash
        for i in range(numHash):
            newL = []
            for j in range(size):
                newL.append(0)
            self.hashTable.append(newL)
        
    def insert(self, key):
        for i in range(self.numHash):
            ind = mmh3.hash(key, i) % self.size
            self.hashTable[i][ind] += 1

    def count(self, key):
        min = 999
        for i in range(self.numHash):
            ind = mmh3.hash(key, i) % self.size
            if (self.hashTable[i][ind] < min):
                min = self.hashTable[i][ind]
        return min
    
    def printTable(self):
        print(self.hashTable)


cms = CountMinSketch()
cms.insert("ahihi")
cms.insert("www")
cms.insert("ahihi")
cms.insert("aaa")
cms.printTable()
print(cms.count("www"))
print(cms.count("sad"))
print(cms.count("ahihi"))