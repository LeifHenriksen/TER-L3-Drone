
public class EdgeElement {
	Point p1;
	Point p2;
	Double distance;
	
	public EdgeElement(Point p1, Point p2, double d) {
		this.p1 = p1;
		this.p2 = p2;
		this.distance = d;
	}
	
	public Point getP1() {
		return p1;
	}
	public void setP1(Point p) {
		p1 = p;
	}
	
	public Point getP2() {
		return p2;
	}
	public void setP2(Point p) {
		p2 = p;
	}
	
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double d) {
		distance = d;
	}
	
	public String toString() {
		return p1+" "+p2+" Distance : "+distance;
		
	}	

}
