/**
 * Exception lancée lors d'un problème de rencontre entre deux humains.
 * <p>
 * Cette classe hérite de {@code Exception} et encapsule des informations
 * sur les humains impliqués dans la rencontre qui a échoué.
 * </p>
 */
class MeetingException extends Exception {

    /** Les humains impliqués dans la rencontre. */
    protected Humain[] source;

    /**
     * Constructeur pour créer une nouvelle instance de {@code MeetingException}.
     *
     * @param h1 Le premier humain impliqué dans la rencontre.
     * @param h2 Le deuxième humain impliqué dans la rencontre.
     */
    public MeetingException(Humain h1, Humain h2) {
        super("problème de rencontre");
        source = new Humain[2];
        source[0] = h1;
        source[1] = h2;
    }

    /**
     * Obtient les humains impliqués dans la rencontre.
     *
     * @return Un tableau contenant les deux humains concernés par l'exception.
     */
    public Humain[] getHumain() {
        return source;
    }
}
