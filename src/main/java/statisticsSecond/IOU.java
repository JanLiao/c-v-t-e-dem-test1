package statisticsSecond;

public class IOU {

	private int id;
	private String name;
	private double discIOU;
	private double cupIOU;
	private String amdXY;
	private String oldName;
	private double foveaX;
	private double foveaY;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDiscIOU() {
		return discIOU;
	}
	public void setDiscIOU(double discIOU) {
		this.discIOU = discIOU;
	}
	public double getCupIOU() {
		return cupIOU;
	}
	public void setCupIOU(double cupIOU) {
		this.cupIOU = cupIOU;
	}
	public String getAmdXY() {
		return amdXY;
	}
	public void setAmdXY(String amdXY) {
		this.amdXY = amdXY;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public double getFoveaX() {
		return foveaX;
	}
	public void setFoveaX(double foveaX) {
		this.foveaX = foveaX;
	}
	public double getFoveaY() {
		return foveaY;
	}
	public void setFoveaY(double foveaY) {
		this.foveaY = foveaY;
	}
	public IOU(int id, String name, double discIOU, double cupIOU) {
		super();
		this.id = id;
		this.name = name;
		this.discIOU = discIOU;
		this.cupIOU = cupIOU;
	}
	public IOU(int id, String name, double discIOU, double cupIOU, double foveaX, double foveaY) {
		super();
		this.id = id;
		this.name = name;
		this.discIOU = discIOU;
		this.cupIOU = cupIOU;
		this.foveaX = foveaX;
		this.foveaY = foveaY;
	}
	public IOU(int id, String name, String oldName, double discIOU, double cupIOU, String amdXY) {
		super();
		this.id = id;
		this.name = name;
		this.discIOU = discIOU;
		this.cupIOU = cupIOU;
		this.amdXY = amdXY;
		this.oldName = oldName;
	}
	public IOU() {
		super();
	}
	@Override
	public String toString() {
		return "IOU [id=" + id + ", name=" + name + ", discIOU=" + discIOU + ", cupIOU=" + cupIOU + ", amdXY=" + amdXY
				+ "]";
	}
	
	
}
