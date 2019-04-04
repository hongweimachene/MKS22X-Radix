import java.util.*;
  import java.util.Arrays;
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
        int digit = (int)Math.abs(data[j] % Math.pow(10, 1 + i) / Math.pow(10,i));
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


 //Sort testing code
  private static final int INCREASE = 0;
  private static final int DECREASE = 1;
  private static final int STANDARD = 2;
  private static final int SMALL_RANGE = 3;

  private static String name(int i){
    if(i==INCREASE)return "Increassing";
    if(i==DECREASE)return "Decreassing";
    if(i==STANDARD)return "Normal Random";
    if(i==SMALL_RANGE)return "Random with Few Values";
    return "Error categorizing array";

  }

  private static int create(int min, int max){
    return min + (int)(Math.random()*(max-min));
  }

  private static int[]makeArray(int size,int type){
    int[]ans =new int[size];
    if(type == STANDARD){
      for(int i = 0; i < size; i++){
        ans[i]= create(-1000000,1000000);
      }
    }
    else if(type == INCREASE){
      int current = -5 * size;
      for(int i = 0; i < size; i++){
        ans[i]= create(current,current + 10);
        current += 10;
      }
    }
    else if(type == DECREASE){
      int current = 5 * size;
      for(int i = 0; i < size; i++){
        ans[i]= create(current,current + 10);
        current -= 10;
      }
    }
    else if(type == SMALL_RANGE){
      for(int i = 0; i < size; i++){
        ans[i]= create(-5,5);
      }
    }
    else{
      ans = new int[0];//empty is default
    }
    return ans;
  }

  public static void main(String[]args){
    if(args.length < 2)return;

    int size =  Integer.parseInt(args[0]);
    int type =   Integer.parseInt(args[1]);

    int [] start = makeArray(size,type);
    int [] result = Arrays.copyOf(start,start.length);
    Arrays.sort(result);

    long startTime = System.currentTimeMillis();
    /*
     * Test your sort here //yoursort(start);
     * Add code to switch which sort is tested by changing one of the args!
     */
    radixsort(start);
    long elapsedTime = System.currentTimeMillis() - startTime;
    if(Arrays.equals(start,result)){
      System.out.println("PASS Case "+name(type)+"\t array, size:"+start.length+"\t"+elapsedTime/1000.0+"sec ");
    }else{
      System.out.println("FAIL ! ERROR ! "+name(type)+" array, size:"+size+"  ERROR!");
    }
  }
}
