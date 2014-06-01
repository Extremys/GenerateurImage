
public abstract class Astre extends Vue{
	
	private int x; //Coordonnées du centre de l'objet
	private int y;
	private double lambda; //Lambda émis par l'astre associé à un modèle à implémenter constant pour simplifier
	
	public Astre(){}
	
	public Astre(int x, int y, double lambda) {
		
		this.x = x;
		this.y = y;
		this.lambda= lambda;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getLambda() {
		return lambda;
	}
	public void setLambda(double lambda) {
		this.lambda = lambda;
	}
	
	public abstract void randomLambda(int lambdaMin, int lambdaMax);
	
	public abstract void addAstre(Vue vue, int nbOcc);
	
}
