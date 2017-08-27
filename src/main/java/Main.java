import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	private static ArrayList<Cidade> cidades = new ArrayList<Cidade>();
	
	public static void main(String[] args){
		readData();
		printData();
		//BFSAlgorithm BFS = new BFSAlgorithm(cidades);
		//BFS.run(203, 600);
		
		AStarAlgorithm Star = new AStarAlgorithm(cidades);
		Star.run(203, 600);
	}
	
	
	private static void readData(){
		String csvFile = "C:\\Users\\Ana Cuder\\workspace\\PROJETO1\\Uruguay.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        Cidade aux;
        
        //Inicializa com cidade "fantasma"
        cidades.add(new Cidade(0,0,0));

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use ponto e virgula as separator
            	// Trocar vigulas por pontos
            	line = line.replaceAll(",",".");
                String[] city = line.split(cvsSplitBy);
                aux = new Cidade(Integer.parseInt(city[0]), Double.parseDouble(city[1]), Double.parseDouble(city[2]));
                for(int i=3; i < city.length-1; i++)
                	aux.addCity(city[i]);
                cidades.add(aux);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	private static void printData(){
		for(int i = 1; i < cidades.size(); i++){
			Cidade aux = cidades.get(i);
			System.out.println("ID = "+String.valueOf(aux.getID())+";  X = "+String.valueOf(aux.getX())+";  Num viz = "+String.valueOf(aux.getNumViz()));
		}
	}
}
