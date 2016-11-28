package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		hello("people");
      double s = 5;
      double p = 6;
      System.out.println(area(s,p));
   }

	public static void hello(String somebody) {
      System.out.println("Hello, " + somebody + "!");
   }
   public static double area (double a) {
      return a * a;
   }
   public static double area (double l, double k) {
      return l * k;
   }
}