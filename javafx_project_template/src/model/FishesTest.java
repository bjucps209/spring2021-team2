package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class FishesTest {

    @Test
    public void ChangeSpeedAndDirectionTest() {
        Fishes fish = new Fishes();
        fish.ChangeSpeedAndDirection();
        assertNotEquals(fish.getDirection(), 0); // it should give the value to speed and direction,
        assertNotEquals(fish.getSpeed(), 0); // speed and direction should not be null any more.
        // not able to test changing value in here, because random can be 0; not change
        // happens
    }

    @Test
    public void randDirectionTest() {
        Fishes fish = new Fishes();
        fish.randDirection();
        // Random test is not able to test. I can't know what value it will return for now. 
        // Test case may happen when method is written
    }

    @Test
    public void randSpeedTest() {
        Fishes fish = new Fishes();
        fish.randSpeed();
        // Random test is not able to test. I can't know what value it will return for now. 
        // Test case may happen when method is written
    }

}
