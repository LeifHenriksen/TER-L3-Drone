import java.util.ArrayList;

public class Polygon {
	
	ArrayList<Point> vertices;
	ArrayList<Point> pointsInternes;
	ArrayList<Line> edges;
	double distanceEntrePoints;
	double distanceBords;
	
	public Polygon(ArrayList<Point> vertices, double distanceEntrePoints, double distanceBords) {
		this.vertices = vertices;
		this.distanceEntrePoints = distanceEntrePoints;
		this.distanceBords = distanceBords;
		
		this.pointsInternes = new ArrayList<Point>();
		this.addPoints();
		
		this.edges = new ArrayList<Line>();
		for(int i = 0; i<this.vertices.size()-1; i++)
		{
			this.edges.add(new Line(this.vertices.get(i), this.vertices.get(i+1)));
		}
		this.edges.add(new Line(this.vertices.get(this.vertices.size()-1), this.vertices.get(0)));
		
		this.enleverPointsDehors();
	}
	
	public ArrayList<Point> getVertices() {
		return vertices;
	}
	
	public ArrayList<Point> getPointsInternes() {
		return pointsInternes;
	}
	
	//Rempli la carte de points
	private void addPoints() 
	{
		int hauteurCarte   = (int)Point.maxY(this.getVertices()).getY();
		int largeurCarte   = (int)Point.maxX(this.getVertices()).getX();
		for(int i = 0; i<largeurCarte/distanceEntrePoints; i++)
		{
			for(int j = 0; j<hauteurCarte/distanceEntrePoints; j++)
			{
				this.pointsInternes.add(new Point(i*distanceEntrePoints,j*distanceEntrePoints,-1,-1,-1));
			}
		}
	}
	
	private boolean pointInside(Point p) 
	{	
		int nbIntersections = 0;
		boolean insideLine = false;
		boolean tropProche = false;
		//Point pointInf = new Point(9999,p.getY(),-1,-1,-1);
		Line rayRight = new Line(p,new Point(9999,p.getY(),-1,-1,-1));
		Line rayLeft  = new Line(p,new Point(-9999,p.getY(),-1,-1,-1));
		Line rayTop   = new Line(p,new Point(p.getX(),9999,-1,-1,-1));
		Line rayBot   = new Line(p,new Point(p.getX(),-9999,-1,-1,-1));
		
		for(int i = 0; i<this.edges.size(); i++)
		{
			Line edge = this.edges.get(i);
			if(Line.intersect(rayRight, edge))
			{
				//Verifier si on touche un coin, si oui ce que le edge contien un autre point p2, tq p2.y > p1.y 
				if(edge.getP1().getY() == p.getY())
				{
					if(edge.getP2().getY() > p.getY())
						nbIntersections++;
				}
				else if(edge.getP2().getY() == p.getY())
				{
					if(edge.getP1().getY() > p.getY())
						nbIntersections++;
				}
				else
				{
					nbIntersections++;
				}
				
				if(Line.pointInsideLine(edge, p))
					insideLine = true;
			}
			//verifier que on n'est pas trop proche de un edge
			if(!insideLine && (nbIntersections%2)>0)
			{
				double dis = Line.distancePointToPointOfInter(p, rayRight, edge); 
				if(Line.intersect(rayRight, edge))
				{
					if(dis < distanceBords && dis >= 0)
						tropProche = true;
				}
				
				if(Line.intersect(rayLeft, edge))
				{
					dis = Line.distancePointToPointOfInter(p, rayLeft, edge);
					if(dis < distanceBords && dis >= 0)
						tropProche = true;	
				}
				
				if(Line.intersect(rayTop, edge))
				{
					dis = Line.distancePointToPointOfInter(p, rayTop, edge);
					if(dis < distanceBords && dis >= 0)
						tropProche = true;	
				}
				
				if(Line.intersect(rayBot, edge))
				{
					dis = Line.distancePointToPointOfInter(p, rayBot, edge);
					if(dis < distanceBords && dis >= 0)
						tropProche = true;	
				}
			}
		}
		if(p.getY() == 100) 
		{
			System.out.println(p + " \n Right : " + nbIntersections + ", insideLine  = " + insideLine + ", tropProche = " + tropProche);
			//System.out.println("Top   " + nbIntersectionsTop);
		}
		return (nbIntersections%2)>0 && !insideLine && !tropProche;
	}
	
	private void enleverPointsDehors() 
	{
		System.out.println(this.pointsInternes.size());
		for(int i = this.pointsInternes.size() - 1; i>=0; i--)
		{
			if(!this.pointInside(this.pointsInternes.get(i)))
			{
				//System.out.println("true");
				this.pointsInternes.remove(i);
			}
		}
		System.out.println(this.pointsInternes.size());
	}
}
