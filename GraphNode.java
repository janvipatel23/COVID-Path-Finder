import java.util.LinkedList;

// Class represnting properties of graph node
public class GraphNode {

    // Properties of graph
    private int weight;
    private City city;
    private LinkedList<GraphNode> shortestPath;
    private TravelMode travelMode;
    private boolean hasNegativeTestResult;

    // Initializes all the required graph node properties
    // @param weight - It represents the weight between two nodes in a graph
    // @param city - It represents the city object
    // @param travelMode - It indicates the mode of travel, which can either flight
    // or train mode
    // @param hasNegativeTestResult -It indicates whether test results are negative
    // or not
    public GraphNode(int weight, City city, TravelMode travelMode, boolean hasNegativeTestResult) {
        this.weight = weight;
        this.city = city;
        this.shortestPath = new LinkedList<>();
        this.travelMode = travelMode;
        this.hasNegativeTestResult = hasNegativeTestResult;
    }

    // getting the weight
    public int getWeight() {
        return weight;
    }

    // setting the weight
    public void setWeight(int weight) {
        this.weight = weight;
    }

    // getting the city
    public City getCity() {
        return city;
    }

    // setting the city
    public void setCity(City city) {
        this.city = city;
    }

    // getting shortest path
    public LinkedList<GraphNode> getShortestPath() {
        return shortestPath;
    }

    // setting shortest path
    public void setShortestPath(LinkedList<GraphNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    // getting the travel mode
    public TravelMode getTravelMode() {
        return travelMode;
    }

    // setting the travel mode
    public void setTravelMode(TravelMode travelMode) {
        this.travelMode = travelMode;
    }

    // getting th test results
    public boolean isHasNegativeTestResult() {
        return hasNegativeTestResult;
    }

    // setting boolean property of whether test results are positive or negative
    public void setHasNegativeTestResult(boolean hasNegativeTestResult) {
        this.hasNegativeTestResult = hasNegativeTestResult;
    }

}