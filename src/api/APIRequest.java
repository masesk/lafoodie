package api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class APIRequest {
    private Gson gson;
    private  String term = "";
    private  String location = "";
    private  int num;
    /*
     * TODO: you will need a few more instance variables than this
     */
    private String cacheText;
    private static int cacheHit = 0;
    private static int cacheMiss = 0;
    private static Map<String, Restaurant[]> cache = new HashMap<>();
    

	/*
     * TODO: declare static arrays to contain possible locations and cuisines
     */
    //location (city, state, country...etc) here
     String locations[] = {"GRAB STRINGS FROM USER", "STORE THEM HERE AS AN ARRAY", "YOU DON'T HAVE TO SAVE ON DB"};
     //types of food here
     String terms[] = {"STORE TYPES OF FOOD HERE","chinese", "mediterranean", "tacos", "pizza", "sushi", "donut"};
    
    
    public APIRequest() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    /**
     * Sends a request to the API and returns a list of Restaurant objects created by Gson
     * @return an array of Restaurant objects
     */
    public Restaurant[] send() {
        YelpAPI api = new YelpAPI();
        Random rand = new Random();
        num = rand.nextInt(locations.length);
        location = locations[num];
        num = rand.nextInt(terms.length);
        term = terms[num];
        // TODO: randomly choose a location and search term and assign them to the String variables term and location
        // TODO: check the cache for the result set before making a new request to Yelp
        cacheText = term + location;
        if(cache.containsKey(cacheText)) {
        	cacheHit++;
        	return cache.get(cacheText);
        } else {
            // This is a new request so we have to retrieve it from the API
            String[] query = {"--term", term, "--location", location};
            
            // Send the request to the API client
            String responseString = api.query(query);

            // Parse the response string as json and create objects out of it
            APIResponse response = gson.fromJson(responseString, APIResponse.class);
    
            Restaurant[] restaurants = response.getBusinesses();
            cacheMiss++;
            cache.put(cacheText, restaurants);
            return restaurants;
        }
//		return null;		
		
    }
    public int getCacheHit() {
		return cacheHit;
	}

	public int getCacheMiss() {
		return cacheMiss;
	}

	public String getTerm() {
		return term;
	}

	public String getLocations() {
		return location;
	}
}
