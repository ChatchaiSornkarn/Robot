package learnRobot;
import static dit948.Random.randomInt;

import becker.robots.*;
public class CpuRobot extends Robot implements Runnable{
    Robot pacman;
    int speed;
    public CpuRobot(City a, int st, int av, Direction d,int speed){
    	super (a,st,av,d);
        this.speed = speed;
    }

    @Override
    protected void breakRobot(String string) {
        super.breakRobot(string); //To change body of generated methods, choose Tools | Templates.
    }
    
	
    public void run(){
    	System.out.println(speed);
    	for(int x=0; x<1000; x++){
    	int nrTurns = randomInt(4);
        if (nrTurns > 0)
            setSpeed(nrTurns*speed);
        for (int i = 0; i < nrTurns; i++)
            turnLeft();
        setSpeed(speed);
        System.out.println(speed);
        move();
    	}

    }

}
