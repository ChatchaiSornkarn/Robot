package robots;

import becker.robots.*;
public class ACity extends City{
     public ACity (int size){//nothing special just a city with a walls around it
    	 super(size,size);
    	 for (int i = 0; i < size; i++) {
             new Wall(this, 0, i, Direction.NORTH);
             new Wall(this, size-1, i, Direction.SOUTH);
             new Wall(this, i, 0, Direction.WEST);
             new Wall(this, i, size-1, Direction.EAST);
         }
    	 
     }
	
	
	public static void main(String[] args) {
		
	}

}
