package org.lights1eep.array.search;

/**
 * 查找工厂
 * @author lights1eep
 */
public class SearchFactory {
    public Search getSearch(SearchType type) {
        if(type == SearchType.SEQUENCE) {
            return new SequenceSearch();
        } else if(type == SearchType.BINARY) {
            return new BinarySearch();
        } else if(type == SearchType.INSERT) {
            return new InsertSearch();
        } else if(type == SearchType.FIBONACCI) {
            return new FibonacciSearch();
        }
        return null;
    }
}
