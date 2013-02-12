package combat;

import javax.swing.*;

public class ImageStateN extends ImageState {

	public ImageStateN(DirectionalImage parent) {
		super(parent);
		myImage = new ImageIcon("N.jpg");
		dir = DirectionalImage.North;
	}
	@Override
	public void turnLeft() {
		parent.setState(new ImageStateNW(parent));
	}

	@Override
	public void turnRight() {
		parent.setState(new ImageStateNE(parent));
	}

	@Override
	public void moveForward() {
		parent.translate(0, -step);
	}

	@Override
	public void moveBackward() {
		parent.translate(0, step);
	}

}
