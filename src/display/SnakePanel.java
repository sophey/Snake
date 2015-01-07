package display;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.SnakeGame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener {

    SnakeView view;
    SnakeGame game;
    private static int DEFAULT_MOVE_RATE = 400;
    private int moveRate;
    private Timer timer;
    private JLabel score;

    public SnakePanel(SnakeGame game) {
	super(new BorderLayout());
	this.game = game;
	view = new SnakeView(game);
	moveRate = DEFAULT_MOVE_RATE;
	setFocusable(true);
	addKeyListener(this);
	setupTimer();
	add(view, BorderLayout.CENTER);
	score = new JLabel("Score: " + game.getScore());
	refreshScore();
	add(score, BorderLayout.NORTH);
    }

    /**
     * Sets the timer
     */
    private void setupTimer() {
	// uses the MOVE_RATE and moves every MOVE_RATE
	timer = new Timer(moveRate, new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		game.move();
		if (!game.checkMove(game.getHead()))
		    view.endGame();
		view.repaint();
		refreshScore();
	    }
	});
	timer.start();
    }

    public void refreshScore() {
	score.setText("Score: " + game.getScore());
	moveRate = DEFAULT_MOVE_RATE - game.getScore() * 2;
	if (moveRate <= 0)
	    view.wonGame();
	timer.setDelay(moveRate);
    }

    @Override
    public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
	boolean move = false;
	int key = e.getKeyCode();
	switch (key) {
	case KeyEvent.VK_LEFT:
	    move = game.setDir(3);
	    break;
	case KeyEvent.VK_RIGHT:
	    move = game.setDir(1);
	    break;
	case KeyEvent.VK_DOWN:
	    move = game.setDir(2);
	    break;
	case KeyEvent.VK_UP:
	    move = game.setDir(0);
	    break;
	}
	if (move) {
	    game.move();
	    view.repaint();
	    timer.restart();
	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub

    }

}
