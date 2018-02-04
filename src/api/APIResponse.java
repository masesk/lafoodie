package api;

/**
 * This class models what the Yelp API response looks like. It has a field for total then an array of restaurants
 */
public class APIResponse {
    private String total;
    private Restaurant[] businesses;

    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    public Restaurant[] getBusinesses() {
        return businesses;
    }
    public void setBusinesses(Restaurant[] businesses) {
        this.businesses = businesses;
    }    
}
