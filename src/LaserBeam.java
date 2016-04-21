import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class LaserBeam extends WorldObject {
	public LaserBeam(int radians) {
		addShape(new Rectangle2D.Double(635, 0, 10, 720)).color(Color.GREEN);
		transform.rotate(radians);
	}
}
