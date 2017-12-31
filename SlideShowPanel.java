import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * SlideShowPanel is the user interface for the SlideShow application. The user
 * can add slides and start the show.
 * 
 * @author Xueying Xu (Shirley)
 */
public class SlideShowPanel extends JPanel implements ActionListener {

	/** GUI data **/
	private JPanel slideInfoPanel;
	private JPanel displayPanel;
	private JTextField slideText;
	private JTextField slideTime;
	private JTextField slideDisplay;
	private JButton addSlideButton;
	private JButton startButton;
	private JPanel messagePanel;
	// Displays messages to remind the user to enter valid information
	private JLabel message;

	/** Non-GUI data **/
	// a Priority Queue of Slides
	private SlideShowQueue slides;
	private Timer timer;
	// duration of the slide show
	private int time = 0;

	/**
	 * Constructs the user interface.
	 */
	public SlideShowPanel() {
		// Creates the backend for a Slideshow
		slides = new SlideShowQueue();
		this.setLayout(new BorderLayout());
		add(createSlideInfoPanel(), BorderLayout.NORTH);
		add(createDisplayPanel(), BorderLayout.CENTER);
		add(createMessagePanel(), BorderLayout.SOUTH);
	}

	/**
	 * Creates and returns a JPanel used for collecting information for a new Slide
	 * object and adding the new Slide to the queue.
	 * 
	 * @return slideInfoPanel
	 */
	private JPanel createSlideInfoPanel() {

		slideInfoPanel = new JPanel(new FlowLayout(50, 50, 20));

		// a JTextField for the user to enter the text for display
		slideText = new JTextField("Slide Text");
		slideText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		slideText.setPreferredSize(new Dimension(300, 40));
		slideText.setHorizontalAlignment(JTextField.LEFT);
		slideText.setFont(new Font("Arial", Font.ITALIC, 25));
		slideText.setForeground(Color.GRAY);

		// a JTextField for the user to enter the time(order) for display
		slideTime = new JTextField("Slide Time (1-10)");
		slideTime.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		slideTime.setPreferredSize(new Dimension(240, 40));
		slideTime.setHorizontalAlignment(JTextField.LEFT);
		slideTime.setFont(new Font("Arial", Font.ITALIC, 25));
		slideTime.setForeground(Color.GRAY);

		// a JButton to add a Slide to the queue
		addSlideButton = new JButton("Add Slide");
		addSlideButton.setPreferredSize(new Dimension(100, 45));
		addSlideButton.setFont(new Font("Arial", Font.BOLD, 15));
		addSlideButton.addActionListener(this);

		slideInfoPanel.add(slideText);
		slideInfoPanel.add(slideTime);
		slideInfoPanel.add(addSlideButton);

		return slideInfoPanel;
	}

	/**
	 * Creates and returns a JPanel to display the slides.
	 * 
	 * @return displayPanel
	 */
	private JPanel createDisplayPanel() {

		displayPanel = new JPanel(new FlowLayout());

		// a JTextField to display the slides
		slideDisplay = new JTextField("Slideshow Appears Here!");
		slideDisplay.setEditable(false);
		slideDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		slideDisplay.setPreferredSize(new Dimension(600, 400));
		slideDisplay.setHorizontalAlignment(JTextField.CENTER);
		slideDisplay.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));

		// a JButton for the user to start the slide show
		startButton = new JButton("Start the Show");
		startButton.setPreferredSize(new Dimension(130, 65));
		startButton.setFont(new Font("Arial", Font.BOLD, 15));
		startButton.addActionListener(this);

		displayPanel.add(slideDisplay);
		displayPanel.add(startButton);

		return displayPanel;
	}

	/**
	 * Creates and returns a JPanel used for displaying reminder messages.
	 * 
	 * @return a JPanel used for displaying reminder messages
	 */
	private JPanel createMessagePanel() {
		messagePanel = new JPanel();
		message = new JLabel();
		message.setFont(new Font("Arial", Font.BOLD, 15));
		messagePanel.add(message);
		return messagePanel;
	}

	/**
	 * Checks if there is a Slide in the queue that has the same time (order) for
	 * display.
	 * 
	 * @param time
	 * @return true if there exists a Slide that has the same time for display
	 */
	private boolean hasSameDisplayTime(Integer time) {
		// Checks every Slide in the queue
		for (int i = 0; i < slides.getPQSize(); i++) {
			if (slides.getSlidesQueue().getElement(i).getSlideTime().compareTo(time) == 0) {
				return true;
			}
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		// if the user wants to add a Slide
		if (e.getSource() == addSlideButton) {
			// first check if the user has entered anything to display
			if (slideText.getText().equals("")) {
				message.setText("Please enter some text for the slide you want to display.");
			// the slide has the text
			// check if the slide has a valid time
			} else {
				// check if the user has entered anything
				if (slideTime.getText().equals("")) {
					message.setText("Please enter a time for the slide you want to display.");
				// the user has entered something
				} else {
					try {
						Integer time = Integer.parseInt(slideTime.getText());
						// if the time entered is not in the required range
						if (time > 10 || time < 1) {
							message.setText("The time for displaying the slide can only be a number between 1 and 10.");
						// if there is a Slide that has the same time to display
						} else if (hasSameDisplayTime(time)) {
							message.setText("The time you entered has been scheduled to display another slide.\n"
					                + "Please enter a different time.");
						// Valid information
						} else {
							String text = slideText.getText();
							// add the slide to the queue
							slides.addSlide(text, time);
							message.setText("The slide has been added to the slideshow.\n"
									+ "Please add another slide or start the show.");
							if (slides.getPQSize() == 10) {
								message.setText("You have already provided 10 slides. Please start the show now.\n"
										+ "Enjoy!");
							}
							slideText.setText("");
							slideTime.setText("");
						}
					// the user has entered something other than a number
					} catch (NumberFormatException nfe) {
						message.setText("Please enter a valid number (1-10) for the time.");
					}
				}
			}
		// if the user wants to start the show
		} else if (e.getSource() == startButton) {
			message.setText("");
			// sets up the timer (delay is 1 second)
			timer = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// keeps track of the time since the start of the slide show
					time += 1;
					try {
						// displays the slide at the specified time
						if (time == (int) slides.peekSlide().getSlideTime()) {
							slideDisplay.setText(slides.displaySlide().getSlideText());
						}
					// when reaching the end of the slide show (no slides any more)
					} catch (NullPointerException npe) {
						// stops the timer
						timer.stop();
						// restores the display
						slideDisplay.setText("Slideshow Appears Here!");
						// resets the time
						time = 0;
					}
				}
			});
			// starts the timer
			timer.start();
		}
	}
}
