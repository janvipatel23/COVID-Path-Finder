import java.util.HashSet;
import java.util.Set;

// Class representing city properties
public class City {

    // Properties for the city
    private String cityName;
    private Boolean testRequired;
    private int timeToTest;
    private int nightlyHotelCost;
    private Set<Route> travelRoute;

    // Initializes all the required city properties
    // @param cityName - Name of the city
    // @param testRequired - Boolean property which indicates whether test is
    // required or not in the specific city
    // @param timeToTest - An integer property which represents the time required to
    // test in the city
    // @param nightlyHotelCost - An integer property which indicates the night hotel
    // cost required in the city till the test results get arrived
    public City(String cityName, Boolean testRequired, int timeToTest, int nightlyHotelCost) {
        this.cityName = cityName;
        this.testRequired = testRequired;
        this.timeToTest = timeToTest;
        this.nightlyHotelCost = nightlyHotelCost;
        this.travelRoute = new HashSet<>();
    }

    // setting the city name
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    // getting the city name
    public String getCityName() {
        return this.cityName;
    }

    // getting whether test is required or not
    public Boolean isTestRequired() {
        return testRequired;
    }

    // setting boolean property of whether test is required or not
    public void setTestRequired(Boolean testRequired) {
        this.testRequired = testRequired;
    }

    // getting the time to test value
    public int getTimeToTest() {
        return timeToTest;
    }

    // setting the time to test value
    public void setTimeToTest(int timeToTest) {
        this.timeToTest = timeToTest;
    }

    // getting the nightly hotel cost
    public int getNightlyHotelCost() {
        return nightlyHotelCost;
    }

    // setting the nightly hotel cost
    public void setNightlyHotelCost(int nightlyHotelCost) {
        this.nightlyHotelCost = nightlyHotelCost;
    }

    // getting the travel route
    public Set<Route> getTravelRoute() {
        return travelRoute;
    }

    // setting the travel route
    public void setTravelRoute(Set<Route> travelRoute) {
        this.travelRoute = travelRoute;
    }

    // Adding the travel route in the Set<Route> travelRoute property, which
    // maintains the unique travel routes
    // @param travelMode - It represents the specific travel mode, which can be
    // either train or flight
    // @param destinationCity - It indicates the destination city specified by the
    // traveller, which is end point of whole journey
    // @param travelTime - The travel time of either flight or train mode
    // @param travelCost - The travel cost of either flight or train mode
    // @return - It returns the boolean value of true or false depending upon
    // whether travel route has been succesfully added or not
    public boolean addTravelRoute(TravelMode travelMode, City destinationCity, int travelTime, int travelCost) {
        return this.travelRoute.add(new Route(travelMode, this, destinationCity, travelTime, travelCost));
    }
}
