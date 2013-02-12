package combat;

import javax.swing.*;

public class ImageStateS extends ImageState {

	public ImageStateS(DirectionalImage parent) {
		super(parent);
		myImage = new ImageIcon("S.jpg");
		dir = DirectionalImage.South;
	}
	
	@Override
	public void turnLeft() {
		parent.setState(new ImageStateSE(parent));

	}

	@Override
	public void turnRight() {
		parent.setState(new ImageStateSW(parent));

	}

	@Override
	public void moveForward() {
		parent.translate(0, step);

	}

	@Override
	public void moveBackward() {
		parent.translate(0, -step);

	}

}
