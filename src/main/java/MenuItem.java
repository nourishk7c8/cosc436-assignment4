/**
 * @author Nourish Khan (nkhan2@students.towson.edu)
 *
 */

public class MenuItem {
	private String itemName;
	private int category;
	private boolean heartHealthy;
	private double price;
	
	public MenuItem(String itemName, int category, boolean heartHealthy, double price){
		this.itemName = itemName;
		this.category = category;
		this.heartHealthy = heartHealthy;
		this.price = price;
	}//end values constructor
	
	public String getItemName(){
		return itemName;
	}
	
	public int getCategory(){
		return category;
	}
	
	public boolean getHeartHealthy(){
		return heartHealthy;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setItemName(String itemName){
		this.itemName = itemName;
	}
	
	public void setCategory(int category){
		this.category = category;;
	}
	
	public void setHeartHealthy(boolean heartHealthy){
		this.heartHealthy = heartHealthy;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public String toString(){
		return "Item: " + itemName + "\nCategory: " + category + "\nHeart Healthy: " + heartHealthy + "\nPrice: " + price;
	}
	
	

}//end class
