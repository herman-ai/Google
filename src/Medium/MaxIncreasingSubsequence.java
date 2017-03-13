package Medium;

public class MaxIncreasingSubsequence {
	
	private static int binarySearch(int[] s, int len, int val) {
		int lo = 0, hi = len, mid = 0;
		while (lo <= hi) {
			mid = (lo + hi) / 2;
			if(val < s[mid]) {
				if (mid == 0 || val > s[mid - 1]) {
					break;
				}
				hi = mid - 1;
			} else if (val > s[mid]) {
				lo = mid + 1;
			}
		}
		return mid;
	}
	
	private static int lengthOfLongestIncreasingSubsequence(int[] array) {
		int []s = new int[array.length];
		
		for (int i = 0; i < array.length; i++) {
			s[i] = -1;
		}
		int l = 0, j = 0;
		s[j] = array[0];
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] > s[j]) {
				j++;
				l++;
				s[j] = array[i];
			} else {
				//find position in s to put the new element
				int idx = binarySearch(s, l, array[i]);
				s[idx] = array[i];
			}
		}
		return l+1;
	}

	public static void main(String[] args) {
		int[] a = {2, 6, 3, 4, 1, 2, 9, 5, 8};
		System.out.println("" + lengthOfLongestIncreasingSubsequence(a));

	}

}
