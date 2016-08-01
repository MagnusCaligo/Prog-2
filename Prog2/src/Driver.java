
public class Driver {

	public Driver(){
		OrderedListPriorityQueue<Tester> array = new OrderedListPriorityQueue<Tester>();
		for(int i = 0; i < 10; i++){
			array.insert(new Tester());
		}
		Tester t = new Tester();
		t.val = 42;
		array.insert(t);
		do{
			System.out.println(array);
			array.remove();
		}while(array.size() > 0);
		
		
	}
	
	
	public static void main(String args[]){
		new Driver();
	}
	
	class Tester implements Comparable<Tester>{
		public int val;
		
		public Tester(){
			val = (int) ((Math.random()*100) + 10);
		}

		@Override
		public int compareTo(Tester obj) {
			return val - obj.val;
		}
		
		public String toString(){
			return Integer.toString(val);
		}
	}
}
