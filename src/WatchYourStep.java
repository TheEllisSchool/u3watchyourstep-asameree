import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class WatchYourStep extends JFrame{
	
	private static final int GRIDSIZE = 10; 
	private TerrainButton [] [] terrain = new TerrainButton [GRIDSIZE] [GRIDSIZE]; // list by list
	private static final int NUMBEROFBOMBS = 10;
	
	
	public WatchYourStep(){	
		
		initGUI();
		setBomb();
		
		setTitle("Game Window");
		setSize(200, 100); //pixels specific size
		setResizable(true);
		pack();//pack overrides set size because its second
		setLocationRelativeTo(null); //centers on screen, do this after packing but before visible null = center

			
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // when you hit the x it will close

	}
	
	public void initGUI(){
		Font titleFont = new Font("Arial" , Font.BOLD, 32);
		JLabel titleLabel = new JLabel("Watch Your Step"); // jlabel is a preexisting class
		titleLabel.setFont(titleFont);
		add(titleLabel);
		titleLabel.setHorizontalAlignment(JLabel.CENTER); //left or right
		titleLabel.setForeground(Color.RED);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setOpaque(true);
		add(titleLabel,BorderLayout.PAGE_START);
		
		JPanel centerPanel = new JPanel ();
		centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);
		//TerrainButton testButton = new TerrianButton(0,0);
		//centerPanel.add(testButton);
		
		for (int r=0; r < GRIDSIZE; r++) {
			for(int c=0; c < GRIDSIZE; c++) {
				terrain [r] [c] = new TerrainButton(r,c);
				terrain [r] [c].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TerrainButton button = (TerrainButton) e.getSource();
						int row = button.getRow();
						int col = button.getCol();
						terrain [row] [col].click();
					}
					
			
				});
				centerPanel.add(terrain[r][c]);
			}
		}
		
	}
	
	public void setBomb() {
		Random rand = new Random();
		int pickRow;
		int pickCol;
		for (int i=0; i<NUMBEROFBOMBS; i++) {
			do {
				pickRow = rand.nextInt(GRIDSIZE);
				pickCol = rand.nextInt(GRIDSIZE);
			}while(terrain[pickRow][pickCol].hasBomb());
			terrain[pickRow][pickCol].setBomb();
		}
	}
	
	public static void main(String[] args) {
		try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch ( Exception e) {}
        
        EventQueue.invokeLater(new Runnable (){
            @Override
            public void run() {
                new WatchYourStep();
            }   
        });

	}

}
