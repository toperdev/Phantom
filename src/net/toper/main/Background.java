package net.toper.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {

	float x, y;
	float offX, offY;
	Image bg;

	public Background() {
		try {
			bg = new Image("res/test.jpg");
		} catch (SlickException e){
			e.printStackTrace();
		}
		x = bg.getTextureWidth() / 2;
		y = bg.getTextureWidth() / 2;
	}

	public void update(Player p, GameContainer gc) {
		y += p.getAccelY();
		x += p.getAccelX();
	}

	public void render(Render r, Graphics g) {
		r.drawImage(bg, -x, -y, g);
	}
}
