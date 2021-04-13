/** 
 * importation des class nécessaire
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.filechooser.*;


/**
 * La classe <code>ListenerMenu</code> s'occupe de la gestion des interactions avec les boutons de l'écran principale
 * @version 1.0.0
 * @author Dylan GIRAULT
 */
public class ListenerMenu implements ActionListener {

	private JFrame f;

	public ListenerMenu(JFrame fenetre) {
		this.f = fenetre;
	}

	/**
	 * Se lance quand il y a interaction avec des éléments dynamiques.
	 * Il génère les fenetres selon les situations.
	 * @param event paramètre permettant d'avoir des informations sur l'élément qui déclenche la méthode
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		if(event.getActionCommand().equals("Jouer aléatoirement")) {


			new Board(new RandomBoard());
			this.f.dispose();
			

		}
		else if (event.getActionCommand().equals("Quitter le Jeu")) {
			System.exit(0);
		}
		else if(event.getActionCommand().equals("Jouer à partir d'un fichier")) {
			int verify;

			try { // rend les éléments visibles plus soigneux
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch(Exception e) {/* ne fais rien */}

			try{ // ouverture d'une fenetre pour trouver les fichiers
				JFileChooser chooser = new JFileChooser("./");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt Texte", "txt");

				chooser.setFileFilter(filter);

				verify = chooser.showOpenDialog(null);

				if(verify == JFileChooser.APPROVE_OPTION) {

					try { // lecture du fichier obtenu
						FileBoard gestion = new FileBoard(chooser.getSelectedFile().getName());

						new Board(gestion);
						this.f.dispose();
						
					} catch (Exception e) {

						new JOptionPane().showMessageDialog(this.f, e.getMessage(), "error", JOptionPane.WARNING_MESSAGE);
					}

				}
				else { // aucun fichier n'a été sélection
					
				}

			} catch(Exception e) {// ne lance pas le plateau de jeu
			}

		}
	}
}