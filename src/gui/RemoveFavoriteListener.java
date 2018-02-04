package gui;

import java.awt.event.ActionEvent;
import api.Restaurant;
import application.Application;
import application.Database;

public class RemoveFavoriteListener extends AbstractButtonListener {
    private Restaurant restaurant;
    private FavoriteRow row;

    public RemoveFavoriteListener(Application app, Restaurant r, FavoriteRow row) {
        super(app);
        this.restaurant = r;
        this.row = row;
    }

    /**
     * This method should remove a favorite from the database and from the GUI
     */
    public void actionPerformed(ActionEvent event) {
        Database db = new Database();
        db.removeFavorite(app.getUsername(), row.getId());
        
        //app.getGUI().rePaint(this.row);
        app.loadFavorites();
        
    }
}
