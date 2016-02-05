import java.util.StringTokenizer;


public class Driver {
	
	public static void main (String [] args) {
		Inventory inven = new Inventory(); 
		BackOrder delayed_orders = new BackOrder();
		process(inven, "transactions.txt",delayed_orders); 
	}
	
	public static void process (Inventory in, String filename, BackOrder backorder) { //reads file
		TextFileInput tfi = new TextFileInput(filename);
		String line =tfi.readLine(); 
		//System.out.println(line);
		
		while (line != null) {
			StringTokenizer st = new StringTokenizer(line); 
				while (st.hasMoreTokens()) {
					String type = st.nextToken(); 
					if (type.equals("R")) {
						in.received(st.nextToken(), st.nextToken());
						
						if ((!backorder.isEmpty()) && !in.notEnough(backorder.first_order())) {
							in.process_backorder(backorder.release_order()); 
						}	
					}
					else {
						if (type.equals("S")) {
							String number = st.nextToken();

							if (in.notEnough(number) || !backorder.isEmpty()) {
								backorder.store_order(number); 
							}
							
							else {
									in.order(number);
							}
						}
					}
				}
			line = tfi.readLine(); 
		}
	}

}
