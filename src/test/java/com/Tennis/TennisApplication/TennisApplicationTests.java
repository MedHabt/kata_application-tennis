package com.Tennis.TennisApplication;

import entities.Joueur;
import entities.MatcheTennis;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import util.Constantes;


@SpringBootTest
@Log4j2
class TennisApplicationTests {

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
	void getScoreAndPlayer(){
		matcheTennis.jouerMatche(j1,j2);
		System.out.println("j1 : "+j1.getPointAct()+" j2 :"+j2.getPointAct());
		log.info("set of j1 : ");
		j1.getSet().stream().forEach(el -> log.info(el));
		log.info("set of j2 : ");
		j2.getSet().stream().forEach(el -> log.info(el));
	}

	/*@Test
	void vainquer(){
		List<Integer> set1 = List.of(2,11);
		List<Integer> set2 = List.of(9,6);
		matcheTennis.verifierGagnant(j1, j2);
	}

	@Test
	void egalite(){
		j1.setPointAct(Constantes.troisPoint);
		j2.setPointAct(Constantes.troisPoint);
		log.info("resultat :"+matcheTennis.remporteRound(j1, j2));
	}*/

	@Test
	void contextLoads() {
	}

}
