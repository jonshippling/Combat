package combat;

import javax.swing.*;

public class ImageStateSW extends ImageState {

	public ImageStateSW(DirectionalImage parent) {
		super(parent);
		myImage = new ImageIcon("SW.jpg");
		dir = DirectionalImage.SouthWest;
	}
	
	@Override
	public void turnLeft() {
		parent.setState(new ImageStateS(parent));

	}

	@Override
	public void turnRight() {
		parent.setState(new ImageStateW(parent));

	}

	@Override
	public void moveForward() {
		parent.translate(-step, step);

	}

	@Override
	public void moveBackward() {
		parent.translate(step, -step);

	}

}
