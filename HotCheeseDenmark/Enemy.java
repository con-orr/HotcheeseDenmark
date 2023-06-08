import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    int health = 10;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(MyWorld.p1.getX()>getX()){
            setLocation(getX()+4,getY());
        }
        if(MyWorld.p1.getX()<getX()){
            setLocation(getX()-4,getY());
        }
        if(MyWorld.p1.getY()>getY()){
            setLocation(getX(),getY()+4);
        }
        if(MyWorld.p1.getY()<getY()){
            setLocation(getX(),getY()-4);
        }
    }
}
