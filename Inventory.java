import java.text.DecimalFormat;


public class Inventory {
	
	private static Stack_Array inventory = new Stack_Array(); 
	private static int total_widgets = 0; 
	
	public void received (String amount, String price) {
		Widget added = new Widget (amount,price); 
		System.out.println("Received: " +amount + " $" + price);
		inventory.push(added); 
		total_widgets = total_widgets + Integer.parseInt(amount); 
	}
	
	
	public void order (String x) { //processes and prints order transactions
		int ordered = Integer.parseInt(x); 
		Widget temp = inventory.pop(); 
		calculate_customer(ordered,temp.price); //prints customer bill
		inventory.push(temp);
		calculate_bookeeper(ordered); //prints bookeeper bill
		
	}
	
	public static boolean notEnough (String x) { //tests to see if there is enough for an order
		int order = Integer.parseInt(x); 
		if (total_widgets < order) {
			return true; 
		}
		return false; 
	}
	
	public static void calculate_customer(int x, float y) { //prints customer bill
		System.out.println("Amount ordered: " + x); 
		float new_price = ((40*y)/100) + y;
		float bill = new_price*x; 
		System.out.println("Customer Bill: " + x + " @ $" + format(new_price) + " = $" + format(bill) + "\n"); 
	}
	
	public static String format (float x) { //makes sure there are two decimal places
		DecimalFormat df = new DecimalFormat("#.00"); 
		String number =String.valueOf(df.format(x));
		return number; 
	}
	
	public static void calculate_bookeeper(int ordered) { //prints and calculates bookeeper bill
		
		String Bookeeper_bill = ""; 
		float Bookeeper_cost = 0; 
		
		Widget temp = inventory.pop(); //the top values

		if (temp.amount > ordered) {
			Bookeeper_bill = ordered + " @ $" + format(temp.price) + " = $" + format(ordered*temp.price);
			total_widgets = total_widgets - ordered;
			Bookeeper_cost = ordered*temp.price;
			
			temp.amount = temp.amount-ordered;
			inventory.push(temp); 
		}
		
		else {
			int sum = 0; 
			while (sum < ordered) {
				sum = sum + temp.amount;
				if (sum > ordered) {
					float total = (temp.amount-(sum-ordered))*temp.price;
					Bookeeper_bill = Bookeeper_bill + "\n" + (format(temp.amount-(sum-ordered))) + " @ $" + format(temp.price) + " = $" + format(total);
					Bookeeper_cost = Bookeeper_cost + ((temp.amount-(sum-ordered))*temp.price);
					total_widgets = total_widgets - (ordered);
					temp.amount = sum-ordered;
					inventory.push(temp);  
					break;
				}
				Bookeeper_bill = Bookeeper_bill + "\n" + format(temp.amount) + " @ $" + temp.price + " = $" + format(temp.amount*temp.price);  
				Bookeeper_cost = Bookeeper_cost + (temp.amount*temp.price);
				temp = inventory.pop();
				
			}
		}
		System.out.println("Actual Cost: " +Bookeeper_bill); 
		System.out.println("Total Cost: $" + format(Bookeeper_cost) + "\n"); 
		System.out.println("InStore: " + total_widgets + "\n"); 
	}

	public void process_backorder (String x) { //takes care of backorder 
		int ordered = Integer.parseInt(x); 
		Widget temp = inventory.pop();
		Widget temp2 = inventory.pop(); 
		calculate_customer(ordered,temp2.price);
		inventory.push(temp2); 
		inventory.push(temp);
		calculate_bookeeper(ordered); 
	}

}
