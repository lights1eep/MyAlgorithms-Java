package org.lights1eep.array.sort;

/**
 * 排序工厂
 * @author lights1eep
 */
public class SortFactory {
    public Sort getSort(SortType sortType) {
        if(sortType == SortType.BUBBLE) {
            return new BubbleSort();
        } else if(sortType == SortType.SELECTION) {
            return new SelectionSort();
        } else if(sortType == SortType.INSERTION) {
            return new InsertionSort();
        } else if(sortType == SortType.SHELL) {
            return new ShellSort();
        } else if(sortType == SortType.MERGE_ITERATION) {
            return new MergeSortIteration();
        } else if(sortType == SortType.MERGE_RECURSION) {
            return new MergeSortRecursion();
        } else if(sortType == SortType.QUICK) {
            return new QuickSort();
        } else if(sortType == SortType.HEAP) {
            return new HeapSort();
        } else if(sortType == SortType.COUNTING) {
            return new CountingSort();
        } else if(sortType == SortType.BUCKET) {
            return new BucketSort();
        } else if(sortType == SortType.RADIX) {
            return new RadixSort();
        }
        return null;
    }
}
