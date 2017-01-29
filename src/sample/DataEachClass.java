package sample;

/**
 * Created by Amineh.ahm on 6/13/2016 AD.
 */
public class DataEachClass {
    public int[] newsType = new int[4];
    public String name = new String();

    public DataEachClass(String name)
    {
        this.name = name;
        for (int i = 0; i < 4; i++)
            this.newsType[i] = 0;
    }
}
