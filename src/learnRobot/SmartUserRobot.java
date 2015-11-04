/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnRobot;

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.RobotUIComponents;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author chatchai
 */
public class SmartUserRobot extends UserRobot implements KeyListener{
    


    public SmartUserRobot(City a, int st, int av, Direction v) {
        super(a, st, av, v);
        addKeyListener(this);
    }
    
    public void run(){
        addKeyListener(this);
        
        
    }

    public void actionPerform(ActionEvent e){
        turnLeft();
        run();
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            turnLeft();
            turnLeft();
        }
        else if(e.getKeyCode() == KeyEvent.VK_W){
            move();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
		City sweden=new City();
		UserRobot pacman=new UserRobot(sweden,3,3,Direction.EAST);
		Thread pac=new Thread(pacman);
		pac.start();
		RobotUIComponents view =new RobotUIComponents(sweden);
		view.getStartStopButton().doClick();
		Object[] array=new Object[3];
        
	}
}
