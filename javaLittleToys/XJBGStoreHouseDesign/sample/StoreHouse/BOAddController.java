package sample.StoreHouse;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.BObject.*;

import java.io.IOException;

public class BOAddController {
    @FXML RadioButton rb1;
    @FXML RadioButton rb2;
    @FXML RadioButton rb3;
    ToggleGroup tg = new ToggleGroup();
    @FXML Label l1;
    @FXML Label l2;
    @FXML Label l3;
    @FXML TextField idField;
    @FXML TextField titleField;
    @FXML TextField authorField;
    @FXML TextField gradeField;
    @FXML TextField tf1;
    @FXML TextField tf2;
    @FXML TextField tf3;
    BasicObject BOtest = new BasicObject();

    //传入StoreHouseController类的列表以进行编号查重
    ObservableList<BasicObject> BOb;

    int selectInt = 1;
    boolean addOK = false;

    public void setBOb(ObservableList<BasicObject> temp){
        this.BOb = temp;
    }

    //使用RadioButton控件提供添加物品种类的选择方式并进行初始化
    @FXML
    public void initialize(){
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);

        rb1.setOnAction(event -> {
            if(rb1.isSelected()) {
                selectInt = 1;

                l1.setText("出版社");
                tf1.setPromptText("输入新物品出版社");
                l2.setText("ISBN");
                tf2.setPromptText("输入新物品ISBN");
                l3.setText("页数");
                tf3.setPromptText("输入新物品页数");
            }
        });

        rb2.setOnAction(event -> {
            if (rb2.isSelected()){
                selectInt = 2;

                l1.setText("出品者");
                tf1.setPromptText("输入新物品出品者");
                l2.setText("出品年份");
                tf2.setPromptText("输入新物品出品年份");
                l3.setText("音频时长");
                tf3.setPromptText("输入新物品音频时长");
            }
        });

        rb3.setOnAction(event -> {
            if(rb3.isSelected()){
                selectInt = 3;

                l1.setText("出品国籍");
                tf1.setPromptText("输入新物品出品国籍");
                l2.setText("长");
                tf2.setPromptText("输入新物品长");
                l3.setText("宽");
                tf3.setPromptText("输入新物品宽");
            }
        });
    }

    //绑定界面文件中确定并保存按钮的点击事件
    @FXML
    public void handleOK(ActionEvent event) throws IOException {
        System.out.println(selectInt);
        if (legalInput()){
            if (selectInt == 1){
                BOtest = new book(idField.getText(),
                        titleField.getText(),
                        authorField.getText(),
                        gradeField.getText(),
                        tf1.getText(),
                        tf2.getText(),
                        tf3.getText()
                );
            } else if (selectInt == 2){
                BOtest = new videoCD(idField.getText(),
                        titleField.getText(),
                        authorField.getText(),
                        gradeField.getText(),
                        tf1.getText(),
                        tf2.getText(),
                        tf3.getText()
                );
            } else if (selectInt == 3){
                BOtest = new painting(idField.getText(),
                        titleField.getText(),
                        authorField.getText(),
                        gradeField.getText(),
                        tf1.getText(),
                        tf2.getText(),
                        tf3.getText()
                );
            }
            addOK = true;

            Stage stage = (Stage) rb1.getScene().getWindow();
            stage.close();
        }else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("新建错误");
            error.setHeaderText("请正确填写物品信息");

            error.showAndWait();
        }
    }

    //用于将新创建的对象传回StoreHouseController类
    public BasicObject returnBObject(){
        return BOtest;
    }

    //判断输入信息是否合法
    public boolean legalInput(){
        if (idField.getText().equals("") || titleField.getText().equals("") || authorField.getText().equals("") || gradeField.getText().equals("") || tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("")){
            return false;
        }else{
            for (BasicObject key: BOb){
                if (key.getIdentifier().equals(idField.getText())){
                    return false;
                }
            }
        }
        return true;
    }

    //绑定界面文件中取消按钮的点击事件
    @FXML
    public void handleCancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}
