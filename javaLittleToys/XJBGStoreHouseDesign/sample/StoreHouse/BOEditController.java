package sample.StoreHouse;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.BObject.*;

import java.io.IOException;

public class BOEditController {
    @FXML private TextField idField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField gradeField;
    @FXML private TextField tf1;
    @FXML private TextField tf2;
    @FXML private TextField tf3;
    @FXML private Label l1;
    @FXML private Label l2;
    @FXML private Label l3;

    //传入StoreHouseController类的列表以进行编号查重
    ObservableList<BasicObject> BObList;

    public boolean saved = false;
    private BasicObject BOb;

    public void setBOb(ObservableList<BasicObject> temp){
        this.BObList = temp;
    }

    //传入需要编辑的对象的相关信息
    public void setBObject(BasicObject bob){
        this.BOb = bob;

        idField.setText(BOb.getIdentifier());
        titleField.setText(BOb.getTitle());
        authorField.setText(BOb.getAuthor());
        gradeField.setText(BOb.getGrade());
        if (BOb instanceof book){
            tf1.setText(((book) BOb).getPublisher());
            tf2.setText(((book) BOb).getISBN());
            tf3.setText(((book) BOb).getPages());

            tf1.setPromptText("输入新出版社");
            tf2.setPromptText("输入新ISBN");
            tf3.setPromptText("输入新页数");

            l1.setText("出版社");
            l2.setText("ISBN");
            l3.setText("页数");
        }else if (BOb instanceof videoCD){
            tf1.setText(((videoCD) BOb).getPublisher());
            tf2.setText(((videoCD) BOb).getProductionYear());
            tf3.setText(((videoCD) BOb).getDuration());

            tf1.setPromptText("输入新出品者");
            tf2.setPromptText("输入新出品年份");
            tf3.setPromptText("输入新音频时长");

            l1.setText("出品者");
            l2.setText("出品年份");
            l3.setText("音频时长");
        }else if (BOb instanceof painting){
            tf1.setText(((painting) BOb).getNationality());
            tf2.setText(((painting) BOb).getPslength());
            tf3.setText(((painting) BOb).getPswidth());

            tf1.setPromptText("输入新出品国籍");
            tf2.setPromptText("输入新长");
            tf3.setPromptText("输入新宽");

            l1.setText("出品国籍");
            l2.setText("长");
            l3.setText("宽");
        }
    }

    //绑定界面文件中确定并保存按钮的点击事件
    @FXML
    public void handleOK(ActionEvent event) throws IOException {
        if (legalInput()){
            BOb.setIdentifier(idField.getText());
            BOb.setTitle(titleField.getText());
            BOb.setAuthor(authorField.getText());
            BOb.setGrade(gradeField.getText());

            if (BOb instanceof book){
                ((book) BOb).setPublisher(tf1.getText());
                ((book) BOb).setISBN(tf2.getText());
                ((book) BOb).setPages(tf3.getText());
            }else if (BOb instanceof videoCD){
                ((videoCD) BOb).setPublisher(tf1.getText());
                ((videoCD) BOb).setProductionYear(tf2.getText());
                ((videoCD) BOb).setDuration(tf3.getText());
            }else if (BOb instanceof painting){
                ((painting) BOb).setNationality(tf1.getText());
                ((painting) BOb).setPslength(tf2.getText());
                ((painting) BOb).setPswidth(tf3.getText());
            }

            saved = true;
            Stage stage = (Stage) idField.getScene().getWindow();
            stage.close();
        }else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("编辑错误");
            error.setHeaderText("请正确填写物品信息");
            error.setContentText("请检查编号是否重复以及是否有空项");

            error.showAndWait();
        }

    }

    //绑定界面文件中取消按钮的点击事件
    @FXML
    public void handleCancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }

    //此方法用于判断输入是否合法
    public boolean legalInput(){
        if (idField.getText().equals("") || titleField.getText().equals("") || authorField.getText().equals("") || gradeField.getText().equals("") || tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("")){
            return false;
        }else{
            for (BasicObject key: BObList){
                if (key.getIdentifier().equals(idField.getText())){
                    return false;
                }
            }
        }
        return true;
    }
}