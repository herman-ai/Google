package Hard;

public class Selection {

	public static void select_numbers(Integer[] v, Integer k) {
		for(int i = 0; i < k; i ++) {
			for(int j = v.length - 1; j > i ; j --) {
				if(v[j] < v[j-1]) {
					int temp = v[j];
					v[j] = v[j-1];
					v[j-1] = temp;
				}
			}
		}
		for(int i = 0; i < k; i ++) {
			System.out.print(v[i] + " ");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] v = {9,1,3,7,2,2};
		Integer k = 3;
		select_numbers(v, k );
	}

}
