package application;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import gui.AbstractButtonListener;
import gui.GUI;

public class LoginListener extends AbstractButtonListener {
	Application app;
	Database db;
	public LoginListener(Application app, Database db) {
		super(app);
		this.app = app;
		this.db = db;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(app.userText.getText().isEmpty() || app.passwordText.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Username or password cannot be empty");
			
		}
		else{
		String pass = app.passwordText.getText();
		String username = app.userText.getText();
		pass = app.hash(username, pass);
		
		if(db.authenticateUser(username, pass)){
			app.setUserName(username);
			app.setPassword(pass);
			app.gui = new GUI(app, username);
			app.gui.init();
			app.frame.dispose();
			app.frame.setVisible(false);
		}
		else{
			JOptionPane.showMessageDialog(null, "Username or password is incorrect");
		}
		}
		
		
	}

}
