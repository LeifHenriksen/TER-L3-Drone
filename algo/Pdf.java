import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pdf {
	static public void afficherPolygon(String fichier, Polygon poly) 
	{
		BufferedWriter out = null;
		try 
		{
		    FileWriter fstream = new FileWriter(fichier);
		    out = new BufferedWriter(fstream);
		   
		    out.write("%!PS-Adobe-3.0 \n");
		    out.write("%%BoundingBox: 0 0 10000 10000 \n");
		    
		    writeVertices(out, poly);
		    writePoints(out, poly);
		    
		    out.write("showpage \n");	 
		    out.close();		    
		}
		catch (IOException e) 
		{
			System.out.println("Error: " + e.getMessage()); 
		}
	}
	
	static public void afficherPolygon(String fichier, Polygon poly, int h, int w) 
	{
		BufferedWriter out = null;
		try 
		{
		    FileWriter fstream = new FileWriter(fichier);
		    out = new BufferedWriter(fstream);
		   
		    out.write("%!PS-Adobe-3.0 \n");
		    out.write("%%BoundingBox: 0 0 "+h+" "+w+" \n");
		    
		    writeVertices(out, poly);
		    writePoints(out, poly);
		    
		    out.write("showpage \n");	 
		    out.close(); 
		}
		catch (IOException e) 
		{
			System.out.println("Error: " + e.getMessage()); 
		}
	}
	
	static public void afficherChemin(String fichier, Polygon poly, ArrayList<Point> chemin) 
	{
		BufferedWriter out = null;
		try 
		{
		    FileWriter fstream = new FileWriter(fichier);
		    out = new BufferedWriter(fstream);
		   
		    out.write("%!PS-Adobe-3.0 \n");
		    out.write("%%BoundingBox: 0 0 500 500\n");
		    

		    writeVertices(out, poly);
		    writePoints(out, poly);
		    writeVerticesChemin(out, chemin);
		    writePointsChemin(out, chemin);
		    
		    out.write("showpage \n");	 
		    out.close(); 
		}
		catch (IOException e) 
		{
			System.out.println("Error: " + e.getMessage()); 
		}
	}
	
	private static void writePoints(BufferedWriter out, Polygon poly) throws IOException 
	{
		//System.out.println(poly.getPointsInternes().size());
		for(int i = 0; i<poly.getPointsInternes().size(); i++)
		{
			out.write((Double.toString(poly.getPointsInternes().get(i).getX())).substring(2));
			//System.out.println((Double.toString(poly.getPointsInternes().get(i).getX())).substring(3));
		    out.write(" ");
		    out.write((Double.toString(poly.getPointsInternes().get(i).getY())).substring(3));
		    //System.out.println((Double.toString(poly.getPointsInternes().get(i).getY())).substring(3));
		    out.write(" 1 0 360 arc \n");
		    if(false)//poly.pointsInternes.get(i).getY() == 100)
		    	out.write("0 1 0 setrgbcolor \n");
		    else
		    	out.write("0 setgray \n");
		    out.write("fill \n");
		    out.write("stroke \n");
		}
	}
	
	private static void writeVertices(BufferedWriter out, Polygon poly) throws IOException 
	{
		writeVerticesLines(out, poly);
	}
	
	private static void writeVerticesLines(BufferedWriter out, Polygon poly) throws IOException 
	{
		for(int i = 0; i<(poly.getVertices().size() - 1); i++)
		{
			out.write(Double.toString(poly.getVertices().get(i).getX()).substring(2));
		    out.write(" ");
		    out.write(Double.toString(poly.getVertices().get(i).getY()).substring(3));
		    out.write(" moveto \n");
		    out.write(Double.toString(poly.getVertices().get(i+1).getX()).substring(2));
		    out.write(" ");
		    out.write(Double.toString(poly.getVertices().get(i+1).getY()).substring(3));
		    out.write(" lineto \n");
		    out.write("stroke \n");
		}
		
		int last = poly.getVertices().size() - 1;
		int first = 0;
		out.write(Double.toString(poly.getVertices().get(last).getX()).substring(2));
	    out.write(" ");
	    out.write(Double.toString(poly.getVertices().get(last).getY()).substring(3));
	    out.write(" moveto \n");
	    out.write(Double.toString(poly.getVertices().get(first).getX()).substring(2));
	    out.write(" ");
	    out.write(Double.toString(poly.getVertices().get(first).getY()).substring(3));
	    out.write(" lineto \n");
	    out.write("stroke \n");
	}
	
	private static void writePointsChemin(BufferedWriter out, ArrayList<Point> chemin) throws IOException 
	{
		//System.out.println(chemin.size());
		for(int i = 0; i<chemin.size(); i++)
		{
			out.write(Double.toString(chemin.get(i).getX()));
		    out.write(" ");
		    out.write(Double.toString(chemin.get(i).getY()));
		    out.write(" 1 0 360 arc \n");
		    out.write("0 1 0 setrgbcolor \n");
		    out.write("fill \n");
		    out.write("stroke \n");
		}
	}
	
	private static void writeVerticesChemin(BufferedWriter out, ArrayList<Point> chemin) throws IOException 
	{
		writeVerticesLinesChemin(out, chemin);
	}
	
	private static void writeVerticesLinesChemin(BufferedWriter out, ArrayList<Point> chemin) throws IOException 
	{
		for(int i = 0; i<chemin.size()-1; i++)
		{
			out.write(Double.toString(chemin.get(i).getX()));
		    out.write(" ");
		    out.write(Double.toString(chemin.get(i).getY()));
		    out.write(" moveto \n");
		    out.write(Double.toString(chemin.get(i+1).getX()));
		    out.write(" ");
		    out.write(Double.toString(chemin.get(i+1).getY()));
		    out.write(" lineto \n");
		    out.write("stroke \n");
		}
	}

}
