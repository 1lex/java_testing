package ru.stqa.pft.sandbox;

/**
 * Created by Lex on 29.11.2016.
 */
public class Point {
   public double x;
   public double y;

   public Point (double x, double y) {
      this.x = x;
      this.y = y;
   }

   public double distance (Point p) {
      return Math.sqrt((p.x * p.x - 2 * this.x * p.x + this.x * this.x) + (p.y * p.y - 2 * this.y * p.y + this.y * this.y));
   }
}
