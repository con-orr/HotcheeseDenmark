import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Color;
/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{ 
    MouseInfo mouse = Greenfoot.getMouseInfo();
    Color wall = new Color(0,0,0);
    
    public void addedToWorld(World world){
        turnTowards(mouse.getX(), mouse.getY());
    }
        
    public void act()
    {
        // Add your action code here.
        move(10);
        if(getWorld().getColorAt(getX(),getY()).equals(wall)||getX()<0||getX()>=getWorld().getWidth()||getY()>=getWorld().getHeight()||getY()<0){
            getWorld().removeObject(this);
        }
    }
}
