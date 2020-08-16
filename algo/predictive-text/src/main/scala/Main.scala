import algo.BloomFilter
import algo.Trie

object Hello extends App {
  val filter = new BloomFilter(10000, 0.05f);
  val trie = new Trie();
  trie.addWord("hello")
  trie.addWord("world")
  trie.addWord("may")
  trie.addWord("might")
  trie.addWord("should")
  trie.addWord("to")
  trie.addWord("ton")
  val results = trie.searchWords("to")
  for (word <- results) {
    println(word)
  }
}