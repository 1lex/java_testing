package ru.stqa.pft.sandbox;

/**
 * Created by Lex on 29.11.2016.
 */
public class Square {
   public double l;

   public Square(double le) {
      this.l = le;
   }
   public double area () {
      return this.l * this.l;
   }
}
