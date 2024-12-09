import java.util.Arrays;
import java.util.Random;

/**
 * Sorters Class. Contains all the sorting algorithms in the project, from bubble to heap.
 * Some methods adapted from GeeksForGeeks, and given credit to links in the given report.
 * Automates array creations, sorting and timing each method, and prints a large list of results
 * coupled with total times running through all increments of sorting.
 *
 * METHODS ADAPTED FROM GFG:
 * Insertion Sort
 * Merge Sort
 * Shell Sort
 * Quick Sort
 * Heap Sort
 *
 * @author Jackson Campbell
 * @version 1.0.0
 */
public class Sorters {

    private int[] arrayToSort;
    private long bubbleSortTotalTime;
    private long selectionSortTotalTime;
    private long insertionSortTotalTime;
    private long mergeSortTotalTime;
    private long shellSortTotalTime;
    private long quickSortTotalTime;
    private long heapSortTotalTime;

    /**
     * Array Creation method. Is used in all sorting methods, and automatically creates
     * and fills the array with randomized values for sorting.
     * @param arraySize the given size to create values for.
     */
    private void arrayCreate(int arraySize) {
        arrayToSort = new int[arraySize];
        Random rng = new Random();
        for(int i = 0; i < arraySize; i++) {
            arrayToSort[i] = rng.nextInt(0, 10000001);
        }
    }

    /**
     * Bubble sort method. Runs bubble sort and times from beginning
     * to the end, where sorting is completed after no swaps are detected anymore.
     *
     * @param arraySize the array size passed into arrayCreate.
     */
    public void bubbleSort(int arraySize) {
        arrayCreate(arraySize);
        long timestamp = System.currentTimeMillis();
        long timestamp2;
        System.out.println("Starting bubble sort...");
        boolean swapped = true;
        while(swapped) {
            swapped = false;
            for (int i = 0; i < arrayToSort.length - 1; i++) {
                if (arrayToSort[i] > arrayToSort[i + 1]) {
                    int temp = arrayToSort[i + 1];
                    arrayToSort[i + 1] = arrayToSort[i];
                    arrayToSort[i] = temp;
                    swapped = true;
                }
            }
        }
        timestamp2 = System.currentTimeMillis() - timestamp;
        System.out.println("Sorted " + arraySize + " values! Final time(ms): " + timestamp2);
        System.out.println("Validated? - " + validate());
        System.out.println();
        bubbleSortTotalTime += timestamp2;
    }

    /**
     * Selection sort method. Runs selection sort and times from beginning
     * to the end, when there are no more selections to be made for sorting.
     *
     * @param arraySize the array size passed into arrayCreate.
     */
    public void selectionSort(int arraySize) {
        arrayCreate(arraySize);
        long timestamp = System.currentTimeMillis();
        long timestamp2;
        System.out.println("Starting selection sort...");
        for(int i = 0; i < arrayToSort.length - 1; i++) {
            int smallestPos = i;
            for(int j = i + 1; j < arrayToSort.length; j++) {
                if (arrayToSort[j] < arrayToSort[smallestPos]) {
                    smallestPos = j;
                }
            }
            int temp = arrayToSort[i];
            arrayToSort[i] = arrayToSort[smallestPos];
            arrayToSort[smallestPos] = temp;
        }
        timestamp2 = System.currentTimeMillis() - timestamp;
        System.out.println("Sorted " + arraySize + " values! Final time(ms): " + timestamp2);
        System.out.println("Validated? - " + validate());
        System.out.println();
        selectionSortTotalTime += timestamp2;
    }

    /**
     * Insertion sort method. Runs insertion sort and times from beginning
     * to the end, when inserting an unsorted value no longer occurs.
     *
     * @param arraySize the array size passed into arrayCreate.
     */
    public void insertionSort(int arraySize) {
        arrayCreate(arraySize);
        long timestamp = System.currentTimeMillis();
        long timestamp2;
        System.out.println("Starting insertion sort...");
        for(int i = 0; i < arrayToSort.length; i++) {
            int largest = arrayToSort[i];
            int beforeSmallest = i-1;

            while(beforeSmallest >= 0 && arrayToSort[beforeSmallest] > largest) {
                arrayToSort[beforeSmallest + 1] = arrayToSort[beforeSmallest];
                beforeSmallest = beforeSmallest -1;
            }
            arrayToSort[beforeSmallest + 1] = largest;
        }
        timestamp2 = System.currentTimeMillis() - timestamp;
        System.out.println("Sorted " + arraySize + " values! Final time(ms): " + timestamp2);
        System.out.println("Validated? - " + validate());
        System.out.println();
        insertionSortTotalTime += timestamp2;
    }

    /**
     * Running merge sort. Takes the array size, and automates merge sort as it
     * works recursively, and thus cannot contain the arrayCreate in itself. Works until
     * the array is merged back together and properly sorted. Times from beginning to end.
     *
     * @param arraySize the array size passed into arrayCreate.
     */
    public void runMergeSort(int arraySize) {
        arrayCreate(arraySize);
        long timestamp = System.currentTimeMillis();
        long timestamp2;
        System.out.println("Starting merge sort...");
        mergeSort(arrayToSort);

        timestamp2 = System.currentTimeMillis() - timestamp;
        System.out.println("Sorted " + arraySize + " values! Final time(ms): " + timestamp2);
        System.out.println("Validated? - " + validate());
        System.out.println();
        mergeSortTotalTime += timestamp2;
    }

    /**
     * Merge sort method. Runs the merge sort recursively until the array has been
     * fully merged back together and no longer needs to be broken down into pieces to merge again.
     * @param arrayToSort the given array to sort by merging.
     */
    private void mergeSort(int[] arrayToSort) {
        if(arrayToSort.length < 2) {
            return;
        }

        int midpoint = arrayToSort.length / 2;
        int[] leftSide = Arrays.copyOfRange(arrayToSort, 0, midpoint);
        int[] rightSide = Arrays.copyOfRange(arrayToSort, midpoint, arrayToSort.length);

        mergeSort(leftSide);
        mergeSort(rightSide);

        merge(arrayToSort, leftSide, rightSide);
    }

    /**
     * Merging helper method. Merges the two halves back together, and runs at
     * the end of mergeSort so it can run recursively until needing the last merge.
     * @param arrToMerge the given array to now merge.
     * @param leftSide the left side to merge.
     * @param rightSide the right side to merge.
     */
    private void merge(int[] arrToMerge, int[] leftSide, int[] rightSide) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSide.length && j < rightSide.length) {
            if(leftSide[i] <= rightSide[j]) {
                arrToMerge[k++] = leftSide[i++];
            } else {
                arrToMerge[k++] = rightSide[j++];
            }
        }

        while(i < leftSide.length) {
            arrToMerge[k++] = leftSide[i++];
        }

        while(j < rightSide.length) {
            arrToMerge[k++] = rightSide[j++];
        }
    }

    /**
     * Shell sort method. Runs shell sort until it no longer makes a swap,
     * ensuring that the values are fully sorted and complete.
     *
     * @param arraySize the array size passed into arrayCreate.
     */
    public void shellSort(int arraySize) {
        arrayCreate(arraySize);
        long timestamp = System.nanoTime();
        long timestamp2;
        System.out.println("Starting shell sort...");
        for(int space = (arrayToSort.length / 2); space > 0; space /= 2) {
            for(int i = space; i < arrayToSort.length; i++) {
                int temp = arrayToSort[i];
                int j;
                for(j = i; j >= space && arrayToSort[j - space] > temp; j -= space) {
                    arrayToSort[j] = arrayToSort[j - space];
                }
                arrayToSort[j] = temp;
            }
        }
        timestamp2 = System.nanoTime() - timestamp;
        System.out.println("Sorted " + arraySize + " values! Final time(ms): " + (timestamp2 / 1000000.0));
        System.out.println("Validated? - " + validate());
        System.out.println();
        shellSortTotalTime += (long) (timestamp2 / 1000000.0);
    }

    /**
     * Runs quick sort, as quick sort runs recursively and cannot call
     * array creation within the same method. Times from beginning to end, when
     * all values are properly sorted.
     *
     * @param arraySize the array size passed into arrayCreate.
     */
    public void runQuickSort(int arraySize) {
        arrayCreate(arraySize);
        long timestamp = System.currentTimeMillis();
        long timestamp2;
        System.out.println("Starting quick sort...");
        quickSort(arrayToSort, 0, arrayToSort.length - 1);

        timestamp2 = System.currentTimeMillis() - timestamp;
        System.out.println("Sorted " + arraySize + " values! Final time(ms): " + timestamp2);
        System.out.println("Validated? - " + validate());
        System.out.println();
        quickSortTotalTime += timestamp2;
    }

    /**
     * Quick sort method. Runs recursively, taking a pivot index created by
     * a partition. Runs until the given partition on each side is sorted properly.
     * Then ensures that the entire array is sorted if recursion no longer occurs.
     * @param arrayToSort the given array to sort.
     * @param lowEnd the high-end boundary.
     * @param highEnd the high-end boundary.
     */
    private void quickSort(int[] arrayToSort, int lowEnd, int highEnd) {
        if(lowEnd < highEnd) {
            int pivotIndex = partition(arrayToSort, lowEnd, highEnd);

            quickSort(arrayToSort, lowEnd, pivotIndex - 1);
            quickSort(arrayToSort, pivotIndex + 1, highEnd);
        }
    }

    /**
     * Partition helper method. Creates a partition to sort around for quick sort.
     * Ensures that pivoting works properly.
     * @param arrayToPartition the given array to create a partition from.
     * @param lowEnd the low-end boundary.
     * @param highEnd the high-end boundary.
     * @return the newly-created pivot index for the partition.
     */
    private int partition(int[] arrayToPartition, int lowEnd, int highEnd) {
        int pivot = arrayToPartition[highEnd];
        int i = (lowEnd - 1);

        for(int j = lowEnd; j < highEnd; j++) {
            if(arrayToPartition[j] <= pivot) {
                i++;
                int temp = arrayToPartition[i];
                arrayToPartition[i] = arrayToPartition[j];
                arrayToPartition[j] = temp;
            }
        }
        int temp = arrayToPartition[i+1];
        arrayToPartition[i+1] = arrayToPartition[highEnd];
        arrayToPartition[highEnd] = temp;

        return i + 1;
    }

    /**
     * Heap sort method. Runs heap sort and first creates a heap for sorting,
     * and runs until the array is entirely sorted. Times from beginning to end.
     *
     * @param arraySize the array size passed into arrayCreate.
     */
    public void heapSort(int arraySize) {
        arrayCreate(arraySize);
        long timestamp = System.currentTimeMillis();
        long timestamp2;
        System.out.println("Starting heap sort...");
        for(int i = (arrayToSort.length / 2) - 1; i >= 0; i--) {
            heapCreate(arrayToSort, arrayToSort.length, i);
        }
        for(int i = arraySize - 1; i > 0; i--) {
            int temp = arrayToSort[0];
            arrayToSort[0] = arrayToSort[i];
            arrayToSort[i] = temp;

            heapCreate(arrayToSort, i, 0);
        }
        timestamp2 = System.currentTimeMillis() - timestamp;
        System.out.println("Sorted " + arraySize + " values! Final time(ms): " + timestamp2);
        System.out.println("Validated? - " + validate());
        System.out.println();
        heapSortTotalTime += timestamp2;
    }

    /**
     * Binary heap creation tool. Creates a binary heap, as needed in heap sort.
     * Shifts the largest index as needed within the helper method.
     * @param arrayToHeap the given array to create a heap from.
     * @param arrayLength the given array's length.
     * @param index the given index to work with.
     */
    private void heapCreate(int[] arrayToHeap, int arrayLength, int index) {
        int largestIndex = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if(left < arrayLength && arrayToHeap[left] > arrayToHeap[largestIndex]) {
            largestIndex = left;
        }
        if(right < arrayLength && arrayToHeap[right] > arrayToHeap[largestIndex]) {
            largestIndex = right;
        }
        if(largestIndex != index) {
            int temp = arrayToHeap[index];
            arrayToHeap[index] = arrayToHeap[largestIndex];
            arrayToHeap[largestIndex] = temp;

            heapCreate(arrayToHeap, arrayLength, largestIndex);
        }
    }

    /**
     * Run method. Increments from 10k to 200k values, and prints the total
     * time of all sorting methods once sorting finishes.
     */
    public void run() {
        int sortingSize = 10000;
        for(int i = 0; i < 20; i++) {
            bubbleSort(sortingSize);
            selectionSort(sortingSize);
            insertionSort(sortingSize);
            runMergeSort(sortingSize);
            shellSort(sortingSize);
            runQuickSort(sortingSize);
            heapSort(sortingSize);
            sortingSize += 10000;
        }
        System.out.println("All sorting complete! Numbers are printed below in seconds for total time sorted.");
        System.out.println("Bubble sort total time: " + (bubbleSortTotalTime / 1000.0));
        System.out.println("Selection sort total time: " + (selectionSortTotalTime / 1000.0));
        System.out.println("Insertion sort total time: " + (insertionSortTotalTime / 1000.0));
        System.out.println("Merge sort total time: " + (mergeSortTotalTime / 1000.0));
        System.out.println("Shell sort total time: " + (shellSortTotalTime / 1000.0));
        System.out.println("Quick sort total time: " + (quickSortTotalTime / 1000.0));
        System.out.println("Heap sort total time: " + (heapSortTotalTime / 1000.0));
    }

    /**
     * Validation helper method. Validates that each array is sorted after calling a sort method.
     * @return the boolean value if the array was sorted or not.
     */
    private boolean validate() {
        for(int i = 0; i < arrayToSort.length - 1; i++) {
            if(arrayToSort[i] > arrayToSort[i + 1]) {
                return false;
            }
        }
        return true;
    }
}