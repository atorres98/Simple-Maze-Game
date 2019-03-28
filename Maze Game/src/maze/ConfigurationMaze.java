package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//Sides = NSEW

public class ConfigurationMaze {
	
	static String[] line = new String[1000];
	//static ArrayList<Integer> rooms = new ArrayList<Integer>();
	//static ArrayList<Room> roomNums = new ArrayList<Room>();
	//static ArrayList<String> doors = new ArrayList<String>();
//	static ArrayList<String> sides = new ArrayList<String>();
//	static ArrayList<String> doorsOfRooms = new ArrayList<String>();
//	static HashMap<Integer, Room> roomKeys = new HashMap<Integer, Room>();
	//static HashMap<Door, ArrayList> doorRooms = new HashMap<Door, ArrayList>();
	
	static Maze madeMaze = new Maze();
	
	static HashMap<Integer, String[]> roomInfo = new HashMap<Integer, String[]>();
	
	static HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();

	static HashMap<String, String[]> doorInfo = new HashMap<String, String[]>();
	
	static HashMap<String, Door> doors = new HashMap<String, Door>();

	static void parse(String path) {
		
		File file = new File(path);
		Scanner scanned;
		try {
			scanned = new Scanner(file);
			int i = 0; 
			while (scanned.hasNext()) {
				String nextLine = scanned.nextLine();
				System.out.println(nextLine);
				line = nextLine.split(" ");
								
//				System.out.println(line[0]);
				switch (line[0]) {
				case "room":
//					System.out.println("Sides of Room in order of N,S,E,W: " + Arrays.toString(roomSides));
					roomInfo.put(Integer.parseInt(line[1]), Arrays.copyOfRange(line,2,line.length));
					System.out.println("Room Info: " + line[1] +"= "+ Arrays.toString(roomInfo.get(Integer.parseInt(line[1]))));
					break;
				case "door":
					doorInfo.put(line[1], Arrays.copyOfRange(line,2,line.length));
					System.out.println("Door Info: " + line[1] + Arrays.toString(doorInfo.get(line[1])));
					break;
				}
				
				i++;
			}
			scanned.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public static void createRooms() {
		/*Using a For loop to iterate through the ArrayList of rooms and create each individual room*/
		for (int count: roomInfo.keySet()) {
			Room newRoom = new Room(count);
			System.out.println(newRoom);
			madeMaze.addRoom(newRoom);
			//roomNums.add(newRoom);
			System.out.println("Room made: "+ newRoom);
			rooms.put(count, newRoom);
			System.out.println("HashMap of rooms as keys and room numbers as values: " + rooms);
		}
		madeMaze.setCurrentRoom(0);
	}
	
	
	public static void createDoors() {
		/*Using a For loop to iterate through the ArrayList of doors and create each individual door*/
		System.out.println(doorInfo.toString());
		System.out.println(doorInfo.keySet().toString());
		for (String door : doorInfo.keySet()) {
			int roomLID = Integer.parseInt(doorInfo.get(door)[0]);
			System.out.println("Room LID" + doorInfo.get(door)[0]);
			int roomRID = Integer.parseInt(doorInfo.get(door)[1]);
			System.out.println("Room RID" + doorInfo.get(door)[1]);
			Room roomL = rooms.get(roomLID);
			Room roomR = rooms.get(roomRID);
			Door  newDoor = new Door(roomL, roomR);
			System.out.println("new door " + newDoor);
			if (doorInfo.get(door)[2].equals("close")) {
				System.out.println("closed " + doorInfo.get(door)[2]);
				newDoor.setOpen(false);
			}else {
				System.out.println("open " + doorInfo.get(door)[2]);
				newDoor.setOpen(true);
			}
			doors.put(door, newDoor);
			System.out.print("door open? " + newDoor.isOpen());
//			doors.put(line[1], newDoor);
			System.out.println("Door made: " + newDoor.toString());
		}
		System.out.println("HashMap of Doors: " + doors.toString());
	}
	
	public static void setSides() {
		/*Set sides using the createRooms and createDoors
		 * Sides are listed in order of North, South, East, West*/
		System.out.println("Set sides");
		for (int roomNum: roomInfo.keySet()) {
			String[] sideInfo = roomInfo.get(roomNum);
			Room currentRoom = rooms.get(roomNum);
			
			int i = 0;
			for (String side: sideInfo) {
				System.out.println("WHY " + side);
				Direction currentDirection = calculateDirection(i);
				System.out.println(currentDirection);
				
				if (side.equals("wall")) {
					System.out.println("WALL");
					currentRoom.setSide(currentDirection, new Wall());
				} else if (side.startsWith("d")) {
					Door currentDoor = doors.get(side);
					System.out.println("side door " + currentDoor);
					currentRoom.setSide(currentDirection, currentDoor);
				} else {
					int sideRoomNum = Integer.parseInt(side);
					Room sideRoom = rooms.get(sideRoomNum);
					System.out.println("side room " + sideRoomNum);
					currentRoom.setSide(currentDirection, sideRoom);
				}
				i++;
		}
	}
}	
	
	
	private static Direction calculateDirection(int i){
		Direction direction = Direction.North;
		switch(i) {
		case 0:
			direction = Direction.North;
			break;
		case 1:
			direction = Direction.South;
			break;
		case 2:
			direction = Direction.East;
			break;
		case 3:
			direction = Direction.West;
			break;
		}
		return direction;
	}
	
	public static Maze createMaze(String path) {
		parse(path);
		createRooms();
		createDoors();
		setSides();
//		for (int count : rooms.keySet()) {
//			madeMaze.addRoom(rooms.get(count));
//		}

		System.out.println("current room: " + madeMaze.getCurrentRoom());
		System.out.println("madeMaze " + madeMaze);
		return madeMaze;
	}

}
