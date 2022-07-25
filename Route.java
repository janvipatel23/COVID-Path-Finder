// enum class represnting the modes of travel, which can be either fly or train
enum TravelMode {
    fly, train
}

// Class representing the route data
public class Route {

    // Properties for route
    private TravelMode travelMode;
    private City sourceCity;
    private City destinationCity;
    private int time;
    private int cost;

    // Initializes all the required route properties
    // @param travelMode - It represents the travel mode
    // @param sourceCity - It represents the source city
    // @param destinationCity - It represents the destination city
    // @param travelTime -It indicates time required to travel from one node to
    // another
    // @param travelCost - It indicates the flight or train cost
    public Route(TravelMode travelMode, City sourceCity, City destinationCity, int travelTime, int travelCost) {
        this.travelMode = travelMode;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.time = travelTime;
        this.cost = travelCost;
    }

    // setting the travel mode
    public void setTravelMode(TravelMode travelMode) {
        this.travelMode = travelMode;
    }

    // getting the travel mode
    public TravelMode getTravelMode() {
        return travelMode;
    }

    // setting the source city
    public void setSourceCity(City sourceCity) {
        this.sourceCity = sourceCity;
    }

    // getting the source city
    public City getSourceCity() {
        return sourceCity;
    }

    // setting the destination city
    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    // getting the destination city
    public City getDestinationCity() {
        return destinationCity;
    }

    // setting the time for a travel mode
    public void setTime(int time) {
        this.time = time;
    }

    // getting the time for a travel mode
    public int getTime() {
        return time;
    }

    // setting the cost for a travel mode
    public void setCost(int cost) {
        this.cost = cost;
    }

    // getting the cost for a travel mode
    public int getCost() {
        return cost;
    }

    // calculating the weight
    // @param costImportance - An integer property indicating till which extent the
    // cost is important for traveller for their journey
    // @param travelTimeImportance - An integer property indicating till which
    // extent the travel time is important for traveller for their journey
    // @param travelHopImportance - An integer property indicating till which extent
    // the travel hop is important for traveller for their journey
    // @param hotelCost - It represents the hotel cost
    // @return - Returns the calculated weight
    public int calculateWeight(int costImportance, int travelTimeImportance, int travelHopImportance, int hotelCost) {

        return ((cost + hotelCost) * costImportance) + (time * travelTimeImportance) + (travelHopImportance);
    }

    // Overriding the hashcode method
    // @return - Returns the hash code for destination city to maintain the
    // uniqueness
    @Override
    public int hashCode() {
        return destinationCity.hashCode();
    }

    // Overriding the equals method to avoid the addition of duplicate flight or
    // train
    // @return - It returns boolean value either true or false depending whether
    // source city, destination city, and travel mode properties are different or
    // not
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Route other = (Route) obj;

        return sourceCity.equals(other.getSourceCity()) && destinationCity.equals(other.getDestinationCity())
                && travelMode == other.getTravelMode();
    }
}