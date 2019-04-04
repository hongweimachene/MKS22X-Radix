import java.util.*;
public class Radix{
  @SuppressWarnings("unchecked")
  public static void radixsort(int[]data){
    MyLinkedList<Integer>[] buckets =  new MyLinkedList[20];
    MyLinkedList order = new MyLinkedList();
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new MyLinkedList<Integer>();
    }
    int times = nDigits(data);
    int mod = 10;
    for (int i = 0; i < times; i++) {
      for (int j = 0; j < data.length; j++) {
        int digit = Math.abs(data[j] % mod);
        if (data[j] < 0) {
          buckets[9-Math.abs(digit)].addFront(data[j]);
        } else {
          buckets[digit+10].add(data[j]);
        }
      }
      mod*=10;
      for (int h = 0; h < buckets.length; h++) {
        order.extend(buckets[h]);
      }
      for (int x = 0; x < data.length; x++){
        data[x] = (int)order.removeFront();
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
