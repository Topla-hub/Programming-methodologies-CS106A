
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {
	// Objects for interactors.
	JLabel nameLabel = new JLabel("Name");
	JTextField profileTextField = new JTextField(TEXT_FIELD_SIZE);
	JButton addButton = new JButton("Add");
	JButton deleteButton = new JButton("Delete");
	JButton lookUpButton = new JButton("Lookup");
	JTextField statusTextField = new JTextField(TEXT_FIELD_SIZE);
	JButton changeStatusButton = new JButton("Change Status");
	JTextField pictureTextField = new JTextField(TEXT_FIELD_SIZE);
	JButton changePictureButton = new JButton("Change Picture");
	JTextField friendTextField = new JTextField(TEXT_FIELD_SIZE);
	JButton addFriendButton = new JButton("Add Friend");
	// For adding classes.
	private FacePamphletDatabase profilesData;
	private FacePamphletProfile profile;
	private FacePamphletCanvas canvas;

	public void init() {
		buildInteractors();
		addActionListeners();
		statusTextField.addActionListener(this);
		pictureTextField.addActionListener(this);
		friendTextField.addActionListener(this);

		profilesData = new FacePamphletDatabase();

		canvas = new FacePamphletCanvas();
		add(canvas);

	}

	/**
	 * This method has the responsibility for initializing the interactors in
	 * the application, and taking care of any other initialization that needs
	 * to be performed.
	 */
	private void buildInteractors() {
		add(nameLabel, NORTH);
		add(profileTextField, NORTH);
		add(addButton, NORTH);
		add(deleteButton, NORTH);
		add(lookUpButton, NORTH);
		add(statusTextField, WEST);
		add(changeStatusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(pictureTextField, WEST);
		add(changePictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friendTextField, WEST);
		add(addFriendButton, WEST);
	}

	private String profileName = null;

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */

	public void actionPerformed(ActionEvent e) {
		profileName = profileTextField.getText();
		if (e.getSource() == addButton) {// Add implementation.
			// user's input check.
			if (!profileName.equals("")) {
				profile = new FacePamphletProfile(profileName);
				// profile existence check.
				if (!profilesData.containsProfile(profileName)) {
					profilesData.addProfile(profile);
					canvas.displayProfile(profilesData.getProfile(profileName));
					canvas.showMessage("New profile created");
				} else {
					canvas.displayProfile(profilesData.getProfile(profileName));
					canvas.showMessage("A profile with the name " + profileName + " already exists");
				}

			}
		} else if (e.getSource() == deleteButton) {// Delete implementation.
			// user's input check.
			if (!profileName.equals("")) {
				// profile existence check.
				if (!profilesData.containsProfile(profileName)) {
					canvas.removeAll();
					canvas.showMessage("This profile with that name does not exist");
				} else {
					profilesData.deleteProfile(profileName);
					canvas.removeAll();
					canvas.showMessage("A profile with the name " + profileName + " does not exists");
				}
			}
		} else if (e.getSource() == lookUpButton) {// Lookup implementation.
			// user's input check.
			if (!profileName.equals("")) {
				// profile existence check.
				if (!profilesData.containsProfile(profileName)) {
					canvas.removeAll();
					canvas.showMessage("A profile with the name " + profileName + " does not exists");
				} else {
					canvas.displayProfile(profilesData.getProfile(profileName));
					canvas.showMessage("Displaying " + profileName);
				}
			}
		} else if (e.getSource() == changeStatusButton || e.getSource() == statusTextField) {// Status
																								// implementation.
			String status = statusTextField.getText();
			// user's input check.
			if (!profileName.equals("")) {
				// profile existence check.
				if (!profilesData.containsProfile(profileName)) {
					canvas.removeAll();
					canvas.showMessage("profile doesnot exist. enter correct profile to change status");
				} else {
					profilesData.getProfile(profileName).setStatus(status);
					canvas.displayProfile(profilesData.getProfile(profileName));
					canvas.showMessage("Status updated to " + status);
				}
			} else if (profileName.equals("")) {// if user did not select any
												// profile.
				canvas.removeAll();
				canvas.showMessage("Please select a profile to change status");
			}
		} else if (e.getSource() == changePictureButton || e.getSource() == pictureTextField) {// Picture
																								// implementation.
			String pictureName = pictureTextField.getText();
			// user's input check.
			if (!pictureName.equals("") && !profileName.equals("")) {
				// profile existence check.
				if (!profilesData.containsProfile(profileName)) {
					canvas.removeAll();
					canvas.showMessage("profile doesnot exist. enter correct profile to change picture");
				} else {
					GImage image = null;
					try {
						image = new GImage(pictureName);
						profilesData.getProfile(profileName).setImage(image);
						canvas.displayProfile(profilesData.getProfile(profileName));
						canvas.showMessage("Picture updated");
					} catch (ErrorException ex) {
						canvas.displayProfile(profilesData.getProfile(profileName));
						canvas.showMessage("Unable to open image file: " + pictureName);
					}

				}
			} else if (profileName.equals("")) {// if user did not select any
												// profile.
				canvas.removeAll();
				canvas.showMessage("Please select a profile to change picture");
			}
		} else if (e.getSource() == addFriendButton || e.getSource() == friendTextField) {// Addfriend
																							// implementation.
			String friendProfileName = friendTextField.getText();
			// user's input check.
			if (!friendProfileName.equals("") && !profileName.equals("") && !profileName.equals(friendProfileName)) {
				// profile existence check.
				if (!profilesData.containsProfile(profileName)) {
					canvas.showMessage("profile doesnot exist. enter correct profile to add friend");
				} else {
					// friend's profile existence check.
					if (!profilesData.containsProfile(friendProfileName)) {
						canvas.displayProfile(profilesData.getProfile(profileName));
						canvas.showMessage("Profile doesnot exist. You cant add this profile as a friend");
					} else {
						// For checking if friend is already added for profile.
						ArrayList<String> friendsList = new ArrayList<>();
						Iterator<String> friendsIterator = profilesData.getProfile(profileName).getFriends();
						while (friendsIterator.hasNext()) {
							friendsList.add(friendsIterator.next());
						}
						if (friendsList.contains(friendProfileName)) {
							canvas.displayProfile(profilesData.getProfile(profileName));
							canvas.showMessage(profileName + " already has " + friendProfileName + " as a friend.");
						} else {
							profilesData.getProfile(profileName).addFriend(friendProfileName);
							profilesData.getProfile(friendProfileName).addFriend(profileName);
							canvas.displayProfile(profilesData.getProfile(profileName));
							canvas.showMessage(friendProfileName + " added as a friend");
						}

					}
				}
			} else if (profileName.equals("")) {// if user did not select any
												// profile.
				canvas.removeAll();
				canvas.showMessage("Please select a profile to add friend");
			}
		}
	}

}
