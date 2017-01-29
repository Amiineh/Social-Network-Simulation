package sample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amineh.ahm on 6/4/2016 AD.
 */
public class DataEachTime
{
    private Map<String, DataEachClass> data = new HashMap<String, DataEachClass>();
    public int time;

    public DataEachTime(SocialNetwork socialNetwork)
    {
        this.time = socialNetwork.time;
        DataEachClass dataForAll = new DataEachClass("All");
        data.put("All", dataForAll);
        for (int newsType = 0; newsType < 4; newsType++) {
            int allValue = 0;
            for (Node node : socialNetwork.getNode()) {
                int hasNews = node.HasNews(newsType);
                if (data.containsKey(node.getName())) {
                    data.get(node.getName()).newsType[newsType] += hasNews;
                } else {
                    System.out.println(node.getName() + newsType);
                    DataEachClass dataForThisNode = new DataEachClass(node.getName());
                    dataForThisNode.newsType[newsType] += hasNews;
                    data.put(node.getName(), dataForThisNode);
                }
                allValue += hasNews;
            }
            data.get("All").newsType[newsType] += allValue;
        }


//        System.out.println("Time: " + socialNetwork.time);
//        for (String name: data.keySet())
//        {
//            String key =name.toString();
//            String value = data.get(name).toString();
//            System.out.println(key + " " + value);
//        }
    }

    public Map<String, DataEachClass> getData() {
        return data;
    }

}
