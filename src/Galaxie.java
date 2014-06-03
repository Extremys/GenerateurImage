import java.util.Random;


public class Galaxie extends Astre{
	
	private double a;
	private double b;
	private double inclinaison; //paramètre d'angle d'inclinaison de l'ellipse
	
	public Galaxie(){}

	public Galaxie(double a, double b, double inclinaison) {
		super();
		this.a = a;
		this.b = b;
		this.inclinaison = inclinaison;
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

	
	public double getInclinaison() {
		return inclinaison;
	}

	public void setInclinaison(double inclinaison) {
		this.inclinaison = inclinaison;
	}

	@Override
	public String toString() {
		return "Galaxie [a=" + a + ", b=" + b + ", inclinaison=" + inclinaison
				+ "]";
	}
	
	public void addAstre(Vue vue, int nbOcc){ 
		
		Random aleatoire = new Random();
		
		this.a= 12;//grd axe
		this.b=6; //demi grd axe
		
		for(int k=0; k<nbOcc; k++){
			
			this.setX( aleatoire.nextInt(vue.getLargeur()) );
			int x0= this.getX();
			this.setY( aleatoire.nextInt(vue.getHauteur()) );
			int y0= this.getY();
			this.setInclinaison((Math.PI*aleatoire.nextInt(180))/180);
			double theta= this.getInclinaison();
			this.randomLambda(400, 800);
			
		
			//System.out.println(k+"Lambda= "+this.getLambda());
			
			for(int i=0; i<vue.getLargeur();i++){
	
				for(int j=0; j<vue.getHauteur();j++){
					
					int x=i;
					int y=j;
					
					
					//(( x-x0)*( x-x0)/**Math.cos(theta)*Math.cos(theta)*/)/(a*a)+(( y-y0)*( y-y0)/**Math.sin(theta)*Math.sin(theta)*/)/(b*b)
	                if ( (Math.pow((x-x0)*Math.cos(theta)+(y-y0)*Math.sin(theta), 2)/(a*a)) + (Math.pow(-(x-x0)*Math.sin(theta)+(y-y0)*Math.cos(theta), 2)/(b*b)) < 1.0 ){

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
