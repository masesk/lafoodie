package api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

/**
 * Yelp has a different version of the API. When we know exactly which business we want to retrieve, we need to call this
 * API instead. But when we want to search multiple restaurants we need to use the other class.
 */
public class BusinessRequest {
    private Gson gson;
    private String term;

    public BusinessRequest(String term) {
        this.term = term;
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    /**
     * Send the request to the API and return the single Restaurant that was returned
     * @return the restaurant result
     */
    public Restaurant send() {
        YelpAPI api = new YelpAPI();

        String responseString = api.searchByBusinessId(this.term);
        Restaurant restaurant = gson.fromJson(responseString, Restaurant.class);

        return restaurant;
    }
}
