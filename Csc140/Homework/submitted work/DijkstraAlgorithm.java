import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;


public class DijkstraAlgorithm {

    public static void main(String[] args) {
        int numOfVertexs;
        int numOfEdges;
        Scanner reader;
        int max = 1;

        try {
            reader = new Scanner(new FileReader(args[0]));
            numOfVertexs = reader.nextInt();
            numOfEdges = reader.nextInt();

            ArrayList<Node> graph = new ArrayList<>(numOfVertexs);
            for(int i = 0; i < numOfVertexs; i++){
                graph.add(new Node(i));
            }

            int from;
            int to;
            int weight;

            while(reader.hasNextInt()){
                from = reader.nextInt();
                to = reader.nextInt();
                weight = reader.nextInt();

                graph.get(from).addEdge(to,weight);
                max += weight;
            }

            dijkstra(graph, max);

            writeOutput(numOfVertexs, graph, max);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dijkstra(ArrayList<Node> graph, int max){
        for(int i = 0; i < graph.size(); i++){
            Node temp = graph.get(i);
            if(i == 0){
                temp.setDistance(0);
                continue;
            }
            temp.setDistance(max);
        }

        PriorityQueue<Node> q = new PriorityQueue<>(graph.size(), new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getDistance() - o2.getDistance();
            }
        });

        for(Node node: graph)
            q.add(node);

        while (!q.isEmpty()){
            Node u = q.poll();
            HashMap<Integer, Integer> adjVertexs = u.getAdjVertexs();

            for (int i = 0; i < graph.size(); i++) {
                if(!adjVertexs.containsKey(i))
                    continue;
                Node v = graph.get(i);
                int previousAmount = u.getDistance() + adjVertexs.get(i);
                if( previousAmount < v.getDistance()){
                    v.setDistance(previousAmount);
                    v.setParent(u.getVertexNum());

                    //This is done as Priority Queue wont update unless this is here
                    q.remove(v);
                    q.add(v);
                }
            }
        }
    }

    public static void writeOutput(int numOfVertexs, ArrayList<Node> graph, int max) throws IOException {
        FileWriter writer = new FileWriter("output.txt");

        writer.write(String.format("Vertex     "));

        for(int i = 0; i < numOfVertexs; i++){
            writer.write(String.format("%-10d ", i));
        }

        writer.write("\n");

        writer.write(String.format("Distance   "));

        for(int i = 0; i < numOfVertexs; i++){
            Node temp = graph.get(i);
            int tempDistance = temp.getDistance();
            if(tempDistance == max)
                writer.write(String.format("%-10s ", "infinity"));
            else
                writer.write(String.format("%-10d ", tempDistance));
        }

        writer.write("\n");

        writer.close();
    }
}


class Node {
    private int parent;
    private int distance;
    private int vertexNum;
    private HashMap<Integer, Integer> adjVertexs;

    public Node(int vertexNum){
        this.vertexNum = vertexNum;
        this.adjVertexs = new HashMap<>();
    }

    public int getVertexNum(){
        return this.vertexNum;
    }

    public int getDistance(){
        return this.distance;
    }

    public void setParent(int newParentindex){
        this.parent = newParentindex;
    }

    public void setDistance(int newDistance){
        this.distance = newDistance;
    }

    public void addEdge(int to, int weight){
        adjVertexs.put(to, weight);
    }

    public HashMap<Integer,Integer> getAdjVertexs(){
        return adjVertexs;
    }
}
