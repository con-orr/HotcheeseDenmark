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
    public void Bullet(){
        turnTowards(mouse.getX(), mouse.getY());
    }
    public void act()
    {
        // Add your action code here.
        for(int i =0; i<10; i++){
            move(1);
            if(getWorld().getColorAt(getX(),getY()).equals(wall)){
                getWorld().removeObject(this);
            }
        }
        
        
        
    }
}
