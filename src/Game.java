import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener {

	private static final long serialVersionUID = 469989049178129651L;

	public ArrayList<WorldObject> objects;

	private static final double SPEED = 50;

	private Context ctx;

	private WorldObject cannon;

	Game() {
		addKeyListener(this);
		objects = new ArrayList<>();
		cannon = new WorldObject();

		cannon.getShapes().push(new Ellipse2D.Double(0, 0, 100, 100));
		cannon.setColor(cannon.getShapes().getLast(), new Color(0x9E, 0x9E, 0x9E));

		cannon.getShapes().push(new Rectangle2D.Double(30, 40, 100, 20));
		cannon.setColor(cannon.getShapes().getLast(), new Color(0x60, 0x7D, 0x8B));

		cannon.getTransform().translate(100, 100);
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawString("Time: " + System.currentTimeMillis(), 100, 100);

		objects.forEach((o) -> {
			o.render(g);
		});
	}

	public void tick() {
		cannon.getTransform().translate(10.0 / Context.TICK, 0);
		
		if (this.isVisible()) {
			System.out.println("visible");
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
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:

			break;
		case KeyEvent.VK_A:

			break;
		case KeyEvent.VK_S:

			break;
		case KeyEvent.VK_D:

			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}