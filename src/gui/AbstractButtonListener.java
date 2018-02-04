package gui;

import application.Application;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class AbstractButtonListener implements ActionListener {
    protected Application app;

    public AbstractButtonListener(Application app) {
        this.app = app;
    }

    public abstract void actionPerformed(ActionEvent event);
}
