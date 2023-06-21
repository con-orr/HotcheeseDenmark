import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Color;
/**
 * Write a description of class EnemyBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBullet extends Actor
{
    //this class goes towarrds the player and checks if it hits a wall
    Color wall = new Color(0,0,0);
    public void addedToWorld(World world){
        turnTowards(MyWorld.p1.getX(),MyWorld.p1.getY());
    }
    public void act()
    {
        // Add your action code here.
        move(8);
        if(getWorld().getColorAt(getX(),getY()).equals(wall)||getX()<0||getX()>=getWorld().getWidth()||getY()>=getWorld().getHeight()||getY()<0){
            getWorld().removeObject(this);
        }
    }
    
}

