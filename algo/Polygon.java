import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Polygon {
	
	ArrayList<Point> vertices;
	ArrayList<Point> pointsInternes;
	ArrayList<Line> edges;
	String fichierAffichage;
	
	public Polygon(String fichierAffichage, ArrayList<Point> vertices) {
		this.fichierAffichage = fichierAffichage;
		this.vertices = vertices;
		
		this.pointsInternes = new ArrayList<Point>();
		this.addPoints();
		
		this.edges = new ArrayList<Line>();
		for(int i = 0; i<this.vertices.size()-1; i++)
		{
			this.edges.add(new Line(this.vertices.get(i), this.vertices.get(i+1)));
		}
		this.edges.add(new Line(this.vertices.get(this.vertices.size()-1), this.vertices.get(0)));
	}
	
	public ArrayList<Point> getVertices() {
		return vertices;
	}
	
	public ArrayList<Point> getPointsInternes() {
		return pointsInternes;
	}
	
	public String getFichierAffichage() {
		return fichierAffichage;
	}

	public void setFichierAffichage(String fichierAffichage) {
		this.fichierAffichage = fichierAffichage;
	}
	//Rempli la carte de points
	private void addPoints() 
	{
		int hauteurCarte   = 300;
		int largeurCarte   = 300;
		int distancePoints = 10;
		for(int i = 0; i<largeurCarte/distancePoints; i++)
		{
			for(int j = 0; j<hauteurCarte/distancePoints; j++)
			{
				this.pointsInternes.add(new Point(i*distancePoints,j*distancePoints,-1,-1,-1));
			}
		}
	}
	
	private boolean pointInside(Point p) 
	{	
		int nbIntersections = 0;
		int nbIntersectionsTop = 0;
		boolean insideLine = false;
		boolean tropProche = false;
		//Point pointInf = new Point(9999,p.getY(),-1,-1,-1);
		Line rayRight = new Line(p,new Point(9999,p.getY(),-1,-1,-1));
		Line rayLeft  = new Line(p,new Point(-9999,p.getY(),-1,-1,-1));
		Line rayTop   = new Line(p,new Point(p.getX(),9999,-1,-1,-1));
		Line rayBot   = new Line(p,new Point(p.getX(),-9999,-1,-1,-1));
		
		for(int i = 0; i<this.edges.size(); i++)
		{
			if(Line.intersect(rayRight, this.edges.get(i)))
			{
				if(!(Line.lineLineIntersection(rayRight, this.edges.get(i)) == null))
				{
					Point corner = Line.lineLineIntersection(rayRight, this.edges.get(i));
					if(!(
					    (corner.getX() == this.edges.get(i).getP1().getX() && 
					     corner.getY() == this.edges.get(i).getP1().getY())
					    ||
					    (corner.getX() == this.edges.get(i).getP2().getX() && 
					     corner.getY() == this.edges.get(i).getP2().getY()) 
					   ))
						nbIntersections++;
					else
					{
						nbIntersections--;
					}
					if(Point.distance(p,Line.lineLineIntersection(rayRight, this.edges.get(i))) < 3)
						tropProche = true;
				}
				if(Line.pointInsideLine(this.edges.get(i), p))
					insideLine = true;
			}
			if(Line.intersect(rayLeft, this.edges.get(i)))
			{
				if(!(Line.lineLineIntersection(rayLeft, this.edges.get(i)) == null))
				{
					if(Point.distance(p,Line.lineLineIntersection(rayLeft, this.edges.get(i))) < 3)
						tropProche = true;
				}
			}
			if(Line.intersect(rayTop, this.edges.get(i)))
			{
				if(!(Line.lineLineIntersection(rayTop, this.edges.get(i)) == null))
				{
					if(Point.distance(p,Line.lineLineIntersection(rayTop, this.edges.get(i))) < 3)
						tropProche = true;
				}
			}
			if(Line.intersect(rayBot, this.edges.get(i)))
			{
				if(!(Line.lineLineIntersection(rayBot, this.edges.get(i)) == null))
				{
					if(Point.distance(p,Line.lineLineIntersection(rayBot, this.edges.get(i))) < 3)
						tropProche = true;
				}
			}
		}
		if(p.getY() == 100) 
		{
			System.out.println("Right " + nbIntersections);
			System.out.println("Top   " + nbIntersectionsTop);
		}
		
		return (nbIntersections%2)>0 && !insideLine && !tropProche;
	}
	
	public void enleverPointsDehors() 
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
	
	private void writePoints(BufferedWriter out) throws IOException 
	{
		System.out.println(this.pointsInternes.size());
		for(int i = 0; i<this.pointsInternes.size(); i++)
		{
			out.write(Double.toString(this.pointsInternes.get(i).getX()));
		    out.write(" ");
		    out.write(Double.toString(this.pointsInternes.get(i).getY()));
		    out.write(" 1 0 360 arc \n");
		    if(this.pointsInternes.get(i).getY() == 100)
		    	out.write("0 1 0 setrgbcolor \n");
		    else
		    	out.write("0 setgray \n");
		    out.write("fill \n");
		    out.write("stroke \n");
		}
	}
	
	private void writeVertices(BufferedWriter out) throws IOException 
	{
		this.writeVerticesLines(out);
	}
	
	private void writeVerticesLines(BufferedWriter out) throws IOException 
	{
		for(int i = 0; i<(this.vertices.size() - 1); i++)
		{
			out.write(Double.toString(this.vertices.get(i).getX()));
		    out.write(" ");
		    out.write(Double.toString(this.vertices.get(i).getY()));
		    out.write(" moveto \n");
		    out.write(Double.toString(this.vertices.get(i+1).getX()));
		    out.write(" ");
		    out.write(Double.toString(this.vertices.get(i+1).getY()));
		    out.write(" lineto \n");
		    out.write("stroke \n");
		}
		
		int last = this.vertices.size() - 1;
		int first = 0;
		out.write(Double.toString(this.vertices.get(last).getX()));
	    out.write(" ");
	    out.write(Double.toString(this.vertices.get(last).getY()));
	    out.write(" moveto \n");
	    out.write(Double.toString(this.vertices.get(first).getX()));
	    out.write(" ");
	    out.write(Double.toString(this.vertices.get(first).getY()));
	    out.write(" lineto \n");
	    out.write("stroke \n");
	}
	
	public void afficherPolygon() {
		BufferedWriter out = null;
		try 
		{
		    FileWriter fstream = new FileWriter(this.fichierAffichage);
		    out = new BufferedWriter(fstream);
		   
		    out.write("%!PS-Adobe-3.0 \n");
		    out.write("%%BoundingBox: 0 0 500 500 \n");
		    
		    this.writeVertices(out);
		    this.writePoints(out);
		    
		    out.write("showpage \n");	 
		    out.close();
		    
		}
		catch (IOException e) 
		{
			System.out.println("Error: " + e.getMessage()); 
		}
	}
}
