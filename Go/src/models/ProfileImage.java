/**
* Author: Nathan Forester
*/
package models; //connected to the game package

import java.io.Serializable; //import library to allow the functions within the class to compile and run properly

/**
* This class represents a long and a string input (serialVersionUID, ProfileImage). 
*/
public class ProfileImage implements Serializable { 
 private static final long serialVersionUID = 1L;
 private String URL;
 /**
 * Generate profile image.
 */
 public ProfileImage (String input) {
 this.URL = input; //the URL is found from input
 }
 /**
 * Get the URL.
 * @return Get the location or address of new image.
 */
 public String getURL () { //gets the location of the image
 return this.URL;
 }
 /**
 * 
 */
 public void setURL (String input) { //sets the location of the image
 this.URL = input;
 }
 /**
 * Return the URL.
 * @return The new image.
 */
 public String toString() { //returns the location and image
     return URL;
 }
}
