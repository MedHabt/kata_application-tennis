package com.Tennis.TennisApplication;

import entities.Joueur;
import entities.MatcheTennis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import util.Constantes;

@SpringBootApplication
public class TennisApplication {

	public static void main(String[] args) {
		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur();
		MatcheTennis matcheTennis = new MatcheTennis();

		j1.setName("mehdi");
		j1.setPointAct(Constantes.debutpoint);

		j2.setName("Zineb");
		j2.setPointAct(Constantes.debutpoint);

		matcheTennis.jouerMatche(j1,j2);
	}

}
