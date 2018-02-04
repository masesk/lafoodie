package api;

/**
 * This class models the restaurant objects in the Yelp API response
 */
public class Restaurant {
    private String name;
    private String id;
    private String snippet_text;
    private String image_url;
    private Location location;
    private int review_count;
    private String rating_img_url;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    
    public String getSnippet_text() {
        return snippet_text;
    }

    public String getImageUrl() {
        return image_url;
    }

    public int getReview_count() {
        return review_count;
    }

    public String getRating_img_url() {
        return rating_img_url;
    }

    public Location getLocation() {
        return location;
    }

    public String toString() {
        return this.name;
    }
}
