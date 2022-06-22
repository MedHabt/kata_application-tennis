package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Constantes;

import java.util.List;

//@SpringBootTest
class MatcheTennisTest {

    static Joueur j1;
    static Joueur j2;

    MatcheTennis matcheTennis = new MatcheTennis();

    @BeforeAll
    static void prepareDonnee(){
        j1 = new Joueur();
        j2 = new Joueur();
        j1.setName("mehdi");
        j1.setPointAct(Constantes.debutpoint);
        j1.setSetAct(0);

        j2.setName("Zineb");
        j2.setPointAct(Constantes.debutpoint);
        j2.setSetAct(0);
    }

    @Test
    void jouerTour_test_match_aleatoire() {
        List<String> names = List.of(j1.getName(),j2.getName());
        matcheTennis.jouerMatche(j1,j2);
        Assertions.assertTrue(names.contains(matcheTennis.verifierGagnant(j1,j2)));
    }

    @Test
    void jouerRound_roundend_differentScoreInPointTable() {
        matcheTennis.jouerRound(j1,j2);
        Assertions.assertTrue(!(j2.getPoints().get(j2.getPoints().size()-1).equalsIgnoreCase(j1.getPoints().get(j1.getPoints().size()-1))));
    }

    @Test
    void remporteRound_j1_wonRound() {
        int setAct = j1.getSetAct();
        j1.setPointAct(Constantes.troisPoint);
        j2.setPointAct(Constantes.premierPoint);
        matcheTennis.jouerRound(j1,j2);
        Assertions.assertTrue(j1.getSetAct()>setAct);
    }

    @Test
    void setDone_allSet_and_getTheWinner() {
        j1.getSet().addAll(List.of(2,6,6));
        j2.getSet().addAll(List.of(6,2,2));
        matcheTennis.setDone(j1,j2);
        Assertions.assertEquals(j1.getName(),matcheTennis.verifierGagnant(j1,j2));
    }

    @Test
    void verifierGagnant_j2won_false() {
        j1.getSet().addAll(List.of(6,6));
        j2.getSet().addAll(List.of(1,8));
        Assertions.assertEquals(null,matcheTennis.verifierGagnant(j1,j2));
    }

    @Test
    void verifierGagnant_j2won() {
        j2.getSet().addAll(List.of(2,6,6));
        j1.getSet().addAll(List.of(6,2,2));
        Assertions.assertEquals(j2.getName(),matcheTennis.verifierGagnant(j1,j2));
    }

    @Test
    void inisialiserJeu_score_of_two_player_to_debutPoint() {
        j1.setPointAct(Constantes.deuxPoint);
        j2.setPointAct(Constantes.troisPoint);
        matcheTennis.inisialiserJeu(j1,j2);
        Assertions.assertEquals(j1.getPointAct(), Constantes.debutpoint);
        Assertions.assertEquals(j2.getPointAct(), Constantes.debutpoint);
    }

    @Test
    void getNextScore_premierpoint_joueur() {
        String score = matcheTennis.getNextScore(j1);
        Assertions.assertEquals(score, Constantes.premierPoint);
    }
}