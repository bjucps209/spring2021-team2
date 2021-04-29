package model;

public interface GameEventObserver {

    public void gameOver();

    public void EatingSound();

    public void GrowSound();

    public void loseSound();

    public void winSound();
}
