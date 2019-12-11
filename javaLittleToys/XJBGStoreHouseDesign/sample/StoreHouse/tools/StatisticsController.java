package sample.StoreHouse.tools;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import sample.BObject.*;

//统计使用BarChart条形图控件进行统计
public class StatisticsController {

    @FXML private BarChart<String, Integer> barChart;
    @FXML private CategoryAxis xAxis;

    //进行条形图横轴元素的初始化用的容器
    private ObservableList<String> classnames = FXCollections.observableArrayList();

    //进行条形图横轴元素与容器的绑定
    @FXML
    private void initialize() {
        classnames.add("图书");
        classnames.add("音频");
        classnames.add("书画");

        xAxis.setCategories(classnames);
    }

    //传入对象列表进行相关信息的统计及建立横纵轴元素映射与条形图的绑定
    public void setBObjectData(ObservableList<BasicObject> BOb) {
        int[] classCounter = new int[3];
        for (BasicObject key : BOb) {
            if (key instanceof book){
                classCounter[0]++;
            }else if (key instanceof videoCD){
                classCounter[1]++;
            }else if (key instanceof painting){
                classCounter[2]++;
            }

        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (int i = 0; i < classCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(classnames.get(i), classCounter[i]));
        }

        barChart.getData().add(series);
    }
}
