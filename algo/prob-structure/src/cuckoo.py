DUMMY = -99999

class CuckooHash:

    def __init__(self, numTable, numItem):
        self.numTable = numTable
        self.numItem = numItem
        self.hashTable = []
        self.pos = [0, 0]
        for i in range(self.numTable):
            newL = []
            for j in range(self.numItem):
                newL.append(DUMMY)
            self.hashTable.append(newL)

    def hash(self, h, key):
        if (h == 0):
            return key % self.numItem
        elif (h == 1):
            return int((key / 11) % self.numItem)

    def place(self, key, tableId = 0, count = 0):
        if (count == self.numItem):
            print(key, "unpositioned")
            return
        
        for i in range(self.numTable):
            self.pos[i] = self.hash(i, key)
            if (self.hashTable[i][self.pos[i]] == key):
                return

        if (self.hashTable[tableId][self.pos[tableId]] != DUMMY):
            old = self.hashTable[tableId][self.pos[tableId]]
            self.hashTable[tableId][self.pos[tableId]] = key
            self.place(old, (tableId + 1) % self.numTable, count + 1)
        else:
            self.hashTable[tableId][self.pos[tableId]] = key
    
    def printHashTable(self):
        for l in self.hashTable:
            for i in l:
                if (i == DUMMY):
                    print(" - ", end='')
                else:
                    print("", i, "", end='')
            print()


testDat = [20, 50, 53, 75, 100, 67, 105, 3, 36, 39, 100, 100, 20, 35, 40, 30, 29, 88, 77]
cuckoo = CuckooHash(2, 11)
for d in testDat:
    cuckoo.place(d)

cuckoo.printHashTable()