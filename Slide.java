/**
 * Slide implements Comparable interface and has the text and order (time) for
 * display.
 * 
 * @author Xueying Xu (Shirley)
 */
public class Slide implements Comparable<Slide> {

	private String slideText;
	private Integer slideTime;
    
	/**
	 * Constructs a slide with the specified text and time.
	 * 
	 * @param slideText
	 * @param slideTime
	 */
	public Slide(String slideText, Integer slideTime) {
		this.slideText = slideText;
		this.slideTime = slideTime;
	}
    
	/**
	 * Returns the text of a slide.
	 * 
	 * @return the text of a slide
	 */
	public String getSlideText() {
		return slideText;
	}
    
	/**
	 * Returns the time (order) of a slide.
	 * 
	 * @return the time (order) of a slide
	 */
	public Integer getSlideTime() {
		return slideTime;
	}

	/**
	 * Compares the order of displaying. 
	 * Returns 1 if the current slide should be displayed earlier than the other slide.
	 * Returns 0 if the order is the same.
	 * Returns -1 if the current slide should be displayed later than the other slide.
	 * 
	 * @param anotherSlide
	 */
	public int compareTo(Slide anotherSlide) {
		// the current slide should be displayed earlier (higher priority)
		if (this.getSlideTime() < anotherSlide.getSlideTime()) {
			return 1;
		} else if (this.getSlideTime() == anotherSlide.getSlideTime()) {
			return 0;
		// the current slide should be displayed later (lower priority)
		} else {
			return -1;
		}
	}
}
