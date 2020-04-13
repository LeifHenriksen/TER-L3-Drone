import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class Chemin3 {
	ArrayList<Point> listePoint;
	ArrayList<Point> krus;
	ArrayList<EdgeElement> edge;
	HashMap<Point,Stack<Point>> voisins;
	ArrayList<Point> parcours;
	
	//CONSTRUCTEUR
	public Chemin3() {
		//listePoints = points du chemin
		listePoint = new ArrayList<>();
		//krus = acpm
	    krus = new ArrayList<>();
		edge = new ArrayList<EdgeElement>();
		voisins = new HashMap<>();
		parcours = new ArrayList<>();
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

	public void trieParOdreCroissant() {
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
		trieParOdreCroissant();						
		
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
	
	public ArrayList<Point> parcours(int n) {
		System.out.println("---------------Debut parcours -----------------------");
		HashMap <Point,Integer> dv = new HashMap<>();	//sommets deja vu : dv initialisé à 0
		for(int i = 0; i < n; i++) {
			dv.put(listePoint.get(i),0);
		}
		Point racine = listePoint.get(0);				//premier point = la racine
		HashMap <Point,Integer> debut = new HashMap<>();
		HashMap <Point,Point> pere = new HashMap<>();
		
		//Initialisation avec la racine
		dv.put(racine,1);
		debut.put(racine,1);
		pere.put(racine,racine);
		
		Stack<Point> AT = new Stack<>();	//Pile AT (à traiter)
		AT.push(racine);
		
		while(!AT.empty()) {			//tant que AT n'est pas vide
			Point x = AT.peek();
			if(voisins.get(x).isEmpty()) {
				AT.pop();
				System.out.println(AT);
			}
			else {
				Point y = voisins.get(x).pop();
				if(dv.get(y) == 0) {
					dv.put(y,1);
					AT.push(y);
					pere.put(y,x);
					System.out.println(AT);
				}
			}
		}
		
		//Getting Set of keys from HashMap          
		Set<Point> keySet = pere.keySet(); 
		         
		//Creating an ArrayList of keys by passing the keySet   
		ArrayList<Point> listOfKeys = new ArrayList<Point>(keySet);
		
		//Getting Collection of values from HashMap        
		Collection<Point> values = pere.values(); 
		         
		//Creating an ArrayList of values 
		ArrayList<Point> listOfValues = new ArrayList<Point>(values);
		
		ArrayList<Point> result = new ArrayList<>();
		System.out.println(listOfKeys.size());
		for(int i = 0; i < listOfKeys.size(); i++) {
			result.add(listOfKeys.get(i));
			result.add(listOfValues.get(i));
		}
//		System.out.println("*********************************");
//		for(int i = 0; i < listOfKeys.size(); i++) {
//			
//			System.out.println(listOfKeys.get(i));
//			System.out.println(listOfValues.get(i));
//		}
		
		System.out.println("----------------Fin parcours ------------------------");
		return result;
		
	}
	
	public HashMap<Point, Stack<Point>> initVoisins(ArrayList<Point> list, int n) {
		
		for(int i = 0; i < n; i++) {
			this.voisins.put(krus.get(i),new Stack<>());
		}
		for(int i = 0; i < n; i = i + 2) {
			this.voisins.get(krus.get(i)).push(krus.get(i+1));
			this.voisins.get(krus.get(i+1)).push(krus.get(i));
		}
		return this.voisins;
	}
	
	public ArrayList<Point> voyageurDeCommerce(Polygon poly) {
		setListePoint(poly.getPointsInternes());
		int nbPoints = this.listePoint.size();
		int nbAretes = (nbPoints*(nbPoints-1))/2;
		ArrayList<Point> maListe = new ArrayList<>();
		
		//for(int i = 0; i < nbAretes; i++) {
			//edge.add(new EdgeElement(new Point(0,0,0,0,0), new Point(0,0,0,0,0), 0.));
		//}
		System.out.println("p :"+nbPoints+" aretes "+nbAretes+" "+edge.size());
		
		//Initialise et tri les aretes par ordre croissant les distances
		initDistances(nbPoints);
		
		afficheEdge();
		
		//Calcul d'un arbre couvrant de poids minimum -> algo kruskal
		this.krus = kruskal(nbPoints, nbAretes);
		
		for(int i =0 ; i< maListe.size();i++) {
			System.out.println("laaaaaaa" + maListe.get(i));
		}
		
		System.out.println("laaaaaaaaaa" + "maajkfjej"+ maListe.size());
		
		initVoisins(krus,nbPoints); //Initialisation des voisins
		
		
		this.parcours = parcours(nbPoints);
		
		
		return this.listePoint;
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
		Polygon poly = new Polygon(vertices, 30, 30);

		chemin.voyageurDeCommerce(poly);
		
		Pdf.afficherChemin("Chemin.ps", poly, chemin.listePoint);
		Pdf.afficherGraphe("Graphe.ps", poly, chemin.krus);
		
		Pdf.afficherChemin("Chemin2.ps", poly, chemin.parcours);
		Pdf.afficherGraphe("Graphe2.ps", poly, chemin.parcours);
		
		System.out.println("FIN");
	}
}