import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("Debut.");
		
		ArrayList<Point> vertices3 = new ArrayList<Point>();
		vertices3.add(new Point(43.609000, 3.880900));
		vertices3.add(new Point(43.606113, 3.879999));
		vertices3.add(new Point(43.605395, 3.875633));
		vertices3.add(new Point(43.608395, 3.879064));
		
		vertices3.get(1).decalage(-30, 100);
		//vertices3.get(2).decalage(300, 100);
		vertices3.get(3).decalage(0, -40);
		
		Polygon poly3 = new Polygon(vertices3, 25, 10);
		Pdf.afficherPolygon("Polygon.ps", poly3);
		
		//==============================Dans Appli=================================
		Chemin chemin = new Chemin();
        ArrayList<Point> parcours = chemin.voyageurDeCommerce(poly3);
        ArrayList<Point> p2 = new ArrayList<Point>();
        parcours.add(parcours.get(parcours.size()-1));
		parcours.add(parcours.get(0));
		for(int i = 0; i < parcours.size(); i += 2)
        {
        	p2.add(parcours.get(i));
        }
		System.out.println(p2);
		//==============================Fin Appli=================================
		
		System.out.println("nbdepoints : " + poly3.getPointsInternes().size());
		System.out.println("taille parcours : " + parcours.size()/2);
		Pdf.afficherGraphe("Chemin.ps", poly3,parcours);
		Pdf.afficherGraphe("Kruskal.ps", poly3,chemin.getKrus());
		System.out.println("Fin.");
	}
}
