package gui;

import java.awt.event.ActionEvent;
import api.Restaurant;
import application.Application;
import application.Database;

import javax.swing.JOptionPane;

public class AddFavoriteListener extends AbstractButtonListener {
    private Restaurant restaurant;

    public AddFavoriteListener(Application app, Restaurant r) {
        super(app);
        this.restaurant = r;
    }

    /**
     * Adds a restaurant to the user's favorites. If there are more than 5 stored already,
     * pop up an error message saying the maximum has been reached.
     */
    public void actionPerformed(ActionEvent event) {
    	
    	Database db = new Database();
    	if(db.favExceedsFive(app.getUsername())==true){
    		JOptionPane.showMessageDialog(null, "Limit of favorites exceeded");
    	}
    	else if(db.DupeExcists(restaurant.getId()) == true){
    		JOptionPane.showMessageDialog(null, "Restaurant is already in favorites");
    	}
    	else{
    		db.addFavorite(app.getUsername(), restaurant.getId());
    		
    		app.loadFavorites();
    		

    	}
    
    	
        //TODO: method body
    }
}