package bataille;

public class Carte {
	private int valeur;
	private String valeurString;
	private String couleur;
	
	public static int [] listeValeur= {1,2,3,4,5,6,7,8,9,10,11,12,13};
	public static String [] stringValeurs = {"as", "2", "3", "4", "5", "6", "7", "8", "9", "10", "valet", "dame", "roi"};
	public static String [] listeCouleur = {"pique", "trèfle", "coeur", "carreau"};
	
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
			//carte supérieure
			return "sup";
		} else if (this.valeur<c.valeur){
			//carte inférieure
			return "inf";
		} else {
			return "equal";
		}
	}
	
	public String compareColor(Carte c) {
		if(this.couleur==c.couleur) {
			return "les cartes sont de même couleur";
		} else {
			return "les cartes ne sont pas de même couleur";
		}
	}
	
	public String toString() {
		return valeurString+", "+couleur;
	}
	
	

}
