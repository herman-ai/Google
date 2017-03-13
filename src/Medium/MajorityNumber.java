package Medium;

public class MajorityNumber {


    public static void majority(Integer[] v) {
    	int currentMajorityNumber = v[0];
    	int currentMajorityCount = 1;
    	
    	for(int i = 1; i < v.length; i++) {
    		if(v[i] == currentMajorityNumber) {
    			currentMajorityCount++;
    		} else {
    			if(currentMajorityCount == 0) {
    				currentMajorityNumber = v[i];
    			} else {
    				currentMajorityCount--;
    			}
    		}
    	}
    	if(currentMajorityCount > 0){
    		System.out.println(currentMajorityNumber);
    	} else {
    		System.out.println("None");
    	}
    }
	
	
	public static void main(String[] args) {
		Integer[] integers = {1, 2, 2, 3};
		majority(integers );
	}

}
