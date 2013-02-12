package combat;

import javax.swing.*;

public abstract class ImageState {
	/**
	 * The DirectionalImage that this represents the State for
	 */
	protected DirectionalImage parent;
	
	/**
	 * The distance to travel each tick
	 */
	protected int step;
	
	/**
	 * The sprite image that relates to this directional state 
	 */
	protected ImageIcon myImage;

	/**
	 * The integer representing the faced direction
	 */
	protected int dir;
	
	
	
	public ImageState(DirectionalImage parent) {
		this.parent = parent;
		step = 6;
	}
	public abstract void turnLeft();
	public abstract void turnRight();
	public abstract void moveForward();
	public abstract void moveBackward();
	
	/**
	 * gets the ImageIcon for this directional state
	 * @return the ImageIcon
	 */
	public ImageIcon getImageIcon() {
		return myImage;
	}
	
	/**
	 * Gets the direction we are facing in terms of the numbers specified in DirectionalImage
	 */
	public int getDirection() {
		return dir;
	}
}
