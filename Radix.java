import java.util.*;
public class Radix{
  @SuppressWarnings("unchecked")
  public static void radixsort(int[]data){
    MyLinkedList<Integer>[] buckets =  new MyLinkedList[20];
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new MyLinkedList<Integer>();
    }
    int times = nDigits(data);
    int mod = 10;
    for (int i = 0; i < times; i++) {
      for (int j = 0; j < data.length; j++) {
        int digit = Math.abs(data[j] % mod);
        if (data[j] < 0) {
          buckets[9-digit].addFront(digit);
        } else {
          buckets[digit+10].add(digit);
        }
      }
    }

  }

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
