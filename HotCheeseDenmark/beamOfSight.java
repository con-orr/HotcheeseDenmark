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
    Color wall = getWorld().getColorAt(239,115);
    public beamOfSight(Enemy spawn){
        this.spawn=spawn;
    }
    /**
     * Act - do whatever the beamOfSight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        life--;
        getWorld().getColorAt(getX(),getY());
        
        if(MyWorld.p1.getX()>getX()){
            setLocation(getX()+20,getY());
        }
        if(MyWorld.p1.getX()<getX()){
            setLocation(getX()-20,getY());
        }
        if(MyWorld.p1.getY()>getY()){
            setLocation(getX(),getY()+20);
        }
        if(MyWorld.p1.getY()<getY()){
            setLocation(getX(),getY()-20);
        }
        if(isTouching(Player.class)){
            spawn.seePlayer=60;
        }
        if(getWorld().getColorAt(getX(),getY()).equals(wall)||life<=0){
            getWorld().removeObject(this);
        }
    }
}
