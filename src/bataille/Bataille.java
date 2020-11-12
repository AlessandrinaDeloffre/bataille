package bataille;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bataille {

	public static void main(String[] args) {
					
	//INIT joueurs
		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur();
		
		//on initialise un tableau en cas de bataille, avec assez de place pour plusieurs batailles consécutives
		Carte [] cartesRetournées = new Carte[12];
		
	//INIT paquet
		Carte [] paquet = new Carte[52];
		int i = 0;
		for (int y=0; y<4; y++) {
			for(int j =0; j<13; j++) {
				paquet[i]=new Carte(j, y);
				i++;	
			}
		}
		shuffleTab(paquet);
		
		//exemple pour une partie avec 3 cartes par joueur
		distribuer(j1, j2, paquet, 3);
		
		System.out.print("Jeu j1 : ");
		for(int v=0; v<j1.getCompteur();v++) {System.out.print(j1.getJeu()[v]+"/ ");}
		System.out.println();
		System.out.print("Jeu j2 : ");
		for(int v=0; v<j1.getCompteur();v++) {System.out.print(j2.getJeu()[v]+"/ ");}
		System.out.println();
		
	//LANCEMENT de la partie
		System.out.println("Lancement de la partie : 3 cartes distribuées à chaque joueur");
		int compteurTour=0;
		do {
			jouerTour(j1,j2, cartesRetournées);
			compteurTour++;
			System.out.println("Fin tour n°"+compteurTour+" : j1 a "+j1.getCompteur()+" cartes et j2 a "+j2.getCompteur()+" cartes");
		} while ((j1.getCompteur()>0 && j2.getCompteur()>0));
		
	//VICTOIRE
		if(j1.getCompteur()==0 ) {
			System.out.println("J2 WIN");
		}
		if(j2.getCompteur()==0 ) {
			System.out.println("J1 WIN");
		}
	}
			
			
public static void shuffleTab(Carte [] c) {
	List<Carte> carteList = Arrays.asList(c);
	Collections.shuffle(carteList);
	carteList.toArray(c);
}
			
public static void distribuer(Joueur j1, Joueur j2, Carte [] c, int nbCartes) {
	for(int i=0; i<nbCartes*2; i=i+2) {
		j1.ajouteCarte(c[i]);
	}
	for(int i=1; i<nbCartes*2; i=i+2) {
		j2.ajouteCarte(c[i]);
	}
}
			
public static void jouerTour(Joueur j1, Joueur j2, Carte [] cartesRetournées){
	Carte c1 = j1.tireCarte();
	Carte c2 = j2.tireCarte();
	System.out.println("Carte j1 : "+c1+", carte j2 : "+c2);
	
	if (c1.compareVal(c2)=="sup") {
		j1.ajouteCarte(c1);
		j1.ajouteCarte(c2);
		if (cartesRetournées[0]!=null) {
			//s'il y a eu bataille, on ajoute également les cartes de l'égalité et les cartes faces cachées
			for (int i =0; i<cartesRetournées.length;i++) {
					if(cartesRetournées[i]!=null) {
						j1.ajouteCarte(cartesRetournées[i]);
					}			
			}
			cartesRetournées = null;
		}
	} else if(c1.compareVal(c2)=="inf") {
		j2.ajouteCarte(c2);
		j2.ajouteCarte(c1);
		if (cartesRetournées[0]!=null) {
			for (int i =0; i<cartesRetournées.length;i++) {
				if(cartesRetournées[i]!=null) {
					j2.ajouteCarte(cartesRetournées[i]);
				}
				}
			cartesRetournées = null;
		}
	} else if (c1.compareVal(c2)=="equal") {
		if(j1.getCompteur()>0 && j2.getCompteur()>0) {
			//si il reste min 1 carte à chaque joueur il rejoue un tour
			if(j1.getCompteur()>1 && j2.getCompteur()>1) {
				//si il reste min 2 cartes à chaque joueur, ils jouent chacun une carte face caché avant
				Carte cTemp1 = j1.tireCarte();
				Carte cTemp2 = j2.tireCarte();
				//on stocke temporairement les 4 cartes jouées
				
				cartesRetournées[2] = cTemp1;
				cartesRetournées[3] = cTemp2;
				
			}
			cartesRetournées[0] = c1;
			cartesRetournées[1] = c2;
			jouerTour(j1, j2, cartesRetournées);
		} else {
			//si l'un des joueurs vient de jouer sa derniere carte, la victoire va à l'autre
			if(j1.getCompteur()==0) {
				j2.ajouteCarte(c2);
				j2.ajouteCarte(c1);
			}
			if(j2.getCompteur()==0) {
				j1.ajouteCarte(c2);
				j1.ajouteCarte(c1);
			}
		}
	}
}

}
