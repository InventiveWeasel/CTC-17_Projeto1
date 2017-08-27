import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class AStarAlgorithm {
	private ArrayList<Cidade> cidades;
	private LinkedList<Cidade> sucessores;
	private Stack<Double> costs;
	private boolean visited[];
	private double h[], g[];
	private double INF = 100000;
	
	public AStarAlgorithm(ArrayList<Cidade> cidades){
		this.cidades = cidades;
		this.sucessores = new LinkedList<Cidade>();
		visited = new boolean[cidades.size()];
		h = new double[cidades.size()];
		g = new double[cidades.size()];
		for(int i = 0; i < visited.length; i++){
			visited[i] = false;
		}
		
	}

	public double run(int start, int dest){
		Cidade actualCity;
		double cost = 0;
		ArrayList<Integer> vizIDs;
		ArrayList<Cidade> viz = new ArrayList<Cidade>();
		
		//Inicializacoes
		for(int i = 0; i < h.length; i++){
			h[i] = distBetween(i, dest);
			g[i] = INF;
		}
		g[start] = 0;
		
		/*
		Cidade x = cidades.get(1);
		sucessores.add(x);
		x.setCostTo(30);
		x.setFromID(100);
		sucessores.add(cidades.get(22));
		x.setCostTo(40);
		sucessores.add(x);
		*/
		
		sucessores.add(cidades.get(start));
		while(!sucessores.isEmpty()){
			actualCity = sucessores.removeFirst();
			//System.out.println("ID = "+actualCity.getID());
			if(actualCity.getID() == dest){
				//System.out.println("Final = "+actualCity.getID());
				
				//Calculando custo e caminho
				int index = dest;
				int counter = 0;
				while(index != start){
					counter++;
					cost = cost+actualCity.getCostTo();
					index = actualCity.getFromID();
					actualCity = cidades.get(index);
					System.out.println("ID = "+index);
				}
				System.out.println("Custo = "+cost);
				System.out.println("Contador = "+counter);
				return cost;
			}
			else{
				//Montando conjunto de vizinhos
				vizIDs = actualCity.getViz();
				viz.clear();
				for(int i = 0; i < actualCity.getNumViz(); i++){
					Cidade actViz = cidades.get(vizIDs.get(i));
					viz.add(actViz);
				}
				
				//Para cada um dos vizinhos
				for(int i = 0; i < actualCity.getNumViz(); i++){
					int vizID = viz.get(i).getID();
					double newG = g[actualCity.getID()] + distBetween(vizID, actualCity.getID());
					if(newG < g[vizID]){
						g[vizID] = newG;
						visited[vizID] = false;
						Cidade actViz = viz.get(i);
						actViz.setCostTo(distBetween(actViz.getID(), actualCity.getID()));
						actViz.setH(newG + distBetween(actViz.getID(), dest));
						actViz.setFromID(actualCity.getID());
						if(!sucessores.contains(actViz))
							sucessores.add(actViz);
						
					}
					/*
					if(!visited[vizID]){
						Cidade actViz = viz.get(i);
						actViz.setCostTo(distBetween(actViz.getID(), actualCity.getID()));
						actViz.setFromID(actualCity.getID());
						visited[vizID] = true;
						sucessores.add(actViz);
					}
					*/
				}
				visited[actualCity.getID()] = true;
				//Reordenar a fila
				Collections.sort(sucessores);
			}
		}
		return cost;
		
	}
	
	public double distBetween(int ID1, int ID2){
		Cidade c1 = cidades.get(ID1);
		Cidade c2 = cidades.get(ID2);
		double x1, x2, y1, y2;
		x1 = c1.getX();
		x2 = c2.getX();
		y1 = c1.getY();
		y2 = c2.getY();
		//return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
		return Math.hypot(x1-x2, y1-y2);
		
	}

}
