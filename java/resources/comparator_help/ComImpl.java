import java.util.Comparator;

public class ComImpl implements Comparator<Integer> {
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
}