package Views;


import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Helpers.Size;
import Models.GameObject;
import rx.functions.Action1;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.UIManager;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Rectangle;

public class PlayingFieldView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int topPanelHeight;
	private int fieldPanelHeight;
	private BackgroundPanel playFieldPanel;
	JLabel lblScoreValue = new JLabel("0");
	JLabel lblLivesValue = new JLabel("3");
	Action1<Integer> scoreObserver;
	Action1<Integer> livesObserver;
	Action1<GameObject> gameObjectObserver;
	
	public PlayingFieldView(Size size) {
		setResizable(false);
		setSize(size);
		initialize();
		
		setupObservers();
	}
	
	public Action1<Integer> getScoreObserver() {
		return scoreObserver;
	}
	
	public Action1<Integer> getLivesObserver() {
		return livesObserver;
	}
	
	public Action1<GameObject> getGameObjectObserver() {
		return gameObjectObserver;
	}
	
	public void setSize(Size size) {
		this.setBounds(100, 100, size.getWidth(), size.getHeight());
		this.topPanelHeight = 50;
		this.fieldPanelHeight = size.getHeight() - topPanelHeight;
	}
	
	private void setupObservers() {
		scoreObserver = new Action1<Integer>() {
			@Override
			public void call(Integer score) {
				setScore(score);
			}
		};
		livesObserver = new Action1<Integer>() {
			@Override
			public void call(Integer lives) {
				setLives(lives);
			}
		};
		gameObjectObserver = new Action1<GameObject>() {
			@Override
			public void call(GameObject gameObject) {
				playFieldPanel.setGameObject(gameObject);
			}
		};
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 500, 50);
		topPanel.setBackground(UIManager.getColor("Button.background"));
		getContentPane().add(topPanel);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblScore = new JLabel("Score: ");
		topPanel.add(lblScore);
		topPanel.add(lblScoreValue);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(new Rectangle(0, 0, 30, 0));
		topPanel.add(separator);
		
		JLabel lblLives = new JLabel("Lives");
		topPanel.add(lblLives);
		topPanel.add(lblLivesValue);
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("assets/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		playFieldPanel = new BackgroundPanel(image);
		playFieldPanel.setBounds(0, 50, 500,fieldPanelHeight);
		getContentPane().add(playFieldPanel);
		
	}
	
	public void setScore(Integer score) {
		lblScoreValue.setText(score.toString());
	}
	
	public void setLives(Integer lives) {
		lblLivesValue.setText(lives.toString());
	}
	
	public BackgroundPanel getPlayingField() {
		return playFieldPanel;
	}
	
	public void setGameObject(GameObject gameObject) {
		this.playFieldPanel.setGameObject(gameObject);
	}
}
