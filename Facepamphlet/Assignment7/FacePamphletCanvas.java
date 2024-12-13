
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */

	public FacePamphletCanvas() {

	}

	/**
	 * This method displays a message string near the bottom of the canvas.
	 * Every time this method is called, the previously displayed message (if
	 * any) is replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		GLabel programMassage = new GLabel(msg);
		programMassage.setFont(MESSAGE_FONT);
		add(programMassage, getWidth() / 2 - programMassage.getWidth() / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the
	 * bottom of the screen) and then the given profile is displayed. The
	 * profile display includes the name of the user from the profile, the
	 * corresponding image (or an indication that an image does not exist), the
	 * status of the user, and a list of the user's friends in the social
	 * network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		//Name.
		GLabel name = new GLabel(profile.getName());
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name, LEFT_MARGIN, TOP_MARGIN + name.getHeight() / 4);
		//Picture.
		if (profile.getImage() == null) {//Profile does not have picture.
			GRect rect = new GRect(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(rect);
			GLabel imageLabel = new GLabel("No Image");
			imageLabel.setFont(PROFILE_IMAGE_FONT);
			add(imageLabel, LEFT_MARGIN + IMAGE_WIDTH / 2 - imageLabel.getWidth() / 2,
					TOP_MARGIN + IMAGE_HEIGHT / 2 + imageLabel.getHeight() / 2);
		} else {//Profile has new picture.
			GImage profImage = profile.getImage();
			profImage.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(profImage, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
		}
		//Status.
		if (profile.getStatus().equals("")) {//Profile does not have status .
			GLabel status = new GLabel("No current status");
			status.setFont(PROFILE_STATUS_FONT);
			add(status, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN);
		} else {//Profile has new status.
			GLabel status = new GLabel(profile.getName() + " is " + profile.getStatus());
			status.setFont(PROFILE_STATUS_FONT);
			add(status, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN);
		}
		//FrienList label.
		GLabel friendsLabel = new GLabel("Friends:");
		friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendsLabel, getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN + friendsLabel.getHeight() / 2);
		//Friends list.
		Iterator<String> friendsIterator = profile.getFriends();
		double starty = TOP_MARGIN + IMAGE_MARGIN + friendsLabel.getHeight() + 10;
		while (friendsIterator.hasNext()) {
			String friend = friendsIterator.next();
			GLabel friendName = new GLabel(friend);
			friendName.setFont(PROFILE_FRIEND_FONT);
			add(friendName, getWidth() / 2, starty);
			starty = starty + friendName.getHeight();
		}

	}

}
