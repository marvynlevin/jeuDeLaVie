/**
 * Classe représentant une exception liée à une population.
 * <p>
 * Cette classe est utilisée pour signaler des problèmes spécifiques
 * qui peuvent survenir lors de la gestion d'une population d'humains.
 * </p>
 */
class PopulationException extends Exception {

    /** L'objet population associé à l'exception. */
    Population pop;

    /**
     * Constructeur de la classe {@code PopulationException}.
     *
     * @param pop L'objet population associé à l'exception.
     */
    public PopulationException(Population pop) {
        super("This is a population exception");
        this.pop = pop;
    }

    /**
     * Obtient un message d'erreur spécifique en fonction de l'état de la
     * population.
     *
     * @return Un message décrivant le type d'erreur de population.
     */
    @Override
    public String getMessage() {
        if (pop.taille() <= 1) {
            return "population is empty";
        }
        if (pop.onlyMen() || pop.onlyWomen()) {
            return "population cannot grow";
        }
        return "Population error";
    }
}
