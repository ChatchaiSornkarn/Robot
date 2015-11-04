package learnRobot;
import javax.swing.*;
import java.awt.ActiveEvent;
import java.awt.*;
import becker.robots.*;
import becker.robots.Robot;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import static dit948.Random.randomInt;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;

public class Frames{
	
     JFrame window;//main window
     ACity sweden;
     CpuRobot pacman;
     Thread pac;
     JPanel robotGUI;
     JPanel first;//user Control
     UserRobot user;
     Thread usr;
     int speed = 1;
     
     
     
     public Frames(){
//         try {
//
//         if(user.getIntersection()){
//             
//            }
//         }
//            catch (Exception e) {
//         }
     }
     
    public void changespeed(int speed) {
        this.speed = speed;
        restart();
    }
     
     public void addMenu(){
         
    	 JMenuBar mnb=new JMenuBar();
    	 window.setJMenuBar(mnb);
         

    	 JMenu actions=new JMenu("Actions");
    	 mnb.add(actions);
    	 JMenu settings=new JMenu("Settings");
    	 mnb.add(settings);
    	 
    	 JRadioButtonMenuItem easy=new JRadioButtonMenuItem("Easy");

    	 JRadioButtonMenuItem hard=new JRadioButtonMenuItem("Hard");

    	 JRadioButtonMenuItem med=new JRadioButtonMenuItem("Medium");
         
         switch(pacman.speed){
             case 1: easy.setSelected(true);
                 break;
             case 2: med.setSelected(true);
                 break;
             case 3: hard.setSelected(true);
                 break;
                 default:
                     System.err.println("Something has gone terribly wrong: " + pacman.speed);
                break;
         }
         
         easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent q) {
                            speed = 1;
                            restart();
			}
		});
         med.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent q) {
			speed = 2;
                        restart();
			}
		});
         hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent q) {
			speed = 3;
                        restart();
			}
		});
//         easy.addActionListener(new SpeedListener(1,this));
//         med.addActionListener(new SpeedListener(2,this));
//         hard.addActionListener(new SpeedListener(3,this));
         
    	 ButtonGroup btnG=new ButtonGroup();
    	 btnG.add(easy);
    	 btnG.add(hard);
    	 btnG.add(med);
    	 
    	 settings.add(easy);
    	 settings.add(med);
    	 settings.add(hard);
    	 
    	 JMenuItem quit=new JMenuItem("Quit");
    	 quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent q) {
			quit();
			}
		});
    	 
    	 
    	 RobotUIComponents startt=new  RobotUIComponents(sweden);
    	 
    	 
    	 actions.add(startt.getMenuBar().getMenu(1).getItem(0));
    	 actions.add(quit);	 
    	
    	 
    	window.pack();
    	window.setVisible(true);
     }
     
     public void quit(){
    	 System.exit(0);
    	 
     }
	
    public void restart(){//got to code this 
    	window.removeAll();
        
        addCity();
        addMenu();
    }
    
    public void addCity(){
         window=new JFrame("City in frame");
 
    	 window.getContentPane().setLayout(new BoxLayout(window.getContentPane(),BoxLayout.Y_AXIS));
    	 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         City.showFrame(false);
    	 sweden = new ACity(11);
    	 RobotUIComponents view= new RobotUIComponents(sweden,0,0,11,11);
         robotGUI = view.getCityView();
    	 pacman=new CpuRobot(sweden,5,5,Direction.NORTH,speed);
    	 user=new UserRobot(sweden,2,2,Direction.SOUTH);
         user.setColor(Color.BLUE);
         
         Thing thing;
         thing = new Thing(sweden, randomInt(10), randomInt(10));

    	 usr=new Thread(user);
    	 pac=new Thread(pacman);
         pac.start();
         usr.start();
    	 window.getContentPane().add(robotGUI);
         
         first=new JPanel();
    	 first.setLayout(new BorderLayout());
    	 
    	 JButton movebtn=new JButton("Move");
         movebtn.addMouseListener(new java.awt.event.MouseAdapter() {  
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user.move();
            }
            
         });
         
    	 first.add(movebtn,BorderLayout.SOUTH);

    	 JButton pickbtn=new JButton("Pick"); 
         pickbtn.addMouseListener(new java.awt.event.MouseAdapter() {  
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user.pickThing();
                JOptionPane.showConfirmDialog(null, "Play agian?", null, JOptionPane.YES_NO_OPTION);
                pacman.breakRobot("");
            }
            
         });
         
         first.add(pickbtn, BorderLayout.CENTER);
         
         
         JButton turnleftbtn=new JButton("TurnLeft");
         turnleftbtn.addMouseListener(new java.awt.event.MouseAdapter(){
         
         @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            user.turnLeft();
         }
});
         
         first.add(turnleftbtn, BorderLayout.WEST);
         
         JButton turnrightbtn=new JButton("TurnRight");
         turnrightbtn.addMouseListener(new java.awt.event.MouseAdapter() {  
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user.turnLeft();
                user.turnLeft();
                user.turnLeft();
            }
            
         });
         
         first.add(turnrightbtn, BorderLayout.EAST);
         
         JButton turnaroundbtn=new JButton("TurnAround");
         turnaroundbtn.addMouseListener(new java.awt.event.MouseAdapter() {  
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user.turnLeft();
                user.turnLeft();
            }
            
         });
         
         first.add(turnaroundbtn, BorderLayout.NORTH);
         
    	 window.getContentPane().add(first);
    }

}
