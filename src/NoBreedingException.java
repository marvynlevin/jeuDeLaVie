/**
 * Exception levée lorsqu'une rencontre entre deux humains est improductive
 * à cause de la non-fertilité de l'un d'eux.
 * <p>
 * Cette exception étend {@link MeetingException} et est utilisée pour
 * signaler qu'une rencontre n'a pas abouti à un résultat attendu en raison
 * de la non-fertilité de l'humain concerné.
 * </p>
 */
public class NoBreedingException extends MeetingException {

    /**
     * Constructeur de l'exception NoBreedingException.
     * 
     * @param h L'humain qui est non-fertile et à l'origine de la rencontre
     *          improductive.
     */
    public NoBreedingException(Humain h) {
        super(h, h);
    }

    /**
     * Retourne le message de l'exception indiquant la cause de l'échec de la
     * rencontre.
     * 
     * @return Un message indiquant que la rencontre est improductive et précisant
     *         que l'humain {@code h} n'est pas fertile.
     */
    @Override
    public String getMessage() {
        return "rencontre improductive : " + source[0].getNom() + " n'est pas fertile";
    }
}
