
public class Line {
	
	Point p1;
	Point p2;
	
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getP1() {
		return p1;
	}
	
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	
	public Point getP2() {
		return p2;
	}
	
	public void setP2(Point p2) {
		this.p2 = p2;
	}
	
	static boolean onSegment(Line l1, Point p3) 
	{ 
	    if (p3.x <= Math.max(l1.getP1().x, l1.getP2().x) && p3.x >= Math.min(l1.getP1().x, l1.getP2().x) && 
	    	p3.y <= Math.max(l1.getP1().y, l1.getP2().y) && p3.y >= Math.min(l1.getP1().y, l1.getP2().y)) 
	    return true; 
	  
	    return false; 
	} 
	
	static public Orientation getOrientation(Line l1, Point p3)
	{
		int val = (int)((l1.getP2().getY() - l1.getP1().getY()) * (p3.getX() - l1.getP2().getX()) - 
	                    (l1.getP2().getX() - l1.getP1().getX()) * (p3.getY() - l1.getP2().getY())); 
	  
	    if (val == 0) 
	    	return Orientation.Collinear;
	  
	    return (val > 0)? Orientation.Clockwise: Orientation.CounterClockwise;
	}
	
	static Point lineLineIntersection(Line l1, Line l2) 
    { 
        // Line AB represented as a1x + b1y = c1 
        double a1 = l1.getP2().getY() - l1.getP1().getY(); 
        double b1 = l1.getP1().getX() - l1.getP2().getX(); 
        double c1 = a1*(l1.getP1().getX()) + b1*(l1.getP1().getY()); 
       
        // Line CD represented as a2x + b2y = c2 
        double a2 = l2.getP2().getY() - l2.getP1().getY(); 
        double b2 = l2.getP1().getX() - l2.getP2().getX();
        double c2 = a2*(l2.getP1().getX()) + b2*(l2.getP1().getY()); 
       
        double determinant = a1*b2 - a2*b1; 
       
        if (determinant == 0) 
        { 
            // The lines are parallel.
            return null; 
        } 
        else
        { 
            double x = (b2*c1 - b1*c2)/determinant; 
            double y = (a1*c2 - a2*c1)/determinant; 
            return new Point(x, y,-1,-1,-1); 
        } 
    } 
	static public boolean pointInsideLine(Line l, Point p) {
		return Point.distance(l.getP1(), l.getP2()) == Point.distance(l.getP1(), p) + Point.distance(p, l.getP2());
	}
	static public boolean intersect(Line l1, Line l2) 
	{
		Orientation o1 = getOrientation(l1, l2.getP1()); 
		Orientation o2 = getOrientation(l1, l2.getP2());
		Orientation o3 = getOrientation(l2, l1.getP1());
		Orientation o4 = getOrientation(l2, l1.getP2());
		
		if(!o1.equals(o2) && !o3.equals(o4))
			return true;
		
		
		// Special Cases 
	    // p1, q1 and p2 are colinear and p2 lies on segment p1q1 
	    if (o1.equals(Orientation.Collinear) && onSegment(l1, l2.getP1())) 
	    	return true; 
	  
	    // p1, q1 and q2 are colinear and q2 lies on segment p1q1 
	    if (o2.equals(Orientation.Collinear) && onSegment(l1, l2.getP2())) 
	    	return true; 
	  
	    // p2, q2 and p1 are colinear and p1 lies on segment p2q2 
	    if (o3.equals(Orientation.Collinear) && onSegment(l2, l1.getP1())) 
	    	return true; 
	  
	    // p2, q2 and q1 are colinear and q1 lies on segment p2q2 
	    if (o4.equals(Orientation.Collinear) && onSegment(l2, l1.getP2())) 
	    	return true; 
	  
	  
		return false;
	}

	@Override
	public String toString() {
		return "Line [p1=" + p1 + ", p2=" + p2 + "]";
	}
}
