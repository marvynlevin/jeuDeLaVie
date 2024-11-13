import java.util.*;

/**
 * Classe abstraite représentant un humain, permettant de modéliser les
 * comportements
 * et les attributs communs à tous les types d'humains.
 * <p>
 * La classe {@code Humain} implémente l'interface {@link Comparable} pour
 * permettre
 * la comparaison entre les instances d'humains. Elle définit les attributs
 * communs
 * tels que l'âge, le poids, le nom et l'espérance de vie, ainsi que des
 * méthodes
 * abstraites pour les comportements spécifiques à chaque sous-classe (Homme,
 * Femme, etc.).
 * </p>
 */
abstract class Humain implements Comparable<Humain> {

    /**
     * Instance de {@link Random} pour générer des nombres aléatoires,
     * initialisée avec la date actuelle.
     */
    protected static Random loto = new Random(Calendar.getInstance().getTimeInMillis());

    /**
     * L'âge de l'humain.
     */
    protected int age;

    /**
     * Le poids de l'humain.
     */
    protected int poids;

    /**
     * Le nom de l'humain.
     */
    protected String nom;

    /**
     * L'espérance de vie de l'humain.
     */
    protected int esperanceVie;

    /**
     * Constructeur de la classe {@code Humain} initialisant le nom.
     * 
     * @param nom Le nom de l'humain.
     */
    Humain(String nom) {
        age = 0;
        poids = 3;
        this.nom = nom;
        setEsperanceVie();
    }

    /**
     * Constructeur de la classe {@code Humain} initialisant l'âge, le poids et le
     * nom.
     * 
     * @param age   L'âge de l'humain.
     * @param poids Le poids de l'humain.
     * @param nom   Le nom de l'humain.
     */
    Humain(int age, int poids, String nom) {
        this.age = age;
        this.poids = poids;
        this.nom = nom;
        setEsperanceVie();
    }

    /**
     * Indique si cet humain est un homme.
     * 
     * @return {@code true} si l'humain est un homme, {@code false} sinon.
     */
    public abstract boolean isHomme();

    /**
     * Indique si cet humain est une femme.
     * 
     * @return {@code true} si l'humain est une femme, {@code false} sinon.
     */
    public abstract boolean isFemme();

    /**
     * Rencontrer un autre humain et potentiellement donner naissance à un enfant.
     * 
     * @param h L'humain rencontré.
     * @return L'enfant issu de la rencontre si toutes les conditions sont réunies.
     * @throws BreedingForbiddenException Si la naissance est impossible à cause
     *                                    d'une condition non respectée.
     * @throws NoBreedingException        Si la rencontre ne mène pas à une
     *                                    naissance en raison de la fertilité.
     */
    public abstract Humain rencontre(Humain h) throws BreedingForbiddenException, NoBreedingException;

    /**
     * Retourne le niveau de fertilité de l'humain.
     * 
     * @return Le niveau de fertilité de l'humain.
     */
    public abstract int getFertilite();

    /**
     * Retourne le salaire de l'humain.
     * 
     * @return Le salaire de l'humain.
     */
    public abstract int getSalaire();

    /**
     * Indique si cet humain est un garçon.
     * 
     * @return {@code true} si l'humain est un garçon, {@code false} sinon.
     */
    public abstract boolean isGarcon();

    /**
     * Indique si cet humain est une fille.
     * 
     * @return {@code true} si l'humain est une fille, {@code false} sinon.
     */
    public abstract boolean isFille();

    /**
     * Définit le nom de l'humain.
     * 
     * @param nom Le nouveau nom à assigner à l'humain.
     */
    void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit l'âge de l'humain.
     * 
     * @param age Le nouvel âge à assigner à l'humain.
     */
    void setAge(int age) {
        this.age = age;
    }

    /**
     * Définit le poids de l'humain.
     * 
     * @param poids Le nouveau poids à assigner à l'humain.
     */
    void setPoids(int poids) {
        this.poids = poids;
    }

    /**
     * Retourne l'âge de l'humain.
     * 
     * @return L'âge de l'humain.
     */
    int getAge() {
        return age;
    }

    /**
     * Retourne le poids de l'humain.
     * 
     * @return Le poids de l'humain.
     */
    int getPoids() {
        return poids;
    }

    /**
     * Retourne le nom de l'humain.
     * 
     * @return Le nom de l'humain.
     */
    String getNom() {
        return nom;
    }

    /**
     * Définit l'espérance de vie de l'humain.
     */
    protected void setEsperanceVie() {
        esperanceVie = 70; // Valeur par défaut pour l'espérance de vie.
    }

    /**
     * Augmente l'âge de l'humain d'un an.
     */
    public void vieillir() {
        age++;
    }

    /**
     * Augmente le poids de l'humain en fonction d'un paramètre donné.
     * 
     * @param p La quantité de poids à ajouter à l'humain.
     */
    public void grossir(int p) {
        poids -= p; // Le poids diminue en raison de la façon dont cette méthode est conçue.
    }

    /**
     * Vérifie si l'humain est mort.
     * 
     * @return {@code true} si l'humain est mort, {@code false} sinon.
     */
    public boolean isDead() {
        return age > esperanceVie || poids < 0;
    }

    /**
     * Retourne une représentation textuelle de l'humain.
     * 
     * @return Une chaîne de caractères décrivant l'humain.
     */
    public String toString() {
        return ("Age : " + age + " | Poids : " + poids + " | Nom : " + nom + " | Esperance de vie : " + esperanceVie);
    }

    /**
     * TUE l'humain en le définissant à un âge fictif élevé.
     */
    public void tuer() {
        setAge(1000);
    }

    /**
     * Compare cet humain avec un autre humain.
     * 
     * @param h L'humain à comparer.
     * @return Un entier indiquant la relation d'ordre :
     *         <ul>
     *         <li>Un nombre négatif si cet humain est inférieur à l'humain passé en
     *         paramètre.</li>
     *         <li>0 si cet humain est égal à l'humain passé en paramètre.</li>
     *         <li>Un nombre positif si cet humain est supérieur à l'humain passé en
     *         paramètre.</li>
     *         </ul>
     */
    public int compareTo(Humain h) {
        return 0; // À compléter pour une comparaison significative
    }

    /**
     * Comparateur pour trier les humains par sexe.
     */
    public static Comparator<Humain> ComparatorSex = new Comparator<Humain>() {
        @Override
        public int compare(Humain e1, Humain e2) {
            if (e1.isHomme() && e2.isFemme()) {
                return 1;
            } else if (e1.isHomme() && e2.isHomme()) {
                return 0;
            }
            return -1;
        }
    };

    /**
     * Comparateur pour trier les humains par âge.
     */
    public static Comparator<Humain> ComparatorAge = new Comparator<Humain>() {
        @Override
        public int compare(Humain e1, Humain e2) {
            return (int) (e1.getAge() - e2.getAge());
        }
    };

    /**
     * Comparateur pour trier les humains par salaire.
     */
    public static Comparator<Humain> ComparatorSalary = new Comparator<Humain>() {
        @Override
        public int compare(Humain e1, Humain e2) {
            if (e1.isHomme() && e2.isHomme()) {
                return e1.getSalaire() - e2.getSalaire();
            }
            return 0;
        }
    };
}
