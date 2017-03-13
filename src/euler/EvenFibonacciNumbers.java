package euler;

public class EvenFibonacciNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int p1 = 1;
		int p2 = 2;
		int c = p1 + p2;
		int sum = 0;
		
		for(; c<=4000000; p1 = p2, p2 = c, c = p1 + p2) {
			if(c % 2 == 0) {
				sum += c;
			}
		}
		System.out.println(sum);
	}

}
