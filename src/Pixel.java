import java.util.Random;


public class Pixel {
	
	private int x;
	private int y;
	private double lambda;
	
	public Pixel(){}
	
	public Pixel(int x, int y, int lambda) {
		super();
		this.x = x;
		this.y = y;
		this.lambda = lambda;
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

	public void setLambda(int lambda) {
		this.lambda = lambda;
	}

	@Override
	public String toString() {
		return "Pixel [x=" + x + ", y=" + y + ", lambda=" + lambda + "]";
	}
	
	public void randomLambda(int lambdaMin, int lambdaMax){ //générer un lambda random entre 400 et 800 nm
		
		Random aleatoire = new Random();
		
		this.lambda= lambdaMin+aleatoire.nextInt(lambdaMax-lambdaMin);
		
	}

}
