import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

/**
 * PriorityQueueBHTest is a JUnit testing class to test the functionality of
 * priority queue data structure.
 *
 * @author Xueying Xu (Shirley)
 */

public class PriorityQueueBHTest {
    protected Integer[] array1;
    protected Integer[] array2;
    protected Integer[] array3;
    protected PriorityQueue<Integer> emptyPQ;
    protected PriorityQueue<Integer> pq1;
    protected PriorityQueue<Integer> pq2;
    protected PriorityQueue<Integer> pq3;
 
    @Before
    public void init() {
        emptyPQ = new PriorityQueueBH<Integer>(10);
        array1 = new Integer[]{6};
        array2 = new Integer[]{7, 9};
        array3 = new Integer[]{2, 1, 3, 10, 15, 8};
        pq1 = new PriorityQueueBH(array1);
        pq2 = new PriorityQueueBH(array2);
        pq3 = new PriorityQueueBH(array3);
    }

    @Test
    public void testConstructor() {     
    	    assertNotNull(pq1);
    	    assertNotNull(pq2);
    	    assertNotNull(pq3);
        assertNotNull(emptyPQ);
        assertEquals(PriorityQueueBH.class, pq1.getClass());
        assertEquals(PriorityQueueBH.class, pq2.getClass());
        assertEquals(PriorityQueueBH.class, pq3.getClass());
        assertEquals(PriorityQueueBH.class, emptyPQ.getClass());
        assertEquals("[null, null, null, null, null, null, null, null, null, null]", emptyPQ.toString());
        assertEquals("[6]", pq1.toString());
        assertEquals("[9, 7]", pq2.toString());
        assertEquals("[15, 10, 8, 2, 1, 3]", pq3.toString());
    }

    @Test
    public void maximumTest() {
    	    assertEquals("test maximum", null, emptyPQ.maximum());
    	    assertEquals("test maximum", 6, (int) pq1.maximum());
        assertEquals("test maximum", 9, (int) pq2.maximum());
        assertEquals("test maximum", 15, (int) pq3.maximum());
        // Same assert statements to test that maximum() only returns
        // the maximum values without removing anything
        assertEquals("test maximum", null, emptyPQ.maximum());
	    assertEquals("test maximum", 6, (int) pq1.maximum());
        assertEquals("test maximum", 9, (int) pq2.maximum());
        assertEquals("test maximum", 15, (int) pq3.maximum());
    }

    @Test
    public void extractMaxTest() {
    	    assertEquals("test extractMax", null, emptyPQ.extractMaximum());
    	    assertEquals("test extractMax", 6, (int) pq1.extractMaximum());
    	    assertEquals("test extractMax", 9, (int) pq2.extractMaximum());
        assertEquals("test extractMax", 15,  (int) pq3.extractMaximum());
        // To test that the previous maximum is removed from the priority queue
        assertEquals("test extractMax", null, emptyPQ.extractMaximum());
	    assertEquals("test extractMax", null, pq1.extractMaximum());
	    assertEquals("test extractMax", 7, (int) pq2.extractMaximum());
        assertEquals("test extractMax", 10, (int) pq3.extractMaximum());
    }

    @Test
    public void increaseValueTest() {
    	    pq1.increaseValue(0, 7);
    	    assertEquals("[7]", pq1.toString());
    	    pq1.increaseValue(0, 4); // Will print out an error message, and the priority queue won't be modified
    	    assertEquals("[7]", pq1.toString()); 
    	    
    	    pq2.increaseValue(1, 10);
    	    assertEquals("[10, 9]", pq2.toString());
    	    pq2.increaseValue(0, 20);
    	    assertEquals("[20, 9]", pq2.toString());
    	    pq2.increaseValue(0, 15); // Will print out an error message, and the priority queue won't be modified
    	    assertEquals("[20, 9]", pq2.toString());
        
    	    pq3.increaseValue(1, 27);
        assertEquals("[27, 15, 8, 2, 1, 3]", pq3.toString());
        assertEquals("test increase value", 27, (int) pq3.extractMaximum());
        assertEquals("[15, 3, 8, 2, 1, null]", pq3.toString());
        pq3.increaseValue(3, 4);
        assertEquals("[15, 4, 8, 3, 1, null]", pq3.toString());
        pq3.increaseValue(0, 16);
        assertEquals("[16, 4, 8, 3, 1, null]", pq3.toString());
        pq3.increaseValue(4, 0); // Will print out an error message, and the priority queue won't be modified
        assertEquals("[16, 4, 8, 3, 1, null]", pq3.toString());
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test 
    public void increaseValueThrowsNullPointerExceptionTest1() {
    		thrown.expect(NullPointerException.class);
    		emptyPQ.increaseValue(0, 1);
    }
    
    @Test
    public void increaseValueThrowsNullPointerExceptionTest2() {
		thrown.expect(NullPointerException.class);
	    pq1.extractMaximum();
	    assertEquals("[null]", pq1.toString());
	    pq1.increaseValue(0, 1);
    }
    
    @Test
    public void increaseValueThrowsNullPointerExceptionTest3() {
		thrown.expect(NullPointerException.class);
		pq2.extractMaximum();
	    assertEquals("[7, null]", pq2.toString());
	    pq2.increaseValue(1, 1);
    }
    
    @Test
    public void increaseValueThrowsNullPointerExceptionTest4() {
		thrown.expect(NullPointerException.class);
	    pq3.extractMaximum();
	    assertEquals("[10, 3, 8, 2, 1, null]", pq3.toString());
	    pq3.increaseValue(5, 6);
    }
    
    @Test
    public void insertTest() {
        emptyPQ.insert(72);
        assertEquals("[72, null, null, null, null, null, null, null, null, null]", emptyPQ.toString());
        assertEquals(72, (int) emptyPQ.extractMaximum());
        assertEquals("[null, null, null, null, null, null, null, null, null, null]", emptyPQ.toString());
        
        pq1.insert(20);
        assertEquals("[20, 6]", pq1.toString());
        pq1.insert(1);
        assertEquals("[20, 6, 1]", pq1.toString());
        pq1.insert(18);
        assertEquals("[20, 18, 1, 6]", pq1.toString());
        
        pq2.insert(12);
        assertEquals("[12, 7, 9]", pq2.toString());
        pq2.insert(3);
        assertEquals("[12, 7, 9, 3]", pq2.toString());
        pq2.insert(8);
        assertEquals("[12, 8, 9, 3, 7]", pq2.toString());
        
        pq3.insert(11);
        assertEquals("[15, 10, 11, 2, 1, 3, 8]", pq3.toString());
        pq3.insert(-1);
        assertEquals("[15, 10, 11, 2, 1, 3, 8, -1]", pq3.toString());
        pq3.insert(30);
        assertEquals("[30, 15, 11, 10, 1, 3, 8, -1, 2]", pq3.toString());  
    }
}
