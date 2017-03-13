package Medium;
/**
 * 
 */

/**
 * @author hermansahota
 *
 */
public class RelativeSort {
	
	public static void relative_sort(Integer[] v) {
        int end = v.length - 1;
        int begin = -1;
        
        for(int i = 0; i < v.length; i++ ){
        	if(v[i]>0){
        		break;
        	}
        	if(v[i] < 0) {
        		begin = i;
        		continue;
        	}
        }
        begin++;
        
        //System.out.println(begin);
        int indexToMoveToBeginning = end;
        int ctr = 0;
        while(end > begin){
            //find the next negative element from the end
            int i;

            boolean found = false;
            for(i = end; i > (begin + ctr); i--) {
                if(v[i] < 0) {
                    indexToMoveToBeginning = i;
                    found = true;
                    break;
                }
            }
            if(!found){
            	break;
            }
        
            int temp = v[indexToMoveToBeginning];
            
            for(i = indexToMoveToBeginning; i > begin; i--){
                v[i] = v[i-1];
            }
            v[begin] = temp;
//            begin++;
            end = indexToMoveToBeginning;
            ctr++;
        }
        for(int i:v){
            System.out.print(i+" ");
        }
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] array = {2,-5,6,-1,-4,3};
		relative_sort(array );
	}

}


