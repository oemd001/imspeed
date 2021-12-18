/*
string.split() keyword help
*/

import java.util.*;

class split {
  public static void main(String[] args) {
    String date = "10/15/1974";
    String[] birthday = date.split("/", 3);
    //position 0: "10"
    //position 1: 15/1974 
    for (int i = 0; i < birthday.length; i++) {
      System.out.println(birthday[i] + " " + i);
    }
  }
}