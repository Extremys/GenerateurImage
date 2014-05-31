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
	
	public void afficheOccupationMatrice(){
		
		for(int i=0; i<this.matrice.length;i++){

			for(int j=0; j<this.matrice.length;j++){
				
				if(this.matrice[i][j]>0){
				
				System.out.println("x="+i+"; y="+j+", lambda="+this.matrice[i][j]+".");
				
				}
			}
			
		}	
	}
	
	public void convolutionMatrice(/*noyau en param?*/){ //ajoute les effets atm+recepteur
		
		
	}
	


	public void calibrerVue(){ //correspondance lambda nivGris (R,G,B)
		
	}

	public void afficherVue(){ //afficher fenêtre
		
	}

	public void enregistrerVue(){ //format JPG FITS
		
	}
	
	//Méthode pour implementer plusieurs formes par vue...

	

}
