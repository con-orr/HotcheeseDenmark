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
    String direction = "a"; // for rotating character
    String pDirection = "w"; // for rotating punch sprite
    int deltaX = 0; // x velocity
    int deltaY = 0; // y velocity
    boolean isHit = false; // checks if player is damaged
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
        playerMovement();
        playerMelee();
    }
    // player movement method
    public void playerMovement() {
        // straight direction movement
        if (Greenfoot.isKeyDown("w") && playerDirection[3]) {
            setLocation(getX(), getY() - 3);
            pDirection = "w";
            setRotation(90);
        }
        if (Greenfoot.isKeyDown("s") && playerDirection[2]) {
            setLocation(getX(), getY() + 3);
            pDirection = "s";
            setRotation(-90);
        }
        if (Greenfoot.isKeyDown("a") && playerDirection[1]) {
            setLocation(getX() - 3, getY());
            pDirection = "a";
            setRotation(0);
        }
        if (Greenfoot.isKeyDown("d") && playerDirection[0]) {
            setLocation(getX() + 3, getY());
            pDirection = "d";
            setRotation(180);
        }
        // diagonal direction movement
        if (Greenfoot.isKeyDown("a") && Greenfoot.isKeyDown("w") && playerDirection[1] && playerDirection[3]) {
            setLocation(getX() - 3, getY() - 3);
            setRotation(45);
        }
        if (Greenfoot.isKeyDown("w") && Greenfoot.isKeyDown("d") && playerDirection[3] && playerDirection[0]) {
            setLocation(getX() + 3, getY() - 3);
            setRotation(135);
        }
        if (Greenfoot.isKeyDown("d") && Greenfoot.isKeyDown("s") && playerDirection[0] && playerDirection[2]) {
            setLocation(getX() + 3, getY() + 3);
            setRotation(225);
        }
        if (Greenfoot.isKeyDown("a") && Greenfoot.isKeyDown("s") && playerDirection[1] && playerDirection[2]) {
            setLocation(getX() - 3, getY() + 3);
        }
    }
    // enables clipping with map
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
    // enables player melee
    public void playerMelee() {
        if (Greenfoot.isKeyDown("space") && cooldown > 30) {
            if (pDirection == "w") {
                Actor actor = new melee(90);
                getWorld().addObject(actor, getX(), getY() - 30); 
            }
            if (pDirection == "s") {
                Actor actor = new melee(-90);
                getWorld().addObject(actor, getX(), getY() + 30);
            }
            if (pDirection == "a") {
                Actor actor = new melee(0);
                getWorld().addObject(actor, getX() - 30, getY());
            }
            if (pDirection == "d") {
                Actor actor = new melee(180);
                getWorld().addObject(actor, getX() + 30, getY());
            }
            cooldown = 0;
        }
    }
}
