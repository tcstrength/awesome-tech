import math
import mmh3

class BloomFilter:
    def __init__(self, counts, probs):
        self.counts = counts
        self.probs = probs
        self.size = self.getSize()
        self.hashCount = self.getHashCount()
        self.bitArray = [False] * self.size
        print("Size of bits array:", self.size)
        print("Number of hash:", self.hashCount)
        print("False positive: ", self.probs)
        print("Init bit array: ", self.bitArray)

    def add(self, item):
        for i in range(self.hashCount):
            digest = mmh3.hash(item, i) % self.size
            self.bitArray[digest] = True
        
    def check(self, item):
        for i in range(self.hashCount):
            digest = mmh3.hash(item, i) % self.size
            if (not self.bitArray[digest]):
                return False
        return True

    def printBitArray(self):
        print(self.bitArray)
    
    def getSize(self):
        a = -self.counts * math.log(self.probs)
        b = math.log(2)
        return int(a / (b * b))

    def getHashCount(self):
        k = (self.size / self.counts) * math.log(2)
        return int(k)

bf = BloomFilter(20, 0.05)
bf.add("bloom")
bf.add("filter")
print(bf.check("bloo"))
print(bf.check("bloom"))
print(bf.check("filter"))
print(bf.check("asd"))