package combat;

import javax.swing.*;

public class ImageStateNW extends ImageState {
	
	public ImageStateNW(DirectionalImage parent) {
		super(parent);
		myImage = new ImageIcon("NW.jpg");
		dir = DirectionalImage.NorthWest;
	}
	
	@Override
	public void turnLeft() {
		parent.setState(new ImageStateW(parent));
	}

	@Override
	public void turnRight() {
		parent.setState(new ImageStateN(parent));

	}

	@Override
	public void moveForward() {
		parent.translate(-step, -step);
	}

	@Override
	public void moveBackward() {
		parent.translate(step, step);
	}

}
