package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

public class FishGameTest {
    @Test
    public void FishmeetTest(){
        FishGame a = new FishGame();
        a.add(new Fishes(null));
        a.add(new Fishes(null));
        a.add(new Fishes(null));
        a.add(new Fishes(null));
        ArrayList<AllObject> b = a.getObjectStorage();
        b.get(0).setX(11);
        b.get(1).setX(11);
        b.get(0).setY(11);
        b.get(1).setY(11);
        assertEquals(b.get(0).getX(), b.get(1).getX());
        assertEquals(b.get(0).getY(), b.get(1).getY());
        a.Fishmeet();
        assertNotEquals(b.get(0).getX(), b.get(1).getX());
    }

    @Test
    public void blockingAreaTest(){
        FishGame a = new FishGame();
        a.add(new Obstacles());
        ArrayList<AllObject> b = a.getObjectStorage();
        a.blockingArea();
        //can't test
    }

    @Test
    public void addTest(){
        FishGame a = new FishGame();
        a.add(new Obstacles());
        ArrayList<AllObject> b = a.getObjectStorage();
        b.get(1).setX(2);
        assertEquals(b.get(1).getY(),2);
    }

    @Test
    public void removeTest(){
        FishGame a = new FishGame();
        var b = new Obstacles();
        a.remove(b);
        ArrayList<AllObject> c = a.getObjectStorage();
        assertEquals(1, c.size());
    }

    @Test
    public void enableCheatModeTest(){
        FishGame a = new FishGame();
        enableCheatModeTest();
        assertEquals(true, a.getIsCheatModeOn()); 
    }

    @Test
    public void disbleCheatModeTest(){
        FishGame a = new FishGame();
        disbleCheatModeTest();
        assertEquals(false, a.getIsCheatModeOn()); 
    }

    @Test
    public void addSharkTest(){
        FishGame a = new FishGame();
        a.addShark();
        assertEquals(a.getObjectStorage().get(1) instanceof Shark, true); 
    }

    @Test
    public void sharkPassTest(){
        FishGame a = new FishGame();
        a.addShark();
        a.sharkPass();
        assertEquals(a.getObjectStorage().get(1) instanceof Shark, true); 
    }

}
