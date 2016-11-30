package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Lex on 01.12.2016.
 */
public class PointTests {
   @Test
   public void testDistance1() {
      Point p1 = new Point (0,0);
      Point p2 = new Point (0,1);
      Assert.assertEquals(p1.distance(p2),1.0);

   }
   @Test
   public void testDistance2() {
      Point p1 = new Point (1,0);
      Point p2 = new Point (-1,0);
      Assert.assertEquals(p1.distance(p2),2.0);
   }
   @Test
   public void testDistance3() {
      Point p1 = new Point (0,-1);
      Point p2 = new Point (-1,-1);
      Assert.assertEquals(p1.distance(p2),2.0);
   }
}
