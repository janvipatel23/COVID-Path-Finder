import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// Class representing graph traversal
public class GraphTraversal {

    // The method finding shortest path
    // @param startCity - It indicates the starting city
    // @param destinationCity - It indicates the destination city
    // @param isVaccinated - It indicates whether travaller is vaccinated or not
    // @param costImportance - It indicates how much cost is important by the
    // traveller in their journey
    // @param travelTimeImportance - It indicates how much travel time is important
    // by the traveller in their journey
    // @param travelHopImportance - It indicates how much travel hop is important by
    // the traveller in their journey
    // @param cityMap - It represnts the hash map of city
    // @return - Returns the final shortest path
    public static GraphNode findShortestPath(City startCity, City destinationCity, boolean isVaccinated,
            int costImportance, int travelTimeImportance, int travelHopImportance, Map<String, City> cityMap) {

        // create a hashmap with a key as destination city and value as GraphNode for
        // particular city.
        Map<String, GraphNode> shortestPath = new HashMap<>();

        // create a object of GraphNode with as startCity and weight as 0 and name it as
        // a sourceNode.
        GraphNode sourceNode = new GraphNode(0, startCity, null, isVaccinated);

        // create a priority queue for holding the object of GraphNode and pass
        // comparator based on weight.
        PriorityQueue<GraphNode> queue = new PriorityQueue<>((o1, o2) -> o1.getWeight() - o2.getWeight());

        // push a source node in the priority queue.
        queue.offer(sourceNode);

        // iterate over the queue till it's not empty
        while (!queue.isEmpty()) {

            // polling the nearest node from a queue
            GraphNode nearestNode = queue.poll();

            // getting the adjacent route
            Set<Route> adjacentRoute = nearestNode.getCity().getTravelRoute();

            // if adjacent route is null then it will continue
            if (adjacentRoute == null) {
                continue;
            }

            // iterating over each adjacent route
            for (Route route : adjacentRoute) {

                // initialzing night cost with zero
                int routeNightCost = 0;

                // fetching the test required value from destination city
                boolean isTestRequired = route.getDestinationCity().isTestRequired();

                // checks whether test is required or not
                // if it is required then checks whether test result are negative or not
                if (isTestRequired) {

                    // if test results are negative
                    if (!nearestNode.isHasNegativeTestResult()) {

                        // getting the nodes
                        List<GraphNode> nodes = nearestNode.getShortestPath();

                        // initialzing the min night cost value to infinity
                        int minNightCost = Integer.MAX_VALUE;

                        // checking whether test is being provided in the source city or not
                        // if it is provided at source city then make minNightCost value zero
                        // else iterate over the nodes and checks if test is being provided in the city
                        // or not
                        // if it is provided then check is current night cost is minimum than
                        // minNightCost or not
                        // if it is minumum then updates the minNightCost with minimum value
                        if (sourceNode.getCity().getTimeToTest() > 0) {
                            minNightCost = 0;
                        } else {
                            for (GraphNode node : nodes) {
                                if (node.getCity().getTimeToTest() > 0) {
                                    if (node.getCity().getNightlyHotelCost()
                                            * node.getCity().getTimeToTest() < minNightCost) {
                                        minNightCost = node.getCity().getNightlyHotelCost()
                                                * node.getCity().getTimeToTest();
                                    }
                                }
                            }
                        }

                        // if routeNightCost is infinty then return -1
                        // else return minNightCost
                        routeNightCost = minNightCost == Integer.MAX_VALUE ? -1 : minNightCost;
                    }
                }

                // if routeNightCost is -1 then continue
                // and indicates that testing is not possible in that path
                // and further specifies that travel is not possible.
                if (routeNightCost == -1) {
                    continue;
                }

                // getting current shortest path for the destination city
                GraphNode currentShortestNodePath = shortestPath.get(route.getDestinationCity().getCityName());

                // calculating the weight for current route node
                int weight = nearestNode.getWeight() + route.calculateWeight(costImportance, travelTimeImportance,
                        travelHopImportance, routeNightCost);

                // if current shortest node path doesnot exist
                if (currentShortestNodePath == null) {

                    // create new graph node
                    currentShortestNodePath = new GraphNode(weight, route.getDestinationCity(), route.getTravelMode(),
                            nearestNode.isHasNegativeTestResult());

                    // setting the test result
                    currentShortestNodePath
                            .setHasNegativeTestResult(isTestRequired || nearestNode.isHasNegativeTestResult());

                    // creating a new linked list based on the shortest path of nearest node
                    LinkedList<GraphNode> path = new LinkedList<>(nearestNode.getShortestPath());

                    // appending current shortest path into the path
                    path.add(currentShortestNodePath);

                    // setting the current shortest path as shortest path
                    currentShortestNodePath.setShortestPath(path);

                    // remove the node of this route for destination city if existed
                    queue.removeIf(obj -> obj.getCity().equals(route.getDestinationCity()));

                    // inserting current shortest node path in the queue
                    queue.offer(currentShortestNodePath);

                    // adding the destination city name and current shortest node path to shortest path
                    shortestPath.put(route.getDestinationCity().getCityName(), currentShortestNodePath);
                }

                // if the shortest path exist then
                // compare if the weight with this route is less than the existed shortest path weight
                else if (weight < currentShortestNodePath.getWeight()) {

                    // updating weight with the minimum weight in the current shortest node path
                    currentShortestNodePath.setWeight(weight);
                    // setting the travel mode in current shortest node path
                    currentShortestNodePath.setTravelMode(route.getTravelMode());

                    // setting the test result in current shortest path
                    currentShortestNodePath
                            .setHasNegativeTestResult(isTestRequired || nearestNode.isHasNegativeTestResult());

                    // creating a new linked list based on the shortest path of nearest node
                    LinkedList<GraphNode> path = new LinkedList<>(nearestNode.getShortestPath());

                    // appending current shortest path into the path
                    path.add(currentShortestNodePath);

                    // setting the current shortest path as shortest path
                    currentShortestNodePath.setShortestPath(path);

                    // remove the node of this route for destination city if existed
                    queue.removeIf(obj -> obj.getCity().equals(route.getDestinationCity()));

                    // inserting current shortest node path in the queue
                    queue.offer(currentShortestNodePath);
                }

            }
        }

        // returns the shortest path from the destination city
        return shortestPath.get(destinationCity.getCityName());
    }
}
