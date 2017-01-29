package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Amineh.ahm on 6/4/2016 AD.
 */
public class SocialNetwork
{
    private Scanner nodesFile;
    private Scanner edgesFile;
    private Scanner seedFile;
    private Scanner lineReader;
    public int numberOfNodes;
    public int time;
    private ArrayList<Node> node;
    private ArrayList<DataEachTime> allData = new ArrayList<DataEachTime>();

    public SocialNetwork(String adrs)
    {
        this.numberOfNodes = 0;
        this.time = 0;

        //read nodes.txt:
        boolean nodesFound = true;
        try{
            nodesFile = new Scanner(new File(adrs + "/Nodes.txt"));
            lineReader = new Scanner(new File(adrs + "/Nodes.txt"));
        } catch(Exception e){
            nodesFound = false;
            System.out.println("Nodes.txt not found");
        }
        while (lineReader.hasNextLine() && nodesFound)
        {
            lineReader.nextLine();
            this.numberOfNodes++;
        }
        this.node = new ArrayList<Node>(numberOfNodes);

        while (nodesFile.hasNextLine() && nodesFound)
        {
            String line = nodesFile.nextLine();
            String[] lineWords = line.split("\\s+");
            int index = Integer.parseInt(lineWords[0]);
            String nodeClass = lineWords[1];
            double[] rate = new double[4];
            rate[0] = Double.parseDouble(lineWords[2]);
            rate[1] = Double.parseDouble(lineWords[3]);
            rate[2] = Double.parseDouble(lineWords[4]);
            rate[3] = Double.parseDouble(lineWords[5]);
            Node nextNode = new Node(index, nodeClass, rate);
            node.add(nextNode);
        }

        //read edges.txt:
        boolean edgesFound = true;
        try{
            edgesFile = new Scanner(new File(adrs + "/Edges.txt"));
        } catch(Exception e){
            edgesFound = false;
            System.out.println("Edges.txt not found");
        }
        while (edgesFile.hasNextLine() && edgesFound)
        {
            String line = edgesFile.nextLine();
            String[] lineWords = line.split("\\s+");
            int from = Integer.parseInt(lineWords[0]);
            int to = Integer.parseInt(lineWords[1]);
            double weight = Double.parseDouble(lineWords[2]);
            this.getNode(to).setEdge(from, weight, this);
        }

        //read seed.txt:
        boolean seedFound = true;
        try{
            seedFile = new Scanner(new File(adrs + "/Seed.txt"));
        } catch(Exception e){
            seedFound = false;
            System.out.println("Seed.txt not found");
        }
        while (seedFile.hasNextLine() && seedFound)
        {
            String line = seedFile.nextLine();
            String[] lineWords = line.split("\\s+");
            int newsType = Integer.parseInt(lineWords[0]) - 1;
            for (int i = 1; i < lineWords.length; i++)
            {
                this.getNode(Integer.parseInt(lineWords[i])).setHasNews(newsType);
            }
        }
        this.allData.add(new DataEachTime(this));
    }

    public void doTurn()
    {
        for (Node node: this.node)
        {
            node.getNewsFromFollowing();
        }
        for (Node node: this.node)
        {
            node.RefreshNews();
        }
        time++;
        this.allData.add(new DataEachTime(this));
    }

    public Node getNode(int index)
    {
        for (Node node: this.node)
        {
            if (node.getIndex() == index)
                return node;
        }
        return null;
    }

    public ArrayList<Node> getNode() {
        return node;
    }

    public ArrayList<DataEachTime> getAllData() {
        return allData;
    }

    public Map<String, DataEachClass> getLastData(){
        return this.allData.get(this.allData.size() - 1).getData();
    }
}
