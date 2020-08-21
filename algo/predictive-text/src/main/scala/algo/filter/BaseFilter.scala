package algo

abstract class BaseFilter {
    def check(key: String): Boolean;

    def insert(key: String);
}