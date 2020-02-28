import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Chemin {
	
	ArrayList<Point> listePoint;
	ArrayList<ArrayList<Double>> edge;
	
	//CONSTRUCTEUR
	public Chemin() {
		listePoint = new ArrayList<>();
		edge = new ArrayList<ArrayList<Double>>();
	}
	
	//ACCESSEURS
	public ArrayList<Point> getListePoint() {
		return listePoint;
	}
	
	public void setListePoint(ArrayList<Point> p) {
		this.listePoint = p;
	}
	
	public ArrayList<ArrayList<Double>> getEdge() {
		return edge;
	}
	
	public void setEdge(ArrayList<ArrayList<Double>> v) {
		this.edge = v;
	}
	
	//METHODES
	public void afficheChemin() {
		for(int i = 0; i < getListePoint().size(); i++) {
			System.out.println(getListePoint().get(i));
		}
	}
	
	public void swap(int j) {
		double temp = edge.get(j).get(2);
		edge.get(j).set(2,edge.get(j+1).get(2));
		edge.get(j+1).set(2,temp);
		double temp2 = edge.get(j).get(0);
		edge.get(j).set(0,edge.get(j+1).get(0));
		edge.get(j+1).set(0,temp2);
		double temp3 = edge.get(j).get(1);
		edge.get(j).set(1,edge.get(j+1).get(1));
		edge.get(j+1).set(1,temp3);
	}
	
	public void initDistances() {
		//Initialise les aretes
		int n = this.getListePoint().size(); 
		int m = n*(n-1)/2;
		int k = 0;
		ArrayList<Double> temp = new ArrayList<>();
		for(double i = 0; i < n; i++) {
			for(double j = 0; j < n; j++) {
				temp.set(0,i);
				temp.set(1,j);
				temp.set(2,getListePoint().get((int)i).distance(getListePoint().get((int)j)));
				edge.add(temp);
//				edge.get(k).set(0,i);
//				edge.get(k).set(1,j);
//				edge.get(k).set(2,Point.distance(getListePoint().get((int)i),getListePoint().get((int)j)));
//				edge[k][0] = i;
//				edge[k][1] = j;
//				edge[k][2] = Point.distance(getListePoint().get(i),getListePoint().get(j));
				k++;
				
			}
		}
		//Trier par odre croissant les distances
//		for(int i = m; i > 1; i--) {
//			for(int j = 0; j < i - 1; j++){
//				if(edge.get(j).get(2) > edge.get(j+1).get(2)) {
//					swap(j);
//					swap(edge[j][2],edge[j+1][2]);
//					swap(edge[j][0],edge[j+1][0]);
//					swap(edge[j][1],edge[j+1][1]);
//				}
//			}
//		}
		afficheChemin();
	}
	
	//Kruskal -> Arbre couvrant de poids minimum
//	public double[][]kruskal() {
//		
//		int n = this.getListePoint().size();
//		int m = n*(n-1)/2;
//	
//		
//		initDistances(); //Initialise et tri les aretes par rapport aux distances
//		ArrayList<Point> acpm = new ArrayList<>();	//resultat
//		double[][] accpm = new double[1000][1000];
//		
//		double comp[] = new double[1000];
//		double aux;
//		for(int i = 0; i < n; i++){
//			comp[i] = i;
//		}
//		  
//		int cpt = 0;
//		for(int i = 0; i < m; i++) {
//			double x =  edge.get(i).get(0);
//			double y = edge.get(i).get(1);
////			double x = edge[i][0];
////			double y = edge[i][1];
//		
//			if(comp[(int) x] != (comp[(int) y])) {
//				aux = comp[(int) x];
//				accpm[cpt][0] = x;
//				accpm[cpt][1] = y;
//				cpt++;
//		
//				for(int k = 0; k < n; k++){
//					if(comp[k]==aux) {
//						comp[k] = comp[(int) y];
//					}
//				}
//			}
//		}
//		return accpm;	
//	}
	
	//A faire 
	//public parcoursEnProfondeur();
	
	public ArrayList<Point> voyageurDeCommerce(Polygon poly) {
		
		setListePoint(poly.getPointsInternes());
		//kruskal(); //Creation d'un acpm
		//double a[][] = kruskal();
		initDistances();
//		for(int i = 0; i < 500; i++) {
//			System.out.println(a[i][0]);
//			System.out.println(a[i][1]);
//					
//		}

		
		
		return this.listePoint;
	}
	
	
	public static void main(String[] args) 
	{
		ArrayList<Point> vertices = new ArrayList<Point>();
		vertices.add(new Point(10,10,-1,-1,-1));
		vertices.add(new Point(200,10,-1,-1,-1));
		vertices.add(new Point(200,200,-1,-1,-1));
		vertices.add(new Point(10,200,-1,-1,-1));
		
		Chemin chemin = new Chemin();
		Polygon poly = new Polygon(vertices, 5, 5);
		
		
		chemin.voyageurDeCommerce(poly);
		//chemin.afficheChemin();
		
//		ArrayList<Point> fauxChemin = new ArrayList<Point>();
//		
//		Random r = new Random();
//		for(int i = 0; i<poly.getPointsInternes().size(); i = i + r.nextInt(50))
//			fauxChemin.add(poly.getPointsInternes().get(i));
//		
//		Pdf.afficherChemin("FauxChemin.ps", poly, fauxChemin);
	}
}


/*
import java.util.ArrayList;
import java.util.Random;

public class Chemin {
	public static ArrayList<Point> voyageurDeCommerce(ArrayList<Point> points)
	{
		ArrayList<Point> chemin = null;
		
		return chemin;
	}
	
	public static void main(String[] args) 
	{
		ArrayList<Point> vertices = new ArrayList<Point>();
		vertices.add(new Point(10,10,-1,-1,-1));
		vertices.add(new Point(200,10,-1,-1,-1));
		vertices.add(new Point(200,200,-1,-1,-1));
		vertices.add(new Point(10,200,-1,-1,-1));
		
		Polygon poly = new Polygon(vertices, 5, 5);
		ArrayList<Point> fauxChemin = new ArrayList<Point>();
		
		Random r = new Random();
		for(int i = 0; i<poly.getPointsInternes().size(); i = i + r.nextInt(50))
			fauxChemin.add(poly.getPointsInternes().get(i));
		
		Pdf.afficherChemin("FauxChemin.ps", poly, fauxChemin);
	}
}
*/