package statistics;
/** 
* @author: jan 
* @date: 2018年5月24日 上午10:57:33 
*/
public class CircleData extends BaseData {

	private double radius;
	private double centerX;
	private double centerY;
	private String name;

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CircleData(String name, double radius, double centerX, double centerY) {
		super();
		this.name = name;
		this.radius = radius;
		this.centerX = centerX;
		this.centerY = centerY;
	}

	public CircleData() {
		super();
	}

	@Override
	public String toString() {
		return "CircleData [radius=" + radius + ", centerX=" + centerX + ", centerY=" + centerY + ", name=" + name
				+ ", getLeft()=" + getLeft() + ", getTop()=" + getTop() + ", getAngle()=" + getAngle()
				+ ", getScaleX()=" + getScaleX() + ", getScaleY()=" + getScaleY() + ", getStroke()=" + getStroke()
				+ ", getStrokeWidth()=" + getStrokeWidth() + ", getOpacity()=" + getOpacity() + ", getWidth()="
				+ getWidth() + ", getHeight()=" + getHeight() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}


}
