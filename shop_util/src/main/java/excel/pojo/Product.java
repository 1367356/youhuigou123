package excel.pojo;

public class Product {

	String id;
	String name;
	String image;
	String category;
	String price;
	String sale;
	String platform;
	String disSum;
	String disSize;
	String startTime;
	String endTime;
	String disherf;
	
	public Product(String id, String name, String image, String category, String price, String sale, String platform,
			String disSum, String disSize, String startTime, String endTime, String disherf) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.category = category;
		this.price = price;
		this.sale = sale;
		this.platform = platform;
		this.disSum = disSum;
		this.disSize = disSize;
		this.startTime = startTime;
		this.endTime = endTime;
		this.disherf = disherf;
	}
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getDisSum() {
		return disSum;
	}
	public void setDisSum(String disSum) {
		this.disSum = disSum;
	}
	public String getDisSize() {
		return disSize;
	}
	public void setDisSize(String disSize) {
		this.disSize = disSize;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDisherf() {
		return disherf;
	}
	public void setDisherf(String disherf) {
		this.disherf = disherf;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", category=" + category + ", price="
				+ price + ", sale=" + sale + ", platform=" + platform + ", disSum=" + disSum + ", disSize=" + disSize
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", disherf=" + disherf + "]";
	}
	
}
