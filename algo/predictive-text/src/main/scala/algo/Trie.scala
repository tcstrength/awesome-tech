package algo;

import scala.collection.mutable.ListBuffer
import BloomFilter

class TrieNode(p: TrieNode, c: Char) {
    var parent = p;
    var children = ListBuffer[TrieNode]();
    var char = c;
    var endOfWord = false;
}

class Trie {
    var charRoots = new Array[TrieNode](26);

    for (i <- 0 until 26) {
        charRoots(i) = new TrieNode(null, (i + 'a'.toInt).toChar);
    }

    def _addWord(root: TrieNode, str: String) {
        if (root == null) {
            return;
        }

        if (str.length() == 0) {
            root.endOfWord = true;
            return;
        }

        val first = str(0)

        for (c <- root.children) {
            if (c.char == first) {
                _addWord(c, str.substring(1));
                return;
            }
        }

        val child = new TrieNode(root, first)
        root.children += child;
        _addWord(child, str.substring(1))
    }

    def addWord(mixedStr: String) {
        val str = mixedStr.toLowerCase()
        val first = str(0)
        val root = charRoots(first.toInt - 'a'.toInt);
        _addWord(root, str.substring(1))
    }

    def _showTrie(root: TrieNode) {
        if (root == null) {
            return;
        }

        if (root.children.size == 0) {
            println(root.char)
            return;
        }

        print(root.char)
        for (node <- root.children) {
            _showTrie(node)
        }
    }

    def showTrie() {
        for (i <- 0 until 26) {
            _showTrie(charRoots(i))
        }
    }

    def getWord(trieEnd: TrieNode): String = {
        var str = ""
        var par = trieEnd
        while (par != null) {
            str += par.char;
            par = par.parent;
        }
        return str.reverse;
    }

    def _searchWords(root: TrieNode, partStr: String, results: ListBuffer[String]) {

        if (root.endOfWord) {
            results += getWord(root)
        }

        if (partStr.length() == 0) {
            for (child <- root.children) {
                _searchWords(child, partStr, results);
            }
            return;
        }

        for (child <- root.children) {
            if (child.char == partStr(0)) {
                _searchWords(child, partStr.substring(1), results)
            }
        }

        return;
    }

    def searchWords(partStr: String): List[String] = {
        val firstClass = partStr(0)
        val charRoot = charRoots(firstClass.toInt - 'a'.toInt);
        var results = new ListBuffer[String]();
        _searchWords(charRoot, partStr.substring(1), results)
        return results.toList
    }
}