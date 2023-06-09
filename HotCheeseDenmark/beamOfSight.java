import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class beamOfSight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class beamOfSight extends Actor
{
    Enemy spawn;
    public beamOfSight(Enemy spawn){
        this.spawn=spawn;
        int px = MyWorld.p1.getX();
        int py = MyWorld.p1.getY();
    }
    /**
     * Act - do whatever the beamOfSight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(MyWorld.p1.getX()>getX()){
            setLocation(getX()+2,getY());
        }
        if(MyWorld.p1.getX()<getX()){
            setLocation(getX()-2,getY());
        }
        if(MyWorld.p1.getY()>getY()){
            setLocation(getX(),getY()+2);
        }
        if(MyWorld.p1.getY()<getY()){
            setLocation(getX(),getY()-2);
        }
        if(isTouching(Player.class)){
            spawn.seePlayer=60;
        }
        if(isTouching(wall.class)){
            getWorld().removeObject(this);
        }
        
    }
}
