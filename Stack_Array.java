
public class Stack_Array { //Last in, first out
	
	private static Widget [] data = new Widget [10]; 
	private static int top = -1; 
	
	//best time, theta(1). Worst case, theta(n) if you run out of room. average case, theta(1). OVERALL
	//IT IS O(n) 
	public void push (Widget x) {
		//when you run out of spaces for array, you make another array that is 2x larger and copy over
		if (top == data.length-1) {
			Widget [] newData = new Widget[2*data.length]; 
				for (int i = 0; i <= top; i++) {
					newData[i] = data[i]; 
				}
				data = newData; 
		}
		data[++top] = x;

	}
	
	public static Widget pop () {
		if (isEmpty()) throw new IllegalArgumentException("No!");
		//System.out.println("Popped: "+ data[top].amount + " " + data[top].price); 
		return data[top--];   
	}
	
	public static boolean isEmpty () {
		return (top == -1); 
	}
	
	public void makeEmpty() {//array still has junk BUT will get rid of stuff eventually 
		top = -1; 
	}
	

	
}
