package display;

import game.SnakeGame;

import javax.swing.JFrame;

public class SnakeApplication {

    public static void main(String[] args) {
	// create a new JFrame to hold a new tictactoe instance
	JFrame snakeFrame = new JFrame("Snake");

	// set size
	snakeFrame.setSize(SnakeView.gridSize, SnakeView.gridSize + 40);

	// make a new snakeview instance and add it
	snakeFrame.add(new SnakePanel(new SnakeGame()));

	// exit normally on closing the window
	snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// show frame
	snakeFrame.setVisible(true);
    }

}
