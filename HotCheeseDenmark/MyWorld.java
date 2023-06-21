import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    static Player p1;//just made making all of the enemy code faster for me
    Enemy e1; //could have just used new enemy() instead of setting all of this up but it's 11pm so either someone can change this in the morning or eh its fine
    Enemy e2;
    Enemy e3; 
    Enemy e4;
    Enemy e5;
    Enemy e6;
    Enemy e7;
    Enemy e8;
    int randX = Greenfoot.getRandomNumber(getWidth());
    int  randY = Greenfoot.getRandomNumber(getHeight());
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(770, 460, 1); 
        p1 = new Player();
        addObject(p1,400,400);
        e1 = new Enemy();
        e2 = new Enemy();
        e3 = new Enemy();
        e4 = new Enemy();
        e5 = new Enemy();
        e6 = new Enemy();
        e7 = new Enemy();
        e8 = new Enemy();
    }

    public void act(){
        for (int i = 0; i < 8; i++) {
            randX = Greenfoot.getRandomNumber(getWidth());
            randY = Greenfoot.getRandomNumber(getHeight());
            addObject(new Enemy(), randX, randY);
        }
    }
}
