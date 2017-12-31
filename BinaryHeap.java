import java.util.Arrays;

/**
 * Binary Heap data structure implemented with an array.
 * 
 * @author Xueying Xu (Shirley)
 * @param <T>
 */
public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {
	// array to hold the heap
	private T[] data;
	// keep track of the heap size (different from the capacity)
	private int heapSize;

	/**
	 * Constructs an empty binary heap with a given capacity.
	 * 
	 * @param capacity
	 */
	public BinaryHeap(int capacity) {
		data = (T[]) new Comparable[capacity];
		heapSize = 0;
	}

	/**
	 * Constructs a new binary heap with a given array.
	 * 
	 * @param arr
	 */
	public BinaryHeap(Comparable[] arr) {
		data = (T[]) arr;
		heapSize = arr.length;
		buildMaxHeap(arr); // make the array a heap
	}

	/**
	 * Constructs a new binary heap with a specified capacity and a given array.
	 * 
	 * @param capacity
	 * @param arr
	 */
	public BinaryHeap(int capacity, Comparable[] arr) {
		data = (T[]) new Comparable[capacity];
		heapSize = arr.length;
		buildMaxHeap(arr);
		//Creates a BinaryHeap of capacity n
	    //Starting with the elements in arr
		System.arraycopy(arr, 0, data, 0, arr.length); 
	}

	/**
	 * Returns true if the heap has no elements; false otherwise.
	 * 
	 * @return true if the heap has no elements; false otherwise
	 */
	public boolean isEmpty() {
		return heapSize == 0;
	}

	/**
	 * Returns the size of the heap.
	 * 
	 * @return the size of the heap
	 */
	public int size() {
		return heapSize;
	}

	/**
	 * Returns the capacity of the heap.
	 * 
	 * @return the capacity of the heap
	 */
	public int capacity() {
		return data.length;
	}

	/**
	 * Sets the capacity of the heap.
	 * 
	 * @param newCapacity
	 */
	public void setCapacity(int newCapacity) {
		data = Arrays.copyOf(data, newCapacity);
	}

	/**
	 * Sets the size of the heap.
	 * 
	 * @param newSize
	 */
	public void setSize(int newSize) {
		heapSize = newSize;
	}

	/**
	 * Returns the value stored at index i.
	 * 
	 * @param i
	 * @return the value stored at index i
	 */
	public T getElement(int i) {
		return data[i];
	}

	/**
	 * Returns the index of the parent for this index.
	 * 
	 * @param i
	 * @return the index of the parent; returns -1 if no parent is possible
	 */
	public int parent(int i) {
		if (i > 0) {
			return (i - 1) / 2;
		}
		return -1; // the root has no parent
	}

	/**
	 * Returns the index of the left child for this index.
	 * 
	 * @param i
	 * @return the index of the left child
	 */
	public int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * Returns the index of the right child for this index.
	 * 
	 * @param i
	 * @return the index of the right child
	 */
	public int rightChild(int i) {
		return 2 * i + 2;
	}

	/**
	 * Exchange the value stored at index1 with the value stored at index2.
	 * 
	 * @param index1
	 * @param index2
	 */
	public void swap(int index1, int index2) {
		T temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}

	/**
	 * maxHeapify lets the value at array[index] "float down" in the max-heap so
	 * that the subtree rooted at index obeys the max-heap property.
	 * 
	 * @param array
	 * @param index
	 */
	public void maxHeapify(Comparable[] array, int index) {
		int leftIdx = leftChild(index);
		int rightIdx = rightChild(index);
		int largest = 0;
		// make sure the current node with the specified index has a left child
		if (leftIdx < heapSize && (array[leftIdx].compareTo(array[index]) > 0)) {
			largest = leftIdx;
		} else {
			largest = index;
		}
		// make sure the current node with the specified index has a right child
		if (rightIdx < heapSize && (array[rightIdx].compareTo(array[largest]) > 0)) {
			largest = rightIdx;
		}
		// restore the max heap property
		if (largest != index) {
			swap(index, largest);
			maxHeapify(array, largest); // recursion
		}
	}

	/**
	 * Converts an array into a max heap. 
	 * It rearranges elements of the array so the array satisfies the heap property.
	 * 
	 * @param array
	 */
	public void buildMaxHeap(Comparable[] array) {
		// maxHeapifying the elements starting from the middle of the array (non-leaf nodes)
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			maxHeapify(array, i);
		}
	}

	/**
	 * Adds a new value to the heap.
	 * 
	 * @param value
	 */
	public void addElement(T value) {
		// if the heap is full
		if (heapSize == capacity()) {
			System.out.println("The heap is full.");
			return;
		} else {
			data[heapSize] = value; // add the new value at the end of the heap
			heapSize++;
			heapUp(data, heapSize - 1); // move the new value up to the right place
		}
	}

	/**
	 * Moves a value stored at index i up in the heap.
	 * 
	 * @param array
	 * @param i
	 */
	private void heapUp(T[] array, int i) {
		while (i > 0 && (array[parent(i)].compareTo(array[i]) < 0)) {
			swap(parent(i), i); // restore the heap property
			i = parent(i);
		}
	}

	/**
	 * Clears the entire heap.
	 */
	public void clear() {
		for(int i = 0; i < heapSize; i++) {
			data[i] = null;
		}
		heapSize = 0;
	}

	/**
	 * Checks if a certain value exists in the heap.
	 * 
	 * @param value
	 * @return true if the value is in the heap, false otherwise
	 */
	public boolean contains(T value) {
		if (heapSize == 0) {
			return false;
		} else {
			for (int i = 0; i < heapSize; i++) {
				if (data[i].equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a String representation of the binary heap.
	 * 
	 * @return a String representation of the binary heap
	 */
	public String toString() {
		return Arrays.toString(data);
	}

	/**
	 * Converts an array of ints to an array of Integers.
	 * 
	 * @param a
	 * @return an array of Integers
	 */
	public static Integer[] convertToIntegerArray(int[] a) {
		Integer[] integerA = new Integer[a.length];
		for (int i = 0; i < a.length; i++) {
			integerA[i] = new Integer(a[i]);
		}
		return integerA;
	}
	
	/**
	 * Removes and returns the maximum element in the heap.
	 * 
	 * @return the maximum element in the heap
	 */
	public T removeRoot() {
		if (isEmpty()) {
			return null;
		} else {
			// store the maximum value
			T maximum = getElement(0);
			data[0] = data[heapSize - 1];
			data[heapSize - 1] = null;
			heapSize--;
			// maintain the heap property
			maxHeapify(data, 0);
			return maximum;
		}
	}
	
	/**
	 * Sets the element at specified index to a new element. Fixes heap through
	 * swapping elements to move the element to the correct position. Assumes that
	 * the value of the new element is greater than or equal to the value of the
	 * original element.
	 * 
	 * @param index
	 * @param element
	 */
	public void increaseValue(int index, T element) {	
		if (element.compareTo(data[index]) < 0) {
			System.err.println("The value of the new element is smaller than the value of the original element.");
		} else {
			data[index] = element;
			heapUp(data, index);
		}
	}
}