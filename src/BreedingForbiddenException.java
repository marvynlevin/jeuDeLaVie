/**
 * Exception levée lorsqu'une naissance entre deux humains est impossible 
 * en raison de certaines restrictions.
 * <p>
 * Cette exception étend {@link MeetingException} et est utilisée pour 
 * signaler qu'une rencontre n'a pas pu aboutir à une naissance en raison 
 * de l'une des conditions suivantes : incompatibilité de sexe, âge ou poids.
 * </p>
 */
public class BreedingForbiddenException extends MeetingException {

    /**
     * Énumération des motifs d'impossibilité de la naissance.
     */
    public enum Erreur {
        /** Motif lié à une incompatibilité de sexe entre les deux humains. */
        SEXE,
        /** Motif lié à une incompatibilité de poids entre les deux humains. */
        POIDS,
        /** Motif lié à une incompatibilité d'âge entre les deux humains. */
        AGE
    };

    /**
     * Motif de l'impossibilité de la naissance.
     */
    Erreur motif;

    /**
     * Constructeur de l'exception BreedingForbiddenException.
     * 
     * @param h1 Le premier humain impliqué dans la rencontre.
     * @param h2 Le second humain impliqué dans la rencontre.
     * @param motif Le motif de l'impossibilité de la naissance.
     */
    public BreedingForbiddenException(Humain h1, Humain h2, Erreur motif) {
        super(h1, h2);
        this.motif = motif;
    }

    /**
     * Retourne le message de l'exception indiquant la cause de l'impossibilité de la naissance.
     * 
     * @return Un message indiquant que la naissance est impossible entre les deux humains 
     *         {@code h1} et {@code h2} en précisant le motif (sexe, poids ou âge).
     */
    @Override
    public String getMessage() {
        String message = "naissance impossible" + source[0].getNom() + " et " + source[1].getNom();
        source = getHumain();
        switch (motif) {
            case SEXE:
                return message + " sont de meme sexe";
            case POIDS:
                return message + " sont trop lourds";
            case AGE:
                return message + " n'ont pas l'âge";
            default:
                return "";
        }
    }
}
