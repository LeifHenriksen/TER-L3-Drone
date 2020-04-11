import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Point {
	public static AtomicInteger genID = new AtomicInteger();
	int numero;
	double x;
	double y;
	double z;
	double lat;
	double lng;
	
	public Point(double x, double y, double z, double lat, double lng) {
		this.numero = genID.getAndIncrement();
		this.x = x;
		this.y = y;
		this.z = z;
		this.lat = lat;
		this.lng = lng;
	}
	public int getNumero() {
		return numero;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public boolean equals(Point p) {
		return this.x == p.x &&
				this.y == p.y &&
				this.z == p.z &&
				this.lat == p.lat &&
				this.lng == p.lng;
	}
	static public Point maxX(ArrayList<Point> listeP) 
	{
		Point max = listeP.get(0);
		for(int i = 1; i<listeP.size(); i++)
			if(max.getX()<listeP.get(i).getX())
				max = listeP.get(i);
		
		return max;
	}
	static public Point maxY(ArrayList<Point> listeP) 
	{
		Point max = listeP.get(0);
		for(int i = 1; i<listeP.size(); i++)
			if(max.getY()<listeP.get(i).getY())
				max = listeP.get(i);
		
		return max;
	}
	static public double distance(Point p1, Point p2){
		return Math.sqrt(((p2.getX() - p1.getX())*(p2.getX() - p1.getX()))+
				         ((p2.getY() - p1.getY())*(p2.getY() - p1.getY())));
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", z=" + z + ", lat=" + lat + ", lng=" + lng + "]";
	}	
}
