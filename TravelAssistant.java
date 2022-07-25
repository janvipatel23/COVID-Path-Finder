import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Class representing travel assistant, which guides traveller to plan the trip
public class TravelAssistant {

    // The hashmap maintaning city data by representing the city name as key and
    // city object as a value
    private Map<String, City> cityMap = new HashMap<>();

    // Adding the city data
    // @param cityName - The name of city
    // @param testRequired - The boolean property indicating whether test is
    // required or not
    // @param timeToTest - The property representing time required to test in the
    // city
    // @param nightlyHotelCost - An integer property which indicates the night hotel
    // cost required in the city till the test results get arrived
    // @returns - Return true or false if city is successfully added or not
    public boolean addCity(String cityName, boolean testRequired, int timeToTest, int nightlyHotelCost) {

        // checking if city name is present in map or not
        // if present then return false
        if (cityMap.get(cityName) != null) {
            return false;
        }

        // validating input parameters
        // if cityName is either null or empty or nightlyHotelCost is less than 0
        // then throws an IllegalArgumentException
        if (cityName == null || cityName == "" || nightlyHotelCost < 0) {
            throw new IllegalArgumentException("Input paramaters are not acceptable");
        }

        // adding city and it's properties in a map
        cityMap.put(cityName, new City(cityName, testRequired, timeToTest, nightlyHotelCost));

        return true;
    }

    // Adding the flight data
    // @param startCity - It represents the start city
    // @param destinationCity - It represents the destination city
    // @param flightTime - It indicates the flight time
    // @param flightCost - It indicates the flight cost
    // @returns - Return true or false flight is added succesfully or not
    public boolean addFlight(String startCity, String destinationCity, int flightTime, int flightCost) {

        return addRoute(TravelMode.fly, startCity, destinationCity, flightTime, flightCost);

    }

    // Adding the flight data
    // @param startCity - It represents the start city
    // @param destinationCity - It represents the destination city
    // @param flightTime - It indicates the train time
    // @param flightCost - It indicates the train cost
    // @returns - Return true or false train is added succesfully or not
    public boolean addTrain(String startCity, String destinationCity, int trainTime, int trainCost) {

        return addRoute(TravelMode.train, startCity, destinationCity, trainTime, trainCost);
    }

    // Adding the route
    // @param travelMode - It represents the travel mode
    // @param startCity - It indicates the starting city
    // @param destinationCity - It indicates the destination city
    // @param travelCost - It indicates the travel cost
    // @return - Returns boolean value of either true or false whether route is
    // added succesfully or not
    private boolean addRoute(TravelMode travelMode, String startCity, String destinationCity, int travelTime,
            int travelCost) {

        // validating input paramaters
        // if startCity and destination city is empty or null or travel cost is less
        // than 0 or start city is equals to destination city
        // then throws IllegalArgumentException
        if (startCity == null || startCity == "" || destinationCity == null || destinationCity == "" || travelCost < 0
                || startCity.equalsIgnoreCase(destinationCity)) {
            throw new IllegalArgumentException("Input paramaters are not acceptable");
        }

        // getting the start city from city map
        City start = cityMap.get(startCity);

        // getting the destination city from city map
        City end = cityMap.get(destinationCity);

        // if start and end city are null then returns false
        if (start == null || end == null) {
            return false;
        }

        // adding the travel route by calling addTravelRoute with required parameters
        return start.addTravelRoute(travelMode, end, travelTime, travelCost);
    }

    // Planning the trip with optimized path for traveller
    // @param startCity - It indicates the starting city
    // @param destinationCity - It indicates the destination city
    // @param isVaccinated - It indicates whether travaller is vaccinated or not
    // @param costImportance - It indicates how much cost is important by the
    // traveller in their journey
    // @param travelTimeImportance - It indicates how much travel time is important
    // by the traveller in their journey
    // @param travelHopImportance - It indicates how much travel hop is important by
    // the traveller in their journey
    // @return - Returns the final shortest path
    public List<String> planTrip(String startCity, String destinationCity, boolean isVaccinated, int costImportance,
            int travelTimeImportance, int travelHopImportance) {

        // validating input params
        // throwing IllegalArgumentException if anyone of validation is false
        if (startCity == null || startCity == "" || costImportance < 0 || travelTimeImportance < 0
                || travelHopImportance < 0 || startCity.equalsIgnoreCase(destinationCity)) {
            throw new IllegalArgumentException("Input paramaters are not acceptable");
        }

        // getting the start city from city map
        City start = cityMap.get(startCity);

        // getting the destination city from city map
        City end = cityMap.get(destinationCity);

        // if start and end city are null then returns false
        if (start == null || end == null) {
            return null;
        }

        // invoking the findShortestPath method to find the shortest path
        GraphNode shortestNodePath = GraphTraversal.findShortestPath(start, end, isVaccinated, costImportance,
                travelTimeImportance, travelHopImportance, cityMap);

        // checking if shortest path is null or not
        // if it's null then return null
        if (shortestNodePath == null) {
            return null;
        }

        // getting the shortest in a linked list format
        LinkedList<GraphNode> path = shortestNodePath.getShortestPath();

        // an arraylist storing the final obtained shortest path
        List<String> finalShortestPath = new ArrayList<>();

        // adding the start city in finalShortestPath
        finalShortestPath.add("start " + startCity);

        // iterating over the path to get the specific graph node and adding it in final
        // shortest path with travel mode and city name
        for (GraphNode graphNode : path) {
            finalShortestPath.add(" " + graphNode.getTravelMode() + " " + graphNode.getCity().getCityName());
        }

        return finalShortestPath;
    }
}
