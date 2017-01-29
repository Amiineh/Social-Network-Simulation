package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Map;

public class Main extends Application {
    SocialNetwork socialNetwork;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("ESP - 94102647");
        primaryStage.setScene(new Scene(root, 700, 200));

        Text intro = new Text("Please enter the full address of your files. The address should contain Nodes.txt, Edges.txt, and Seed.txt.\nIf there are more than 1 news, please include the information in separate lines in file Seed.txt.\nPress done to continue to the next page.");
        intro.setLayoutX(5);
        intro.setLayoutY(20);
        root.getChildren().add(intro);

        Text adrsTxt = new Text("Address: ");
        adrsTxt.setLayoutX(20);
        adrsTxt.setLayoutY(80);
        root.getChildren().add(adrsTxt);

        TextField adrsInput = new TextField("enter full address");
        adrsInput.setLayoutX(100);
        adrsInput.setLayoutY(62);
        adrsInput.setMinWidth(400);
        root.getChildren().add(adrsInput);

        Button done = new Button("Done");
        done.setLayoutX(600);
        done.setLayoutY(132);
        done.setVisible(true);
        root.getChildren().add(done);

        Group tempGroup = new Group();
        done.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String adrs = adrsInput.getText();
                socialNetwork = new SocialNetwork(adrs);
                primaryStage.setScene(new Scene(tempGroup, 650, 500));
            }
        });

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("time");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Informed users");

        Button nextTurn = new Button("50 turns forward");
        nextTurn.setLayoutX(0);
        nextTurn.setLayoutY(400);
        tempGroup.getChildren().add(nextTurn);

        nextTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int i = 0; i < 50; i++)
                   socialNetwork.doTurn();
                LineChart lineChart = new LineChart(xAxis, yAxis);
                VBox vbox = new VBox(lineChart);
                vbox.getChildren().add(nextTurn);
                primaryStage.setTitle("ESP - 94102647");
                primaryStage.setScene(new Scene(vbox, 650, 500));

                Map<String, DataEachClass> tempMap = socialNetwork.getLastData();
                for (int newsType = 0; newsType < 4; newsType++) {
                    for (String name: tempMap.keySet())
                    {
                        String key = name.toString();
                        XYChart.Series dataSeries1 = new XYChart.Series();
                        for (int j = 0; j < socialNetwork.time; j++) {
                            DataEachClass tempDataEachClass = socialNetwork.getAllData().get(j).getData().get(key);
                            dataSeries1.getData().add(new XYChart.Data(j, tempDataEachClass.newsType[newsType]));
                        }
                        dataSeries1.setName(key + " with news type " + (newsType + 1));
                        lineChart.getData().add(dataSeries1);
                    }
                }
            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
