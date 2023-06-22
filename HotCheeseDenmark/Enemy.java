import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Color;
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    //some of these might not be used I went through a lot of methods that I ended up ditching and I'm not sure if I left any variables
    int health = 10;
    int fireRate = 60;
    int seePlayer = 0;
    int beamTimer = 0;
    int immuneFrames = 30;
    Color wall = new Color(0,0,0);
    boolean[] aiDirection = {true,true,true,true};
    //                       right left down up
    String weapon = "fists";
    int speed = 4;
    int delay = 60;
    int[] playerCord = new int[2]; //not used explained in getMap method
    int[] enemyCord = new int[2];// not used explained in getMap method
    int moveTime;
    int randDirection;
    GreenfootImage GunEnemy = new GreenfootImage("EnemyGun.png");
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        aiDirectionDetection();
        sightAndMovement();
        getHit();
    }

    public void aiDirectionDetection(){
        //detects which directions the enemy can go without hitting walls
        if(getX()+4<getWorld().getWidth()&& getX()-4>0 && getY()+4<getWorld().getHeight() && getY()-4>0){
            if(getWorld().getColorAt(getX()+4,getY()).equals(wall)){ 
                aiDirection[0]=false;
            }
            else{
                aiDirection[0]=true;
            }
            if(getWorld().getColorAt(getX()-4,getY()).equals(wall)){
                aiDirection[1]=false;
            }
            else{
                aiDirection[1]=true;
            }
            if(getWorld().getColorAt(getX(),getY()+4).equals(wall)){
                aiDirection[2]=false;
            }
            else{
                aiDirection[2]=true;
            }
            if(getWorld().getColorAt(getX(),getY()-4).equals(wall)){
                aiDirection[3]=false;
            }
            else{
                aiDirection[3]=true;
            }
        }
    }

    public void chase(int speed){
        //makes the enemy chase the player at a determined speed
        if(MyWorld.p1.getX()>getX()&&aiDirection[0]){
            setLocation(getX()+speed,getY());
        }
        if(MyWorld.p1.getX()<getX()&&aiDirection[1]){
            setLocation(getX()-speed,getY());
        }
        if(MyWorld.p1.getY()>getY()&&aiDirection[2]){
            setLocation(getX(),getY()+speed);
        }
        if(MyWorld.p1.getY()<getY()&&aiDirection[3]){
            setLocation(getX(),getY()-speed);
        }
    }

    public void idle(){
        // this method just makes the enemy randomly walk around when it doesn't see the player
        aiDirectionDetection();
        if(moveTime<=0){
            moveTime=30;
            randDirection = Greenfoot.getRandomNumber(4);
        }
        moveTime--;
        if(moveTime>0){
            if(randDirection==0 && aiDirection[0]){
                setLocation(getX()+(speed/2),getY());
            }
            else if(randDirection==1 && aiDirection[1]){
                setLocation(getX()-(speed/2),getY());
            }
            else if(randDirection==2 && aiDirection[2]){
                setLocation(getX(),getY()+(speed/2));
            }
            else if(randDirection==3 && aiDirection[3]){
                setLocation(getX(),getY()-(speed/2));
            }
        }
    }

    public int[][] getMap(){
        //Note: method not used I just didn't want to delete it incase I come back and improve the game after the course
        int[][] map = new int[getWorld().getHeight()][getWorld().getWidth()];
        for(int i = 0; i<getWorld().getHeight(); i++){
            for(int j = 0; j<getWorld().getWidth(); j++){
                if(MyWorld.p1.getY()==i && MyWorld.p1.getX()==j){
                    map[i][j] = 1;
                    playerCord[0]=j;
                    playerCord[1]=i;
                }
                if(getWorld().getColorAt(j,i).equals(wall)){
                    map[i][j]=2;
                }
                if(getY()==i && getX() == j){
                    map[i][j] = 3;
                    enemyCord[0]=j;
                    enemyCord[1]=i;
                }
            }
        }
        return map;
    }
    
    public void sightAndMovement(){
        //counter to spawn a new beam once every thirty frames
        beamTimer++;
        if(beamTimer >=30){
            getWorld().addObject(new beamOfSight(this),getX(),getY());
            beamTimer = 0;
        }
        
        seePlayer--; //the beam of sight will update the seePlayer int whenever the enemy can see the player, this counts down to make the enemy stop seeing the player after if the beams are no longer reaching the player.
        if(seePlayer>0){
            if(weapon.equals("fists")){
                chase(4);
            }
            else if(weapon.equals("gun")){
                shoot();
                chase(1);
            }
        }
        else{
            idle();
        }
        if(isTouching(Gun.class)){
            setImage(GunEnemy);
            weapon="gun";
            removeTouching(Gun.class);
        }
    }
    public void shoot(){
        if(fireRate>0){
            fireRate--; // makes it so that the enemy doesn't rapid fire every frame
        }
        if(fireRate<=0){
            getWorld().addObject(new EnemyBullet(),getX(),getY());
            //could have gotten away with only one bullet class but didn't have time so made two seperate
            fireRate = 60;
        }
    }
    public void getHit(){
        //makes the enemy get hit by player attacks
        immuneFrames--;
        if (isTouching(melee.class) && immuneFrames <= 0) {
            health -= 5;
            immuneFrames = 30;
        }
        if(isTouching(Bullet.class)){
            health-=10;
            removeTouching(Bullet.class);
        }
        if (health <= 0) {
            getWorld().removeObject(this);
        }
    }
}
