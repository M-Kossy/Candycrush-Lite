import java.io.*;

/**
 * Classe qui gère un plateau généré à partir d'un fichier
 * @version 1.0.0
 * @author Guillaume TOUTAIN
 */
public class FileBoard extends BoardModel
{
    /**
     * Constructeur qui générere le tableau à partir d'un fichier
     * @param path Chemin du fichier à lire
     * @throws Exception Erreur de lecture du fichier
     */
    public FileBoard(String path) throws Exception
    {
        super();

        try
        {
            // On crée le fichier et le flux
            FileReader fichier = new FileReader(path);
            BufferedReader flux = new BufferedReader(fichier);

            try
            {
                for (int i = 0; i < BoardModel.BOARD_HEIGHT; i++)
                {
                    String ligne = flux.readLine();

                    if (ligne == null) // Si on a un null, alors on est à la fin du fichier
                    {
                        System.err.println("Taille du fichier incorrect");
                        throw new Exception("Taille du fichier incorrect");
                    }

                    char[] chars = ligne.toCharArray();

                    // On verifie la taille de la ligne
                    if (chars.length != BoardModel.BOARD_WIDTH)
                    {
                        System.err.println("Taille du fichier incorrect");
                        throw new Exception("Taille du fichier incorrect");
                    }

                    for (int j = 0; j < BoardModel.BOARD_WIDTH; j++)
                    {
                        if (chars[j] == BoardModel.CELL_BLUE || chars[j] == BoardModel.CELL_RED || chars[j] == BoardModel.CELL_GREEN)
                        {
                            board[j][i] = chars[j];
                        }
                        else
                        {
                            System.err.println("Caractère interdit [" + chars[j] + "] dans le fichier !");
                            throw new Exception("Caractère interdit [" + chars[j] + "] dans le fichier !");
                        }
                    }
                }

                try
                {
                    // On ferme le flux
                    flux.close();
                }
                catch (IOException e)
                {
                    System.err.println("Erreur lors de la fermuture du flux");
                    throw new Exception("Erreur lors de la fermuture du flux");
                }
            }
            catch (EOFException e)
            {
                // On est arrivé au bout du fichier trop tot
                System.err.println("Fichier incomplet");
                throw new Exception("Fichier incomplet");
            }
            catch (IOException e)
            {
                // Erreur pendant la lecture
                System.err.println("Erreur de lecture");
                throw new Exception("Erreur de lecture");
            }
        }
        catch (IOException e)
        {
            // On ecrit l'erreur et on lève une exception
            System.err.println("Impossible d'ouvrir le fichier [" + path + "] en lecture !");
            throw new Exception("Impossible d'ouvrir le fichier [" + path + "] en lecture !");
        }
    }
}