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
