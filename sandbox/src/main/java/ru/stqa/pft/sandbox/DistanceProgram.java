package ru.stqa.pft.sandbox;

/**
 * Created by Lex on 29.11.2016.
 */
public class DistanceProgram {
   public static void main (String args[]) {
      Point p1 = new Point(5,7);
      Point p2 = new Point(6,6);
      System.out.println("Расстояние между точками (" + p1.x + "," + p1.y + ") и (" + p2.x + "," + p2.y + "), вычисленное с помощью метода равно " + p1.distance(p2));
   }
}
