package models;

import java.io.Serializable;

public class ProfileImage implements Serializable {
 private static final long serialVersionUID = 1L;
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
 public String toString() {
     return URL;
 }
}