package gui;

import application.Application;
import java.awt.event.ActionEvent;

public class SearchButtonListener extends AbstractButtonListener {
    
    public SearchButtonListener(Application app) {
        super(app);
    }

    public void actionPerformed(ActionEvent event) {
        app.loadRestaurants();
    }
}