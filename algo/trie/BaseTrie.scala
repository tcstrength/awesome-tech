package algo.search

abstract class BaseTrie {
    def put(ketword: String)

    def search(keyword: String): List[String]
}