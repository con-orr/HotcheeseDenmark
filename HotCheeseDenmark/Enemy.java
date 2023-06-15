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
    int[] playerCord = new int[2];
    int[] enemyCord = new int[2];
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

    public void pathfind(){
        // IMPORTANT**** do not question the effeciency or effectivness of this section it's totally fine. 
        //I will attempt to add coments to explain but honestly I'm not sure how much they'll help.
        int[][] map = getMap();
        int pixlesX = 0;
        int pixlesY = 0;
        for(int i = 0; i<map.length;){
            for(int j = 0; j<map[i].length;){
                if(playerCord[0]>enemyCord[0]&&map[i][j+1]!=2){
                    j++;
                    pixlesX++;
                }
                else if(playerCord[0]<enemyCord[0]&&map[i][j+1]==2){
                    if(playerCord[1]>enemyCord[1]){
                        for(int q = 0; q<getWorld().getHeight()-enemyCord[1];q++){
                            if(map[i+q][j-1]!=2){
                                i+=q;
                                pixlesY+=q;
                            }
                        }
                    }
                    else if(playerCord[1]<enemyCord[1]){
                        for(int q = 0; q<0+enemyCord[1];q--){
                            if(map[i+q][j+1]!=2){
                                i-=q;
                                pixlesY-=q;
                            }
                        }
                    }
                }
                if(playerCord[0]<enemyCord[0]&&map[i][j-1]!=2){
                    j--;
                    pixlesX--;
                }
                else if(playerCord[0]<enemyCord[0]&&map[i][j-1]==2){
                    if(playerCord[1]>enemyCord[1]){
                        for(int q = 0; q<getWorld().getHeight()-enemyCord[1];q++){
                            if(map[i+q][j-1]!=2){
                                i+=q;
                                pixlesY+=q;
                            }
                        }
                    }
                    else if(playerCord[1]<enemyCord[1]){
                        for(int q = 0; q<0+enemyCord[1];q--){
                            if(map[i+q][j+1]!=2){
                                i-=q;
                                pixlesY-=q;
                            }
                        }
                    }
                }
                if(playerCord[1]<enemyCord[1]&&map[i-1][j]!=2){
                    i--;
                    pixlesY--;
                }
                else if(playerCord[1]<enemyCord[1]&&map[i-1][j]==2){
                    if(playerCord[0]>enemyCord[0]){
                        for(int q = 0; q<getWorld().getWidth()-enemyCord[0];q++){
                            if(map[i+1][j+q]!=2){
                                j+=q;
                                pixlesX+=q;
                            }
                        }
                    }
                    else if(playerCord[0]<enemyCord[0]){
                        for(int q = 0; q<0+enemyCord[0];q--){
                            if(map[i+1][j+q]!=2){
                                j-=q;
                                pixlesX-=q;
                            }
                        }
                    }
                }
                if(playerCord[1]>enemyCord[1]&&map[i+1][j]!=2){
                    i++;
                    pixlesY++;
                }
                else if(playerCord[1]>enemyCord[1]&&map[i+1][j]==2){
                    if(playerCord[0]>enemyCord[0]){
                        for(int q = 0; q<getWorld().getWidth()-enemyCord[0];q++){
                            if(map[i+1][j+q]!=2){
                                j+=q;
                                pixlesX+=q;
                            }
                        }
                    }
                    else if(playerCord[0]<enemyCord[0]){
                        for(int q = 0; q<0+enemyCord[0];q--){
                            if(map[i+1][j+q]!=2){
                                j-=q;
                                pixlesX-=q;
                            }
                        }
                    }
                }
                
                if(j==playerCord[0]){
                    break;
                }
            }
            if(i==playerCord[1]){
                break;
            }
        }
    }
}
