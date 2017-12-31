/**
 * PriorityQueueBH is BinaryHeap that implements PriorityQueue interface.
 * 
 * @author Xueying Xu (Shirley)
 * @param <T>
 */
public class PriorityQueueBH<T extends Comparable<T>> implements PriorityQueue<T> {

	protected BinaryHeap pqHeap;

	/**
	 * Creates an empty priority queue with the specified capacity.
	 * 
	 * @param capacity
	 */
	public PriorityQueueBH(int capacity) {
		pqHeap = new BinaryHeap(capacity);
	}

	/**
	 * Creates a priority queue with the given array.
	 * 
	 * @param array
	 */
	public PriorityQueueBH(Comparable[] array) {
		pqHeap = new BinaryHeap(array);
	}

	/**
	 * Inserts a new element in the priority queue.
	 * 
	 * @param element
	 */
	public void insert(T element) {

		// increase the capacity of the priority queue if needed
		if (pqHeap.size() >= pqHeap.capacity()) {
			pqHeap.setCapacity(pqHeap.capacity() + 1);
			pqHeap.addElement(element);
		} else {
			pqHeap.addElement(element);
		}
	}

	/**
	 * Returns (Peeks) the element with the highest priority.
	 * 
	 * @return the element with the highest priority
	 */
	public T maximum() {
		return (T) pqHeap.getElement(0);
	}

	/**
	 * Removes and returns the element with the highest priority.
	 * 
	 * @return the element with the highest priority
	 */
	public T extractMaximum() {
		return (T) pqHeap.removeRoot();
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
		pqHeap.increaseValue(index, element);
	}

	/**
	 * Returns a String representation of the priority queue. Used for testing.
	 * 
	 * @return a String representation of the priority queue
	 */
	public String toString() {
		return pqHeap.toString();
	}

	/**
	 * Returns the number of elements stored in the priority queue.
	 * 
	 * @return the number of elements stored in the priority queue
	 */
	public int getSize() {
		return pqHeap.size();
	}

	/**
	 * Returns the element stored at index i.
	 * 
	 * @param i
	 * @return the element stored at index i
	 */
	public T getElement(int i) {
		return (T) pqHeap.getElement(i);
	}
}
