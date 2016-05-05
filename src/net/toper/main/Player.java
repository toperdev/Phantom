package net.toper.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player {

	Image carImg;
	float carX;
	float carY;
	float carDeltaX = 50;
	float carDeltaY = 300;

	float rotAccel = 0.0001f;
	float rotChangeL = 0.0f;
	float rotChangeR = 0.0f;
	float maxRotSpeed = 0.5f;
	float rot = 1;
	float oldRot;

	float accel = 0.00075f;
	float moveSpeed = 0;
	float stopSpeed = 0.00075f;
	float maxSpeed = 1.25f;

	float delta;

	public Player() {
		try {
			carImg = new Image("res/car.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void update(GameContainer gc, int delta) {
		Input input = gc.getInput();
		this.delta = delta;
		if (!(input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_UP))) {
			decelerate();
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			decelerate();
			decelerate();
			decelerate();

		} else {
			if (input.isKeyDown(Input.KEY_DOWN)) {
				if (moveSpeed < maxSpeed) {
					moveSpeed += accel * delta;
				}
			}
			if (input.isKeyDown(Input.KEY_UP)) {
				if (moveSpeed > -maxSpeed) {
					moveSpeed -= accel * delta;
				}
			}
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			rotChangeL += rotAccel * Math.abs(moveSpeed);
		} else {
			if (rotChangeL > 0.0f)
				rotChangeL -= 0.00075f;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			rotChangeR += rotAccel * Math.abs(moveSpeed);
		} else {
			if (rotChangeR > 0.0f)
				rotChangeR -= 0.00075f;
		}
		float rotChange = rotChangeR - rotChangeL;
		if (rotChange > maxRotSpeed) {
			rotChange = maxRotSpeed;
		}
		if (rotChange < -maxRotSpeed) {
			rotChange = -maxRotSpeed;
		}
		rot -= rotChange;
		carDeltaY = (float) ((moveSpeed * delta) * Math.cos(Math.toRadians(rot)));
		carDeltaX = (float) ((moveSpeed * delta) * Math.sin(Math.toRadians(rot)));
	}

	private void decelerate() {
		if (moveSpeed > 0.0) {
			moveSpeed -= stopSpeed * delta;
		}
		if (moveSpeed < 0.0) {
			moveSpeed += stopSpeed * delta;
		}
	}

	public void render(Render r, Graphics g, GameContainer gc) {
		if (oldRot != rot) {
			carImg.setRotation(-rot);
			oldRot = rot;
		}
		r.drawImage(carImg, gc.getWidth() / 2 - carImg.getWidth() / 2, gc.getHeight() / 2 - carImg.getHeight() / 2, g);
	}

	public float getAccelX() {
		return -carDeltaX;
	}

	public float getAccelY() {
		return -carDeltaY;
	}

	public int getWidth() {
		return carImg.getWidth();
	}

	public int getHeight() {
		return carImg.getHeight();
	}
}
