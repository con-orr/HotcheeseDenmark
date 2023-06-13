import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    char direction = 'r';
    int deltaX = 0; // x velocity
    int deltaY = 0; // y velocity
    boolean isHit = false; // checks if player is damaged\
    int cooldown = 0; // cooldown for attack
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        cooldown++;
        // movement keys
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - 3);
            direction = 'w';
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + 3);
            direction = 's';
        }
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - 3, getY());
            direction = 'a';
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + 3, getY());
            direction = 'd';
        }
        // melee attack
        if (Greenfoot.isKeyDown("space") && cooldown > 30) {
            if (direction == 'w') {
                Actor actor = new melee();
                getWorld().addObject(actor, getX(), getY() - 30);
            }
            if (direction == 's') {
                Actor actor = new melee();
                getWorld().addObject(actor, getX(), getY() + 30);
            }
            if (direction == 'a') {
                Actor actor = new melee();
                getWorld().addObject(actor, getX() - 30, getY());
            }
            if (direction == 'd') {
                Actor actor = new melee();
                getWorld().addObject(actor, getX() + 30, getY());
            }
            cooldown = 0;
        }
    }
}
