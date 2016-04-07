import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class WelcomeScreen extends JPanel implements ActionListener {

	private static final long serialVersionUID = -5432368345116470291L;

	private JButton play;
	private JButton howToPlay;
	private SpringLayout layout;
	
	public WelcomeScreen(){
		layout = new SpringLayout();
		setLayout(layout);
		
		play = new JButton("Play");
		play.addActionListener(this);
		add(play);
		
		howToPlay = new JButton("How to Play");
		howToPlay.addActionListener(this);
		add(howToPlay);
		
		layout.putConstraint(SpringLayout.WEST, play, 20, SpringLayout.WEST,
				this);
		layout.putConstraint(SpringLayout.NORTH, play, 20, SpringLayout.NORTH,
				this);
		layout.putConstraint(SpringLayout.NORTH, howToPlay, 20,
				SpringLayout.NORTH, this);
	    layout.putConstraint(SpringLayout.WEST, howToPlay, 20, SpringLayout.EAST, play);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play){
			
		}
	}
	
}
