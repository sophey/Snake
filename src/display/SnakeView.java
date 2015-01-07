// SnakeView.java
// Sophey Dong

package display;

import game.SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JComponent;

/**
 * JComponent view of the snake game
 * 
 * @author Sophey
 *
 */
public class SnakeView extends JComponent {

    private SnakeGame game;
    public static int gridSize = 600;

    public SnakeView(SnakeGame game) {
	this.game = game;
	setOpaque(true);
	setBackground(Color.WHITE);
	setPreferredSize(new Dimension(gridSize, gridSize));
    }

    public void paint(Graphics g) {
	int boxSize = gridSize / SnakeGame.gridLength;
	g.setColor(Color.BLACK);
	for (int[] coordinate : game.getSnake()) {
	    int x = (int) (boxSize * (coordinate[0] + .1));
	    int y = (int) (boxSize * (coordinate[1] + .1));
	    g.fillRect(x, y, (int) (boxSize * .8), (int) (boxSize * .8));
	}

	int[] egg = game.getEgg();
	int eggX = (int) (boxSize * (egg[0] + .1));
	int eggY = (int) (boxSize * (egg[1] + .1));
	g.fillOval(eggX, eggY, (int) (boxSize * .8), (int) (boxSize * .8));
    }

    public void endGame() {
	// pops up a panel asking to play again, says no winner
	int reply = JOptionPane.showConfirmDialog(null,
	        "You've lost with a score of " + game.getScore()
	                + ". \n Play again?", "Play again?",
	        JOptionPane.YES_NO_OPTION);
	// if yes, setup game and refresh panel
	if (reply == JOptionPane.YES_OPTION) {
	    game.startGame();
	    repaint();
	} // otherwise quit panel
	else
	    System.exit(0);
    }

    public void wonGame() {
	// pops up a panel asking to play again, says no winner
	int reply = JOptionPane.showConfirmDialog(null,
	        "You've won!!! \n Play again?", "Play again?",
	        JOptionPane.YES_NO_OPTION);
	// if yes, setup game and refresh panel
	if (reply == JOptionPane.YES_OPTION) {
	    game.startGame();
	    repaint();
	} // otherwise quit panel
	else
	    System.exit(0);
    }

}
