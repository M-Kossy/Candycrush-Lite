import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>Menu</code> correspond à la fenêtre du menu principale
 * @version 1.0.0
 * @author Dylan GIRAULT
 */
public class Menu extends JFrame{

	/**
	 * Constructeur permettant la formation de l'écran
	 */
	public Menu() {

		super("CandyCrush Lite");

		try { // pour que ce soit plus beau
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {/* ne fais rien */}

		int tailleX = 500;
		int tailleY = 250;

		ListenerMenu listener = new ListenerMenu(this);

		//bouton
		JButton button_alea = new JButton("Jouer aléatoirement");
		JButton button_file = new JButton("Jouer à partir d'un fichier");
		JButton button_quit = new JButton("Quitter le Jeu");

		//JPanel
		JPanel panneau_button = new JPanel();
		//panneau_button.setLayout(new GridLayout(3, 1));


		//Image de fond
		MenuView view = new MenuView();

		// ajout des boutons au panneau des bouttons
		panneau_button.add(button_alea);

		panneau_button.add(button_file);

		panneau_button.add(button_quit);


		// changement du cursor lors du survol des boutons
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

		button_alea.setCursor(cursor);
		button_file.setCursor(cursor);
		button_quit.setCursor(cursor);


		// ajout des boutons à un listener
		button_alea.addActionListener(listener);
		button_file.addActionListener(listener);
		button_quit.addActionListener(listener);

		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // prend la taille de l'écran

		//formation de la this
		this.setSize(tailleX, tailleY);
		this.setLocation((int)(screenSize.getWidth()-tailleX)/2, (int)(screenSize.getHeight()-tailleY)/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // impossible de redimensionner
		//this.setLayout(new GridLayout(3, 1));

		this.add(view, BorderLayout.CENTER);

		this.add(panneau_button, BorderLayout.NORTH); 
		

		this.setVisible(true);
	

	}
}