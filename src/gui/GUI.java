package gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;


import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import api.Restaurant;
import application.Application;

public class GUI {
    private Application app;
    private JFrame frame;
    private  JLabel label4 = new JLabel();
    private  JLabel label5 = new JLabel();
    private JPanel favoritesPanel;
    private Helpers help = new Helpers();
    private JScrollPane panel1 = new  JScrollPane();
    private JScrollPane panel2 = new  JScrollPane();
    //TODO: change to an appropriate height/width
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;
    private String username;
    
    public GUI(Application app, String userName) {
    	this.app = app;
    	this.username = userName;
        this.frame = new JFrame("LA Foodie");
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());
        
    }

    public void init() {
       createTopBar();
       createTabbedPane();
       createBottomBar();
       app.loadRestaurants();
       app.loadFavorites();
       frame.pack();
       frame.setVisible(true);
    }

    /**
     * Create and display all the GUI components for the top bar
     */
    private void createTopBar() {
    	ImageIcon happyFace = help.getRemoteImage("http://i.imgur.com/rptkd7d.png");
        JLabel label5 = new JLabel(happyFace);
    	JPanel panel2 = new JPanel();
    	panel2.setLayout(new BorderLayout());
    	JLabel label4 = new JLabel("LA FOODIE");
    	help.setFontSize(label4, 20);
    	JLabel label3 = new JLabel("Welcome, " + username + "!");
    	label4.setHorizontalAlignment(SwingConstants.CENTER);
    	label3.setHorizontalAlignment(SwingConstants.LEFT);
        panel2.add(label4, BorderLayout.CENTER);
        panel2.add(label3, BorderLayout.WEST);
        panel2.add(label5, BorderLayout.EAST);
        frame.getContentPane().add (panel2,BorderLayout.NORTH);
        
    }

    /**
     * Create and display all the GUI components for the tabbed pane in the center
     */
    private void createTabbedPane() {
    	 JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(900, 400));
        tabbedPane.addTab("Search", panel1);
        tabbedPane.addTab("Favorites", panel2);
        panel1.setPreferredSize(new Dimension (900, 400));
        panel2.setPreferredSize(new Dimension (900, 400));
        frame.getContentPane().add (tabbedPane,BorderLayout.CENTER);
    }

    /**
     * Create and display all the GUI components for the tabbed pane in the center
     */
    private void createBottomBar() {

    	JPanel panel2 = new JPanel();
    	panel2.setLayout(new BorderLayout());
    	JButton button1 = new JButton("Search Again");
    	button1.addActionListener(new SearchButtonListener(this.app));
    	panel2.add(button1, BorderLayout.WEST);
    	//label4 = new JLabel("Search results for " + term + " in " + location);
    	label4.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.add(label4, BorderLayout.CENTER);
        //label5 = new JLabel("Cache stats: " + cacheHit + " hits. " + cacheMiss + " misses.");
        panel2.add(label5, BorderLayout.EAST);
        
        frame.getContentPane().add (panel2,BorderLayout.SOUTH);
    }

    /**
     * This method accepts a list of restaurants that should come back from the API,
     * and creates a SearchRow for each of them
     * @param results
     */
    public void renderSearchResults(Restaurant[] results) {
    	JPanel cont = new JPanel(new BorderLayout());
    	cont.setLayout(new GridLayout(10,1));
    	for(int i = 0; i < results.length; i++){
    		SearchRow a = new SearchRow(app, results[i]);
    		a.setOpaque(true);
    		//a.setBackground(Color.BLACK);
    	    cont.add(a);
    		
    	}
    	panel1.getVerticalScrollBar().setValue(0);
    	panel1.getViewport().add(cont);
    }
    public void renderFavResults(Restaurant[] results) {
    	JPanel cont = new JPanel();
    	cont.setLayout(new GridLayout(5,1));
    	//cont.setLayout(new BoxLayout(cont, BorderLayout.Y_AXIS));
    	for(int i = 0; i < results.length; i++){
    		FavoriteRow b = new FavoriteRow(app, results[i]);
    		b.setOpaque(true);
    		//b.setBackground(Color.BLACK);
    	    cont.add(b);
    		
    	}
//    	JLabel name1 = new JLabel("Im at fav");
//    	cont.add(name1, BorderLayout.CENTER);
    	panel2.getViewport().add(cont);
    }
    
    

    //TODO: you will likely need additional methods here to set the cache/status label text and to render rows
   
    public void setLabels(String location, String term, int cacheMiss, int cacheHit){

    	label4.setText("Search results for " + term + " in " + location);
    	label5.setText("Cache stats: " + cacheHit + " hits. " + cacheMiss + " misses.");
    	panel1.getVerticalScrollBar().setValue(0);
   
    }
    public void addFavoriteRow(Restaurant r) {
        FavoriteRow row = new FavoriteRow(this.app, r);
        this.favoritesPanel.add(row);
        this.favoritesPanel.validate();
        this.panel2.validate();
    }
    
    public void rePaint(FavoriteRow r){
    	panel2.remove(r);
    	panel2.validate();
    }
    
    public Application getApplication() {
        return this.app;
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
}
