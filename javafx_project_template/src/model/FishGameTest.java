package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;

public class FishGameTest {
    @Test
    public void FishmeetTest(){
        FishGame a = new FishGame(3, 5, 20, 15, 10, 5, 3);
        ArrayList<AllObject> b = a.getObjectStorage();
        b.get(0).setX(new SimpleIntegerProperty(11) );
        b.get(1).setX(new SimpleIntegerProperty(11));
        b.get(0).setY(new SimpleIntegerProperty(11));
        b.get(1).setY(new SimpleIntegerProperty(11));
        assertEquals(b.get(0).getX(), b.get(1).getX());
        assertEquals(b.get(0).getY(), b.get(1).getY());
        a.Fishmeet();
        assertNotEquals(52,a.getObjectStorage().size());
    }

    @Test
    // not using yet
    public void blockingAreaTest(){
        FishGame a = new FishGame(3, 5, 20, 15, 10, 5, 3);
        a.add(new Obstacles(10, 100));
        ArrayList<AllObject> b = a.getObjectStorage();
        a.blockingArea();
        //can't test
    }

    @Test
    public void addTest(){
        FishGame a = new FishGame(3, 5, 20, 15, 10, 5, 3);
        a.addShark();
        assertEquals(a.getObjectStorage().size(), 54);
    }

    @Test
    public void removeTest(){
        FishGame a = new FishGame(3, 5, 20, 15, 10, 5, 3);
        Fishes b = new Fishes(Type.FishType2, 6, 2, 100);
        a.add(b);
        assertEquals(a.getObjectStorage().size(), 54);
        a.remove(b);
        assertEquals(a.getObjectStorage().size(), 53);
    }

    @Test
    public void enableCheatModeTest(){
        FishGame a = new FishGame();
        a.enableCheatMode();
        assertEquals(true, a.getIsCheatModeOn()); 
    }

    @Test
    public void disbleCheatModeTest(){
        FishGame a = new FishGame();
        a.disableCheatMode();
        assertEquals(false, a.getIsCheatModeOn()); 
    }

    @Test
    public void addSharkTest(){
        FishGame a = new FishGame();
        a.addShark();
        assertEquals(a.getObjectStorage().get(0) instanceof Shark, true); 
    }

    @Test
    public void circleCheckingTest(){
        int[] a = new int[]{30, 30, 10};
        int[] b = new int[]{60, 60, 5};
        assertEquals(FishGame.circleChecking(a, b), -1);
        int[] c = new int[]{40, 40, 5};
        assertEquals(FishGame.circleChecking(a, c), 0);
        
    }

    @Test
    public void userfishcollisionTest(){
        FishGame a = new FishGame(3, 5, 20, 15, 10, 5, 3);
        a.getObjectStorage().get(a.getObjectStorage().size()-1).setX(new SimpleIntegerProperty(450));
        a.getObjectStorage().get(a.getObjectStorage().size()-1).setY(new SimpleIntegerProperty(450));
        a.setLife(1);
        a.userfishcollision();
        assertEquals(a.getIsGameOver(), false);
        a.userfishcollision();
        a.userfishcollision();
        a.userfishcollision();
        a.userfishcollision();
        assertEquals(a.getIsGameOver(), true);
    }

}
