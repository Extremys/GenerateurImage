import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Etoile extends Astre{
	
	private double rayon;


	public Etoile(){
		
	}
	
	public Etoile(int x, int y, double lambda) {
		super(x, y, lambda);
		this.rayon=0;
		// TODO Auto-generated constructor stub
	}

	public Etoile(int x, int y, double lambda, double rayon) {
		super(x, y, lambda);
		this.rayon = rayon;
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon) {
		this.rayon = rayon;
	}
	
	public void randomLambda(int lambdaMin, int lambdaMax){
		
		Random aleatoire = new Random();
		this.setLambda(lambdaMin+aleatoire.nextInt(lambdaMax-lambdaMin));
	}
	
	//ajoute N etoiles à une vue au lambda aléatoire
	public void addAstre(Vue vue, int nbOcc){ 
		
		Random aleatoire = new Random();
		
		this.rayon= 4;//Rayon des Etoiles
		double R=this.getRayon(); 
		
		for(int k=0; k<nbOcc; k++){
			
			this.setX( aleatoire.nextInt(vue.getLargeur()) );
			int x0= this.getX();
			this.setY( aleatoire.nextInt(vue.getHauteur()) );
			int y0= this.getY();
			this.randomLambda(400, 800);
			
		
			System.out.println(k+"Lambda= "+this.getLambda());
			
			for(int i=0; i<vue.getLargeur();i++){
	
				for(int j=0; j<vue.getHauteur();j++){
					
					int x=i;
					int y=j;
					
	                if ( (( x-x0)*( x-x0))+(( y-y0)*( y-y0)) < R*R ){

	              	   vue.getMatrice()[i][j]= this.getLambda();
	  					 
	              	 //System.out.println("Lambda= "+vue.getMatrice()[i][j]);
	                
	                }
					
				}
				
			}
		}
		
	}

}
