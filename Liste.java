/**
 * Classe qui simule le comportement d'une liste chainée (de int)
 * @version 1.0.0
 * @author Guillaume TOUTAIN
 */
public class Liste 
{
    private int[] data;
    private int values;

    /**
     * Constructeur de classe
     */
    public Liste()
    {
        // On initialise a un taille définie (optimisation)
        data = new int[BoardModel.BOARD_HEIGHT * BoardModel.BOARD_WIDTH];
        values = 0;
    }

    /**
     * Permet d'ajouter un int à la fin de la liste
     * @param val
     */
    public void add(int val)
    {
        // On ajoute à la fin la valeur
        data[values] = val;

        // On incremente le nombre de valeurs
        values++;
    }

    /**
     * Permet de recuperer la première valeur de la liste chainée
     * @return Première valeur
     */
    public int remove()
    {
        // On recupere le premier élément du tableau
        int val = data[0];
        values--;

        // On décale les valeurs
        for (int i = 0; i < values; i++)
        {
            data[i] = data[i+1];
        }

        // On retourne la valeur
        return val;
    }

    /**
     * Permet de savoir si la liste est vide
     * @return La liste est vide ?
     */
    public boolean isEmpty()
    {
        return values == 0;
    }
}
