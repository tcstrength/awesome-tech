package algo.search

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack

class WordTrie extends BaseTrie {
    class Node(p: Node, k: String) {
        var parent = p;
        var children = ListBuffer[Node]();
        var data = k;
        var endOfWord = false;
    }

    var gRoot = new Node(null, "")
    var queueResult = Stack[String]()
    var maxCount = 100

    def _put(root: Node, sent: List[String], startIndex: Int) {

        if (sent.size == 1) {
            return;
        }

        if (root == null) {
            return;
        }

        if (startIndex == sent.size) {
            // println("End of sentence " + root.data)
            root.endOfWord = true
            return;
        }

        // println(sent(startIndex))

        var first = sent(startIndex)
        var next = root

        for (child <- root.children) {
            if (child.data == first) {
                // println("Insert " + first)
                // println("Key found " + first)
                _put(child, sent, startIndex + 1)
                return;
            }
        }

        var child = new Node(root, first)
        root.children += child
        _put(child, sent, startIndex + 1)
    }

    def put(sent: String) {
        var listStr = sent.toLowerCase().split(" ")
        if (listStr.size == 1) {
            return;
        }

        for (nl <- listStr.sliding(5)) {
            _put(gRoot, nl.toList, 0)
        }
    }

    def _search(root: Node, results: ListBuffer[String]) {
        if (root == null || results.size == maxCount) {
            return;
        }

        if (root.endOfWord) {
            var result = ""

            for (w <- queueResult.reverse) {
                result += w + " "
            }
            
            results += result
        }

        for (child <- root.children) {
            queueResult.push(child.data)
            _search(child, results)
            queueResult.pop()
        }
    }

    def search(keyword: String): List[String] = {
        var results = new ListBuffer[String]()

        for (child <- gRoot.children) {
            if (child.data == keyword) {
                queueResult.push(child.data)
                _search(child, results)
                queueResult.pop()
            }
        }

        return results.toList;
    }

    def _print(root: Node) {
        if (root == null) {
            return;
        }
    
        for (child <- root.children) {
            println(child.data)
            _print(child)
        }
    }

    def print() {
        // _print(gRoot)
        for (child <- gRoot.children) {
            println(child.data)
        }
    }
}