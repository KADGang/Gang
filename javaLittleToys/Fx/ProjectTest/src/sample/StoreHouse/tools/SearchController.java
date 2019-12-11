package sample.StoreHouse.tools;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.BObject.*;

import java.io.IOException;

public class SearchController {
    //l1~l7为成功查找后中BasicObject对象的各元素名称 lm1~lm7分别为l1~l7所对应元素的信息 两个RadioButton控件用以提供查询方式的切换
    @FXML Label l;
    @FXML Label l1;
    @FXML Label l2;
    @FXML Label l3;
    @FXML Label l4;
    @FXML Label l5;
    @FXML Label l6;
    @FXML Label l7;
    @FXML Label lm1;
    @FXML Label lm2;
    @FXML Label lm3;
    @FXML Label lm4;
    @FXML Label lm5;
    @FXML Label lm6;
    @FXML Label lm7;
    @FXML RadioButton rb1;
    @FXML RadioButton rb2;
    @FXML TextField tf;
    ToggleGroup tg = new ToggleGroup();

    ObservableList<BasicObject> temp;

    int selectInt = 1;

    //传入StoreHouseController的对象列表进行查找
    public void setBOList(ObservableList<BasicObject> BOb){
        this.temp = BOb;
    }

    //初始化查询方式
    @FXML
    public void initialize(){
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);

        rb1.setOnAction(event -> {
            if(rb1.isSelected()) {
                selectInt = 1;

                tf.setPromptText("请输入查找编号");
            }
        });

        rb2.setOnAction(event -> {
            if (rb2.isSelected()){
                selectInt = 2;

                tf.setPromptText("请输入查找名称");
            }
        });
    }

    //绑定查询按钮的点击事件
    public void handleSearch(ActionEvent event) throws IOException {
        if (selectInt == 1){
            for (BasicObject key: temp){
                if (key.getIdentifier().equals(tf.getText())) {
                    showBOb(key);
                    return;
                }
            }
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("查询失败");
            warning.setHeaderText("找不到编号为" + tf.getText() + "的物品");

            warning.showAndWait();
        }else {
            for (BasicObject key: temp){
                if (key.getTitle().equals(tf.getText())) {
                    showBOb(key);
                    return;
                }
            }
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("查询失败");
            warning.setHeaderText("找不到标题为" + tf.getText() + "的物品");

            warning.showAndWait();
        }
    }

    //显示查询到的对象信息
    public void showBOb(BasicObject BOb){
        l.setText("物品信息");
        l1.setText("编号");
        l2.setText("标题");
        l3.setText("作者");
        l4.setText("分级");

        lm1.setText(BOb.getIdentifier());
        lm2.setText(BOb.getTitle());
        lm3.setText(BOb.getAuthor());
        lm4.setText(BOb.getGrade());

        if (BOb instanceof book){
            lm5.setText(((book) BOb).getPublisher());
            lm6.setText(((book) BOb).getISBN());
            lm7.setText(((book) BOb).getPages());

            l5.setText("出版社");
            l6.setText("ISBN");
            l7.setText("页数");
        }else if (BOb instanceof videoCD){
            lm5.setText(((videoCD) BOb).getPublisher());
            lm6.setText(((videoCD) BOb).getProductionYear());
            lm7.setText(((videoCD) BOb).getDuration());

            l5.setText("出品者");
            l6.setText("出品年份");
            l7.setText("音频时长");
        }else if (BOb instanceof painting){
            lm5.setText(((painting) BOb).getNationality());
            lm6.setText(((painting) BOb).getPslength());
            lm7.setText(((painting) BOb).getPswidth());

            l5.setText("出品国籍");
            l6.setText("长");
            l7.setText("宽");
        }
        System.out.println(BOb.getIdentifier() + "\n" + BOb.getTitle() + "\n" + BOb.getAuthor() + "\n");
    }
}
