package combat;

import javax.swing.*;

public class ImageStateNE extends ImageState {

	public ImageStateNE(DirectionalImage parent) {
		super(parent);
		myImage = new ImageIcon("NE.jpg");
		dir = DirectionalImage.NorthEast;
	}
	@Override
	public void turnLeft() {
		parent.setState(new ImageStateN(parent));
	}

	@Override
	public void turnRight() {
		parent.setState(new ImageStateE(parent));
	}

	@Override
	public void moveForward() {
		parent.translate(step, -step);
	}

	@Override
	public void moveBackward() {
		parent.translate(-step, step);
	}

}
