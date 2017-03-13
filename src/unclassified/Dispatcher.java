package unclassified;

public class Dispatcher {
	
	public static void find_closest_car(String[] city_map, Integer[] cars, Integer customer) {
		java.util.Map<Integer, java.util.Map<Integer, Integer>> adjacencyList = makeMap(city_map);
		
        
		for(int car: cars) {
			int d = distance(car, customer, adjacencyList);
		}
		
    }
    
	private static java.util.Map<Integer, java.util.Map<Integer, Integer>> makeMap(String[] city_map) {
		java.util.Map<Integer, java.util.Map<Integer, Integer>> map = new java.util.HashMap<>();
		for(String street:city_map) {
			String[] tokens = street.split(",");
			Integer from = Integer.parseInt(tokens[0]);
			Integer to = Integer.parseInt(tokens[1]);
			Integer d = Integer.parseInt(tokens[2]);
			
			java.util.Map<Integer, Integer> adjacencyListFromNode = map.get(from);
			
			if(adjacencyListFromNode==null) {
				adjacencyListFromNode = new java.util.HashMap<>();
			}	
			adjacencyListFromNode.put(to, d);
			
			java.util.Map<Integer, Integer> adjacencyListToNode = map.get(to);
			
			if(adjacencyListToNode==null) {
				adjacencyListToNode = new java.util.HashMap<>();
			}	
			adjacencyListToNode.put(from, d);
			
			map.put(from, adjacencyListToNode);
		}
		return map;
	}

	private static int distance(int car, Integer customer, java.util.Map<Integer, java.util.Map<Integer, Integer>> adjacencyList) {
		int d = 0;
		boolean routeFound = false;
		while(!routeFound) {
			for(Entry<Integer, Integer> link: adjacencyList.get(customer).entrySet()) {
				if(link.getKey() == car) {
					return d;
				}
				return d + distance(car, link.getKey(), adjacencyList); 
			}
		}
		return d;
	}

	public static void main(String[] args) {
		
		String[] city_map = {"1,2,1", "2,3,2", "1,3,1", "3,4,1"};
		Integer[] cars = {2, 1};
		Integer customer = 4;
		find_closest_car(city_map, cars, customer );
	}

}
