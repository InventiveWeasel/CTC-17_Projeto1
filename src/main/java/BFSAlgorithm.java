import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class BFSAlgorithm {
	private ArrayList<Cidade> cidades;
	
	private LinkedList<Cidade> sucessores;
	private Stack<Double> costs;
	private boolean visited[];
	
	public BFSAlgorithm(ArrayList<Cidade> cidades){
		this.cidades = cidades;
		this.sucessores = new LinkedList<Cidade>();
		visited = new boolean[cidades.size()];
		for(int i = 0; i < visited.length; i++)
			visited[i] = false;
	}

	public double run(int start, int dest){
		Cidade actualCity;
		double cost = 0;
		ArrayList<Integer> vizIDs;
		ArrayList<Cidade> viz = new ArrayList<Cidade>();
		
		sucessores.add(cidades.get(start));
		while(!sucessores.isEmpty()){
			actualCity = sucessores.removeFirst();
			if(actualCity.getID() == dest){
				
				//Calculando custo e caminho
				int index = dest;
				int counter = 0;
				String path = "";
				while(index != start){
					counter++;
					cost = cost+actualCity.getCostTo();
					index = actualCity.getFromID();
					actualCity = cidades.get(index);
					path = path+index+", ";
				}
				System.out.println(path);
				return cost;
			}
			else{
				//Ordenando a prioridade por custo dos vizinhos
				vizIDs = actualCity.getViz();
				viz.clear();
				for(int i = 0; i < actualCity.getNumViz(); i++){
					Cidade actViz = cidades.get(vizIDs.get(i));
					viz.add(actViz);
				}
				
				//Para cada um dos vizinhos
				for(int i = 0; i < actualCity.getNumViz(); i++){
					int auxID = viz.get(i).getID();
					if(!visited[auxID]){
						Cidade actViz = viz.get(i);
						actViz.setCostTo(distBetween(actViz.getID(), actualCity.getID()));
						actViz.setH(distBetween(actViz.getID(), dest));
						actViz.setFromID(actualCity.getID());
						visited[auxID] = true;
						sucessores.add(actViz);
					}
				}
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
		return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
		
	}
}
