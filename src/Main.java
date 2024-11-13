import java.util.*;

/**
 * Classe principale pour gérer la simulation d'une population d'humains.
 * <p>
 * Cette classe contient la méthode {@code main} qui initialise la population,
 * gère les interactions entre les humains, et exécute les cycles de génération.
 * Les paramètres de la simulation peuvent être ajustés via les arguments de la
 * ligne de commande.
 * </p>
 */
public class Main {
    /**
     * Point d'entrée de l'application.
     *
     * @param args Les arguments de la ligne de commande.
     *             Les paramètres attendus sont :
     *             <ul>
     *             <li>nbTourDeJeu : Le nombre de tours de jeu à exécuter.</li>
     *             <li>tailleInit : La taille initiale de la population.</li>
     *             <li>param : Paramètres de fonctionnement (0 : normal, 1 :
     *             croissance forcée, 2 : croissance régulée).</li>
     *             <li>nbb (facultatif) : Pour le paramètre 2, spécifie un nombre
     *             entre 1 et 100.</li>
     *             </ul>
     * @throws PopulationException Si la population ne peut pas se reproduire.
     * @throws MeetingException    Si une erreur survient lors d'une rencontre entre
     *                             humains.
     */
    public static void main(String[] args) throws PopulationException, MeetingException {

        Random loto = new Random(Calendar.getInstance().getTimeInMillis());

        int nbTourDeJeu = Integer.parseInt(args[0]);
        int tailleInit = Integer.parseInt(args[1]);
        int param = Integer.parseInt(args[2]);
        int nbb = 0;

        /*
         * 0 Fonctionnement normal
         * 1 Croissance forcée
         * 2 Croissance régulée
         */
        if (param == 2) {
            nbb = Integer.parseInt(args[3]);
            if (nbb < 1 || nbb > 100) {
                System.out.println("Erreur sur dernier args");
                return;
            }
        }

        // Création de la population initiale
        Population population = new Population();
        for (int i = 0; i < tailleInit / 2; i++) {
            int batifolage = loto.nextInt(70, 100);
            population.addHumain(new Homme(17, 70, "Homme" + i, batifolage));
            Homme h = (Homme) population.getHumain(i);
            h.vieillir();
        }

        for (int i = 0; i < tailleInit / 2; i++) {
            int fertilite = loto.nextInt(1, 100);
            population.addHumain(new Femme(17, 70, "Femme" + i, fertilite));
            Femme f = (Femme) population.getHumain(i + tailleInit / 2);
            f.vieillir();
        }

        // Simulation des tours de jeu
        for (int i = 0; i < nbTourDeJeu; i++) {

            int n = 0;
            List<Humain> tirageHuman = population.getPop();

            if (population.taille() <= 1) {
                throw new PopulationException(population);
            }

            // Calcul du nombre de bébés à créer
            if (param == 0) {
                n = loto.nextInt(population.taille() / 2);
            } else if (param == 1) { // Croissance forcée
                int countH = 0;
                int countF = 0;
                for (Humain h : tirageHuman) {
                    if (h.isHomme() && h.getAge() > 15 && h.getAge() < 60) {
                        countH++;
                    }
                    if (h.isFemme() && h.getAge() > 15 && h.getAge() < 60) {
                        countF++;
                    }
                }
                n = Math.min(countH, countF);
            }

            List<Humain> newBebe = new ArrayList<>();

            // Gestion de la reproduction et de la mortalité
            if (param == 0) {
                for (int j = 0; j < n; j++) {
                    int i1 = loto.nextInt(tirageHuman.size());
                    Humain h1 = tirageHuman.remove(i1);
                    int i2 = loto.nextInt(tirageHuman.size());
                    Humain h2 = tirageHuman.remove(i2);

                    Humain bebe = null;

                    try {
                        bebe = population.rencontre(population.getIndex(h1), population.getIndex(h2));
                    } catch (BreedingForbiddenException e) {
                        System.out.println(e.getMessage());
                    } catch (NoBreedingException e) {
                        System.out.println(e.getMessage());
                    } catch (MeetingException e) {
                        System.out.println(e.getMessage());
                    }

                    if (bebe != null) {
                        newBebe.add(bebe);
                        int rand = loto.nextInt(0, 100);
                        if (rand < 20) {
                            int randomMort = newBebe.size() + population.taille() - 1;
                            int valMort = loto.nextInt(randomMort);
                            if (valMort >= population.taille()) {
                                valMort %= population.taille();
                                newBebe.get(valMort).tuer();
                            } else {
                                population.getHumain(valMort).tuer();
                            }
                        }
                    }
                }
            } else if (param == 1) {
                for (int k = 0; k < n; k++) {
                    Humain h1 = tirageHuman.get(k);
                    for (int j = 0; j < n; j++) {
                        if (j != k) {
                            Humain h2 = tirageHuman.get(j);
                            Humain bebe = null;
                            try {
                                bebe = population.rencontre(population.getIndex(h1), population.getIndex(h2));
                            } catch (BreedingForbiddenException e) {
                                System.out.println(e.getMessage());
                            } catch (NoBreedingException e) {
                                System.out.println(e.getMessage());
                            } catch (MeetingException e) {
                                System.out.println(e.getMessage());
                            }
                            if (bebe != null) {
                                newBebe.add(bebe);
                                int rand = loto.nextInt(0, 100);
                                if (rand < 20) {
                                    int randomMort = newBebe.size() + population.taille() - 1;
                                    int valMort = loto.nextInt(randomMort);
                                    if (valMort >= population.taille()) {
                                        valMort %= population.taille();
                                        newBebe.get(valMort).tuer();
                                    } else {
                                        population.getHumain(valMort).tuer();
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                int compteur = 0;
                int hommeFertile = 0;
                int femmeFertile = 0;

                for (Humain h : tirageHuman) {
                    if (h.isHomme() && h.getAge() > 15 && h.getAge() < 60) {
                        hommeFertile++;
                    } else if (h.isFemme() && h.getAge() > 15 && h.getAge() < 60) {
                        femmeFertile++;
                    }
                }
                while (compteur < nbb) {
                    if (hommeFertile == 0 || femmeFertile == 0) {
                        break;
                    }

                    int i1 = loto.nextInt(tirageHuman.size() - 1);
                    int i2 = loto.nextInt(tirageHuman.size() - 1);
                    Humain h1 = tirageHuman.get(i1);
                    Humain h2 = tirageHuman.get(i2);
                    Humain bebe = null;

                    try {
                        bebe = population.rencontre(population.getIndex(h1), population.getIndex(h2));
                    } catch (BreedingForbiddenException e) {
                        System.out.println(e.getMessage());
                    } catch (NoBreedingException e) {
                        System.out.println(e.getMessage());
                    } catch (MeetingException e) {
                        System.out.println(e.getMessage());
                    }
                    if (bebe != null) {
                        newBebe.add(bebe);
                        int rand = loto.nextInt(0, 100);
                        if (rand < 20) {
                            int randomMort = newBebe.size() + population.taille() - 1;
                            int valMort = loto.nextInt(randomMort);
                            if (valMort >= population.taille()) {
                                valMort %= population.taille();
                                newBebe.get(valMort).tuer();
                            } else {
                                population.getHumain(valMort).tuer();
                            }
                        }
                    }
                }
            }

            // Vieillissement de la population
            try {
                population.vieillir();
            } catch (PopulationException e) {
                System.out.println(e.getMessage());
                return;
            }
            for (Humain bebe : newBebe) {
                if (bebe.getAge() != 1000) {
                    population.addHumain(bebe);
                }
            }

            // Trier la population et afficher l'état actuel
            population.trierPop();
            population.print();
            System.out.println("\nGeneration numero : " + i);
        }
    }
}
