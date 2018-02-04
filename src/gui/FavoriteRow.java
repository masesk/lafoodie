package gui;

import java.awt.Color;
//import java.awt.Container;
import java.awt.Dimension;

import api.Restaurant;
import application.Application;

@SuppressWarnings("serial")
public class FavoriteRow extends SearchRow {
	private String id;
	public FavoriteRow(Application app, Restaurant r) {
		
        super(app, r);
        
        // We don't want this button to add a favorite so let's remove the existing action listener
        id = r.getId();
        button.removeActionListener(this.button.getActionListeners()[0]);
        button.setText("Remove From Favorites");
        button.setPreferredSize(new Dimension(200, 10));
        button.addActionListener(new RemoveFavoriteListener(app, r, this));
        
        //TODO: the only difference here is the look and behavior of the button
        //change the text and apply a different action listener to the button
    }

    @Override
    protected Color getBackgroundColor() {
		return new Color(197, 175, 255);
        //TODO: choose a different color for the favorites rows
    }
    public String getId() {
		return id;
	}

}
