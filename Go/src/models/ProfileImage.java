package models; 
public class ProfileImage {
 private String URL;
 public ProfileImage (String input) {
 this.URL = input;
 }
 public String getURL () {
 return this.URL;
 }
 public void setURL (String input) {
 this.URL = input;
 }
}
