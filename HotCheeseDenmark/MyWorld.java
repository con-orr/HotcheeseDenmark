import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    static Player p1;
    Enemy e1;
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
        super(770, 460, 1,false); 
        p1 = new Player();
        addObject(p1,400,400);
        e1 = new Enemy();
        addObject(e1,randX,randY);
        e2 = new Enemy();
        addObject(e2,randX,randY);
        e3 = new Enemy();
        addObject(e3,randX,randY);
        e4 = new Enemy();
        addObject(e4,randX,randY);
        e5 = new Enemy();
        addObject(e5,randX,randY);
        e6 = new Enemy();
        addObject(e6,randX,randY);
        e7 = new Enemy();
        addObject(e7,randX,randY);
        e8 = new Enemy();
        addObject(e8,randX,randY);
    }

    public void act(){
        randX = Greenfoot.getRandomNumber(getWidth());
        randY = Greenfoot.getRandomNumber(getHeight());
    }
}
