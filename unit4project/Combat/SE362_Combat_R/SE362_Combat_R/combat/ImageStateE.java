package combat;

import javax.swing.*;

public class ImageStateE extends ImageState {
	
	public ImageStateE(DirectionalImage parent) {
		super(parent);
		myImage = new ImageIcon("E.jpg");
	}
	
	@Override
	public void turnLeft() {
		parent.setState(new ImageStateNE(parent));
	}

	@Override
	public void turnRight() {
		parent.setState(new ImageStateSE(parent));

	}

	@Override
	public void moveForward() {
		parent.translate(step, 0);

	}

	@Override
	public void moveBackward() {
		parent.translate(-step, 0);
	}
	


}
