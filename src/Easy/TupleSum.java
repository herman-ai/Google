package Easy;

import java.util.LinkedList;
import java.util.List;

public class TupleSum {
	
	    public static void tuple_sum(Integer[] a, Integer s) {
	    	List<Integer> empty = new LinkedList<Integer>();
			try {
				fun(a, s, empty);
			} catch(RuntimeException e) {
				//do nothing
			}

	    }
	    
	    public static void fun(Integer[] a, Integer s, List<Integer> cIndeces) {
	    	List<Integer> currentIndeces = new LinkedList<>(cIndeces);
	    	if(currentIndeces.size() == 3) {
	    		for(int i = 0; i< a.length ;i++) {
	    			if(currentIndeces.contains(i)) {
	    				continue;
	    			}
	    			if(a[i] == s) {
	    				for(int k = 0; k < currentIndeces.size(); k++) {
	    					System.out.println(currentIndeces.get(k));
	    				}
	    				System.out.println(i);
	    				throw new RuntimeException();
	    			}
	    		}
	    		
	    	}

	    	for(int i = 0; i<a.length; i++) {
	    		if(currentIndeces.contains(i)) {
	    			continue;
	    		}
    			currentIndeces.add(i);
    			fun( a, s-a[i], currentIndeces);
    			currentIndeces.remove(new Integer(i));
	        }
	    }


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] a = {3,2,1,4,5,7,6,9,7,8};
		Integer s = 30;
		 
			tuple_sum(a, s);
	}

}
