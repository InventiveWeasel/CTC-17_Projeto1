import java.util.ArrayList;

public class Cidade implements Comparable<Cidade>{
	private int id;
	private double x,y;
	private ArrayList<Integer> adjList;
	private double costTo = 0;
	private int fromID = 0;
	
	
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
	
	public ArrayList<Integer> getViz(){
		return adjList;
	}
	
	public void setCostTo(double cost){
		this.costTo = cost;
	}
	
	public double getCostTo(){
		return this.costTo;
	}
	
	public void setFromID(int fromID){
		this.fromID = fromID;
	}
	
	public int getFromID(){
		return fromID;
	}
	public int compareTo(Cidade outracidade){
		if(this.costTo < outracidade.getCostTo()){
			return -1;
		}
		if(this.costTo > outracidade.getCostTo()){
			return 1;
		}
		return 0;
	}
	

}
