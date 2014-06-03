import java.awt.Color;
import java.util.ArrayList.*;
import java.util.Arrays;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Vue { //ici qu'on va assembler l'image par écritures successives  d'abor le fond puis les objets
	
	//taille des pixels???
	private int hauteur; //Pour la taille & résolution
	private int largeur;
	private double[][]  matrice ; //= new Pixel[this.largeur*this.hauteur];
	//numéro du pixel + info pixel
	private ArrayList<Astre> astreContenu;
	
	private Filtre filtreApplique;
	
	
	public Vue(){}
	
	

	public Vue(int hauteur, int largeur, double[][] matrice,
			ArrayList<Astre> astreContenu, Filtre filtreApplique) {
		super();
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.matrice = matrice;
		this.astreContenu = astreContenu;
		this.filtreApplique = filtreApplique;
	}



	public int getHauteur() {
		return hauteur;
	}




	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}




	public int getLargeur() {
		return largeur;
	}




	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public ArrayList<Astre> getAstreContenu() {
		return astreContenu;
	}




	public void setAstreContenu(ArrayList<Astre> astreContenu) {
		this.astreContenu = astreContenu;
	}




	public Filtre getFiltreApplique() {
		return filtreApplique;
	}




	public void setFiltreApplique(Filtre filtreApplique) {
		this.filtreApplique = filtreApplique;
	}


	@Override
	public String toString() {
		return "Vue [hauteur=" + hauteur + ", largeur=" + largeur
				+ ", matrice=" + Arrays.toString(matrice) + ", astreContenu="
				+ astreContenu + ", filtreApplique=" + filtreApplique + "]";
	}


	public double[][] getMatrice() {
		return matrice;
	}



	public void setMatrice(double[][] matrice) {
		this.matrice = matrice;
	}
	
	
	public void addDoubleMatrice(int i, int k, double n) {
		this.matrice[i][k] = n;
	}



	public void initialiserMatrice(){
		
		this.matrice = new double[this.largeur][this.hauteur];

			for(int i=0; i<this.largeur; i++){
				
				for(int j=0; j<this.hauteur; j++){
				
				
					this.matrice[i][j]= 0.0;//lambda initial nul
					
					//coordonnées x=i et y=j
				
					//System.out.println(this.matrice[i][j]);
				}
			}
		
	}
	
	public void afficheMatrice(){
		
		for(int i=0; i<this.matrice.length;i++){

			for(int j=0; j<this.matrice.length;j++){
				
				System.out.println("x="+i+"; y="+j+", lambda="+this.matrice[i][j]+".");
				
			}
			
		}	
	}
	
	public void afficheOccupationMatrice(){ //où lambda différent de 0
		
		for(int i=0; i<this.matrice.length;i++){

			for(int j=0; j<this.matrice.length;j++){
				
				if(this.matrice[i][j]>0){
				
				System.out.println("x="+i+"; y="+j+", lambda="+this.matrice[i][j]+".");
				
				}
			}
			
		}	
	}
	
	
	public void addAstreMulti(int nbreEtoile, int nbreGalaxie /*int nbreNebuleuse*/){
		Etoile star = new Etoile();
		Galaxie galaxie = new Galaxie();
		star.addAstre(this, nbreEtoile);
		galaxie.addAstre(this, nbreGalaxie);
		
	}
	
	public void convolutionMatrice(double[][] noyau/*noyau en param 3x3*/){ //ajoute les effets atm+recepteur
		
		
		int tailleMatrice= (noyau.length%2)+this.matrice.length;
		int tailleBordure= noyau.length%2;
		
		double[][] matriceTemp= new double[tailleMatrice][tailleMatrice];
		
		for(int i=0; i<tailleMatrice; i++){ //initialise la matrice copie avec des zéros
			
			for(int j=0; j<tailleMatrice; j++){
			
			
				matriceTemp[i][j]= 0.0;//lambda initial nul
				
				//coordonnées x=i et y=j
			
				//System.out.println(this.matrice[i][j]);
			}
		}
		
		for(int i=0; i<this.largeur; i++){ //copie la matrice où on applique la convolution (parcours de this.matrice)
			
			for(int j=0; j<this.hauteur; j++){
			
			
				matriceTemp[i+tailleBordure][j+tailleBordure]= this.matrice[i][j]; //-1 ??
				
				
		
			}
		}
		
		for(int i=tailleBordure; i<tailleMatrice-tailleBordure; i++){ //on applique la convolution à matriceTemp
			
			for(int j=tailleBordure; j<tailleMatrice-tailleBordure; j++){
			
				double som=0.0; //à chaque fois qu'on change de Px on réinitialise la somme
				
				double K11=noyau[0][0];
				double K12=noyau[0][1];
				double K13=noyau[1][2];
				
				double K21=noyau[1][0];
				double K22=noyau[1][1];
				double K23=noyau[1][2];
				
				double K31=noyau[2][0];
				double K32=noyau[2][1];
				double K33=noyau[2][2];
				
				som= K11*matriceTemp[i-1][j-1]+K12*matriceTemp[i][j-1]+K13*matriceTemp[i+1][j-1]+K21*matriceTemp[i-1][j]+K22*matriceTemp[i][j]+K23*matriceTemp[i+1][j]+K31*matriceTemp[i-1][j+1]+K32*matriceTemp[i][j+1]+K33*matriceTemp[i+1][j+1];
			    
				//Traitement du Px central courant
				//parcours du noyau:
				
				/*
				for(int k=0; k<noyau.length; k++){
					
					for(int l=0; l<noyau.length; l++){
						
						som=som+noyau[k][l]*;
					}
					
				} */
				matriceTemp[i][j]= som;

			}
		}
		
		//On copie la matrice (matriceTemp) convoluée dans la matrice de la vue (matrice)...
		
		for(int i=0; i<this.largeur; i++){ //on parcourt this.matrice et on copie l'élément de matriceTemp
			
			for(int j=0; j<this.hauteur; j++){
			
			
				this.matrice[i][j]= matriceTemp[i+tailleBordure][j+tailleBordure]; //-1 ??
				
				
		
			}
		}
		
		
		
		
	}
	


	public void calibrerVue(){ //correspondance lambda nivGris (R,G,B)
		//copie & conversion des lambda en RGB lambdaEff->255
		
		
	}
	public void afficherVue(BufferedImage image, String nomFichier){
		
		File copieVue = new File(nomFichier+"-temp.jpg");
		try {
			ImageIO.write(image, "jpg", copieVue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//pour parer la corruption du fichier une fois capturé on enregistre une copie temporaire
		
		JFrame f = new JFrame("Vue générée");
        f.setSize(this.hauteur,this.largeur);
	   	ImageIcon capture = new ImageIcon(nomFichier+"-temp.jpg");
	   	JLabel label = new JLabel(capture);
	   	f.add(label);
	   	f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	   	f.setLocationRelativeTo(null);
	   	f.setVisible(true);
	   //	copieVue.delete(); //on efface la copie créée vérifier si c'est un dossier...
	}
	
	public BufferedImage dessinerImage(){ //Dessine une image RGB
		
		int h = this.hauteur;
		int l = this.largeur;
		int PIX_SIZE = 1; //Défini la granularité d'écriture
		int xi;
		int yi;
		
		
		BufferedImage tempPic = new BufferedImage( h*PIX_SIZE, l*PIX_SIZE, 

                BufferedImage.TYPE_3BYTE_BGR );
		
		Graphics2D surface =(Graphics2D)tempPic.getGraphics();
		
		for(int i=0; i<this.matrice.length;i++){

			for(int j=0; j<this.matrice.length;j++){
				
                xi = i * PIX_SIZE;

                yi = j * PIX_SIZE;
				
				if(this.matrice[i][j]>0.009){ //sensibilité d'affichage
					
					int nivGris= 255; 
				
	                surface.setColor( new Color(nivGris, nivGris, nivGris) );

	                System.out.println(nivGris);
					
				//System.out.println("x="+i+"; y="+j+", lambda="+this.matrice[i][j]+".");
				
				}
				else{
					
					int nivGris= 0;
					surface.setColor( new Color(nivGris, nivGris, nivGris) );
					System.out.println(nivGris);
				}
				
				surface.fillRect( yi, xi , PIX_SIZE , PIX_SIZE );
			}
			//surface.dispose(); //libere la memoire du graph
		}
		return tempPic;
	}
	

	public void saveVueJPG(String nomFichier){ //format JPG FITS
		
		BufferedImage image = this.dessinerImage();
		
		File nouvelleImage = new File(nomFichier+".jpg");
		try {
			ImageIO.write(image, "jpg", nouvelleImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Méthode pour implementer plusieurs formes par vue...

	

}
