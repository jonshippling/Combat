package combat;

import javax.swing.*;

public class ImageStateW extends ImageState {
	
	public ImageStateW(DirectionalImage parent) {
		super(parent);
		myImage = new ImageIcon("W.jpg");
		dir = DirectionalImage.West;
	}
	@Override
	public void turnLeft() {
		parent.setState(new ImageStateSW(parent));

	}

	@Override
	public void turnRight() {
		parent.setState(new ImageStateNW(parent));

	}

	@Override
	public void moveForward() {
		parent.translate(-step, 0);
	}

	@Override
	public void moveBackward() {
		parent.translate(step, 0);
	}

}
