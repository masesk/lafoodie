package gui;

import api.Location;
import api.Restaurant;
import application.Application;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class SearchRow extends JPanel {

    protected Application application;
    protected Restaurant restaurant;
    private Helpers help = new Helpers();
    //TODO: you will need to declare the GUI components here
    protected JButton button = new JButton("Add to Favorites");
    protected JPanel main = new JPanel();
    public SearchRow(Application app, Restaurant r) {
        this.restaurant = r;
        this.application = app;
        Location LocationList = r.getLocation();
        String ListOfLications[] = LocationList.getDisplay_address();
        ImageIcon restIcon = help.getRemoteImage(restaurant.getImageUrl());
        ImageIcon rating = help.getRemoteImage(restaurant.getRating_img_url());
   
        main.setPreferredSize(new Dimension(850,100));
        main.setLayout(new BorderLayout());
        JLabel photo = new JLabel(restIcon);
        JPanel left = new JPanel(new BorderLayout());
        JPanel left2Grids = new JPanel(new GridLayout(1,2));
        JPanel otherStuff = new JPanel(new GridLayout(4,1));
        JLabel name = new JLabel(restaurant.getName());
        JLabel photo2 = new JLabel(rating);
        photo2.setHorizontalAlignment(SwingConstants.LEFT);
        String numOfRev = Integer.toString(restaurant.getReview_count());
        JLabel review = new JLabel(numOfRev + " reviewes");
        button.addActionListener(new AddFavoriteListener(app, r));
        button.setPreferredSize(new Dimension(200, 10));
        otherStuff.add(name);
        otherStuff.add(photo2);
        otherStuff.add(review);
        otherStuff.add(button);
        left2Grids.add(photo);
        left2Grids.add(otherStuff);
        left.add(left2Grids, BorderLayout.WEST);
        left.add(otherStuff, BorderLayout.EAST);
        JPanel Center = new JPanel();
        for(int i = 0; i < ListOfLications.length; i++){
        	Center.add(new JLabel(ListOfLications[i]));
        }
    	JPanel rightText = new JPanel();
    	JTextArea textArea = new JTextArea(5, 20);
    	textArea.setPreferredSize(new Dimension(5, 20));
    	textArea.setEditable(false);
    	textArea.setWrapStyleWord(true);
    	textArea.setLineWrap(true);
    	textArea.insert(restaurant.getSnippet_text(), 0);
    	textArea.setBackground((new Color(0, 0, 0, 0)));
    	rightText.add(textArea);
    	main.add(left, BorderLayout.WEST);
    	main.add(rightText, BorderLayout.EAST);
    	main.add(Center, BorderLayout.CENTER);
    	rightText.setBackground(getBackgroundColor());
    	Center.setBackground(getBackgroundColor());
    	left.setBackground(getBackgroundColor());
    	otherStuff.setBackground(getBackgroundColor());
    	main.setBackground(getBackgroundColor());
    	super.add(main);
    }

    //TODO: I recommend creating methods to help you organize your code
    
    protected Color getBackgroundColor() {;
        return new Color(204, 231, 135);
    }
    public JButton returnButton(){
		return button;
    	
    }
    
}
