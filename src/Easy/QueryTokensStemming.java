package Easy;

public class QueryTokensStemming {

	public static void token_stemming(String[] tokens, String[] suffixes) {
		for(String token:tokens) {
			String suffixRemovedToken = token;
			for(String suffix:suffixes) {
				String newSuffixRemovedToken = removeSuffix(token, suffix);
				if(newSuffixRemovedToken.length() < suffixRemovedToken.length()) {
					suffixRemovedToken = newSuffixRemovedToken;
				}					
			}
			System.out.println(suffixRemovedToken);
		}
    }
	
	//Removes suffix if possible
	public static String removeSuffix(String word, String suffix) {
		if(suffix.length() > word.length()) {
			return word;
		}
		boolean match = true;
		for(int i = 1; i <= suffix.length(); i++) {
			if(word.charAt(word.length() - i) != suffix.charAt(suffix.length() - i)) {
				match = false;
				break;
			}
		}
		String returnVal = word;
		if(match) {
			returnVal = word.substring(0, word.length() - suffix.length());
		}
		return returnVal;
	}
	
	public static void main(String[] args) {
		
		String[] tokens = {"10","times","a","year","IEN","Italia","provides","a","digest","of","the","latest","products","news","and","technologies","available","on","the","Italian","market","In","2009","nearly","14","000","subscribers","received","IEN","Italia","mostly","engineers","and","purchasing","managers","IEN","Italia","also","publishes","newsletters","and","updates","its","website","with","daily","news","about","new","products","and","services","available","to","the","Italian","market"};
		String[] suffixes = {"es","a","est","le","n","e","09","rly","ved","lia","rs","ers","N","ia","so","s","ters","nd","th","ws","w","ts","d"};
		token_stemming(tokens, suffixes);
	}

}
