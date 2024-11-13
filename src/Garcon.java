/**
 * Classe représentant un garçon, héritant de la classe {@link Homme}.
 * <p>
 * Cette classe spécialise le comportement de {@link Homme} pour représenter
 * spécifiquement un jeune homme (garçon) avec les attributs et comportements
 * associés.
 * </p>
 */
public class Garcon extends Homme {

    /**
     * Constructeur de la classe {@code Garcon} initialisant le nom.
     * 
     * @param nom Le nom du garçon.
     */
    public Garcon(String nom) {
        super(nom);
    }

    /**
     * Indique que cet humain est un garçon.
     * 
     * @return {@code true}, car il s'agit d'un garçon.
     */
    @Override
    public boolean isGarcon() {
        return true;
    }

    /**
     * Indique que cet humain n'est pas une fille.
     * 
     * @return {@code false}, car il s'agit d'un garçon.
     */
    @Override
    public boolean isFille() {
        return false;
    }

    /**
     * Retourne une représentation textuelle du garçon.
     * 
     * @return Une chaîne de caractères décrivant le garçon, incluant les
     *         informations de {@link Homme}.
     */
    @Override
    public String toString() {
        return super.toString() + " | Garçon";
    }
}
