import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("Debut.");
		
		ArrayList<Point> vertices = new ArrayList<Point>();
		vertices.add(new Point(10,10,-1,-1,-1));
		vertices.add(new Point(200,10,-1,-1,-1));
		vertices.add(new Point(200,200,-1,-1,-1));
		vertices.add(new Point(150,200,-1,-1,-1));
		vertices.add(new Point(150,100,-1,-1,-1));
		/*vertices.add(new Point(100,100,-1,-1,-1));
		vertices.add(new Point(75,175,-1,-1,-1));
		vertices.add(new Point(10,175,-1,-1,-1));
		*/
		Polygon poly = new Polygon("Exemple.ps", vertices);
		Polygon poly2 = new Polygon("Exemple2.ps", vertices);
		poly.enleverPointsDehors();
		poly.afficherPolygon();
		poly2.afficherPolygon();
		System.out.println("Fin.");
	}
}
