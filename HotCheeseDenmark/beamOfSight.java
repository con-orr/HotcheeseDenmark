import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Color;
/**
 * Write a description of class beamOfSight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class beamOfSight extends Actor
{
    Enemy spawn;
    int life = 120;
    Color wall;
    public beamOfSight(Enemy spawn){
        this.spawn=spawn;
        turnTowards(MyWorld.p1.getX(),MyWorld.p1.getY());
    }
    /**
     * Act - do whatever the beamOfSight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        while(true){
            if(getX()<0||getX()>=getWorld().getWidth()||getY()>=getWorld().getHeight()||getY()<0){
                break;
            }
            if(getWorld().getColorAt(getX(),getY()).equals(wall)){
                 break;    
            }
            if(isTouching(Player.class)){
                spawn.seePlayer=60;
                break;
            }
            move(1);
        }
        getWorld().removeObject(this);
    }
    public void addedToWorldat(World world){
        wall = getWorld().getColorAt(239,115);
    }
}
