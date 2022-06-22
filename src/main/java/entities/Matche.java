package entities;


public interface Matche {

    default public void jouerMatche(Joueur j1, Joueur j2) {
        while(!(verifierGagnant(j1, j2)!=null)){
            jouerRound(j1,j2);
        }
        afficherScore(j1, j2);
        afficherGagnant(verifierGagnant(j1, j2));
    }

    void afficherScore(Joueur j1, Joueur j2);

    void afficherGagnant(String verifierGagnant);

    void jouerRound(Joueur j1, Joueur j2);

    String verifierGagnant(Joueur j1, Joueur j2);
}
