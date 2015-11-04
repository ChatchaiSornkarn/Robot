package learnRobot;
import java.util.Scanner;

import becker.robots.*;
public class UserRobot extends Robot implements Runnable{

	Robot pacman;
	double speed=3;
	Scanner scan;
	
	
	public UserRobot(City a,int st,int av,Direction v){
		super (a,st,av,v);
		scan=new Scanner(System.in);
		setSpeed(speed);
                
	}
	
        
        
	public void run(){
		while(true){
		int cmd;
		cmd=4;
		try{
			cmd=scan.nextInt();
                        
                        switch (cmd){
		case 0:
			move();
			break;
		case 1:
			turnLeft();
		    break;
		case 2:
			turnLeft();
			turnLeft();
			turnLeft();
			break;
		case 3:
			turnLeft();
			turnLeft();
			break;
			
		}
		}
		catch(Exception e) {
			System.err.println(e);
			System.exit(0);
		}
			
		}
		
	}

}
