package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		hello("people");
      Square s = new Square(5);
      Rectangle r = new Rectangle(2,3);
      System.out.println(s.area());
      System.out.println(r.area());
   }

	public static void hello(String somebody) {
      System.out.println("Hello, " + somebody + "!");
   }

}