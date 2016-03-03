package search;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import utilities.HeapWithAugment;
public class Median { 
  private static HeapWithAugment minHeap, maxHeap;
  public static void main1(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    minHeap = new HeapWithAugment(n, true);
    maxHeap = new HeapWithAugment(n, false);
    while(n-->0){
      String str = sc.next();
      int num = sc.nextInt();       
      if(str.equalsIgnoreCase("a")){
        add(num);
      }else{
        remove(num);
      }
    }
    sc.close();
  }
  
  
  public static void add(int a){
    if(maxHeap.size()>0 && maxHeap.peek()>=a){
      maxHeap.insert(a);
    }else{
      minHeap.insert(a);
    }
    balance();
    median();
  }
  
  public static void remove(int a){
    // remove a value a and return mediam
    if((maxHeap.size()>0 && maxHeap.peek()>=a && maxHeap.pop(a))
        ||(minHeap.size()>0 && minHeap.peek()<=a && minHeap.pop(a))){
      balance();
      median();
    }else{
      System.out.println("Wrong!");
    }
  }
  
  public static void balance(){
    if(maxHeap.size()-minHeap.size()>1){
      minHeap.insert(maxHeap.pop());
    }else if(maxHeap.size()-minHeap.size()<-1){
      maxHeap.insert(minHeap.pop());
    }
  }
  
  public static void median(){
    if(maxHeap.size()!=0 && maxHeap.size()==minHeap.size()){
      double median = (maxHeap.peek()*1.0+minHeap.peek()*1.0)/2;
      if(median!=Math.ceil(median)){
        System.out.format("%.1f%n", median);
      }else{
        System.out.println((int)median);
      }    
    }else if(maxHeap.size()-minHeap.size()==1){
      System.out.println(maxHeap.peek());
    }else if(maxHeap.size()-minHeap.size()==-1){
      System.out.println(minHeap.peek());
    }else{
      System.out.println("Wrong!");
    }
  }
  
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    MedianWithUpdates mwu = new MedianWithUpdates();
    while(n-->0){
      String str = sc.next();
      int num = sc.nextInt();       
      if(str.equalsIgnoreCase("a")){
        mwu.add(num);
      }else{
        mwu.remove(num);
      }
    }
    sc.close();
  }

}

class MedianWithUpdates{
  private ArrayList<Integer> list = new ArrayList<Integer>();
  public void add(int a){
    if(list.isEmpty()){
      list.add(a);
    }else {
      int pos = Collections.binarySearch(list, a);
      if(pos>=0){
        list.add(pos, a);
      }else{
        list.add(-pos-1, a);
      }
    }
    median();
  }
  
  public void remove(int a){
    int pos = Collections.binarySearch(list, a);
    if(pos>=0){
      list.remove(pos);
      median();
    }else{
      System.out.println("Wrong!");
    }
  }
  
  public void median(){
    if(list.size()>0){
      if((list.size()&1)==1){
        System.out.println(list.get(list.size()/2));
      }else{
        double median = (list.get(list.size()/2)*1.0+list.get(list.size()/2-1)*1.0)/2;
        if(median!=Math.ceil(median)){
          System.out.format("%.1f%n", median);
        }else{
          System.out.println((int)median);
        }    
      }
    }else{
      System.out.println("Wrong!");
    }
  }
  
  
}

