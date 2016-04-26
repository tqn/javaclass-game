import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {

	private static final long serialVersionUID = 469989049178129651L;

	protected static final int LASER_COOLDOWN = 500;

	public ArrayDeque<WorldObject> objects;

	private Context ctx;

	private WorldObject cannon;

	private boolean cmdLeft;
	private boolean cmdRight;

	private long timeLastShot;

	private Direction direction;
	
	private String word = "";
	private BufferedReader reader;
	private int [] wordCoordinates = {600, 0};

	Game() throws IOException {
		cmdLeft = false;
		cmdRight = false;
		timeLastShot = 0;
		direction = Direction.NEUTRAL;

		objects = new ArrayDeque<>();
		cannon = new WorldObject();

		objects.add(cannon);

		//cannon.addShape(new Rectangle2D.Double(30, 40, 100, 20));
		cannon.addShape(new Rectangle2D.Double(40, -30, 20, 100)).color(
				new Color(0x60, 0x7D, 0x8B));

		Double[] center = cannon.addShape(new Ellipse2D.Double(0, 0, 100, 100)).color(new Color(0x9E, 0x9E, 0x9E))
				.center();

		cannon.setCenter(center);

		cannon.getTransform().translate(590, 660);
		
		reader = new BufferedReader(new FileReader("/usr/share/dict/words"));
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		objects.forEach((o) -> {
			o.render(g);
		});

		g.drawString(word, wordCoordinates[0], wordCoordinates[1]);
	}

	public void tick() {
		// decay laser beam
		objects.removeIf(o -> o instanceof LaserBeam && ((LaserBeam) o).dead());

		if (direction == Direction.LEFT) {
			cannon.rotate(-Math.PI/ Context.TICK);
		} else if (direction == Direction.RIGHT) {
			cannon.rotate(Math.PI/ Context.TICK);
		}

		if (this.isVisible()) {
			repaint();
		}
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// if (!this.isVisible()) {
		// return;
		// }
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.isVisible()) {
			return;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			cmdLeft = true;
			direction = Direction.LEFT;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			cmdRight = true;
			direction = Direction.RIGHT;
			break;
		case KeyEvent.VK_SPACE:
			if (System.currentTimeMillis() - timeLastShot > LASER_COOLDOWN) {
				Double[] center = cannon.getCenter();
				// Don't mutate center
				Double[] position = { center[0] + cannon.getTransform().getTranslateX(),
						center[1] + cannon.getTransform().getTranslateY() };

				objects.addFirst(new LaserBeam(cannon.getRotation() - Math.PI / 2, position));
				timeLastShot = System.currentTimeMillis();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!this.isVisible()) {
			return;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			cmdLeft = false;
			direction = cmdRight ? Direction.RIGHT : Direction.NEUTRAL;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			cmdRight = false;
			direction = cmdLeft ? Direction.LEFT : Direction.NEUTRAL;
			break;
		}
	}
	
	public void newWord() throws IOException{
		word = reader.readLine();
	}
	public void updateWord(){
		wordCoordinates[1]+=5;
	}

	private enum Direction {
		RIGHT, NEUTRAL, LEFT
	}
}