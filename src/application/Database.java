package application;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    //TODO: enter the following information
	//Make sure you have columns for 'username' and 'password'
    private final String url = "DATABASE_URL";
    private final String username = "DB_USERNAME_HERE";
    private final String password = "DB_PASS_HERE";
    private final String database = "DATABASE_NAME";
    private Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";

  

    public Database() {
        this.connect();
    }

    private boolean connect() {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + database, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * This method accepts a username and password and checks if such a user exists in the database
     * @param username the username we are trying to log in with checking
     * @param password the hashed password we are trying to log in with
     * @return true if the user was successfully authenticated
     */
    public boolean authenticateUser(String username, String password) {
    	try {
            Statement st = conn.createStatement();
            String query = "SELECT * FROM people";
            ResultSet results = st.executeQuery(query);
            while (results.next()) {
                if(username.equals(results.getString("username")) && password.equals(results.getString("password"))){
                	return true;
                }

                
            }
        } catch (SQLException e) {
            System.out.println("SQL error in method");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    	return false;

    }
    

    /**
     * This method checks if a username is already registered in the database
     * @param username the username we are checking
     * @return true if the user already exists
     */
    public boolean doesUserExist(String username) {
    	try {
            Statement st = conn.createStatement();
            String query = "SELECT * FROM people";
            ResultSet results = st.executeQuery(query);
            while (results.next()) {
                if(username.equals(results.getString("username"))){
                	return true;
       
                }
                
            }
        } catch (SQLException e) {
            System.out.println("SQL error in getPeople method");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    	return false;
    }

    /**
     * This method creates a new user and stores it in the database
     * @param username the username of the new user
     * @param password the hashed password of the new user
     * @return true if the user was created without any error (exception)
     */
    public boolean createUser(String username, String password) {
    	try {
            Statement st = conn.createStatement();
            String query = ("INSERT INTO people(username,password) VALUE ('"+username+"','"+password+"')");
           
            // This gives us the number of rows that were affected
            int numRows = st.executeUpdate(query);

            if (numRows == 1) {
                return true; // row was inserted successfully
            } else {
                return false; // nothing was inserted
            }
        } catch (SQLException e) {
            System.out.println("SQL error in addPerson method");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method adds a favorite restaurant for a particular user
     * @param userName the user's name
     * @param restaurant the name of the restaurant
     * @return true if the favorite was added successfully 
     */
    public boolean addFavorite(String userName, String restaurant) {
    	try {
            Statement st = conn.createStatement();
            String query = ("INSERT INTO favorites(username,restaurant) VALUE ('"+userName+"','"+restaurant+"')");
            // This gives us the number of rows that were affected
            int numRows = st.executeUpdate(query);

            if (numRows == 1) {
                return true; // row was inserted successfully
            } else {
                return false; // nothing was inserted
            }
        } catch (SQLException e) {
            System.out.println("SQL error in addPerson method");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method removes a favorite restaurant for a particular user
     * @param userName the user's name
     * @param restaurant the name of the restaurant
     * @return true if the favorite was removed successfully 
     */
    public boolean removeFavorite(String userName, String restaurant) {
    	try {
            Statement st = conn.createStatement();
            String query = "DELETE FROM favorites WHERE username ='"+userName+"' AND  restaurant ='"+restaurant+"' ";

            

            // This gives us the number of rows that were affected
            int numRows = st.executeUpdate(query);

            if (numRows == 1) {
                return true; // row was inserted successfully
            } else {
                return false; // nothing was inserted
            }
        } catch (SQLException e) {
            System.out.println("SQL error in addPerson method");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method retrieves the stored list of favorites for a particular user. It only
     * retrieves the ID/name of the restaurants rather than all the data. Afterwards you will
     * have to make a request to the Yelp Business API (not the search API) to pull in the 
     * data for all these favorites
     * @param userName the username of the user
     * @return a list of the IDs of the stored favorites for this user
     */
    public ArrayList<String> getFavorites(String userName) {
    	ArrayList<String> favs = new ArrayList<>();
    	try {
            Statement st = conn.createStatement();
            String query = "SELECT * FROM favorites";
            ResultSet results = st.executeQuery(query);
            while (results.next()) {
                if(userName.equals(results.getString("username"))){
                	favs.add(results.getString("restaurant"));
                	
                }
                
            }
        } catch (SQLException e) {
            System.out.println("SQL error in getPeople method");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    	return favs;
    }
    public boolean favExceedsFive(String userName) {
    	try {
    		int num = 0;
            Statement st = conn.createStatement();
            String query = "SELECT * FROM favorites";
            ResultSet results = st.executeQuery(query);
            while (results.next()) {
                if(userName.equals(results.getString("username"))){
                	num++;
                }
            }
            if(num>=5){
            	return true;
            }
            else{
            	return false;
            }
        } catch (SQLException e) {
            System.out.println("SQL error in method");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }
    public boolean DupeExcists(String restaurant) {
    	try {
            Statement st = conn.createStatement();
            String query = "SELECT * FROM favorites";
            ResultSet results = st.executeQuery(query);
            while (results.next()) {
                if(restaurant.equals(results.getString("restaurant"))){
                	return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL error in method");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        	}
		return false;
    }
}