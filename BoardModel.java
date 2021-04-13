/**
 * Classe abstraite pour sauvegarder l'etat du jeu et gerer les règles (gravite, etc...)
 * @version 1.0.0
 * @author Guillaume TOUTAIN
 */
public abstract class BoardModel 
{
    /**
     * Contient le tableau de jeu
     */
    protected char[][] board;

    /**
     * Contient le score
     */
    protected int score;

    /**
     * Constante d'une case avec un element rouge
     */
    public static final char CELL_RED = 'R';

    /**
     * Constante d'une case avec un element vert
     */
    public static final char CELL_GREEN = 'V';

    /**
     * Constante d'une case avec un element bleu
     */
    public static final char CELL_BLUE = 'B';

    /**
     * Constante d'une case vide
     */
    public static final char CELL_EMPTY = ' ';

    /**
     * Constante de la taille horizontale du plateau
     */
    public static final int BOARD_WIDTH = 15;

    /**
     * Constante de la taille verticale du plateau
     */
    public static final int BOARD_HEIGHT = 10;

    /**
     * Constructeur de classe
     */
    public BoardModel()
    {
        score = 0;
        board = new char[BoardModel.BOARD_WIDTH][BoardModel.BOARD_HEIGHT];
    }

    /**
     * Peremet de recuperer la totalité du de l'etat du plateau
     * @return Tableau à deux dimensions de char
     */
    public char[][] getAllBoard()
    {
        return board;
    }

    /**
     * Peremet de recuperer les cellules liées à une cellule
     * @return Tableau à deux dimensions de boolean
     */
    public boolean[][] getLinkedCells(int x, int y)
    {
        boolean[][] linkedCells = new boolean[BoardModel.BOARD_WIDTH][BoardModel.BOARD_HEIGHT];

        for (int i = 0; i < BoardModel.BOARD_WIDTH; i++)
        {
            for (int j = 0; j < BoardModel.BOARD_HEIGHT; j++)
            {
                linkedCells[i][j] = false;
            }
        }

        char filter = board[x][y];

        // Listes chaines utilisée sous la forme LIFO pour stocker les points à parcourir
        Liste storageX = new Liste();
        Liste storageY = new Liste();

        // On ajoute le premier point
        storageX.add(x);
        storageY.add(y);

        // Nombre de points parcourus
        int pointParsed = 0;

        // On parcours le tableau
        while (!storageX.isEmpty() && !storageY.isEmpty())
        {
            // On récupère le premier point de la file
            int localx = storageX.remove();
            int localy = storageY.remove();
            pointParsed++;

            if (!linkedCells[localx][localy])
            {
                // On marque la case actuelle
                linkedCells[localx][localy] = true;

                if (localy != 0) // Verif en haut
                {
                    if (board[localx][localy - 1] == filter)
                    {
                        // On ajoute à la file le point 
                        storageX.add(localx);
                        storageY.add(localy - 1);
                    }
                }

                if (localx != (BoardModel.BOARD_WIDTH - 1)) // Verif droite
                {
                    if (board[localx + 1][localy] == filter)
                    {
                        // On ajoute à la file le point 
                        storageX.add(localx + 1);
                        storageY.add(localy);
                    }
                }

                if (localy != (BoardModel.BOARD_HEIGHT - 1)) // Verif bas
                {
                    if (board[localx][localy + 1] == filter)
                    {
                        // On ajoute à la file le point 
                        storageX.add(localx);
                        storageY.add(localy + 1);
                    }
                }

                if (localx != 0) // Verif gauche
                {
                    if (board[localx - 1][localy] == filter)
                    {
                        // On ajoute à la file le point 
                        storageX.add(localx - 1);
                        storageY.add(localy);
                    }
                }
            }
        }

        if (pointParsed <= 1) // Si on a trouvé 1 ou moins de points connectes alors on indique aucune case connectée
        {
            for (int i = 0; i < BoardModel.BOARD_WIDTH; i++)
            {
                for (int j = 0; j < BoardModel.BOARD_HEIGHT; j++)
                {
                    linkedCells[i][j] = false;
                }
            }
        }

        return linkedCells;
    }

    /**
     * Permet de recuperer l'etat d'une cellule
     * @param x position horizontale
     * @param y position verticale
     * @return char qui represente l'etat de la cellule (voir constantes)
     */
    public char getCell(int x, int y)
    {
        return board[x][y];
    }

    /**
     * Permet de recuperer le score actuel du plateau
     * @return le score actuel
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Permet de mettre à jour le plateau apres un click
     * @param x coordonnées x du click
     * @param y coordonnées y du click
     */
    public void registerClick(int x, int y)
    {
        char filter = board[x][y];

        // On ne peux pas supprimer du vide
        if (filter != BoardModel.CELL_EMPTY)
        {
            boolean[][] linkedCells = getLinkedCells(x, y);
            int destroyedCells = 0;

            for (int i = 0; i < BoardModel.BOARD_WIDTH; i++)
            {
                for (int j = 0; j < BoardModel.BOARD_HEIGHT; j++)
                {
                    // si la case est liée au click alors on la supprime
                    if (linkedCells[i][j])
                    {
                        board[i][j] = BoardModel.CELL_EMPTY;
                        destroyedCells++;
                    }
                }
            }
            
            score += (destroyedCells - 2) * (destroyedCells - 2);
        }

        // Gestion décalement sur la gauche

        // On crée un nouveau plateau temporaire
        char[][] newBoard = new char[BoardModel.BOARD_WIDTH][BoardModel.BOARD_HEIGHT];

        // On initialise avec des espaces
        for (int j = 0; j < BoardModel.BOARD_WIDTH; j ++)
        {
            for (int k = 0; k < BoardModel.BOARD_HEIGHT; k++)
            {   
                newBoard[j][k] = BoardModel.CELL_EMPTY;
            }
        }

        // On cherche des collones vides
        for (int i = (BoardModel.BOARD_WIDTH - 1); i >= 0; i--)
        {
            boolean isRowEmpty = true;

            for (int j = 0; j < BoardModel.BOARD_HEIGHT; j++)
            {
                if (board[i][j] != BoardModel.CELL_EMPTY)
                {
                    isRowEmpty = false;
                }
            }

            if (isRowEmpty)
            {
                // On veut "deplacer" la ligne vide tout a droite du plateau
                if (i != 0) // Si on est pas tout à gauche il a des lignes à copier avant
                {
                    // On copie toutes les lignes avant
                    for (int j = 0; j < i; j ++)
                    {
                        for (int k = 0; k < BoardModel.BOARD_HEIGHT; k++)
                        {
                            newBoard[j][k] = board[j][k];
                        }
                    }
                }

                // On copie toutes les lignes apres
                for (int j = i; j < BoardModel.BOARD_WIDTH - 1; j ++)
                {
                    for (int k = 0; k < BoardModel.BOARD_HEIGHT; k++)
                    {
                        newBoard[j][k] = board[j + 1][k];
                    }
                }

                // On remplace le plateau
                board = newBoard;
            }
        }

        // Gestion "gravité"
        // On copie le plateau
        char[][] tempBoard = new char[15][10];
            
        for (int i = 0; i < BoardModel.BOARD_WIDTH; i++)
        {
            System.arraycopy(board[i], 0, tempBoard[i], 0, BoardModel.BOARD_HEIGHT);
        }

        // On cherche des trous
        for (int i = 0; i < BoardModel.BOARD_WIDTH; i++)
        {
            for (int j = 0; j < BoardModel.BOARD_HEIGHT; j++)
            {
                // On a trouvé un trou
                if (board[i][j] == BoardModel.CELL_EMPTY)
                {
                    for (int k = j; k > 0; k--)
                    {
                        tempBoard[i][k] = board[i][k - 1];
                    }

                    tempBoard[i][0] = BoardModel.CELL_EMPTY;
                    board = tempBoard;
                }
            }
        }
    }

    /**
     * Determine si la partie est finie (cad que l'utilisateur ne peux plus rien faire)
     * @return La partie est finie ?
     */
    public boolean isFinished()
    {
        // On cherche le premier groupe d'au moins 2
        for (int i = 0; i < BoardModel.BOARD_WIDTH; i++)
        {
            for (int j = 0; j < BoardModel.BOARD_HEIGHT; j++)
            {
                if (board[i][j] != CELL_EMPTY)
                {
                    if (this.nbOfLinkedCells(i, j) >= 2)
                    {
                        // Il y en a un donc la partie n'est pas finie !
                        return false;
                    }
                }
            }
        }

        // On a rien trouvé donc la pertie est finie
        return true;
    }

    /**
     * Permet de connaitre le nombre de cases connecté
     * @param x Corrdonées X du point de depart
     * @param y Coordonnées Y du point d'arivée
     * @return
     */
    private int nbOfLinkedCells(int x, int y)
    {
        boolean[][] linked = this.getLinkedCells(x, y);

        int nb = 0;

        for (int i = 0; i < BoardModel.BOARD_WIDTH; i++)
        {
            for (int j = 0; j < BoardModel.BOARD_HEIGHT; j++)
            {
                if (linked[i][j])
                {
                    nb++;
                }
            }
        }

        return nb;
    }
}