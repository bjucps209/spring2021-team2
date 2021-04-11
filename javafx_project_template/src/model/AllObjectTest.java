package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AllObjectTest {
    @Test
    //All the object can extends allobjec class will be able to updataPosition

     public void updatePositionTest(){
        AllObject a = new Fishes(Type.FishType1, 5, 10, 70);
        a.setX(new SimpleIntegerProperty(100));
        a.setY(new SimpleIntegerProperty(100));
        a.setDirection(180);
        a.updatePosition();
        assertEquals(new SimpleIntegerProperty(95), a.getX());
    }

    @Test
    public void drawCircleTest(){
        AllObject a = new Fishes(Type.FishType1, 5, 10, 70);
        a.setX(new SimpleIntegerProperty(100));
        a.setY(new SimpleIntegerProperty(100));
        a.drawCircle();
        int[] b = new int[]{85, 85, 35};
        assertThat((a.drawCircle()), is(b));

    }

    
}
