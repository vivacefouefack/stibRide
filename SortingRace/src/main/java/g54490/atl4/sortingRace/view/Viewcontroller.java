package g54490.atl4.sortingRace.view;

import g54490.atl4.sortingRace.model.CreateAllThread;
import g54490.atl4.sortingRace.model.Data;
import g54490.atl4.sortingRace.observers.Observer;
import g54490.atl4.sortingRace.model.Threade;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class Viewcontroller implements Initializable, Observer {

    @FXML
    private MenuItem quitItem;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private TableView<Data> table;

    @FXML
    private TableColumn<Data, String> nameCol;

    @FXML
    private TableColumn<Data, Integer> sizeCol;

    @FXML
    private TableColumn<Data, Integer> swapCol;

    @FXML
    private TableColumn<Data, Integer> durationCol;

    @FXML
    private LineChart<Number, Number> chart;

    @FXML
    private Spinner<Integer> threadSpinner;

    @FXML
    private ChoiceBox<String> sortChoice;

    @FXML
    private ChoiceBox<String> configurationChoice;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private NumberAxis axisY;

    @FXML
    private NumberAxis axisX;

    //@FXML private Button start;
    @FXML
    private Label leftStatus;

    @FXML
    private Font x3;
    @FXML
    private Color x4;
    @FXML
    private Label rightStatus;
    private XYChart.Series merge;
    private XYChart.Series bubble;
    private int taille;
    private List<Threade> allThread;

    public ChoiceBox<String> getConfigurationChoice() {
        return configurationChoice;
    }

    @FXML
    private void start(ActionEvent event) {
        
        if(configurationChoice.getValue()=="easy : 0-1 000"){
            taille=1000;
        }
        if(configurationChoice.getValue()=="very easy : 0-100"){
            taille=100;
        }

        CreateAllThread top=new CreateAllThread(this,threadSpinner.getValue(),sortChoice.getValue(), taille);
    }

    public Viewcontroller() {

        this.nameCol = new TableColumn<>("");
        this.sizeCol = new TableColumn<>("");
        this.swapCol = new TableColumn<>("");
        this.durationCol = new TableColumn<>("");
        this.table = new TableView<>();
        this.progressBar = new ProgressBar(0.0);
        this.axisX = new NumberAxis();
        this.axisY = new NumberAxis();
        this.chart = new LineChart<Number, Number>(axisX, axisY);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.nameCol.setCellValueFactory(new PropertyValueFactory<Data, String>("tri"));
        this.sizeCol.setCellValueFactory(new PropertyValueFactory<Data, Integer>("taille"));
        this.swapCol.setCellValueFactory(new PropertyValueFactory<Data, Integer>("operation"));
        this.durationCol.setCellValueFactory(new PropertyValueFactory<Data, Integer>("duree"));
        final int beginValue = 1;
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, beginValue);
        this.threadSpinner.setValueFactory(valueFactory);
        this.sortChoice.getItems().setAll("BUBBLE", "MERGE");
        this.configurationChoice.getItems().setAll("easy : 0-1 000", "very easy : 0-100");
        this.merge = new XYChart.Series();
        this.bubble = new XYChart.Series();

    }
    
    private void updateTableView(Threade thread) {
        this.table.getItems().add(new Data(thread.getSortchoice(), thread.getSize(),thread.getNboperation(),thread.getDuration().toMillis()));
    }

    private void startProcess(Threade thread) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateProgress(thread.getSize(), taille);
                Thread.sleep(10);
                return null;
            }
        };
        this.progressBar.progressProperty().unbind();
        this.progressBar.progressProperty().bind(task.progressProperty());
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
    
    private void ConstructLineChart(Threade thread) {
        chart.setAnimated(false);
        XYChart.Data bubbleData;
        XYChart.Data mergeData;

        if (sortChoice.getValue()=="BUBBLE") {
            bubbleData = new XYChart.Data(thread.getSize(), thread.getNboperation());
            merge.getData().add(bubbleData);
        }
        if (sortChoice.getValue()=="MERGE"){
            mergeData = new XYChart.Data(thread.getSize(), thread.getNboperation());
            bubble.getData().add(mergeData);
        }

        if (!chart.getData().isEmpty()) {
            chart.getData().clear();
        }
        chart.getData().addAll(merge, bubble);
    }

    @Override
    public void update(Threade t) {
        updateTableView(t);
        ConstructLineChart(t);
        startProcess(t);
    }
}
