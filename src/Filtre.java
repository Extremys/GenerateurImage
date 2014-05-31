import java.awt.Color;
import   java.lang.Math.*;


public class Filtre { // plutôt filtre visuel de floutage...etc...
	
	private String nomFiltre; //en faire une énumération avec une série de filtres prédéfinis?
	private double lambdaEff; //Pour un filtre ne laissant passer qu'une longueur d'onde en moyenne
	private double largeurBande;
	
	/*private double lambdaMin;
	private double lambdaMax;*/
	
	//private int delta; //pas de discretisation de la plage de longueur d'onde cas non lineaire
	
	//???private double[] lambda; //Quand le filtre laisse passer une plage de long d'onde donnée à un delta donné 

	public Filtre(String nomFiltre, double lambda, double largeurBande) {
		super();
		this.nomFiltre = nomFiltre;
		this.lambdaEff = lambda;
		this.largeurBande = largeurBande;
	}
	
	
	public String getNomFiltre() {
		return nomFiltre;
	}







	public void setNomFiltre(String nomFiltre) {
		this.nomFiltre = nomFiltre;
	}







	public double getLambda() {
		return lambdaEff;
	}







	public void setLambda(double lambda) {
		this.lambdaEff = lambda;
	}







	public double getLargeurBande() {
		return largeurBande;
	}







	public void setLargeurBande(double largeurBande) {
		this.largeurBande = largeurBande;
	}



	public double getLambdaAbsBorne(){
		
		return this.lambdaEff-(this.largeurBande/2.0);
	}


	public void appliqueFiltre(Vue vue){
		
		//definition filtre en this lambdaEff + Largeur à 1/2 hauteur 
		//implémenter la gaussienne pour calculer chaque trasmission lambda = lamba*T(lambda) calculé
	
		for(int i=0; i<vue.getLargeur();i++){
			
			for(int j=0; j<vue.getHauteur();j++){
				
				//calcul de la transmission du filtre T
				
				double lambdaEf= this.lambdaEff;
				double lambda= vue.getMatrice()[i][j];
				double sigma= this.largeurBande;
				
				//Problème normalisation de la gaussienne
				
				double T=(1/Math.sqrt(2*Math.PI))*Math.exp(-Math.pow(lambda-lambdaEf,2)/(2*Math.pow(sigma, 2)));
				
                vue.addDoubleMatrice(i, j, vue.getMatrice()[i][j]*T);
				
			}
			
		}
	}
		
	

	
	
	
	
	public Color conversionLambdaToRGB(){
		
		
		
		int intensite= (int) ((this.lambdaEff*255)/(largeurBande)); //Les pixels sont codés sur 256 bits
		
		Color nivGris = new Color(intensite,intensite,intensite);

		return nivGris;

	}


}
