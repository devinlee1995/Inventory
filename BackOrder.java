
public class BackOrder {
	private static Queue backorder = new Queue(); 
	
	public void store_order(String x) {
		 backorder.enQ(x);
		 System.out.println("Stored order: " + x + "\n");	
	}
	
	public String release_order() {
		return backorder.deQ().toString(); 
	}
	
	public boolean isEmpty () {
		return backorder.isEmpty(); 
	}
	
	public String first_order() {
		return backorder.get_front(); 
	}
}
