package task;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main extends Application {


    private GridPane root = new GridPane();


    private ObservableList<PieChart.Data> createChartData() {
        return FXCollections.observableArrayList(
                new PieChart.Data("Post-production age", 424236),
                new PieChart.Data("Production age", 1030060),
                new PieChart.Data("Pre-production age", 310319)
        );
    }

    private void createGrid(){
        int gridSize = 6 ;
        for (int i = 0 ; i < gridSize ; i++) {

            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / gridSize);
            rc.setValignment(VPos.BOTTOM);
            root.getRowConstraints().add(rc);

            ColumnConstraints cc = new ColumnConstraints();
            cc.setHalignment(HPos.CENTER);
            cc.setPercentWidth(100/gridSize);
            root.getColumnConstraints().add(cc);
        }

    }

    @Override
    public void start(Stage primaryStage) {

        createGrid();


        PieChart chart = new PieChart(createChartData());
        chart.setStartAngle(90.0);
        chart.setTitle("People od Warsaw:");


        primaryStage.setScene(new Scene(root, 800,800));
        primaryStage.show();
        primaryStage.setTitle("Economical division");
        primaryStage.setMinWidth(270);
        primaryStage.setMinHeight(270);


        EventHandler<MouseEvent> eventHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                e.consume();
                PieChart.Data removed = chart.getData().remove(chart.getData().size() - 1);
                chart.getData().add(0, new PieChart.Data(removed.getName(), removed.getPieValue()));
            }
        };

        root.add(chart,1,1 , 4, 4);
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
