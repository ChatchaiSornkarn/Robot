package learnRobot;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import becker.robots.*;
public class ACity extends City{
     public ACity (int size){
    	 super(size,size);
    	 for (int i = 0; i < size; i++) {
             new Wall(this, 0, i, Direction.NORTH);
             new Wall(this, size-1, i, Direction.SOUTH);
             new Wall(this, i, 0, Direction.WEST);
             new Wall(this, i, size-1, Direction.EAST);
         }
    	 
     }
	
	
	public static void main(String[] args) {
		City.showFrame(false);
	ACity sweden= new ACity (10);
	Thing a=new Thing (sweden ,3,3);
	a.setIcon(null);
	RobotUIComponents view =new RobotUIComponents(sweden,0,0,10,10);
	JFrame frame=new JFrame();
    frame.getContentPane().setLayout(new BorderLayout());;
    
    
    
    frame.getContentPane().add(view.getCityView(), BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
	}

}
