
public class Queue {
	
	private Node front;
	private Node back;
	private int count; 
	
	public Queue() {
		front = back = null; 
	}
	
	public void enQ(Object x) {
		if (front == null) {
			back = front = new Node (x,back); 
		}
		
		else {back = back.next = new Node(x, front);}
		count = count + 1; 
		
	}
	
	public Object deQ( ) {
		if (front == null) {
		throw new IllegalArgumentException("no!"); 
		}
		if (back == front) {
			back.next = null; 
		}
		Object oldfront = front.data; 
		front = front.next; 
		return oldfront;
		}
	
	public void makeEmpty () {
		front = null; 
		back = null; 
	}
	
	public boolean isEmpty () {
		if (front == null) {
			return true;
		}
		return false; 
	}
	
	public String get_front () {
		return front.data.toString(); 
	}
	

}
