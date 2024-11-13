import java.util.*;

/**
 * Classe représentant une population d'humains.
 * <p>
 * Cette classe permet de gérer une liste d'objets de type {@code Humain},
 * d'effectuer des opérations sur cette population, telles que l'ajout,
 * la suppression, le vieillissement des individus, et le tri de la population.
 * </p>
 */
class Population {

    /** Liste des humains dans la population. */
    List<Humain> pop;

    /**
     * Constructeur de la classe {@code Population}.
     * <p>
     * Initialise une nouvelle population vide.
     * </p>
     */
    Population() {
        pop = new ArrayList<Humain>();
    }

    /**
     * Vide la population en supprimant tous les humains.
     */
    public void vider() {
        pop.clear();
    }

    /**
     * Ajoute un humain à la population.
     *
     * @param h L'humain à ajouter.
     */
    public void addHumain(Humain h) {
        pop.add(h);
    }

    /**
     * Obtient un humain à un index donné.
     *
     * @param index L'index de l'humain à récupérer.
     * @return L'humain à l'index spécifié.
     */
    public Humain getHumain(int index) {
        return pop.get(index);
    }

    /**
     * Supprime un humain de la population.
     *
     * @param h L'humain à supprimer.
     * @return L'humain supprimé, ou {@code null} si l'humain n'existe pas.
     */
    public Humain removeHumain(Humain h) {
        for (Humain humain : pop) {
            if (humain == h) {
                pop.remove(h);
                return h;
            }
        }
        return null;
    }

    /**
     * Supprime un humain à un index donné.
     *
     * @param index L'index de l'humain à supprimer.
     * @return L'humain supprimé.
     */
    public Humain removeHumain(int index) {
        Humain h = getHumain(index);
        pop.remove(index);
        return h;
    }

    /**
     * Obtient la taille de la population.
     *
     * @return Le nombre d'humains dans la population.
     */
    public int taille() {
        return pop.size();
    }

    /**
     * Fait vieillir tous les humains de la population.
     *
     * @throws PopulationException Si la population est vide ou ne contient que des
     *                             hommes ou que des femmes.
     */
    public void vieillir() throws PopulationException {
        if (this.taille() <= 1) {
            throw new PopulationException(this);
        }
        if (onlyMen() || onlyWomen()) {
            throw new PopulationException(this);
        }

        List<Humain> tempList = new ArrayList<Humain>();
        List<Integer> indexTemp = new ArrayList<Integer>();

        for (int i = pop.size() - 1; i >= 0; i--) {
            Humain h = pop.get(i);

            if (h.isGarcon() && h.getAge() == 17) {
                pop.remove(i); // Safe removal
                Homme ho = new Homme(17, h.getPoids(), h.getNom(), 0);
                ho.vieillir();
                tempList.add(ho);
                indexTemp.add(i);
            } else if (h.isFille() && h.getAge() == 17) {
                Femme f = new Femme(17, h.getPoids(), h.getNom(), h.getFertilite());
                f.vieillir();
                tempList.add(f);
                indexTemp.add(i);
            }

            if (h.isHomme() || h.isGarcon()) {
                ((Homme) h).vieillir();
            } else if (h.isFemme() || h.isFille()) {
                ((Femme) h).vieillir();
            }
        }

        for (int i = 0; i < tempList.size(); i++) {
            removeHumain(indexTemp.get(i));
            addHumainIndex(indexTemp.get(i), tempList.get(i));
        }

        for (int i = pop.size() - 1; i >= 0; i--) {
            Humain h = pop.get(i);
            if (h.isDead()) {
                System.out.println(h.getNom() + " est mort !");
                pop.remove(i); // Safe removal
            }
        }
    }

    /**
     * Affiche la population et le nombre d'individus.
     */
    public void print() {
        for (Humain h : pop) {
            if (h.isHomme()) {
                System.out.println((Homme) h);
            } else {
                System.out.println(h);
            }
        }
        System.out.println("Population de : " + pop.size() + " individus");
    }

    /**
     * Obtient la liste des humains dans la population.
     *
     * @return La liste des humains.
     */
    public List<Humain> getPop() {
        return pop;
    }

    /**
     * Trie la population en fonction de plusieurs critères.
     */
    public void trierPop() {
        Collections.sort(pop, Humain.ComparatorSex);
        Collections.sort(pop, Humain.ComparatorSalary);
        Collections.sort(pop, Humain.ComparatorAge);
    }

    /**
     * Ajoute un humain à un index donné.
     *
     * @param index L'index où l'humain doit être ajouté.
     * @param h     L'humain à ajouter.
     */
    public void addHumainIndex(int index, Humain h) {
        pop.add(index, h);
    }

    /**
     * Vérifie si la population ne contient que des hommes.
     *
     * @return {@code true} si la population ne contient que des hommes, sinon
     *         {@code false}.
     */
    public boolean onlyMen() {
        for (Humain h : pop) {
            if (h.isFemme() || h.isFille()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si la population ne contient que des femmes.
     *
     * @return {@code true} si la population ne contient que des femmes, sinon
     *         {@code false}.
     */
    public boolean onlyWomen() {
        for (Humain h : pop) {
            if (h.isHomme() || h.isGarcon()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Effectue une rencontre entre deux humains de la population.
     *
     * @param index1 L'index du premier humain.
     * @param index2 L'index du deuxième humain.
     * @return Le bébé résultant de la rencontre.
     * @throws MeetingException Si un problème survient lors de la rencontre.
     */
    public Humain rencontre(int index1, int index2) throws MeetingException {
        Humain h1 = getHumain(index1);
        Humain h2 = getHumain(index2);
        return h1.rencontre(h2);
    }

    /**
     * Obtient l'index d'un humain donné.
     *
     * @param h L'humain dont l'index doit être trouvé.
     * @return L'index de l'humain, ou 0 si l'humain n'est pas trouvé.
     */
    public int getIndex(Humain h) {
        for (int i = 0; i < taille(); i++) {
            if (pop.get(i).equals(h)) {
                return i;
            }
        }
        return 0;
    }
}
