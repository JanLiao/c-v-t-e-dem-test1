package tmp;

public class ValidateData {

	private String id;
	
	private String name;
	
	private String imgOldName;
	
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgOldName() {
		return imgOldName;
	}

	public void setImgOldName(String imgOldName) {
		this.imgOldName = imgOldName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ValidateData(String id, String name, String imgOldName, String type) {
		super();
		this.id = id;
		this.name = name;
		this.imgOldName = imgOldName;
		this.type = type;
	}

	public ValidateData() {
		super();
	}
}
