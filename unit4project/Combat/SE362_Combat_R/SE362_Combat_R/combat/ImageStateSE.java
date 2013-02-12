package combat;

import javax.swing.*;

public class ImageStateSE extends ImageState {
	
	public ImageStateSE(DirectionalImage parent) {
		super(parent);
		myImage = new ImageIcon("SE.jpg");
		dir = DirectionalImage.SouthEast;
	}
	@Override
	public void turnLeft() {
		parent.setState(new ImageStateE(parent));

	}

	@Override
	public void turnRight() {
		parent.setState(new ImageStateS(parent));

	}

	@Override
	public void moveForward() {
		parent.translate(step, step);

	}

	@Override
	public void moveBackward() {
		parent.translate(-step,-step);

	}

}
