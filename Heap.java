/**
 * This is an interface for the Heap data structure.
 * 
 * @author Xueying Xu (Shirley)
 * @param <T>
 */
public interface Heap<T extends Comparable<T>> {

	/**
	 * Returns true if the heap has no elements; false otherwise.
	 * 
	 * @return true if the heap has no elements; false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Returns the size of the heap.
	 * 
	 * @return size of the heap
	 */
	public int size();
	
	/**
	 * Returns the capacity of the heap.
	 * 
	 * @return capacity of the heap
	 */
	public int capacity();

	/**
	 * Clears the entire heap.
	 */
	public void clear();

	/**
	 * Checks if a certain value exists in the heap.
	 * 
	 * @param value
	 * @return true if the value is in the heap
	 */
	public boolean contains(T value);

}
