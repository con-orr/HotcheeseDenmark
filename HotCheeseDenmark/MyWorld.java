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
        addObject(new Gun(),700,60);
        addObject(new Gun(),107,383);
        addObject(new Gun(),683,380);
    }

    public void act(){
        for (int i = 0; i < 8; i++) {
            randX = Greenfoot.getRandomNumber(getWidth());
            randY = Greenfoot.getRandomNumber(getHeight());
            if (i == 1) { //good old else if chain never hurt anyone 
                addObject(e1,randX,randY);
            }
            else if (i == 2) {
                addObject(e2, randX, randY);
            }
            else if (i == 3) {
                addObject(e3, randX, randY);
            }
            else if (i == 4) {
                addObject(e4, randX, randY);
            }
            else if (i == 5) {
                addObject(e5, randX, randY);
            }
            else if (i == 6) {
                addObject(e6, randX, randY);
            }else if (i == 7) {
                addObject(e7, randX, randY);
            }
            else if (i == 8) {
                addObject(e8, randX, randY);
            }
        }
    }
}
