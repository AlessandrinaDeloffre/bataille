package bataille;

public class Carte {
	private int valeur;
	private String valeurString;
	private String couleur;
	
	public static int [] listeValeur= {1,2,3,4,5,6,7,8,9,10,11,12,13};
	public static String [] stringValeurs = {"as", "2", "3", "4", "5", "6", "7", "8", "9", "10", "valet", "dame", "roi"};
	public static String [] listeCouleur = {"pique", "tr�fle", "coeur", "carreau"};
	
	//public Carte(int val, String color) {
		//valeur = val;
		//couleur = color;
	//}
	

	public Carte(int val, int color) {
		setValeur(val);
		setCouleur(color);
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	public int getValeur() {
		return valeur;
	}
	
	private void setCouleur(int color) {
		this.couleur = listeCouleur[color];
	}
	
	private void setValeur(int val) {
		this.valeur=listeValeur[val];
		this.valeurString=stringValeurs[val];
	}
	
	public String compareVal(Carte c) {
		if(this.valeur>c.valeur) {
			//carte sup�rieure
			return "sup";
		} else if (this.valeur<c.valeur){
			//carte inf�rieure
			return "inf";
		} else {
			return "equal";
		}
	}
	
	public String compareColor(Carte c) {
		if(this.couleur==c.couleur) {
			return "les cartes sont de m�me couleur";
		} else {
			return "les cartes ne sont pas de m�me couleur";
		}
	}
	
	public String toString() {
		return valeurString+", "+couleur;
	}
	
	

}
