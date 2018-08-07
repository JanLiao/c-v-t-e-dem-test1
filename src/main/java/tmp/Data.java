package tmp;

public class Data {

	private int id;
	private String ImgName;
	private int label;
	private double x;
	private double y;
	private String oldName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImgName() {
		return ImgName;
	}
	public void setImgName(String imgName) {
		ImgName = imgName;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public Data(int id, String imgName, int label, double x, double y) {
		super();
		this.id = id;
		ImgName = imgName;
		this.label = label;
		this.x = x;
		this.y = y;
	}
	public Data() {
		super();
	}
	
}
