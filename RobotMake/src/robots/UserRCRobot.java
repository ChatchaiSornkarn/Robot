package robots;


import becker.robots.*;

public class UserRCRobot extends RobotRC implements Runnable{//this Robot is extended out of the RobotRC from Becker's package

	
	char control;//to control the Robot 
    boolean breakme;//the break the robot
	
	public UserRCRobot(City a,int st,int av,Direction v){//constructor 
		super (a,st,av,v);
		breakme=false;
	}
	
	public void keyTyped(char key){//override the KeyTyped method and make it public 
		
		if(key=='m')//obvious
			move();
		if(key=='l')
			turnLeft();
		if(key=='r'){
			turnLeft();
			turnLeft();
			turnLeft();
		}else if(key=='t'){
			turnLeft();
			turnLeft();
		}else if(key=='p'){
			pickThing();
		}else ;
	
	}
	
	public void run(){//the run method
		setSpeed(5);//set the speed to 5
		while(true){
			System.out.println();
		keyTyped(control);//do the next move depending on the char control (if control is not one of the characters (m,l..etc)the UserRobot won't do anything)
		if(breakme==true)//if breakme is true then this Robot will unfortunately die 
			breakRobot("wow");
		}
		
	}
	
	public boolean hasPrize(){//check if this Robot collects the prize if so return true if not return false
		if(countThingsInBackpack() ==1)
			return true;
		    return false;
	}
	
	
	
	public static void main(String[] args) {
		
   
	}

	

}
