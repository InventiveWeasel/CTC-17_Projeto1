import java.util.ArrayList;

public class Cidade {
	private int id;
	private double x,y;
	private ArrayList<Integer> adjList;
	
	
	public Cidade(int id, double x, double y){
		this.id = id;
		this.x = x;
		this.y = y;
		adjList = new ArrayList<Integer>();
	}
	
	public void addCity(String cityID){
		adjList.add(new Integer(cityID));
	}
	
	public int getID(){
		return id;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public int getNumViz(){
		return adjList.size();
	}
}
