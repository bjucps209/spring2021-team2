//--------------------------------------------------------------------
//File:   FishGameTest.java
//Desc:   Main object Code FishGame unit test
//By:     Shubin Yuan except serialize method
//-------------------------------------------------------------------

package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;

public class FishGameTest {

    // test for updata number of fish
    @Test
    public void updatanumTest() {
        FishGame a = new FishGame(3, 5, 3, 3, 2, 1, 1);
        a.getObjectStorage().clear();
        a.setNumberOfFood(0);
        a.setNumberOfType1Fish(0);
        a.setNumberOfType2Fish(0);
        a.setNumberOfType3Fish(0);
        a.setNumberOfPoisonFish(0);
        a.updatanum();
        assertEquals(a.getObjectStorage().size(), 7);
        a.updatanum();
        assertEquals(a.getObjectStorage().size(), 10);
    }

    // test for fish out of screen
    @Test
    public void userFishOutOfScreenCheckerTest() {
        FishGame a = new FishGame(3, 5, 3, 3, 2, 1, 1);
        a.getUser().setX(10000);
        a.userFishOutOfScreenChecker();
        assertEquals(a.getUser().getX().get(), 1280);
        a.getUser().setX(-1111);
        a.userFishOutOfScreenChecker();
        assertEquals(a.getUser().getX().get(), -15);
        a.getUser().setY(10000);
        a.userFishOutOfScreenChecker();
        assertEquals(a.getUser().getY().get(), 535);
        a.getUser().setY(-1111);
        a.userFishOutOfScreenChecker();
        assertEquals(a.getUser().getY().get(), -105);
    }

    // test for add method
    @Test
    public void addTest() {
        FishGame a = new FishGame(3, 5, 20, 15, 10, 5, 3);
        a.addShark();
        assertEquals(a.getObjectStorage().size(), 54);
    }

    // test for add remove method
    @Test
    public void removeTest() {
        FishGame a = new FishGame(3, 5, 20, 15, 10, 5, 3);
        Fishes b = new Fishes(Type.FishType2, 6, 2, 100);
        a.add(b);
        assertEquals(a.getObjectStorage().size(), 54);
        a.remove(b);
        assertEquals(a.getObjectStorage().size(), 53);
    }

    // test for cheat mode test
    @Test
    public void enableCheatModeTest() {
        FishGame a = new FishGame();
        a.enableCheatMode();
        assertEquals(true, a.getIsCheatModeOn());
    }

    // test for cheat model test
    @Test
    public void disbleCheatModeTest() {
        FishGame a = new FishGame();
        a.disableCheatMode();
        assertEquals(false, a.getIsCheatModeOn());
    }

    // test for add shark method
    @Test
    public void addSharkTest() {
        FishGame a = new FishGame();
        a.addShark();
        assertEquals(a.getObjectStorage().get(0) instanceof Shark, true);
    }

    // test for circlechecking mothed
    @Test
    public void circleCheckingTest() {
        int[] a = new int[] { 30, 30, 10 };
        int[] b = new int[] { 60, 60, 5 };
        assertEquals(FishGame.circleChecking(a, b), -1);
        int[] c = new int[] { 40, 40, 5 };
        assertEquals(FishGame.circleChecking(a, c), 0);

    }

    // test for userfish collison method
    @Test
    public void userfishcollisionTest() {
        FishGame a = new FishGame(3, 5, 3, 3, 2, 1, 1);
        a.getObjectStorage().get(0).setX(615);
        a.getObjectStorage().get(0).setY(180);
        assertEquals(a.getObjectStorage().size(), 10);
        a.getObjectStorage().get(8).setX(615);
        a.getObjectStorage().get(8).setY(180);
        // untestable after view involve sound effects.
    }

    @Test
    // not using yet
    public void blockingAreaTest() {
        FishGame a = new FishGame(3, 5, 20, 15, 10, 5, 3);
        a.add(new Obstacles(10, 100));
        ArrayList<AllObject> b = a.getObjectStorage();
        a.blockingArea();
        // can't test
    }

    @Test
    public void situationHandleTest() {
        // do not need
    }

    // called the interface that involve view sound effect not able to test
    @Test
    public void increaseSizeCheckerTest() {
        // called the interface that involve view sound effect not able to test
    }

    // called the interface that involve view sound effect and game over event
    // not able to test
    @Test
    public void healthAndLifeCheckerTest() {

    }

    // test for min import
    @Test
    public void mineImporttest() {
        FishGame a = new FishGame();
        a.mineImport();
        assertEquals(a.getObjectStorage().get(0) instanceof Mine, true);
    }

}
