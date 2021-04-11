package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class FishesTest {

    // except eatTest method, all other method involve random, therefore, 
    // it is possible random 0, then the test will not pass.

    @Test
    public void ChangeSpeedAndDirectionTest() {
        Fishes fish = new Fishes(Type.FishType1, 2, 3, 100);
        fish.setDirection(188);
        fish.ChangeSpeedAndDirection();
        assertNotEquals(fish.getDirection(), 188);
        assertNotEquals(fish.getSpeed(), 2);
    }

    @Test
    public void randDirectionTest() {
        Fishes fish = new Fishes(Type.FishType1, 2, 3, 100);
        fish.setDirection(188);
        fish.randDirection();
        assertNotEquals(fish.getSpeed(), 188);
        // Random test is not able to test. I can't know what value it will return for
        // now.
        // Test case may happen when method is written
    }

    @Test
    public void randSpeedTest() {
        Fishes fish = new Fishes(Type.FishType1, 2, 3, 100);
        fish.randSpeed();
        assertNotEquals(fish.getSpeed(), 2);
        // Random test is not able to test. I can't know what value it will return for
        // now.
        // Test case may happen when method is written
    }

    @Test
    public void eatTest() {
        Fishes fish = new Fishes(Type.FishType1, 2, 3, 100);
        Fishes fish2 = new Fishes(Type.FishType1, 2, 2, 100);
        Fishes fish3 = new Fishes(Type.Mine, 2, 4, 60);
        Fishes fish4 = new Fishes(Type.Mine, 2, 2, 60);
        var ssss = fish2.eat(fish4);
        AllObject[] a = new AllObject[1];
        a[0] = fish2;
        AllObject[] b = new AllObject[2];
        b[0] = fish;
        b[1] = fish3;
        assertEquals(a, fish.eat(fish2));
        assertEquals(b, fish.eat(fish3));
        // Random test is not able to test. I can't know what value it will return for
        // now.
        // Test case may happen when method is written
    }

}
