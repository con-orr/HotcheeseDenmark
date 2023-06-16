import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Color; // colors and stuff
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    char direction = 'r';
    int deltaX = 0; // x velocity
    int deltaY = 0; // y velocity
    boolean isHit = false; // checks if player is damaged\
    int cooldown = 0; // cooldown for attack
    boolean[] playerDirection = {true, true, true, true};
    //                           right left  down   up
    Color wall = new Color(0, 0, 0); // Color of wall (black)
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        playerDirectionDetection();
        cooldown++;
        // movement keys
        if (Greenfoot.isKeyDown("w") && playerDirection[3]) {
            setLocation(getX(), getY() - 3);
            direction = 'w';
        }
        if (Greenfoot.isKeyDown("s") && playerDirection[2]) {
            setLocation(getX(), getY() + 3);
            direction = 's';
        }
        if (Greenfoot.isKeyDown("a") && playerDirection[1]) {
            setLocation(getX() - 3, getY());
            direction = 'a';
        }
        if (Greenfoot.isKeyDown("d") && playerDirection[0]) {
            setLocation(getX() + 3, getY());
            direction = 'd';
        }
        if (getWorld().getColorAt(getX(), getY()).equals(wall)) {
            
        }
        // melee attack
        if (Greenfoot.isKeyDown("space") && cooldown > 30) {
            if (direction == 'w') {
                Actor actor = new melee(90);
                getWorld().addObject(actor, getX(), getY() - 30); 
            }
            if (direction == 's') {
                Actor actor = new melee(-90);
                getWorld().addObject(actor, getX(), getY() + 30);
            }
            if (direction == 'a') {
                Actor actor = new melee(0);
                getWorld().addObject(actor, getX() - 30, getY());
            }
            if (direction == 'd') {
                Actor actor = new melee(180);
                getWorld().addObject(actor, getX() + 30, getY());
            }
            cooldown = 0;
        }
        // rotating player
        if (Greenfoot.isKeyDown("left")) {
            turn(-5);
        }
        if (Greenfoot.isKeyDown("right")) {
            turn(5);
        }
    }
    public void playerDirectionDetection(){
        if(getX()+3<getWorld().getWidth()&& getX()-3>0 && getY()+3<getWorld().getHeight() && getY()-3>0){
            if(getWorld().getColorAt(getX()+3,getY()).equals(wall)){
                playerDirection[0]=false;
            }
            else{
                playerDirection[0]=true;
            }
            if(getWorld().getColorAt(getX()-3,getY()).equals(wall)){
                playerDirection[1]=false;
            }
            else{
                playerDirection[1]=true;
            }
            if(getWorld().getColorAt(getX(),getY()+3).equals(wall)){
                playerDirection[2]=false;
            }
            else{
                playerDirection[2]=true;
            }
            if(getWorld().getColorAt(getX(),getY()-3).equals(wall)){
                playerDirection[3]=false;
            }
            else{
                playerDirection[3]=true;
            }
        }
    }
}
