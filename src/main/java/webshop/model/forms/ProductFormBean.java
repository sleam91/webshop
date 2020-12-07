package webshop.model.forms;

public class ProductFormBean {

    private String name;
    private double price;
    private String img;
    private String category;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public String getImg() {
	return img;
    }

    public void setImg(String img) {
	this.img = img;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

}
