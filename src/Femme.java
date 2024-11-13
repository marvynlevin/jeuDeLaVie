/**
 * Classe représentant une femme dans le système, héritant de la classe
 * {@link Humain}.
 * <p>
 * Gère les spécificités liées à la fertilité, à la prise de poids au cours de
 * la vie,
 * et à la possibilité de rencontrer un autre humain pour potentiellement donner
 * naissance
 * à un enfant.
 * </p>
 */
public class Femme extends Humain {

    /**
     * Le niveau de fertilité de la femme, compris entre 0 et 100.
     */
    private int fertilite;

    /**
     * Constructeur de la classe {@code Femme} initialisant le nom et la fertilité à
     * 0.
     * 
     * @param nom Le nom de la femme.
     */
    Femme(String nom) {
        super(nom);
        fertilite = 0;
        setEsperanceVie();
    }

    /**
     * Constructeur de la classe {@code Femme} initialisant l'âge, le poids, le nom
     * et la fertilité.
     * 
     * @param age       L'âge de la femme.
     * @param poids     Le poids de la femme.
     * @param nom       Le nom de la femme.
     * @param fertilite Le niveau de fertilité de la femme.
     */
    Femme(int age, int poids, String nom, int fertilite) {
        super(age, poids, nom);
        this.fertilite = fertilite;
        setEsperanceVie();
    }

    /**
     * Retourne le niveau de fertilité de la femme.
     * 
     * @return Le niveau de fertilité de la femme.
     */
    @Override
    public int getFertilite() {
        return fertilite;
    }

    /**
     * Augmente l'âge de la femme d'un an et met à jour la fertilité et le poids en
     * conséquence.
     * La fertilité est déterminée aléatoirement à partir de l'âge de 15 ans.
     */
    public void vieillir() {
        age++;
        if (age == 15) {
            fertilite = loto.nextInt(101);
        }
        if (age <= 20) {
            poids = 3 + (int) (2.6 * age);
        } else if (age >= 50) {
            poids += (age % 2);
        }
    }

    /**
     * Rencontrer un autre humain et potentiellement donner naissance à un enfant.
     * <p>
     * Cette méthode vérifie différentes conditions telles que le poids, l'âge et le
     * sexe
     * des deux humains avant de permettre une naissance.
     * Si les conditions ne sont pas remplies, elle lève une exception
     * {@link BreedingForbiddenException}
     * ou {@link NoBreedingException}.
     * </p>
     * 
     * @param h L'humain rencontré.
     * @return L'enfant issu de la rencontre si toutes les conditions sont réunies.
     * @throws BreedingForbiddenException Si la naissance est impossible à cause
     *                                    d'une condition non respectée.
     * @throws NoBreedingException        Si la rencontre ne mène pas à une
     *                                    naissance en raison de la fertilité.
     */
    @Override
    public Humain rencontre(Humain h) throws BreedingForbiddenException, NoBreedingException {
        if (this.poids > 150 || h.getPoids() > 150) {
            throw new BreedingForbiddenException(this, h, BreedingForbiddenException.Erreur.POIDS);
        }

        if ((!(this.getAge() > 15 && this.getAge() < 50)) || (!(h.getAge() > 15 && h.getAge() < 50))) {
            throw new BreedingForbiddenException(this, h, BreedingForbiddenException.Erreur.AGE);
        }

        if (h.isFemme() || h.isFille()) {
            throw new BreedingForbiddenException(this, h, BreedingForbiddenException.Erreur.SEXE);
        }

        int b = loto.nextInt(101);
        Homme h1 = (Homme) h;
        if (b < h1.getBatifolage()) {
            throw new NoBreedingException(h);
        }

        int f = loto.nextInt(101);
        if (f > this.fertilite) {
            throw new NoBreedingException(this);
        }

        int p = loto.nextInt(101);
        Humain bebe;
        String nomBaby = h.getNom() + nom + " ";
        if (p < 50) {
            bebe = new Garcon(nomBaby);
        } else {
            bebe = new Fille(nomBaby);
        }

        int g = loto.nextInt(21);
        h.grossir(g);
        this.grossir(10);

        return bebe;
    }

    /**
     * Définir une espérance de vie aléatoire pour la femme, comprise entre 55 et 95
     * ans.
     */
    protected void setEsperanceVie() {
        esperanceVie = loto.nextInt(55, 95);
    }

    /**
     * Indique que cet humain n'est pas un homme.
     * 
     * @return {@code false}, car il s'agit d'une femme.
     */
    @Override
    public boolean isHomme() {
        return false;
    }

    /**
     * Indique que cet humain est une femme.
     * 
     * @return {@code true}, car il s'agit d'une femme.
     */
    @Override
    public boolean isFemme() {
        return true;
    }

    /**
     * Retourne le salaire de la femme, qui est de 0 dans cette implémentation.
     * 
     * @return Le salaire, ici {@code 0}.
     */
    @Override
    public int getSalaire() {
        return 0;
    }

    /**
     * Indique que cet humain n'est pas un garçon.
     * 
     * @return {@code false}, car il s'agit d'une femme.
     */
    @Override
    public boolean isGarcon() {
        return false;
    }

    /**
     * Indique que cet humain n'est pas une fille.
     * 
     * @return {@code false}, car il s'agit d'une femme.
     */
    @Override
    public boolean isFille() {
        return false;
    }

    /**
     * Retourne une représentation textuelle de la femme.
     * 
     * @return Une chaîne de caractères décrivant la femme, incluant les
     *         informations de {@link Humain}.
     */
    @Override
    public String toString() {
        return super.toString() + " | Femme";
    }
}
