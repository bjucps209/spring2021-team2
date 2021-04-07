package model;


public class Level {
    
    private static Level Singletonlevel = null;

    private static int level;

    Level(){
        level = 1;
    }

    public static Level Instance(){
        if (Singletonlevel == null){
            Singletonlevel = new Level();
        }
        return Singletonlevel;
    }

    public static void Levelincrease(){
        level += 1;
    }

    public static int getLevel() {
        return Level.level;
    }

    public static void setLevel(int level) {
        Level.level = level;
    }
}
