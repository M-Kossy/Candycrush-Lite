/** 
 * importation des class nécessaire
 */
import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>BoardView</code> s'occupe de la gestion d'affichage des éléments durant une partie
 * @version 1.0.0
 * @author Dylan GIRAULT
 */
public class BoardView extends JComponent {

	/**
	 * class qui gère les données stockés
	 */
	private BoardModel gestion;

	/**
	 * Pinceau de type Graphics permettant de dessiner dans la fenêtre 
	 */
	private Graphics pinceau;

	/**
	 * tableau boolean représentant le plateau de jeu afin de savoir si la case est survolé ou non
	 */
	private boolean[][] tab_bool;

	/**
	 * Valeur permettant de savoir si la partie est terminé ou pas
	 */
	private boolean finish;

	/**
	 * Constante donnant l'espace entre deux éléments
	 */
	public static final int SEPARATION_PAR_CASE = 6;


	/**
	 * Constante représentant la taille de l'élément additionner à l'espace nécessaire entre deux élément
	 */
	public static final int ESPACEMENT = 22+SEPARATION_PAR_CASE;

	/**
	 * Constante de l'opacité du fond blanc des éléments
	 */
	public static final int OPACITY = 150;

	/**
	 * Construit une interface de jeu selon les données contenues dans le BoardModel 
	 * @param p_gestion classe qui gère les données stockés
	 */
	public BoardView(BoardModel p_gestion) {

		int i, y;

		this.gestion = p_gestion;

		this.tab_bool = new boolean[BoardModel.BOARD_WIDTH][BoardModel.BOARD_HEIGHT];

		for(i=0; i<tab_bool.length; i++) {
			for(y=0; y<tab_bool[i].length; y++) {

				this.tab_bool[i][y] = false;
			}
		}

		this.finish = this.gestion.isFinished();
	}
	/**
	 * Permet d'éliminer les éléments affichés sur la fenêtre
	 * @param x position horizontal relatif au tableau représentant la position des éléments
	 * @param y position verticale relatif au tableau représentant la position des éléments
	 * @return Retourne un booléen, "true" si la partie est terminé ou "false" si la partie n'est pas terminé
	 */
	public boolean removeElement(int x, int y) {
		if(this.tab_bool[x][y]) {
			gestion.registerClick(x, y);

			this.finish = gestion.isFinished();
		
			return this.finish;
		}
		return false;
	}


	/**
	 * Permet de créer un Cercle de couleur bleu 
	 * @param x position horizontal relatif au tableau représentant la position des éléments
	 * @param y position verticale relatif au tableau représentant la position des éléments
	 */
	public void dessinerCercle(int x, int y) {

		// Variables permettant de recalibrer les éléments quand redimensionnement de la fenêtre
		int centrage_X = (this.getWidth()-ESPACEMENT*15)/2;
		int centrage_Y = (this.getHeight()-ESPACEMENT*10)/2;

		if(tab_bool[x][y] == true) { // si la case est considéré comme survolé, coloration du fond en jaune

			this.pinceau.setColor(Color.YELLOW);
			this.pinceau.fillRect(x*ESPACEMENT+centrage_X-SEPARATION_PAR_CASE/2, y*ESPACEMENT+centrage_Y-SEPARATION_PAR_CASE/2, 22+SEPARATION_PAR_CASE, 22+SEPARATION_PAR_CASE);
		}
		else { // si la case n'est pas considéré comme survolé, coloration du fond en blanc

			this.pinceau.setColor(new Color(255, 255, 255, OPACITY));
			this.pinceau.fillRect(x*ESPACEMENT+centrage_X-SEPARATION_PAR_CASE/2, y*ESPACEMENT+centrage_Y-SEPARATION_PAR_CASE/2, 22+SEPARATION_PAR_CASE, 22+SEPARATION_PAR_CASE);		
		}

		// Partie sombre du cercle
		this.pinceau.setColor(new Color(0, 137, 188));
		this.pinceau.fillOval(x*this.ESPACEMENT+centrage_X, y*this.ESPACEMENT+centrage_Y, 22, 22);

		// Partie couleur normal du cercle
		this.pinceau.setColor(new Color(0, 174, 239));
		this.pinceau.fillOval(x*this.ESPACEMENT+centrage_X, y*this.ESPACEMENT+centrage_Y, 19, 19);

		// Partie de surbrillance du cercle
		this.pinceau.setColor(new Color(147, 218, 244));
		this.pinceau.fillOval(x*this.ESPACEMENT+3+centrage_X, y*this.ESPACEMENT+3+centrage_Y, 5, 5);

		// Contour noir du cercle
		this.pinceau.setColor(Color.BLACK);
		this.pinceau.drawOval(x*this.ESPACEMENT+centrage_X, y*this.ESPACEMENT+centrage_Y, 22, 22);
	}

	/**
	 * Permet de créer un Cube de couleur rouge
	 * @param x position horizontal relatif au tableau représentant la position des éléments
	 * @param y position verticale relatif au tableau représentant la position des éléments
	 */
	public void dessinerCube(int x, int y) {

		// Variables permettant de recalibrer les éléments quand redimensionnement de la fenêtre
		int centrage_X = (this.getWidth()-ESPACEMENT*15)/2;
		int centrage_Y = (this.getHeight()-ESPACEMENT*10)/2;

		// Contour du polygon uniquement de coordonnées x
		int[] polygonX = {11+x*this.ESPACEMENT+centrage_X, 22+x*this.ESPACEMENT+centrage_X,
    					  22+x*this.ESPACEMENT+centrage_X, 11+x*this.ESPACEMENT+centrage_X,
    					  0+x*this.ESPACEMENT+centrage_X, 0+x*this.ESPACEMENT+centrage_X};

    	// Contour du carré haut du polygon uniquement de coordonnées x
    	int[] squareTopX = {polygonX[0], polygonX[1],
    						polygonX[0], polygonX[4]};

    	// Contour du carré droit du polygon uniquement de coordonnées x
    	int[] squareRightX = {polygonX[1], polygonX[1],
    						  polygonX[0], polygonX[0]};

		// Contour du carré gauche du polygon uniquement de coordonnées x
    	int[] squareLeftX = {polygonX[0], polygonX[4],
    						 polygonX[4], polygonX[0]};

    	// Contour du polygon uniquement de coordonnées y
    	int[] polygonY = {0+y*this.ESPACEMENT+centrage_Y, 6+y*this.ESPACEMENT+centrage_Y, 
    					  18+y*this.ESPACEMENT+centrage_Y, 25+y*this.ESPACEMENT+centrage_Y,
    					  18+y*this.ESPACEMENT+centrage_Y, 6+y*this.ESPACEMENT+centrage_Y};

    	// Contour du carré haut du polygon uniquement de coordonnées y
    	int[] squareTopY = {polygonY[0], polygonY[1],
    						12+y*this.ESPACEMENT+centrage_Y, polygonY[1]};

    	// Contour du carré droite du polygon uniquement de coordonnées y
	   	int[] squareRightY = {polygonY[1], polygonY[2],
    						  polygonY[3], 12+y*this.ESPACEMENT+centrage_Y};

    	// Contour du carré gauche du polygon uniquement de coordonnées y
	   	int[] squareLeftY = {polygonY[3], polygonY[2],
    						 polygonY[1], 12+y*this.ESPACEMENT+centrage_Y};


    	if(tab_bool[x][y] == true) { // si la case est considéré comme survolé, coloration du fond en jaune

			this.pinceau.setColor(Color.YELLOW);
			this.pinceau.fillRect(x*ESPACEMENT+centrage_X-SEPARATION_PAR_CASE/2, y*ESPACEMENT+centrage_Y-SEPARATION_PAR_CASE/2, 22+SEPARATION_PAR_CASE, 22+SEPARATION_PAR_CASE);
		}
		else { // si la case n'est pas considéré comme survolé, coloration du fond en blanc

			this.pinceau.setColor(new Color(255, 255, 255, OPACITY));
			this.pinceau.fillRect(x*ESPACEMENT+centrage_X-SEPARATION_PAR_CASE/2, y*ESPACEMENT+centrage_Y-SEPARATION_PAR_CASE/2, 22+SEPARATION_PAR_CASE, 22+SEPARATION_PAR_CASE);
		}

		// Face claire en haut du cube
    	this.pinceau.setColor(new Color(240, 100, 106));
   		this.pinceau.fillPolygon(squareTopX, squareTopY, 4);

   		// Face sombre à droite du cube
    	this.pinceau.setColor(new Color(186, 22, 28));
   		this.pinceau.fillPolygon(squareRightX, squareRightY, 4);

   		// Face couleur normal à gauche du cube
    	this.pinceau.setColor(new Color(237, 28, 36));
    	this.pinceau.fillPolygon(squareLeftX, squareLeftY, 4);

    	// Contour noir du cube
    	this.pinceau.setColor(Color.BLACK);
    	this.pinceau.drawPolygon(polygonX, polygonY, 6);
	}

	/**
	 * Permet de créer un Hexagone de couleur vert 
	 * @param x position horizontal relatif au tableau représentant la position des éléments
	 * @param y position verticale relatif au tableau représentant la position des éléments
	 */
	public void dessinerHexagone(int x, int y) {

		// Variables permettant de recalibrer les éléments quand redimensionnement de la fenêtre
		int centrage_X = (this.getWidth()-ESPACEMENT*15)/2;
		int centrage_Y = (this.getHeight()-ESPACEMENT*10)/2;

		// Suite de position X d'une figure hexagonale
		int[] polygonX = {11+x*this.ESPACEMENT+centrage_X, 22+x*this.ESPACEMENT+centrage_X,
						22+x*this.ESPACEMENT+centrage_X, 11+x*this.ESPACEMENT+centrage_X,
						0+x*this.ESPACEMENT+centrage_X, 0+x*this.ESPACEMENT+centrage_X};

		// Suite de position X du triangle haut de l'hexagone
		int[] triangleTopX = {polygonX[0], polygonX[1], polygonX[4]};

		// Suite de position X du triangle droite de l'hexagone
		int[] triangleRightX = {polygonX[1], polygonX[1], polygonX[0]};

		// Suite de position X du triangle gauche de l'hexagone
		int[] triangleLeftX = {polygonX[0], polygonX[4], polygonX[4]};

		// Suite de position X du triangle central de l'hexagone
		int[] triangleCenterX = {polygonX[1], polygonX[0], polygonX[4]};

		// Suite de position Y d'une figure hexagonale
		int[] polygonY = {0+y*this.ESPACEMENT+centrage_Y, 6+y*this.ESPACEMENT+centrage_Y, 
			 			  18+y*this.ESPACEMENT+centrage_Y, 25+y*this.ESPACEMENT+centrage_Y,
						  18+y*this.ESPACEMENT+centrage_Y, 6+y*this.ESPACEMENT+centrage_Y};


		if(this.tab_bool[x][y] == true) { // si la case est considéré comme survolé, coloration du fond en jaune

			this.pinceau.setColor(Color.YELLOW);
			this.pinceau.fillRect(x*ESPACEMENT+centrage_X-SEPARATION_PAR_CASE/2, y*ESPACEMENT+centrage_Y-SEPARATION_PAR_CASE/2, 22+SEPARATION_PAR_CASE, 22+SEPARATION_PAR_CASE);
		}
		else { // si la case n'est pas considéré comme survolé, coloration du fond en blanc

			this.pinceau.setColor(new Color(255, 255, 255, OPACITY));
			this.pinceau.fillRect(x*ESPACEMENT+centrage_X-SEPARATION_PAR_CASE/2, y*ESPACEMENT+centrage_Y-SEPARATION_PAR_CASE/2, 22+SEPARATION_PAR_CASE, 22+SEPARATION_PAR_CASE);
		}

		// Suite de position Y du triangle haut de l'hexagone
		int[] triangleTopY = {polygonY[0], polygonY[1], polygonY[1]};

		// Suite de position Y du triangle droite de l'hexagone
		int[] triangleRightY = {polygonY[1], polygonY[2], polygonY[3]};

		// Suite de position Y du triangle gauche de l'hexagone
		int[] triangleLeftY = {polygonY[3], polygonY[2], polygonY[1]};

		// Suite de position Y du triangle centrale de l'hexagone
		int[] triangleCenterY = {polygonY[1], polygonY[3], polygonY[1]};;

		// Face clair en haut du hexagone
		this.pinceau.setColor(new Color(0, 243, 118));
		this.pinceau.fillPolygon(triangleTopX, triangleTopY, 3);

		// Face sombre à droite de l'hexagone
		this.pinceau.setColor(new Color(0, 115, 56));
		this.pinceau.fillPolygon(triangleRightX, triangleRightY, 3);

		// Face clair à gauche de l'hexagone
		this.pinceau.setColor(new Color(0, 243, 118));
		this.pinceau.fillPolygon(triangleLeftX, triangleLeftY, 3);

		// Face un peu sombre au centre de l'hexagone
		this.pinceau.setColor(new Color(0,166, 81));
		this.pinceau.fillPolygon(triangleCenterX, triangleCenterY, 3);

		// Contour noir de l'hexagone
		this.pinceau.setColor(Color.BLACK);
		this.pinceau.drawPolygon(polygonX, polygonY, 6);
	}

	/**
	 * Permet de colorer le fond de l'élément selon sa position et également les éléments de même type situé autour de celui-ci
	 * @param x position horizontal relatif au tableau représentant la position des éléments
	 * @param y position verticale relatif au tableau représentant la position des éléments
	 * @return retourne un boolean, "true" si la case est survolé et qu'elle possède des voisins sinon "false"
	 */
	public boolean updateColor(int x, int y) {

		this.tab_bool = gestion.getLinkedCells(x, y);

		return this.tab_bool[x][y];
	}

	/**
	 * Permet de blanchir le fond de tout les éléments
	 */
	public void removeColor() {
		int i, y;

		for(i=0; i<BoardModel.BOARD_WIDTH; i++) {
			for(y=0; y<BoardModel.BOARD_HEIGHT; y++) {

				this.tab_bool[i][y] = false;
			}
		}
	}
	

	/**
	 * Réecriture de la fonction permettant de dessiner sur la fenêtre
	 * @param p Paramètre donné par la fonction de base
	 */
	@Override
	protected void paintComponent(Graphics p) {
		Image img = Toolkit.getDefaultToolkit().getImage("Plaine.png");

		
    // obligatoire : on crée un nouveau pinceau pour pouvoir le modifier plus tard
		this.pinceau = p.create();
    // obligatoire : si le composant n'est pas censé être transparent
		if (this.isOpaque()) {
      // obligatoire : on repeint toute la surface avec la couleur de fond
			this.pinceau.setColor(this.getBackground());
			this.pinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		// Coloration du fond en blanc
	/*	this.pinceau.setColor(Color.WHITE);
		this.pinceau.fillRect(0, 0, this.getWidth(), this.getHeight()); */

		this.pinceau.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		int i, y;

		for(i=0; i<BoardModel.BOARD_WIDTH; i++) {
			for(y=0; y<BoardModel.BOARD_HEIGHT; y++) {

    			if(this.gestion.getCell(i, y) == BoardModel.CELL_RED) { // si la case est sensé posséder des blocs rouges
    				
    				this.dessinerCube(i, y);
    			}
				else if(this.gestion.getCell(i, y) == BoardModel.CELL_BLUE) { // si la case est sensé posséder des blocs bleus
					
					this.dessinerCercle(i, y);
					

				}
				else if(this.gestion.getCell(i, y) == BoardModel.CELL_GREEN){ // si la case est sensé posséder des blocs verts
					
					this.dessinerHexagone(i, y);


				}
			} // n'affiche rien si la case est considéré vide
		}

		if(this.finish) {

			// dessine "Partie Terminé" au centre de l'écran
			this.pinceau.setColor(Color.BLACK);
			this.pinceau.setFont(new Font("default", Font.ITALIC, ( this.getWidth()/18 )));
			this.pinceau.drawString("Partie Terminé", this.getWidth()/4, this.getHeight()/8);

			this.pinceau.setFont(new Font("default", Font.BOLD, ( this.getWidth()/40 )));
			this.pinceau.drawString("Faites un clic pour revenir au menu principal", this.getWidth()/6, this.getHeight()-10);
		}
	}
}




		
	