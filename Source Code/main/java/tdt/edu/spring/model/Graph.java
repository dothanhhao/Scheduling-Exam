package tdt.edu.spring.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

	private class Vertex{
		
		private String node;
		private List<String> adj;
		
		public Vertex(String node){
			this.node = node;
			adj = new ArrayList<String>();
		}
		
		public String getNode(){
			return this.node;
		}
		
		public List<String> getAdj(){
			return this.adj;
		}
		
		public void setNode(String node){
			this.node  = node;
		}
		
		public void addAdj(String adjVertex){
			this.getAdj().add(adjVertex);
		}

		@Override
		public String toString() {
			return "Vertex [node=" + node + ", adj=" + adj + "]";
		}
		
		
	}

	private int numVertex;
	private Vertex[] adj;

	public Graph(ArrayList<String> listIDSubject){
		this.numVertex = listIDSubject.size();
		this.adj = new Vertex[this.numVertex];
		
		for(int i = 0; i < this.adj.length; i++){
			this.adj[i] = new Vertex(listIDSubject.get(i));
		}
		
	}
	
	@Override
	public String toString() {
		return "Graph [numVertex=" + numVertex + ", adj=" + Arrays.toString(adj) + "]";
	}

	public void addEdge(int row, int col){
		if(!this.adj[row].getAdj().contains(this.adj[col].getNode()))
			this.adj[row].addAdj(this.adj[col].getNode());
		if(!this.adj[col].getAdj().contains(this.adj[row].getNode()))
			this.adj[col].addAdj(this.adj[row].getNode());
	}
	
	
	public int getNumVertex(){
		return this.numVertex;
	}
	
	public void setNumVertex(int numVertex){
		this.numVertex = numVertex;
	}

	public String getNodeVertex(int index) {
		return this.adj[index].getNode();
	}
	
	public int getDegreeVertex(int index){
		return this.adj[index].getAdj().size();
	}
	
	public String getadj(int index){
		return this.adj[index].getAdj().toString();
	}
	
	/*
	 * Sort graph base on degree of vertex
	 */
	public void sortDegree(){
		for(int i = 0; i < this.adj.length; i++){
			int t = i;
			for(int j = i + 1; j< this.adj.length; j++){
				if(this.adj[t].getAdj().size() < this.adj[j].getAdj().size())
					t = j;
			}
			if(t != i){
				Vertex tmp = this.adj[i];
				this.adj[i] = this.adj[t];
				this.adj[t] = tmp;
			}
		}
	}
	
	/*
	 * Coloring graph using welsh-powell algorithm
	 */
	public int[] graphColoring(){
		
		int[] colors = new int[this.adj.length];
		
		for(int i = 0 ; i < colors.length; i++)
			colors[i] = -1;
		
		int color = 0;
		for(int i = 0 ; i < this.adj.length; i++){
			if(colors[i] == -1){
				colors[i] = color;
				for(int j = 0 ; j < this.adj.length; j++){
					if(!this.adj[i].getAdj().contains(this.adj[j].getNode()) && colors[j] == -1){
						boolean checkColors = true;
						for(int index = 0 ; index < this.adj[j].getAdj().size(); index++){
							if(getIndexNode(this.adj[j].getAdj().get(index)) != -1 
									&& colors[getIndexNode(this.adj[j].getAdj().get(index))] == color){
								checkColors = false;
								break;
							}
						}
						if(checkColors == true)	colors[j] = color;
					}
				}
				color++;
			}
		}
		return colors;
	}
	
	/*
	 * Return index of node that is contained in Adj
	 */
	private int getIndexNode(String node){
		for(int i = 0 ; i < this.adj.length; i++){
			if(this.adj[i].getNode().equals(node))
				return i;
		}
		return -1;
	}
	
}
