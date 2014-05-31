import java.util.Random;


public class Galaxie extends Astre{
	
	private double a;
	private double b;
	
	public Galaxie(){}

	public Galaxie(double a, double b) {
		super();
		this.a = a;
		this.b = b;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "Galaxie [a=" + a + ", b=" + b + "]";
	}
	
	public void addGalaxie(Vue vue, int nbGalaxie){ 
		
		Random aleatoire = new Random();
		
		this.a= 10;//demi grd axe
		this.b=5; //grd axe
		
		for(int k=0; k<nbGalaxie; k++){
			
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
					
	                if ( (( x-x0)*( x-x0))/(a*a)+(( y-y0)*( y-y0))/(b*b) < 1 ){

	              	   vue.getMatrice()[i][j]= this.getLambda();
	  					 
	              	 //System.out.println("Lambda= "+vue.getMatrice()[i][j]);
	                
	                }
					
				}
				
			}
		}
		
	}

	@Override
	public void randomLambda(int lambdaMin, int lambdaMax){
		
		Random aleatoire = new Random();
		this.setLambda(lambdaMin+aleatoire.nextInt(lambdaMax-lambdaMin));
	}

	

}
