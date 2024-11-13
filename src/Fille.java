/**
 * Classe représentant une fille, héritant de la classe {@link Femme}.
 * <p>
 * Cette classe spécialise le comportement de {@link Femme} pour représenter
 * spécifiquement une jeune femme (fille) avec les attributs et comportements
 * associés.
 * </p>
 */
public class Fille extends Femme {

    /**
     * Constructeur de la classe {@code Fille} initialisant le nom.
     * 
     * @param nom Le nom de la fille.
     */
    public Fille(String nom) {
        super(nom);
    }

    /**
     * Indique que cet humain n'est pas un garçon.
     * 
     * @return {@code false}, car il s'agit d'une fille.
     */
    @Override
    public boolean isGarcon() {
        return false;
    }

    /**
     * Indique que cet humain est une fille.
     * 
     * @return {@code true}, car il s'agit d'une fille.
     */
    @Override
    public boolean isFille() {
        return true;
    }

    /**
     * Retourne une représentation textuelle de la fille.
     * 
     * @return Une chaîne de caractères décrivant la fille, incluant les
     *         informations de {@link Femme}.
     */
    @Override
    public String toString() {
        return super.toString() + " | Fille";
    }
}
