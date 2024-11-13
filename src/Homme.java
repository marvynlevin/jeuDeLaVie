/**
 * Classe représentant un homme, héritant de la classe {@link Humain}.
 * <p>
 * Cette classe gère les spécificités liées à l'âge, au poids, au salaire,
 * au taux de batifolage et aux interactions avec d'autres humains, 
 * notamment en ce qui concerne la rencontre et la possibilité d'engendrer des enfants.
 * </p>
 */
public class Homme extends Humain {

    /**
     * Le taux de batifolage de l'homme, représentant sa propension à engendrer des enfants.
     */
    protected int batifolage;

    /**
     * Le salaire de l'homme.
     */
    private int salaire;

    /**
     * Constructeur de la classe {@code Homme} initialisant le nom.
     * 
     * @param nom Le nom de l'homme.
     */
    Homme(String nom) {
        super(nom);
        batifolage = 0;
        salaire = 0;
        setEsperanceVie();
    }

    /**
     * Constructeur de la classe {@code Homme} initialisant l'âge, le poids, le nom et le taux de batifolage.
     * 
     * @param age L'âge de l'homme.
     * @param poids Le poids de l'homme.
     * @param nom Le nom de l'homme.
     * @param batifolage Le taux de batifolage de l'homme.
     */
    Homme(int age, int poids, String nom, int batifolage) {
        super(age, poids, nom);
        this.batifolage = batifolage;
        setEsperanceVie();
    }

    /**
     * Rencontrer un autre humain et potentiellement donner naissance à un enfant.
     * <p>
     * Cette méthode vérifie différentes conditions telles que l'âge, le poids et le sexe
     * avant de permettre une naissance. Si les conditions ne sont pas remplies, 
     * elle lève une exception {@link BreedingForbiddenException} ou {@link NoBreedingException}.
     * </p>
     * 
     * @param f L'humain rencontré.
     * @return L'enfant issu de la rencontre si toutes les conditions sont réunies.
     * @throws BreedingForbiddenException Si la naissance est impossible à cause d'une condition non respectée.
     * @throws NoBreedingException Si la rencontre ne mène pas à une naissance en raison de la fertilité.
     */
    @Override
    public Humain rencontre(Humain f) throws BreedingForbiddenException, NoBreedingException {
        if ((!(this.getAge() > 15 && this.getAge() < 50)) || (!(f.getAge() > 15 && f.getAge() < 50))) {
            throw new BreedingForbiddenException(this, f, BreedingForbiddenException.Erreur.AGE);
        }

        if (this.poids > 150 || f.getPoids() > 150) {
            throw new BreedingForbiddenException(this, f, BreedingForbiddenException.Erreur.POIDS);
        }

        if (f.isHomme() || f.isGarcon()) {
            throw new BreedingForbiddenException(this, f, BreedingForbiddenException.Erreur.SEXE);
        }

        int b = loto.nextInt(101);
        if (b < batifolage) {
            throw new NoBreedingException(this);
        }

        int c = loto.nextInt(101);
        if (c > f.getFertilite()) {
            throw new NoBreedingException(f);
        }

        int p = loto.nextInt(101);
        Humain bebe;
        String nomBebe = this.nom + f.getNom();

        if (p < 50) {
            bebe = new Garcon(nomBebe);
        } else {
            bebe = new Fille(nomBebe);
        }

        int g = loto.nextInt(-10, 10);
        this.grossir(g);
        f.grossir(10);

        return bebe;
    }

    /**
     * Augmente l'âge de l'homme d'un an et met à jour le salaire, le batifolage et le poids en conséquence.
     * Le salaire est déterminé aléatoirement à partir de l'âge de 18 ans.
     */
    public void vieillir() {
        age++;
        if (age == 18) {
            salaire = loto.nextInt(1000, 11000);
        }

        if (age > 15) {
            batifolage = loto.nextInt(70, 101);
        } else if (age > 30) {
            batifolage = loto.nextInt(30, 51);
        } else if (age > 60) {
            batifolage = loto.nextInt(50, 101);
        }

        if (age <= 20) {
            poids = 3 + (int) (3.6 * age);
        } else if (age >= 50) {
            poids += (age % 2);
        }
    }

    /**
     * Définit une espérance de vie aléatoire pour l'homme, comprise entre 50 et 81 ans.
     */
    protected void setEsperanceVie() {
        esperanceVie = loto.nextInt(50, 81);
    }

    /**
     * Indique que cet humain est un homme.
     * 
     * @return {@code true}, car il s'agit d'un homme.
     */
    @Override
    public boolean isHomme() {
        return true;
    }

    /**
     * Indique que cet humain n'est pas une femme.
     * 
     * @return {@code false}, car il s'agit d'un homme.
     */
    @Override
    public boolean isFemme() {
        return false;
    }

    /**
     * Retourne le niveau de fertilité de l'homme, qui est fixé à -1 dans cette implémentation.
     * 
     * @return Le niveau de fertilité, ici {@code -1}.
     */
    @Override
    public int getFertilite() {
        return -1;
    }

    /**
     * Retourne le salaire de l'homme.
     * 
     * @return Le salaire de l'homme.
     */
    @Override
    public int getSalaire() {
        return salaire;
    }

    /**
     * Retourne une représentation textuelle de l'homme.
     * 
     * @return Une chaîne de caractères décrivant l'homme, incluant les informations de {@link Humain} et le salaire.
     */
    @Override
    public String toString() {
        return (super.toString() + " | Salaire : " + salaire);
    }

    /**
     * Indique que cet humain n'est pas un garçon.
     * 
     * @return {@code false}, car il s'agit d'un homme adulte.
     */
    @Override
    public boolean isGarcon() {
        return false;
    }

    /**
     * Indique que cet humain n'est pas une fille.
     * 
     * @return {@code false}, car il s'agit d'un homme.
     */
    @Override
    public boolean isFille() {
        return false;
    }

    /**
     * Retourne le taux de batifolage de l'homme.
     * 
     * @return Le taux de batifolage de l'homme.
     */
    public int getBatifolage() {
        return batifolage;
    }
}
