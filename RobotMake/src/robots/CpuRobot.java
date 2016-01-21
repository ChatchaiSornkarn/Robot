package robots;
import static dit948.Random.randomInt;
import java.awt.Color;
import becker.robots.*;
public class CpuRobot extends Robot implements Runnable{
    Robot pacman;
	 static double speed=1;//the initial speed is 1
	boolean breakme;
    public CpuRobot(City a, int st, int av, Direction d){
    	super (a,st,av,d);
    	setColor(Color.blue);//obvious
    	breakme=false;
    }
    
	/*
	 * 
	 * here I'm always checking of the breakme condition because of the way the threads work, like in the main method 
	 * I'm changing the boolean breakme to true then I immediately stop the game so if the run method is not checking of the breakme
	 * condition at that time then the Robot won't break.
	 * I'm very bad at explaining this but I hope that you just trust me :p 
	 */
    public void run(){
    	double speed=CpuRobot.speed;
    	while(true){
    		if(breakme==true)
    			breakRobot("arghh!!");
    	int nrTurns = randomInt(4);//random number from 0 to 3
        if (nrTurns > 0)
            setSpeed(nrTurns*speed);//speed up if you're turning 
        if(breakme==true)
			breakRobot("arghh!!");
        for (int i = 0; i < nrTurns; i++){//just obvious 
        	if(breakme==true)
    			breakRobot("arghh!!");
            turnLeft();
        }
        setSpeed(CpuRobot.speed);
        if(breakme==true)
			breakRobot("arghh!!");
        if(frontIsClear())
        move();
        if(breakme==true)
			breakRobot("arghh!!");
        
    	}

    }
	public static void main(String[] args) {
		
	}

}
