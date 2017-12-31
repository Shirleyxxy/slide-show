/**
 * This is an interface for the Priority Queue data structure. Priority Queue
 * represents a set of elements with keys whose values indicate a priority.
 * 
 * @author Xueying Xu (Shirley)
 * @param <T>
 */
public interface PriorityQueue<T extends Comparable<T>> {

	/**
	 * Inserts a new element in the priority queue.
	 * 
	 * @param element
	 */
	public void insert(T element);

	/**
	 * Returns the element with the highest priority.
	 * 
	 * @return the element with the highest priority
	 */
	public T maximum();

	/**
	 * Removes and returns the element with the highest priority.
	 * 
	 * @return the element with the highest priority
	 */
	public T extractMaximum();

	/**
	 * Sets the element at specified index to a new element. Fixes heap through
	 * swapping elements to move the element to the correct position. Assumes that
	 * the value of the new element is greater than or equal to the value of the
	 * original element.
	 * 
	 * @param index
	 * @param element
	 */
	public void increaseValue(int index, T element);

}
