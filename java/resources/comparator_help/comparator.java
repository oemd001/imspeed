/*
Comparator Help/Example
*/

//imporant to knows: 
/*
1 -> o1 > o2 implies ascending order
1 -> o1 < o2 implies descending order
*/
import java.util.*;

public class comparator {
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(305);
    list.add(998);
    list.add(774);
    list.add(236);
    list.add(881);

    //this sorts the list in ascending order --> Collections.sort(list);
    //this sorts the list in reverse order (based on values) --> Collections.reverse(list);

    //what if we want to sort based on the last value? the "9" in 309, the "8" in 998
    //Comparator: modifying the logic of the sorting algorithm
    //This isn't "leetcode friendly" as we need to create another file to do that
    Comparator<Integer> com = new ComImpl();
    

    //anonymous class method without lambda expression
    Comparator<Integer> com2 = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        /*
        o1 is the first value, o2 is the second value
        if we want to compare the last values of a number, we can simply do 
        o1 % 10 > o2 % 10. This would compare based on the last values of a number
        */
        if (o1 % 10 > o2 % 10) {
          return 1; 
        }
        return -1;
        }
    };

    //anonymous class method with lambda expression
    Comparator<Integer> com3 = (o1, o2) -> {
      if (o1 % 10 > o2 %10) {
        return 1; 
      }
      return -1;
    };

    //anonymous class method with terninary operators
    Comparator<Integer> com4 = (o1, o2) -> {
      return o1 % 10 > o2 % 10 ? 1 : -1;
    };

    //or a simple "one liner"
    Collections.sort(list, (o1, o2) -> {
      return o1 % 10 > o2 % 10 ? 1 : -1;
    });
    
    for (int i : list) {
      System.out.println(i);
    }
  }
}