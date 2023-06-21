import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WoodenBat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WoodenBat extends Actor
{
    int frames = 10; // time fist png is on screen when player attacks
    public WoodenBat(int rotation){
        turn(rotation);
    }
    /**
     * Act - do whatever the melee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        frames--;
        if (frames <= 0) {
            getWorld().removeObject(this);
        }
    }
}
