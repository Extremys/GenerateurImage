import java.awt.Graphics2D;
//import ij.IJ;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PicGenerator { //classe main déplaçable dans Vue????

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage image= new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D impr= (Graphics2D) image.getGraphics();
		Filtre filter = new Filtre("U", 5448, 840);
		Astre star = new Etoile();
		double[][][] tab = new double[600][600][1];
		Pixel px = new Pixel();
		ArrayList<Astre> liste = new ArrayList();
		
		double[][] matrice = new double[600][600];
		
		Vue vue = new Vue(600,600,matrice,liste,filter);
		
		Etoile star1 = new Etoile();
		Galaxie galax= new Galaxie();
		
		vue.initialiserMatrice();
		
		//star1.addEtoile(vue, 100);
		//galax.addGalaxie(vue, 100);
		
		
		//filter.appliqueFiltre(vue);
		
		vue.afficheOccupationMatrice();
		
		//vue.afficheMatrice();
		
		//System.out.println(vue.getMatrice()[0][0][0]);
		//System.out.println(vue);
		//vue.getMatrice().toString();

	    
		   	
	}

}
