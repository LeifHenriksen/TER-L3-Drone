import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Chemin3 {
	ArrayList<Point> listePoint;
	ArrayList<Point> krus;
	ArrayList<EdgeElement> edge;

	
	//CONSTRUCTEUR
	public Chemin3() {
		//listePoints = points du chemin
		listePoint = new ArrayList<>();
		krus = new ArrayList<>();
		edge = new ArrayList<EdgeElement>();

	}
	
	//ACCESSEURS
	public ArrayList<Point> getListePoint() {
		return listePoint;
	}
	
	public void setListePoint(ArrayList<Point> p) {
		this.listePoint.addAll(p);
	}
	
	public ArrayList<EdgeElement> getEdge() {
		return edge;
	}
	
	public void setEdge(ArrayList<EdgeElement> v) {
		this.edge = v;
	}
	
	//METHODES
	public void afficheChemin() {
		for(int i = 0; i < getListePoint().size(); i++) {
			System.out.println(getListePoint().get(i));
		}
	}
	
	public void afficheEdge() {
		for(int i = 0; i < getEdge().size(); i++) {
			System.out.print(getEdge().get(i));
			System.out.println();
		}
	}
	
	public static void swap(EdgeElement edgeElement, EdgeElement edgeElement2) {			//Echange deux points
		Point temp = edgeElement2.getP1();
		edgeElement2.setP1(edgeElement.getP1());
		edgeElement.setP1(temp);
		
		Point temp2 = edgeElement2.getP2();
		edgeElement2.setP2(edgeElement.getP2());
		edgeElement.setP2(temp2);
		
		Double temp3 = edgeElement2.getDistance();
		edgeElement2.setDistance(edgeElement.getDistance());
		edgeElement.setDistance(temp3);
	}

	public void triParOdreCroissant() {
		for(int i = 0; i < this.getEdge().size(); i++) {
			for(int j = 0; j < this.getEdge().size()-1; j++){
				if(this.getEdge().get(j).getDistance() > this.getEdge().get(j+1).getDistance()) {
					swap(this.getEdge().get(j),this.getEdge().get(j+1));
				}
			}
		}
	}
	
	public void initDistances(int n) {
		int k = 0;
		//Remplissage de edge
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {			
				this.edge.add(new EdgeElement(listePoint.get(i),listePoint.get(j),Point.distance(listePoint.get(i), listePoint.get(j))));
				k++;
			}
		}
		
		//Trier par odre croissant des distances  
		triParOdreCroissant();						
		
	}
	
	//Kruskal -> Arbre couvrant de poids minimum
	public ArrayList<Point> kruskal(int n, int m) {
	System.out.println("-----------------Debut kruskal--------------------");
        ArrayList<Point> acpm = new ArrayList<>(); //Arbre couvrant de poids min -> resultat
      //ArrayList<Point> comp = new ArrayList<>();
        HashMap <Point,Integer> comp = new HashMap<>();
        for(int i=0;i<listePoint.size();i++ ) {
        	comp.put(listePoint.get(i),i);
        	//System.out.println(comp.get(listePoint.get(i)));
        	//System.out.println("taille : " + listePoint.size());
        }
      
        Integer aux=0;
       // comp.put(edge.get(4).p1,4);
       // System.out.println(comp.get(edge.get(4).p1));

        for(int i = 0; i < edge.size(); i++) {
        	//System.out.println( "comp(x) :" + comp.get(edge.get(i).p1) + "  " + " comp(y)" + comp.get(edge.get(i).p2));
            if(!(comp.get(edge.get(i).p1).equals(comp.get((edge.get(i).p2))))) {// if comp(x) != comp(y)
                aux =comp.get(edge.get(i).p1);//comp(x)
                acpm.add(edge.get(i).p1);//A u {xy}
                acpm.add(edge.get(i).p2);
               // cpt2++;
                //System.out.println("Dans comp(x)!=comp(y)");
            }else {
            //System.out.println("laaaaaaa");
            	//cpt++;
            }

              for(int k= 0 ; k< listePoint.size();k++) {
            	  if(comp.get(listePoint.get(k)).equals(aux)) {
            		  //System.out.println("avant :" + comp.get(listePoint.get(k)));
            	   comp.put(listePoint.get(k),comp.get(edge.get(i).p2));
            	   //System.out.println("apres :" + comp.get(listePoint.get(k)));
            	  }
              }
             // for (Integer j : comp.values()) {
            	//  System.out.println(j);
            	//}
            
            }
      //  System.out.println("taille de l'acpm " + acpm.size() + " " );
       // for(int i =0 ;i< acpm.size()-1;i+=2) {
        	//System.out.print(acpm.get(i));
        	//System.out.println(acpm.get(i+1));
        //}
        System.out.println("-----------------Fin kruskal--------------------");
        return acpm;
    }
	
	private Integer findPoint(Point p) {
		for (Integer i = 0; i < listePoint.size(); i++) {
			if(listePoint.get(i).equals(p)) {
				
				return i;
			}
		}
		for (Integer i = 0; i < listePoint.size(); i++) {
		System.out.println("le point nn touve :" + p + "le point comparï¿½ : " + listePoint.get(i));
		}
		return -1;
		
	}
	
	public ArrayList<Point> parcours(ArrayList<Point> kruskal) {
		System.out.println("---------------Debut parcours -----------------------");
		ArrayList<Point> parcours = new ArrayList<Point>();
		HashMap<Point,ArrayList<Point>> voisins = initVoisins(kruskal);
		HashMap <Point,Integer> dv = new HashMap<>();
		//sommets deja vu : dv initialisé à 0
		for(int i = 0; i < listePoint.size(); i++) {
			dv.put(listePoint.get(i),0);
		}
		HashMap <Point,Integer> debut = new HashMap<>();
		HashMap <Point,Integer> fin = new HashMap<>();
		HashMap <Point,Point> pere = new HashMap<>();
		
		Point racine = listePoint.get(0);				//premier point = la racine
		dv.put(racine,1);
		debut.put(racine,1);
		pere.put(racine,racine);
		Integer t =2;
		Stack<Point> AT = new Stack<>();	//Pile AT (à traiter)
		AT.push(racine);
		
		while(!AT.empty()) {			//tant que AT n'est pas vide
			Point x = AT.peek();
			if(voisins.get(x).isEmpty()) {
				AT.pop();
				fin.put(x,t);
				t++;
				
			}
			else {
				
				Point y =  voisins.get(x).get(voisins.get(x).size()-1);
				voisins.get(x).remove(voisins.get(x).size()-1);
				
				System.out.println("y : " + y);
				if(dv.get(y) == 0) {
					
					dv.put(y,1);
					AT.push(y);
					debut.put(y,t);
					t++;
					pere.put(y,x);
					parcours.add(x);
					parcours.add(y);
				}
				if(voisins.get(x).isEmpty()) {
					Point leplusproche = new Point(9999999,9999999,0,0,0);
					for (int i = 0; i < listePoint.size(); i++) {
						if(  (!(x.equals(listePoint.get(i)))) && dv.get(listePoint.get(i))==0) {
							if(Point.distance(x, listePoint.get(i))<Point.distance(x, leplusproche)) {
								leplusproche= listePoint.get(i);
								
							}
						}
					}
					if(!leplusproche.equals(new Point(9999999,9999999,0,0,0))) {
					voisins.get(x).add(leplusproche);
					}
				}
			}
		}
		/*
		while(!AT.empty()) {			//tant que AT n'est pas vide
			Point x = AT.peek();
			if(voisins.get(x).isEmpty()) {
				AT.pop();
				fin.put(x,t);
				t++;
				
			}
			else {
				Point y =  voisins.get(x).get(voisins.get(x).size()-1);
				voisins.get(x).remove(voisins.get(x).size()-1);
				if(dv.get(y) == 0) {
					dv.put(y,1);
					AT.push(y);
					debut.put(y,t);
					t++;
					pere.put(y,x);
					
				}
			}
		}*/
		
		
		return parcours;
		
	}
	
	public HashMap<Point,ArrayList<Point>> initVoisins(ArrayList<Point> maListe){
		HashMap<Point,ArrayList<Point>> voisins = new HashMap<Point, ArrayList<Point>>();
		for (int i = 0; i < listePoint.size(); i++) {
			voisins.put(listePoint.get(i),new ArrayList<Point>());
		}
		for (int i = 0; i <maListe.size()-1 ; i+=2) {
				voisins.get(maListe.get(i)).add(maListe.get(i+1));
				voisins.get(maListe.get(i+1)).add(maListe.get(i));
		}
		return voisins;
	}
	
	public ArrayList<Point> voyageurDeCommerce(Polygon poly) {
		setListePoint(poly.getPointsInternes());
		int nbPoints = this.listePoint.size();
		int nbAretes = (nbPoints*(nbPoints-1))/2;
		ArrayList<Point> maListe = new ArrayList<>();
		
	
		//Initialise et tri les aretes par ordre croissant les distances
		System.out.println("avant init ");
		initDistances(nbPoints);
		System.out.println("apres init ");
		
		afficheEdge();
		
		//Calcul d'un arbre couvrant de poids minimum -> algo kruskal
		this.krus = kruskal(nbPoints, nbAretes);
		HashMap<Point,ArrayList<Point>> voisins = initVoisins(krus);
		/*for(int i =0 ; i< krus.size()-1;i+=2) {
		//	System.out.println("vosins :" +  krus.get(i) + voisins.get(krus.get(i)));
		}
		for (int i = 0; i < listePoint.size(); i++) {
			for (int j = 0; j < listePoint.size(); j++) {
				if(i!=j) {
				krus.add(listePoint.get(i));
				krus.add(listePoint.get(j));
				}
			}
		}*/
		ArrayList<Point> parcours = parcours(krus);
		
	
		
		//initVoisins(krus,nbPoints); //Initialisation des voisins
		
		
		//this.parcours = parcours(nbPoints);
		
		
		return parcours;
	}
	
	
	public static void main(String[] args) 
	{
		ArrayList<Point> vertices = new ArrayList<Point>();
		
		vertices.add(new Point(0,0,-1,-1,-1));
		
		vertices.add(new Point(400,0,-1,-1,-1));
		vertices.add(new Point(400,400,-1,-1,-1));
		vertices.add(new Point(0,400,-1,-1,-1));
		vertices.add(new Point(0,0,-1,-1,-1));
		
		Chemin3 chemin = new Chemin3();
		Polygon poly = new Polygon(vertices, 30	, 30);

		ArrayList<Point> parcours = chemin.voyageurDeCommerce(poly);
		System.out.println("taille :" + parcours.size());
		parcours.add(parcours.get(parcours.size()-1));
		parcours.add(parcours.get(0));
	//	Pdf.afficherChemin("Chemin.ps", poly, chemin.listePoint);
		Pdf.afficherGraphe("Graphe.ps", poly,parcours);
		
		//Pdf.afficherChemin("Chemin2.ps", poly, chemin.parcours);
		//Pdf.afficherGraphe("Graphe2.ps", poly, chemin.parcours);
		
		System.out.println("FIN");
	}
}