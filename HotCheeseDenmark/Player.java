import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    int deltaX = 0; // x velocity
    int deltaY = 0; // y velocity
    boolean isHit = false; // checks if player is damaged
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // movement keys
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - 3);
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + 3);
        }
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - 3, getY());
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + 3, getY());
        }
        // rotating player
    }
}
