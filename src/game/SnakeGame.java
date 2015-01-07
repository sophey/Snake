// SnakeGame.java
// Sophey Dong

package game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Backend for the snake game
 * 
 * @author Sophey
 *
 */
public class SnakeGame {

    ArrayList<int[]> snake; // arraylist of all the coordinates of the snake
    int direction; // 0 = up, 1 = right, 2 = down, 3 = left
    public static int gridLength = 20; // length of the grid
    private int[] egg; // coordinates for the egg

    /**
     * Default constructor, starts the game.
     */
    public SnakeGame() {
	startGame();
    }

    /**
     * Starts the game, resets the snake ArrayList for the original coordinate,
     * sets the direction to right, and puts a random egg on the board.
     */
    public void startGame() {
	snake = new ArrayList<int[]>();
	snake.add(new int[] { 0, 0 });
	direction = 1;
	randomEgg();
    }

    /**
     * Default move method, calls the move method with the current direction.
     */
    public void move() {
	move(direction);
    }

    /**
     * Move method that moves the snake in the inputed direction.
     * 
     * @param dir
     *            direction of movement
     */
    private void move(int dir) {
	int[] head = new int[] { getHead()[0], getHead()[1] }; // new head
	// sets the coordinates of the new head according to the direction given.
	if (dir == 0)
	    head[1] -= 1;
	else if (dir == 1)
	    head[0] += 1;
	else if (dir == 2)
	    head[1] += 1;
	else if (dir == 3)
	    head[0] -= 1;
	snake.add(0, head); // adds the new head to the ArrayList
	// if the head picked up an egg, do not delete the last element of 
	// the array and add a random egg, otherwise remove the last element 
	// of the array
	if (!Arrays.equals(head, egg))
	    snake.remove(snake.size() - 1);
	else
	    randomEgg();
    }

    /**
     * Checks if the move is valid, given a coordinate array
     * 
     * @param newHead
     *            coordinate array of move that is being checked
     * @return true if valid, false if not
     */
    public boolean checkMove(int[] newHead) {
	int x = newHead[0];
	int y = newHead[1];
	// if either x or y coordinate is out of bounds, return false
	if (x < 0 || x >= gridLength || y < 0 || y >= gridLength)
	    return false;
	// if the head hits any part of the snake, return false
	for (int i = 1; i < snake.size(); i++) {
	    if (Arrays.equals(snake.get(i), newHead))
		return false;
	}
	return true; // otherwise return true
    }

    /**
     * Adds an egg at a random point on the board
     */
    private void randomEgg() {
	// sets egg to a random coordinate within the board
	int x = (int) (Math.random() * gridLength);
	int y = (int) (Math.random() * gridLength);
	egg = new int[] { x, y };
	// if egg is where the head is, put a new egg on the board
	while (Arrays.equals(egg, getHead()))
	    randomEgg();
    }

    /**
     * Sets the direction of the snake
     * 
     * @param dir
     *            new direction of the snake
     * @return true if direction was changed, false if not
     */
    public boolean setDir(int dir) {
	// if dir is opposite that of direction or is direction, return false
	if (Math.abs(dir - direction) == 2 || dir == direction)
	    return false;
	// otherwise change the direction to dir and return true
	direction = dir;
	return true;
    }

    /**
     * Returns the int array coordinate of the head
     * 
     * @return array representing the head of the snake
     */
    public int[] getHead() {
	return snake.get(0);
    }

    /**
     * Returns the snake ArrayList
     * 
     * @return snake
     */
    public ArrayList<int[]> getSnake() {
	return snake;
    }

    /**
     * Returns the int array coordinate of the egg
     * 
     * @return array representing the egg
     */
    public int[] getEgg() {
	return egg;
    }

    /**
     * Returns the score
     * 
     * @return score
     */
    public int getScore() {
	return 10 * (snake.size() - 1);
    }

}
