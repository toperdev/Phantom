package net.toper.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {

	Render r = new Render();
	Player p;
	Background bg;

	public Main() {
		super("game");
	}

	public static void main(String[] arguments) {
		try {
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(1600, 900, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void init(GameContainer container) throws SlickException {
		bg = new Background();
		p = new Player();
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		p.update(gc, delta);
		bg.update(p, gc);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		bg.render(r, g);
		p.render(r, g, gc);
	}
}