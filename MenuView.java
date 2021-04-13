import javax.swing.*;
import java.awt.*;


/**
 * La classe <code>MenuView</code> permet l'affichage d'éléments sur l'écran principale
 * @version 1.0.0
 * @author Dylan GIRAULT
 */
public class MenuView extends JComponent {


	/**
	 * Réecriture de la fonction permettant de dessiner sur la fenêtre
	 * @param p Paramètre donné par la fonction de base
	 */
	@Override
	protected void paintComponent(Graphics p) {

    // obligatoire : on crée un nouveau pinceau pour pouvoir le modifier plus tard
		Graphics pinceau = p.create();
    // obligatoire : si le composant n'est pas censé être transparent
		if (this.isOpaque()) {
      // obligatoire : on repeint toute la surface avec la couleur de fond
			pinceau.setColor(this.getBackground());
			pinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		// Création du fond avec l'image
		Image img = Toolkit.getDefaultToolkit().getImage("ruelle.png");
		pinceau.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		// implentation d'un cadre gris
		pinceau.setColor(Color.lightGray);
		pinceau.fillRect(this.getWidth()/2 - 100, this.getHeight()/2 - 25, 210, 30);

		// Ecriture du titre du jeu
		pinceau.setColor(Color.BLACK);
		pinceau.setFont(new Font("default", Font.PLAIN, 23));
		pinceau.drawString("CandyCrush Lite", this.getWidth()/2 - 100, this.getHeight()/2);
	}
}