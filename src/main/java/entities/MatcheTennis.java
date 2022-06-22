package entities;

import lombok.extern.log4j.Log4j2;
import util.Constantes;

@Log4j2
public class MatcheTennis implements Matche{

    static boolean winSet    = false;
    static String gagnant;

    /*
     *affichage de l'historique du match
     */
    @Override
    public void afficherScore(Joueur j1, Joueur j2) {
        System.out.println("MATCH  : "+j1.getName()+" | "+j2.getName());
        for (int i = 0; i < j1.getPoints().size(); i++) {
            System.out.printf("Round %s:  %s    |    %s", i+1, j1.getPoints().get(i), j2.getPoints().get(i)+"\n");
        }
    }

    /*
     *affichage du gangant
     */
    @Override
    public void afficherGagnant(String verifierGagnant) {
        System.out.printf("" +"\n"+
                "                   JAVA TENNIS Application" +"\n"+
                "===================================================================" +"\n"+
                "               le gagnant est: ****** %s *****" +"\n"+
                "===================================================================" +"\n"+
                "",verifierGagnant);
    }

    /*
     *lancer un jeu
     */
    @Override
    public void jouerRound(Joueur j1, Joueur j2) {
        while(!remporteRound(j1,j2)){
            int tourDeJoueur = (int) (Math.random()*2)+1;
            if (tourDeJoueur == 1) {
                j1.setPointAct(getNextScore(j1));
                if(j1.getPointAct().equalsIgnoreCase(Constantes.avantagePoint)){
                    j2.setPointAct(Constantes.troisPoint);
                }
            } else {
                j2.setPointAct(getNextScore(j2));
                if(j2.getPointAct().equalsIgnoreCase(Constantes.avantagePoint)){
                    j1.setPointAct(Constantes.troisPoint);
                }
            }
            j1.getPoints().add(j1.getPointAct());
            j2.getPoints().add(j2.getPointAct());
            //Controle de l'egalite
            if(j1.getPointAct().equalsIgnoreCase(Constantes.troisPoint) && j2.getPointAct().equalsIgnoreCase(Constantes.troisPoint)){
                j2.setPointAct(getNextScore(j2));
                j1.setPointAct(getNextScore(j1));
                j1.getPoints().add(j1.getPointAct());
                j2.getPoints().add(j2.getPointAct());
            }
        }
    }

    /*
     *verifier si un joueur a remporter 2 set
     */
    @Override
    public String verifierGagnant(Joueur j1, Joueur j2) {
        //compare 2 Set gagne
        int setJ1Gagner = 0;
        int setJ2Gagner = 0;
        for (int i=0; i<j1.getSet().size(); i++){
            if ((j1.getSet().get(i) > j2.getSet().get(i))) {
                setJ1Gagner++;
            } else {
                setJ2Gagner++;
            }
        }
        if(setJ1Gagner == 2){
            return j1.getName();
        }else if(setJ2Gagner == 2){
            return j2.getName();
        }else{
            return null;
        }
    }

    /*
     *veridier si un joueur a remporter le round
     */
    public boolean remporteRound(Joueur j1, Joueur j2) {
        boolean roundDone = false;
        if((j1.getPointAct().equalsIgnoreCase(Constantes.troisPoint)) && !(j2.getPointAct().equalsIgnoreCase(Constantes.troisPoint)) && !(j2.getPointAct().equalsIgnoreCase(Constantes.avantagePoint))
            || (j1.getPointAct().contains(Constantes.avantagePoint)) && (j2.getPointAct().equalsIgnoreCase(Constantes.troisPoint))){
            j1.setSetAct(j1.getSetAct()+1);
            roundDone = true;
        }else if((j2.getPointAct().equalsIgnoreCase(Constantes.troisPoint)) && !(j1.getPointAct().equalsIgnoreCase(Constantes.troisPoint)) && !(j1.getPointAct().equalsIgnoreCase(Constantes.avantagePoint))
                || (j2.getPointAct().contains(Constantes.avantagePoint)) && (j1.getPointAct().equalsIgnoreCase(Constantes.troisPoint))){
            j2.setSetAct(j2.getSetAct()+1);
            roundDone = true;
        }
        setDone(j1,j2);
        if(roundDone)
            inisialiserJeu(j1,j2);
        return roundDone;
    }

    /*
     *verifier si un set et fini et designer le gagnant si 2 sets gagner par le même joueur sinon initialsier jeu
     */
    public void setDone(Joueur j1, Joueur j2) {
        if(j1.getSetAct() >= 6 && Math.abs(j1.getSetAct()-j2.getSetAct())>=2){
            winSet = true;
        }else if(j2.getSetAct() >= 6 && Math.abs(j1.getSetAct()-j2.getSetAct())>=2){
            winSet = true;
        }
        if(winSet){
            j1.getSet().add(j1.getSetAct());
            j2.getSet().add(j2.getSetAct());
            gagnant = verifierGagnant(j1,j2);
            inisialiserJeu(j1, j2);
        }
    }

    /*
     *Initlaiser le jeu une fois round gagner ou set gagner
     */
    public void inisialiserJeu(Joueur j1, Joueur j2) {
        j1.setPointAct(Constantes.debutpoint);
        j2.setPointAct(Constantes.debutpoint);

        if(winSet){
            j1.setSetAct(0);
            j2.setSetAct(0);
            winSet = false;
        }
    }

    public String getNextScore(Joueur joueur) {
        String point = joueur.getPointAct();
        switch (point) {
            case Constantes.debutpoint:
                return Constantes.premierPoint;
            case Constantes.premierPoint:
                return Constantes.deuxPoint;
            case Constantes.deuxPoint:
                return Constantes.troisPoint;
            case Constantes.troisPoint:
                return Constantes.egalitePoint;
            case Constantes.egalitePoint:
                return Constantes.avantagePoint;
            default:
                return joueur.getName()+" "+Constantes.gagnerPartie;
        }
    }
}
