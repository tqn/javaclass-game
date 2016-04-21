import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class LaserBeam extends WorldObject {
	public LaserBeam() {
		this(0);
	}
	
	public LaserBeam(double radians) {
		this(radians, ORIGIN);
	}
	
	public LaserBeam(double radians, Double[] center) {
		super(new Rectangle2D.Double(635, 0, 10, 720));
		setColor(lastShape(), Color.GREEN);
		transform.rotate(radians);
	}
}
