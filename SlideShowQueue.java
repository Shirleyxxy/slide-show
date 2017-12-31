/**
 * A SlideShowQueue is a priority queue that can at most store 10 Slide objects.
 * 
 * @author Xueying Xu (Shirley)
 */
public class SlideShowQueue {

	private PriorityQueueBH<Slide> slidesQueue;

	/** The capacity of a priority queue for Slides **/
	public static final int MAXIMUM_LENGTH = 10;

	/**
	 * Constructs a SlideShowQueue that can store 10 Slides.
	 */
	public SlideShowQueue() {
		slidesQueue = new PriorityQueueBH(MAXIMUM_LENGTH);
	}

	/**
	 * Constructs a new Slide and then adds it to the priority queue.
	 * 
	 * @param slideText
	 * @param slideTime
	 */
	public void addSlide(String slideText, Integer slideTime) {
		Slide newSlide = new Slide(slideText, slideTime);
		slidesQueue.insert(newSlide);
	}

	/**
	 * Displays the Slide of the highest priority in the priority queue. Removes it
	 * from the priority queue after displaying.
	 * 
	 * @return the Slide of the highest priority
	 */
	public Slide displaySlide() {
		return slidesQueue.extractMaximum();
	}

	/**
	 * Peeks the Slide of the highest priority in the priority queue.
	 * 
	 * @return the Slide of the highest priority
	 */
	public Slide peekSlide() {
		return slidesQueue.maximum();
	}

	/**
	 * Returns the PriorityQueueBH object.
	 * 
	 * @return the PriorityQueueBH object
	 */
	public PriorityQueueBH<Slide> getSlidesQueue() {
		return slidesQueue;
	}

	/**
	 * Returns the number of slides stored in the PriorityQueueBH object.
	 * 
	 * @return the number of slides
	 */
	public int getPQSize() {
		return slidesQueue.getSize();
	}
}
