package application;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import gui.AbstractButtonListener;
import gui.GUI;

public class RegisterListener extends AbstractButtonListener {
	Application app;
	Database db;
	public RegisterListener(Application app, Database db) {
		super(app);
		this.app = app;
		this.db = db;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String pass = app.passwordText.getText();
		String username = app.userText.getText();
		if(app.userText.getText().isEmpty() || app.passwordText.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Username or password cannot be empty");
			
		}
		else if(app.passwordText.getText().length() > 12 || app.passwordText.getText().length() < 6){
			JOptionPane.showMessageDialog(null, "Password cannot be less than 6 or greater than 12");
		}
		else if(db.doesUserExist(username)){
			JOptionPane.showMessageDialog(null, "Cannot create new account under that username because it already exists");
			
		}
		else{
			pass = app.hash(username, pass);
			db.createUser(username, pass);
			JOptionPane.showMessageDialog(null, "Account has been successfulyl created. Please log in now.");
			
		}
		
		
	}

}
