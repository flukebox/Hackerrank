package java8;

import java.util.ArrayList;

public class Java8HandsOn {

  public static void main(String[] args){
    ArrayList<Person> plist = new ArrayList<Person>();
    
  }
}

class Calculator {
  interface IntegerMath {
      int operation(int a, int b);   
  }

  public int operateBinary(int a, int b, IntegerMath op) {
      return op.operation(a, b);
  }

}

class Person {
  public enum Sex {
      MALE, FEMALE
  }

  String name;
  Sex gender;
  String emailAddress;
  int age;
  
  public int getAge() {
    return age;
  }

  public void printPerson() {
    System.out.println("name="+name+", gender="+gender+", age="+age+", emailAddress="+emailAddress);
  }
}
