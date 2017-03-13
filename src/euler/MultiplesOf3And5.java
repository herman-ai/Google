package euler;

public class MultiplesOf3And5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int limit = Integer.parseInt(args[0]);
		int sum = 0;
		for(int i = 1; i<limit; i++) {
			if((i%5 == 0)||(i%3 == 0)) {
				sum += i;
			}
		}
		System.out.println(sum);

	}

}
