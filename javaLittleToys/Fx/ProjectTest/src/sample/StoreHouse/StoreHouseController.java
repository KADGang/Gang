package sample.StoreHouse;

//此Controller类为仓库管理界面的绑定控制类

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.BObject.*;
import sample.StoreHouse.tools.SearchController;
import sample.StoreHouse.tools.StatisticsController;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class StoreHouseController {
    //此页面内左侧为一个存储类型为BasicObject的TableView控件 右边为15个Label控件 其中一个为页面标题 未作绑定 其余中l1~l7为响应表中BasicObject对象的各元素名称 lm1~lm7分别为l1~l7所对应元素的信息 这14个Label及添加、删除、编辑按钮和MenuBar中各选项控件均在布局文件中进行了相应的fx:id绑定及行为绑定
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
    @FXML private TableView<BasicObject> bo;

    //boData为一个基类的列表容器 内容绑定TableView控件
    private ObservableList<BasicObject> boData = FXCollections.observableArrayList();

    //提供一个其他类获取当前基类列表容器的方法供其他类或方法调用
    public ObservableList<BasicObject> getBoData(){
        return boData;
    }

    //窗体创建之前根据Preferences的状态进行TableView以及监听表行为的初始化
    public void initialize() {
        this.bo.setItems(getBoData());
        if (getBObjectFilePath() != null) {
            this.loadBObjectDataFromFile(getBObjectFilePath());
        }
        bo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBObjectDetails(newValue));
    }

    //依靠对监听表传回对象类型的判断初始化物品详情面板的元素名称及内容的初始化
    @FXML
    public void showBObjectDetails(BasicObject BOb){
        if (BOb != null){
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
        }
        else{
            lm1.setText("");
            lm2.setText("");
            lm3.setText("");
            lm4.setText("");
            lm5.setText("");
            lm6.setText("");
            lm7.setText("");
        }
    }

    //此方法绑定删除按钮的点击事件
    @FXML
    public void deleteBasicObject(ActionEvent event) throws IOException{
        int selectIndex = bo.getSelectionModel().getSelectedIndex();
        if (selectIndex >= 0){
            bo.getItems().remove(selectIndex);
        }
        else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("没有选择物品");
            error.setHeaderText("没有物品被选中！");
            error.setContentText("请在删除前先选择物品");

            error.showAndWait();
        }
    }

    //此方法绑定添加按钮的点击事件
    @FXML
    public void addBasicObject(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BOAdd.fxml"));

        Stage BOAdd = new Stage();
        AnchorPane boa = loader.load();
        BOAddController ctrler = loader.getController();
        ctrler.setBOb(boData);
        BOAdd.setTitle("物品添加");
        BOAdd.setScene(new Scene(boa));
        BOAdd.showAndWait();

        if(ctrler.addOK){
            boData.add(ctrler.returnBObject());
        }
    }

    //此方法绑定删除按钮的点击事件
    @FXML
    public void editBasicObject(ActionEvent event) throws IOException{
        int selectIndex = bo.getSelectionModel().getSelectedIndex();
        if (selectIndex >= 0){
            BasicObject selectBO = bo.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BOEdit.fxml"));

            Stage BOEdit = new Stage();
            AnchorPane boe = loader.load();
            BOEditController ctrler = loader.getController();
            ctrler.setBObject(selectBO);
            ctrler.setBOb(boData);
            BOEdit.setTitle("物品编辑");
            BOEdit.setScene(new Scene(boe));
            BOEdit.showAndWait();

            if (ctrler.saved)
                bo.refresh();
        }
        else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("没有选择物品");
            error.setHeaderText("没有物品被选中！");
            error.setContentText("请在编辑前先选择物品");

            error.showAndWait();
        }
    }

    //此方法绑定为MenuBar中 工具 -> 统计 的功能
    @FXML
    protected void BObjectStatistics(ActionEvent event) throws IOException {
        FXMLLoader tjloader = new FXMLLoader(getClass().getResource("tools/Statistics.fxml"));
        AnchorPane tj = tjloader.load();
        StatisticsController ctrler = tjloader.getController();

        Stage statistics = new Stage();
        statistics.setTitle("物品库统计页面");
        statistics.setScene(new Scene(tj));
        ctrler.setBObjectData(boData);
        statistics.show();
    }

    //此方法绑定为MenuBar中 工具 -> 查找 的功能
    @FXML
    protected void SearchBObject(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tools/Search.fxml"));
        AnchorPane sc = loader.load();
        SearchController ctrler = loader.getController();

        Stage SearchStage = new Stage();
        SearchStage.setTitle("查找页面");
        SearchStage.setScene(new Scene(sc));
        ctrler.setBOList(boData);
        SearchStage.show();
    }

    //此方法绑定为MenuBar中 帮助 -> 关于 的功能
    @FXML
    protected void AboutPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("About.fxml"));
        AnchorPane ab = loader.load();

        Stage statistics = new Stage();
        statistics.setTitle("傻了吧");
        statistics.setScene(new Scene(ab));
        statistics.show();
    }

    //获取Preferences中上次关闭应用时的读取文件状态
    public File getBObjectFilePath(){
        Preferences preferences = Preferences.userNodeForPackage(StoreHouseController.class);
        String filePath = preferences.get("filePath",null);
        if (filePath != null){
            return new File(filePath);
        }else {
            return null;
        }
    }

    //初始化打开文件时的窗体状态以及修改Preferences状态
    public void setBObjectFilePath(File file){
        Preferences preferences = Preferences.userNodeForPackage(StoreHouseController.class);
        if (file != null){
            preferences.put("filePath",file.getPath());

            Stage stage = (Stage)bo.getScene().getWindow();
            stage.setTitle("仓库管理页面 - " + file.getName());
        }else{
            preferences.remove("filePath");

            Stage stage = (Stage)bo.getScene().getWindow();
            stage.setTitle("仓库管理页面");
        }
    }

    //此方法绑定为MenuBar中 文件 -> 打开 的功能
    public void loadBObjectDataFromFile(File file){
        Preferences preferences = Preferences.userNodeForPackage(StoreHouseController.class);
        try{
            JAXBContext context = JAXBContext.newInstance(BObjectListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            BObjectListWrapper wrapper = (BObjectListWrapper) um.unmarshal(file);

            boData.clear();
            boData.addAll(wrapper.getBasicObjects());

        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("读取错误");
            error.setHeaderText("无法加载文件:"+file.getPath());
            error.setContentText("文件不存在或被损坏，即将新建文件。");
            preferences.remove("filePath");

            error.showAndWait();
        }
    }

    //此方法绑定为MenuBar中 文件 -> 保存 的功能
    public void saveBObjectDataToFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(BObjectListWrapper.class);
            Marshaller m = context.createMarshaller();

            BObjectListWrapper wrapper = new BObjectListWrapper();
            wrapper.setBasicObjects(boData);

            m.marshal(wrapper,file);

            setBObjectFilePath(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    //弹出文件选择对话框 设置文件筛选器
    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML文件(*.xml)","*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(bo.getScene().getWindow());
        this.setBObjectFilePath(file);

        if (file != null){
            this.loadBObjectDataFromFile(file);
        }
    }

    //弹出文件保存对话框
    @FXML
    private void handleSave(){
        File BObjectFile = this.getBObjectFilePath();
        if (BObjectFile != null){
            this.saveBObjectDataToFile(BObjectFile);
        }else{
            handleSaveAs();
        }
    }

    //弹出文件另存为对话框
    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML文件 (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(bo.getScene().getWindow());

        if (file != null){
            if (!file.getPath().endsWith(".xml")){
                file = new File(file.getParent()+".xml");
            }
            this.saveBObjectDataToFile(file);
        }
    }
}