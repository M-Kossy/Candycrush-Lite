import java.util.Random;

/**
 * Classe qui gère un plateau généré aléatoirement
 * @version 1.0.0
 * @author Guillaume TOUTAIN
 */
public class RandomBoard extends BoardModel
{
    /**
     * Génère un tableau aléatoirement
     */
    public RandomBoard()
    {
        super();

        Random rdm = new Random();

        // Tableau des "tokens" disponibles
        char[] tokens = new char[] {BoardModel.CELL_BLUE, BoardModel.CELL_GREEN, BoardModel.CELL_RED};

        // Remplissage aléatoire du tableau
        for (int i = 0; i < BoardModel.BOARD_WIDTH; i++)
        {
            for (int j = 0; j < BoardModel.BOARD_HEIGHT; j++)
            {
                board[i][j] = tokens[rdm.nextInt(3)];
            }
        }
    }
}
