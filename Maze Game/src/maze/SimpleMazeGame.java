/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * 
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class SimpleMazeGame
{
	/**
	 * Creates a small maze.
	 */
	public static Maze createMaze()
	{
		Maze maze = new Maze();
//		make rooms
		Room room0 = new Room(0);
		Room room1 = new Room(1);
		Room room2 = new Room(2);
		
//		add rooms
		maze.addRoom(room0);
		maze.addRoom(room1);
		
//		make doors
		Door door1 = new Door(room0,room1);
		
//		set room sides
		room0.setSide(Direction.North, new Wall());
		room0.setSide(Direction.West, new Wall());
		room0.setSide(Direction.South, door1);
		room0.setSide(Direction.East, new Wall());
		room1.setSide(Direction.North, door1);
		room1.setSide(Direction.South, new Wall());
		room1.setSide(Direction.East, new Wall());
		room1.setSide(Direction.West, new Wall());
//		maze.addRoom(room1);
		maze.setCurrentRoom(room0);
//		System.out.println("The maze does not have any rooms yet!");
		return maze;

	}

	public  Maze loadMaze(final String path)
	{

		Maze maze = new Maze();

		System.out.println("Please load a maze from the file!");
		return maze;
	}

/**	hints for building a maze from a file:
 * 1. read file and save the content 
 * 2. loop through information
 * 3. create rooms
 * 4. create doors
 * 5. build connection between rooms
**/
	public static void main(String[] args)
	{

		System.out.println("Please provide a maze file! \n");
		Scanner in = new Scanner(System.in);
		String givenMaze = in.nextLine();
		if (!givenMaze.isEmpty()) {
			Maze newMaze = ConfigurationMaze.createMaze(givenMaze);
			MazeViewer viewnewMaze = new MazeViewer(newMaze);
			viewnewMaze.run();
			System.out.println("See pop-up for the displayed maze!");
		}else{
			
			System.out.println("You did not provide a file. Here is a premade maze with two rooms and a door.");
			Maze maze = createMaze();
			MazeViewer viewer = new MazeViewer(maze);
			viewer.run();
		}
	 in.close();   
	}
}
