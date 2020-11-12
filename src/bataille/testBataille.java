package bataille;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class testBataille {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
			//TEST class carte
		/*	Carte c1 = new Carte(7, 1);
			Carte c2 = new Carte(6, 2);

			System.out.println("Carte 1 : "+c1.getCouleur()+", "+c1.getValeur());	
			System.out.println("Carte 2 : "+c2.getCouleur()+", "+c2.getValeur());
			
			System.out.println(c1.compareColor(c2));
			System.out.println(c1.compareVal(c2));*/
			
			//INIT joueurs
			Joueur j1 = new Joueur();
			Joueur j2 = new Joueur();
			
			//INIT paquet
			Carte [] paquet = new Carte[52];
			int i = 0;
			for (int y=0; y<4; y++) {
				for(int j =0; j<13; j++) {
					paquet[i]=new Carte(j, y);
					i++;	
				}
			}

			
			Carte [] paquetTest = new Carte[8];
			int ii = 0;
			for (int y=0; y<4; y++) {
				for(int j =11; j<13; j++) {
					paquetTest[ii]=new Carte(j, y);
					ii++;	
				}
			}
			//TEST class joueur
			//for(int v=0; v<j1.getCompteur()+1;v++) {System.out.print("Jeu : "+j1.getJeu()[v]+", ");}
			//System.out.println("Compteur "+j1.getCompteur());
			//j1.ajouteCarte(paquet[0]);
			//j1.ajouteCarte(paquet[1]);
			//for(int v=0; v<j1.getCompteur()+1;v++) {System.out.print("Jeu : "+j1.getJeu()[v]+", ");}
			//System.out.println("Compteur "+j1.getCompteur());
			//j1.tireCarte();
			//for(int v=0; v<j1.getCompteur()+1;v++) {System.out.print(j1.getJeu()[v]+", ");}
			//System.out.println("Compteur "+j1.getCompteur());
			
			//for(int t=0; t<paquet.length;t++) {System.out.print(paquet[t]+"/ ");}
			//System.out.println();
			shuffleTab(paquet);
			//for(int t=0; t<paquet.length;t++) {System.out.print(paquet[t]+"/ ");}
			
			//exemple pour une partie avec 3 cartes par joueur
			distribuer(j1, j2, paquet, 3);
			System.out.print("Jeu j1 : ");
			for(int v=0; v<j1.getCompteur();v++) {System.out.print(j1.getJeu()[v]+"/ ");}
			System.out.println();
			System.out.print("Jeu j2 : ");
			for(int v=0; v<j1.getCompteur();v++) {System.out.print(j2.getJeu()[v]+"/ ");}
			System.out.println();
			
			int compteurTour=0;
			
			do {
				jouerTour(j1,j2);
				
				compteurTour++;
				System.out.println("Fin tour n°"+compteurTour+" : j1 a "+j1.getCompteur()+" cartes et j2 a "+j2.getCompteur()+" cartes");
			} while ((j1.getCompteur()>0 && j2.getCompteur()>0));
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
	
	public static void jouerTour(Joueur j1, Joueur j2){
		Carte c1 = j1.tireCarte();
		Carte c2 = j2.tireCarte();
		System.out.println("Carte j1 : "+c1+", carte j2 : "+c2);
		if (c1.compareVal(c2)=="sup") {
			j1.ajouteCarte(c1);
			j1.ajouteCarte(c2);
		
			//System.out.println("J1 : "+j1.getCompteur()+", J2 : "+j2.getCompteur());
		} else if(c1.compareVal(c2)=="inf") {
			j2.ajouteCarte(c2);
			j2.ajouteCarte(c1);
		
			//System.out.println("J1 : "+j1.getCompteur()+", J2 : "+j2.getCompteur());
		} else if (c1.compareVal(c2)=="equal") {
			if(j1.getCompteur()>0 && j2.getCompteur()>0) {
				//si il reste min 1 carte à chaque joueur il rejoue un tour
				if(j1.getCompteur()>1 && j2.getCompteur()>1) {
					//si il reste min 2 cartes à chaque joueur, ils jouent chacun une carte face caché
				j1.tireCarte();
				j2.tireCarte();
				}
				//System.out.println("J1 : "+j1.getCompteur()+", J2 : "+j2.getCompteur());
				jouerTour(j1, j2);
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
			//System.out.println("J1 : "+j1.getCompteur()+", J2 : "+j2.getCompteur());
		}
	}
	
}
