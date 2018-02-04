package application;

import api.APIRequest;
import api.BusinessRequest;
import api.Restaurant;
import gui.GUI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.math.BigInteger;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.util.ArrayList;
//import java.util.LinkedHashSet;
import java.util.LinkedHashSet;

public class Application {
    private Database db;
    private String username;
	private String password;
    private String term;
    private String location;
    private int cacheHit;
    private int cacheMiss;
    GUI gui;
    public JFrame frame;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 240;
    final JTextField userText = new JTextField(12);
    final JPasswordField passwordText = new JPasswordField(12); 
    public Application() {
        this.db = new Database();
    }

    /**
     * Start the app by asking the user to login/resiter
     * Then load the GUI and make the first API calls to show results on the UI
     */
    public void start() {
    	this.frame = new JFrame("LA Foodie");
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new GridLayout(6,0));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JLabel label1 = new JLabel("Username");
        JLabel label2 = new JLabel("Password");
        panel4.add(label1);
        panel5.add(label2);
        panel1.add(userText);
        panel2.add(passwordText);
        JButton button1 = new JButton("Login");
        JButton button2 = new JButton("Register");
        button1.addActionListener(new LoginListener(this, db));
        button2.addActionListener(new RegisterListener(this, db));
        panel3.add(button1);
        panel3.add(button2);
        JLabel label3 = new JLabel("This software was created by Mases Krikorian for masesk.com");
        JPanel panel6 = new JPanel();
        panel6.add(label3);
        frame.getContentPane().add(panel4);
        frame.getContentPane().add(panel1);
        frame.getContentPane().add(panel5);
        frame.getContentPane().add(panel2);
        frame.getContentPane().add(panel3);
        frame.getContentPane().add(panel6);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Makes a request to the API then put the results onto the scroll pane
     */
    public void loadRestaurants() {
        APIRequest req = new APIRequest();
        Restaurant[] results = req.send();
        location = req.getLocations();
        term = req.getTerm();
        cacheMiss = req.getCacheMiss();
        cacheHit = req.getCacheHit();
        gui.renderSearchResults(results);
        gui.setLabels(location, term, cacheMiss, cacheHit);
        
    }



	/**
     * Ask the database for stored favorites, then call the API to get those Restaurants and show them on the UI
     */
    public void loadFavorites() {
        /**
         * To request information for a particular restaurant (rather than search)
         * You must use a BusinessRequest rather than Search Request
         * Here is an example:
            BusinessRequest req = new BusinessRequest(name);
            Restaurant fave = req.send();
        
    	BusinessRequest req = new BusinessRequest(name);
        Restaurant fave = req.send();
        //TODO: complete the rest of the method
         */
    	ArrayList<String> favos = db.getFavorites(this.username);
    	ArrayList<Restaurant> res = new ArrayList<>();
    	for(int i = 0; i < favos.size(); i++){
    		BusinessRequest req = new BusinessRequest(favos.get(i));
            Restaurant fave = req.send();
            res.add(fave);
    	}
    	Restaurant[] k = new Restaurant[res.size()];
    	res.toArray(k);
    	gui.renderFavResults(k);
    	
    	
    }

    /**
     * Hash the password before storing or comparing in the database
     * @param username the user's username
     * @param pw the user's raw password
     * @return a hashed version of the password
     */
    
    public String hash(String username, String pw) {
        try {
           char f = username.charAt(0);
           char l = username.charAt(username.length() - 1);
           String input = pw + f + l;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(input.getBytes(), 0, input.length());
            BigInteger num = new BigInteger(1,m.digest());
            return num.toString(16);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return "";
        }
    }
    
    public String getTerm() {
    	return term;
	}
    public GUI getGUI(){
    	return this.gui;
    }

	public String getLocation() {
		return location;
		
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public int getCacheHit() {
		return cacheHit;
	}

	public int getCacheMiss() {
		return cacheMiss;
	}
}
