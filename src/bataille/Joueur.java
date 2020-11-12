package bataille;

public class Joueur {
	private Carte [] listeCarte;
	private int compteur;
	
	public Joueur() {
		listeCarte= new Carte[52];
		compteur = 0; 
	}
	
	public int getCompteur() {
		return compteur;
	}
	
	public Carte[] getJeu() {
		return listeCarte;
	}
	
	public Carte tireCarte() {
		Carte currentCarte = listeCarte[0];
		this.compteur--;
		//return listeCarte[0];
		for(int i=0; i<this.compteur+1;i++) {
			listeCarte[i] = listeCarte[i+1];
		}
		return currentCarte;
	}
	
	public void ajouteCarte(Carte c) {
		int carteCompteur=0;
		if(listeCarte[0]==null) {listeCarte[0]=c;} else {
			do {
				carteCompteur++;			
			} while (listeCarte[carteCompteur]!=null);
		}
		listeCarte[carteCompteur]=c;
		this.compteur++;
		
	}

}
