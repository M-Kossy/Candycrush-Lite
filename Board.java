/** 
 * importation des class nécessaire
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * La classe <code>Board</code> correspond à la fenêtre durant une partie
 * @version 1.0.0
 * @author Dylan GIRAULT
 */
public class Board extends JFrame{

	/**
	 * Construit l'écran de jeu à partir des valeurs contenu dans le BoardModel
	 * @param gestion Variable de la classe BoardModel permettant d'accéder aux différentes informations sur le déroulement du tableau de jeu
	 */
	public Board(BoardModel gestion) {
		super("CandyCrush Lite");

		// Taille de la fenêtre lors du lancement du programme
		int tailleX = 900;
		int tailleY = 600;

		// Permet d'avoir la taille de l'écran de la machine
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

		//Formation de la fenêtre
		this.setSize(tailleX, tailleY);
		this.setLocation((int)(screenSize.getWidth()-tailleX)/2, (int)(screenSize.getHeight()-tailleY)/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(BoardView.ESPACEMENT*(BoardModel.BOARD_WIDTH + 2), BoardView.ESPACEMENT*(BoardModel.BOARD_WIDTH + 2))); // met une taille minimale

		// panneau du SCORE
		JLabel panneau = new JLabel("SCORE : 0");
		panneau.setHorizontalAlignment(JLabel.CENTER);

		// Création des class nécessaires
		BoardView view = new BoardView(gestion);
		BoardListener listener = new BoardListener(view, panneau, gestion, this);

		// Permet de réagir aux actions de l'utilisateur
		view.addMouseMotionListener(listener);
		view.addMouseListener(listener);

		 

		//Placement des éléments dans la fenêtre
		this.add(view, BorderLayout.CENTER);
		this.add(panneau, BorderLayout.NORTH);

		this.setVisible(true);
	

	}
}