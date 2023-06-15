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
    int health = 10;
    int seePlayer = 0;
    int beamTimer = 0;
    int frames = 30;
    Color wall = new Color(0,0,0);
    boolean[] aiDirection = {true,true,true,true};
    //                       right left down up
    String weapon = "fists";
    int speed = 4;
    int delay = 60;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        aiDirectionDetection();
        // Add your action code here.
        beamTimer++;
        if(beamTimer >=30){
            getWorld().addObject(new beamOfSight(this),getX(),getY());
            beamTimer = 0;
        }
        frames--;
        seePlayer--;
        if(seePlayer>0){
            if(weapon.equals("fists")){
                chase();
            }
        }
        else{
            idle();
        }
        if (isTouching(melee.class) && frames <= 0) {
            health -= 5;
            frames = 30;
        }
        if (health <= 0) {
            getWorld().removeObject(this);
        }
    }

    public void aiDirectionDetection(){
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

    public void chase(){
        if(MyWorld.p1.getX()>getX()&&aiDirection[0]){
            setLocation(getX()+4,getY());
        }
        if(MyWorld.p1.getX()<getX()&&aiDirection[1]){
            setLocation(getX()-4,getY());
        }
        if(MyWorld.p1.getY()>getY()&&aiDirection[2]){
            setLocation(getX(),getY()+4);
        }
        if(MyWorld.p1.getY()<getY()&&aiDirection[3]){
            setLocation(getX(),getY()-4);
        }
    }

    public void idle(){
        aiDirectionDetection();
        delay--;
        if(delay<0){
            int randDirection = Greenfoot.getRandomNumber(4);
            int moveTime = Greenfoot.getRandomNumber(120);
            if(moveTime>0){
                moveTime--;
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
        delay = 60;
    }
    public int[][] getMap(){
        int[][] map = new int[getWorld().getHeight()][getWorld().getWidth()];
        for(int i = 0; i<getWorld().getHeight(); i++){
            for(int j = 0; j<getWorld().getWidth(); j++){
                if(MyWorld.p1.getY()==i && MyWorld.p1.getX()==j){
                    map[i][j] = 1;
                }
                if(getWorld().getColorAt(j,i).equals(wall)){
                    map[i][j]=2;
                }
                if(getY()==i && getX() == j){
                    map[i][j] = 3;
                }
            }
        }
        return map;
    }
    public void pathfind(){
        int[][] map = getMap();
        int[] playerCord = new int[2];
        int[] enemyCord = new int[2];
        for(int i = 0; i<getWorld().getHeight(); i++){
            for(int j = 0; j<getWorld().getWidth(); j++){
                if(map[i][j]==3){
                    enemyCord[0]=j;
                    enemyCord[1]=i;
                }
                if(map[i][j]==1){
                    playerCord[0]=j;
                    playerCord[1]=i;
                }
            }
        }
        
    }
}
