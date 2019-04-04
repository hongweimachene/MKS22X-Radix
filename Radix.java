import java.util.*;
public class Radix{
  @SuppressWarnings("unchecked")
  public static void radixsort(int[]data){
    //buckets are the possible digits from negative to positive
    MyLinkedList<Integer>[] buckets =  new MyLinkedList[20];
    //to store sorts by digits
    MyLinkedList order = new MyLinkedList();
    //initialize the array
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new MyLinkedList<Integer>();
    }
    //max number of digits in a value in data
    int times = nDigits(data);
    //running maximum number of digits times to sort
    for (int i = 0; i < times; i++) {
      //runs through array, takes the ith digit and puts into bucket
      for (int j = 0; j < data.length; j++) {
        int digit = Math.abs(data[j] % Math.pow(10, 1 + i) / Math.pow(10,i));
        //positives 10-19, negatives 0-9
        if (data[j] < 0) {
          buckets[9-Math.abs(digit)].add(data[j]);
        } else {
          buckets[digit+10].add(data[j]);
        }
      }
      //to store the ordered values in bucket
      for (int h = 0; h < buckets.length; h++) {
        order.extend(buckets[h]);
      }
      //change data into the new order
      for (int x = 0; x < data.length; x++){
        data[x] = (int)order.removeFront();
      }
    }

  }

  //function return the highest number of digits of a value in an array of ints
  public static int nDigits(int[]data) {
    int max = 0;
    for (int i = 0; i < data.length; i++){
      int digit = 1;
      int temp = 0;
      while (digit <= Math.abs(data[i])) {
        temp++;
        digit*=10;
      }
      if (temp >= max) {
        max = temp;
      }
    }
    return max;
  }
}
