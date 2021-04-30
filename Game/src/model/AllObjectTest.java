//--------------------------------------------------------------------
//File:   AllObjectTest.java
//Desc:   Unit test for all method in AllObject.java
//By:     Shubin Yuan
//-------------------------------------------------------------------
package model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AllObjectTest {
    @Test
    // All the object can extends allobjec class will be able to updataPosition

    public void updatePositionTest() {
        AllObject a = new Fishes(Type.FishType1, 5, 10, 70);
        a.setX(100);
        a.setY(100);
        a.setDirection(180);
        a.updatePosition();
        assertEquals(new SimpleIntegerProperty(95).get(), a.getX().get());
    }

    @Test
    public void drawTrangleTest() {
        // Don't need test
    }
}
