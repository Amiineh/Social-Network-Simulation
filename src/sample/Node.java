package sample;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Amineh.ahm on 6/4/2016 AD.
 */
public class Node
{
    private int index;
    private String name = new String();
    double[] rate = new double[4];
    boolean[] shareNews = new boolean[4];
    boolean[] hasNews = new boolean[4];
    boolean[] hasNewsNext = new boolean[4];
    public ArrayList<Node> following = new ArrayList<Node>();
    public HashMap<Integer, Double> edgeWeight = new HashMap<Integer, Double>();

    public Node(int index, String name, double[] rate) {
        this.index = index;
        this.name = name;
        this.rate = rate;
        for (int i = 0; i < 4; i++)
        {
            shareNews[i] = Compute.isProbable(rate[i]);
            this.hasNews[i] = false;
            this.hasNewsNext[i] = false;
        }
    }

    public void getNewsFromFollowing ()
    {
        for (Node tempFollowing: this.following)
        {
            if (Compute.isProbable(Compute.Poisson(edgeWeight.get(tempFollowing.getIndex())) ) )
            {
                for (int j = 0; j < 4; j++)
                {
                    this.hasNewsNext[j] = (tempFollowing.hasNews[j] & tempFollowing.shareNews[j]) | this.hasNews[j];
                }
            }
        }
    }

    public void RefreshNews()
    {
        for (int i = 0; i < 4; i++)
        {
            this.hasNews[i] = this.hasNewsNext[i];
        }
    }

    public void setEdge(int neighbourIndex, double weight, SocialNetwork socialNetwork)
    {
        following.add(socialNetwork.getNode(neighbourIndex));
        edgeWeight.put(neighbourIndex, weight);
    }

    public void setHasNews(int newsType)
    {
        this.hasNews[newsType] = true;
        this.hasNewsNext[newsType] = true;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int HasNews(int index)
    {
        if (this.hasNews[index])
            return 1;
        return 0;
    }
}
