import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("Debut.");
		/*
		ArrayList<Point> vertices = new ArrayList<Point>();
		vertices.add(new Point(10, 10, -1));
		vertices.add(new Point(200, 10, -1));
		vertices.add(new Point(200, 200, -1));
		vertices.add(new Point(150, 200, -1));
		vertices.add(new Point(150, 100, -1));
		vertices.add(new Point(100, 100, -1));
		vertices.add(new Point(75, 175, -1));
		vertices.add(new Point(10, 175, -1));
		
		Polygon poly = new Polygon(vertices, 10, 2);
		Pdf.afficherPolygon("Exemple1.ps", poly);
		
		ArrayList<Point> vertices2 = new ArrayList<Point>();
		vertices2.add(new Point(10, 10, -1));
		vertices2.add(new Point(200, 10, -1));
		vertices2.add(new Point(200, 200, -1));
		vertices2.add(new Point(150, 200, -1));
		vertices2.add(new Point(150, 100, -1));
		
		Polygon poly2 = new Polygon(vertices2, 5, 5);
		Pdf.afficherPolygon("Exemple2.ps", poly2);
		
		ArrayList<Point> vertices3 = new ArrayList<Point>();
		vertices3.add(new Point(10, 10, -1));
		vertices3.add(new Point(200,10, -1));
		vertices3.add(new Point(200,200, -1));
		vertices3.add(new Point(10,200, -1));
		
		Polygon poly3 = new Polygon(vertices3, 5, 5);
		Pdf.afficherPolygon("Exemple3.ps", poly3);
		*/
		
		ArrayList<Point> vertices3 = new ArrayList<Point>();
		vertices3.add(new Point(43.608113, 3.879064));
		vertices3.add(new Point(43.608113, 3.879633));
		vertices3.add(new Point(43.608395, 3.879633));
		vertices3.add(new Point(43.608395, 3.879064));
		
		vertices3.get(1).decalage(-30, 100);
		//vertices3.get(2).decalage(300, 100);
		vertices3.get(3).decalage(0, -40);
		
		Polygon poly3 = new Polygon(vertices3, 5, 5);
		Pdf.afficherPolygon("Exemple3.ps", poly3);
		
		System.out.println(poly3.getVertices());
		System.out.println("Fin.");
	}
}
