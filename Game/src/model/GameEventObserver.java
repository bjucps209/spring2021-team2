//--------------------------------------------------------------------
//File:   GameEventObserver.java
//Desc:   Observer pattern, use to pass code from view to model
//        purpose for handel sound effects
//By:     Shubin Yuan
//-------------------------------------------------------------------
package model;

public interface GameEventObserver {

    public void gameOver();

    public void EatingSound();

    public void GrowSound();

    public void loseSound();

    public void winSound();
}
