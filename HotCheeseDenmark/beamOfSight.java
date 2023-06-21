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
    Color wall = new Color(0,0,0);
    public beamOfSight(Enemy spawn){
        this.spawn=spawn;
        //fancy way of checking which enemy the beam belongs to
    }

    /**
     * Act - do whatever the beamOfSight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        //checks if it hits a player,wall,or edge of world first
        while(true){ //everything gets done in one frame
            if(getX()<0||getX()>=getWorld().getWidth()||getY()>=getWorld().getHeight()||getY()<0){
                break;
            }
            if(isTouching(Player.class)){
                spawn.seePlayer=60;
                break;
            }
            if(getWorld().getColorAt(getX(),getY()).equals(wall)){
                break;
            }
            turnTowards(MyWorld.p1.getX(),MyWorld.p1.getY());
            move(1);
        }
        getWorld().removeObject(this);
    }

}
