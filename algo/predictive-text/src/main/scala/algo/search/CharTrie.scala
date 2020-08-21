package algo.search

import scala.collection.mutable

class CharTrie extends BaseTrie{
    class Node(_parent: Node, _char: Char) {
        var parent = _parent
        var data = _char
        var children = new mutable.ListBuffer[Node]
        var isEnd = false
    }

    var _root = new Node(null, '_')
    var _resultStack = new mutable.Stack[Char]
    var _maxCount = 5

    def _put(root: Node, input: List[Char], startIndex: Int) {
        if (root == null ||
            input == null || 
            input.size == 1) {
            return
        }

        if (startIndex == input.size) {
            root.isEnd = true
            return
        }

        var first = input(startIndex)

        for (child <- root.children) {
            if (child.data == first) {
                _put(child, input, startIndex + 1)
                return
            }
        }

        var child = new Node(root, first)
        root.children += child
        _put(child, input, startIndex + 1)
    }

    def put(input: String) {
        _put(_root, input.toLowerCase().toList, 0)
    }

    def _search(root: Node, keyword: String, results: mutable.ListBuffer[String]) {
        if (results.size == _maxCount ||
            root == null) {
            return
        }

        if (root.isEnd) {
            println("End of word")
            var result = ""
            for (char <- _resultStack.reverse) {
                result += char
            }
            results += result
        }
        
        if (keyword.size == 0) {
            for (child <- root.children) {
                _resultStack.push(child.data)
                _search(child, keyword, results)
                _resultStack.pop()
            }
            return
        }

        var first = keyword(0)

        for (child <- root.children) {
            if (child.data == first) {
                _resultStack.push(child.data)
                _search(child, keyword.substring(1), results)
                _resultStack.pop()
                return
            }
        }
    }

    def search(input: String): List[String] = {
        var results = new mutable.ListBuffer[String]
        _search(_root, input, results)
        return results.toList
    }
}
