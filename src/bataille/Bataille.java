package bataille;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bataille {

	public static void main(String[] args) {
					
	//INIT joueurs
		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur();
		
		//on initialise un tableau en cas de bataille, avec assez de place pour plusieurs batailles cons�cutives
		Carte [] cartesRetourn�es = new Carte[12];
		
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
		System.out.println("Lancement de la partie : 3 cartes distribu�es � chaque joueur");
		int compteurTour=0;
		do {
			jouerTour(j1,j2, cartesRetourn�es);
			compteurTour++;
			System.out.println("Fin tour n�"+compteurTour+" : j1 a "+j1.getCompteur()+" cartes et j2 a "+j2.getCompteur()+" cartes");
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
			
public static void jouerTour(Joueur j1, Joueur j2, Carte [] cartesRetourn�es){
	Carte c1 = j1.tireCarte();
	Carte c2 = j2.tireCarte();
	System.out.println("Carte j1 : "+c1+", carte j2 : "+c2);
	
	if (c1.compareVal(c2)=="sup") {
		j1.ajouteCarte(c1);
		j1.ajouteCarte(c2);
		if (cartesRetourn�es[0]!=null) {
			//s'il y a eu bataille, on ajoute �galement les cartes de l'�galit� et les cartes faces cach�es
			for (int i =0; i<cartesRetourn�es.length;i++) {
					if(cartesRetourn�es[i]!=null) {
						j1.ajouteCarte(cartesRetourn�es[i]);
					}			
			}
			cartesRetourn�es = null;
		}
	} else if(c1.compareVal(c2)=="inf") {
		j2.ajouteCarte(c2);
		j2.ajouteCarte(c1);
		if (cartesRetourn�es[0]!=null) {
			for (int i =0; i<cartesRetourn�es.length;i++) {
				if(cartesRetourn�es[i]!=null) {
					j2.ajouteCarte(cartesRetourn�es[i]);
				}
				}
			cartesRetourn�es = null;
		}
	} else if (c1.compareVal(c2)=="equal") {
		if(j1.getCompteur()>0 && j2.getCompteur()>0) {
			//si il reste min 1 carte � chaque joueur il rejoue un tour
			if(j1.getCompteur()>1 && j2.getCompteur()>1) {
				//si il reste min 2 cartes � chaque joueur, ils jouent chacun une carte face cach� avant
				Carte cTemp1 = j1.tireCarte();
				Carte cTemp2 = j2.tireCarte();
				//on stocke temporairement les 4 cartes jou�es
				
				cartesRetourn�es[2] = cTemp1;
				cartesRetourn�es[3] = cTemp2;
				
			}
			cartesRetourn�es[0] = c1;
			cartesRetourn�es[1] = c2;
			jouerTour(j1, j2, cartesRetourn�es);
		} else {
			//si l'un des joueurs vient de jouer sa derniere carte, la victoire va � l'autre
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
