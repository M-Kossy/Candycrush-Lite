/** 
 * importation des class dans java.awt.event
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>BoardListener</code> s'occupe de la gestion des actions de la souris durant une partie
 * @version 1.0.0
 * @author Dylan GIRAULT
 */
public class BoardListener implements MouseListener, MouseMotionListener {

	/** 
	* BoardView qui permet d'afficher des éléments dans la fênetre
	*/
	private BoardView view;

	/**
	 * JPanel permettant de modifier l'affichage du score 
	 */
	private JLabel score;

	private boolean finish;

	private JFrame fenetre;

	private BoardModel gestion;

	/** 
	* Construit une classe qui peut changer le score grâce à l'accès aux données de jeu et fermer la fenetre en cas de fin de jeu
	* @param p_view Permet d'interargir avec la fenetre selon les actions de l'utilisateur
	* @param p_score Permet de modifier le label selon le score actuel durant la partie
	* @param p_gestion Permet d'accéder aux données dont le score actuel durant la partie
	* @param p_fenetre Permet de gérer la fenetre en fin de partie
	*/
	public BoardListener(BoardView p_view, JLabel p_score, BoardModel p_gestion, JFrame p_fenetre) {

		this.view = p_view;

		this.score = p_score;

		this.gestion = p_gestion;

		this.finish = this.gestion.isFinished();

		this.fenetre = p_fenetre;
	}

	/**
	* Se lance si la souris est relaché après maintiens
	* @param event Permet de savoir par qui la méthode a été enclenché
	*/
	@Override
	public void mouseReleased(MouseEvent event) {/*Vide*/}


	/** 
	* Se lance si la souris est maintenu
	* @param event Permet de savoir par qui la méthode a été enclenché
	*/
	@Override
	public void mousePressed(MouseEvent event) {/*Vide*/}


	/** Se lance si la souris est en mouvement et que le clic est maintenu
	* @param event Permet de savoir par qui la méthode a été enclenché
	*/
	@Override
	public void mouseDragged(MouseEvent event) {/*Vide*/}


	/** 
	* Se lance si il y a eu un clic
	* @param event Permet de savoir par qui la méthode a été enclenché
	*/
	@Override
	public void mouseClicked(MouseEvent event) { //Si il y a un clic 
			

		// Variables permettant de recalibrer les éléments quand redimensionnement de la fenêtre
		int centrage_X = (view.getWidth()-view.ESPACEMENT*15)/2;
		int centrage_Y = (view.getHeight()-view.ESPACEMENT*10)/2;

		int x, y;

		if(this.finish) {
			this.fenetre.dispose();

			new Menu();
		}

		for(x=0; x<15; x++) {
			for(y=0; y<10; y++) {

				if((x*view.ESPACEMENT+centrage_X-view.SEPARATION_PAR_CASE/2 < event.getX()) && (event.getX() < x*view.ESPACEMENT+centrage_X-view.SEPARATION_PAR_CASE/2+23+view.SEPARATION_PAR_CASE)
				&& (y*view.ESPACEMENT+centrage_Y-view.SEPARATION_PAR_CASE/2 < event.getY()) && (event.getY() < y*view.ESPACEMENT+centrage_Y-view.SEPARATION_PAR_CASE/2+23+view.SEPARATION_PAR_CASE)) 
				{ // si la souris fait un clic au dessus d'un élément 

					
					this.view.updateColor(x, y);
					this.finish = this.view.removeElement(x, y);
					this.score.setText("SCORE : " + this.gestion.getScore());
					this.view.removeColor();
					this.view.updateColor(x, y);
				}
			}
		}
		view.repaint();
	}
	

	/** Se lance si la souris est au dessus de l'élément
	* @param event Permet de savoir par qui la méthode a été enclenché
	*/
	@Override
	public void mouseEntered(MouseEvent event) { /* VIDE */ }

	/** Se lance si la souris est en mouvement et ne maintiens pas le clic
	* @param event Permet de savoir par qui la méthode a été enclenché
	*/
	@Override
	public void mouseMoved(MouseEvent event) { // Se manifeste quand la souris est en mouvement

		// Variables permettant de recalibrer les éléments quand redimensionnement de la fenêtre
		int centrage_X = (view.getWidth()-view.ESPACEMENT*15)/2;
		int centrage_Y = (view.getHeight()-view.ESPACEMENT*10)/2;

		int x, y;

		Cursor curso = new Cursor(Cursor.DEFAULT_CURSOR);

		for(x=0; x<15; x++) {
			for(y=0; y<10; y++) {

				if((x*view.ESPACEMENT+centrage_X-view.SEPARATION_PAR_CASE/2 < event.getX()) && (event.getX() < x*view.ESPACEMENT+centrage_X-view.SEPARATION_PAR_CASE/2+23+view.SEPARATION_PAR_CASE)
				&& (y*view.ESPACEMENT+centrage_Y-view.SEPARATION_PAR_CASE/2 < event.getY()) && (event.getY() < y*view.ESPACEMENT+centrage_Y-view.SEPARATION_PAR_CASE/2+23+view.SEPARATION_PAR_CASE)) 
				{ // si la souris se situe au dessus d'un élément 

					boolean know = this.view.updateColor(x, y); // colore l'élément et les éléments autour

					if(this.gestion.getCell(x, y) != BoardModel.CELL_EMPTY && know) { // change la forme de la souris

						curso = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
					}
				
					
				}
				else if( event.getX() < centrage_X-view.SEPARATION_PAR_CASE/2 || event.getX() > 14*view.ESPACEMENT+centrage_X-view.SEPARATION_PAR_CASE/2+22+view.SEPARATION_PAR_CASE 
					 || event.getY() < centrage_Y-view.SEPARATION_PAR_CASE/2 || event.getY() > 9*view.ESPACEMENT+centrage_Y-view.SEPARATION_PAR_CASE/2+22+view.SEPARATION_PAR_CASE)
				{ // si la souris se situe au dessus d'aucun élément

					this.view.removeColor();
				}
				
			}
		}
		this.fenetre.setCursor(curso);
		view.repaint();


		
	}


	/** 
	* Se lance si la souris n'est plus au dessus de l'élément
	* @param event Permet de savoir par qui la méthode a été enclenché
	*/
	@Override
	public void mouseExited(MouseEvent event) {/* VIDE */}
}