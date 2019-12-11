package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeViewController {

    //此方法作为HomeView.fxml中按钮控件的点击事件绑定行为 将在登录按钮被按下时被调用
    @FXML
    protected void ckTest(ActionEvent event) throws IOException {
        Stage storeHouse = new Stage();
        //使用FXMLLoader类加载StoreHouse包下StoreHouse.fxml布局文件完成图书馆仓库管理界面的加载
        AnchorPane sH = FXMLLoader.load(getClass().getResource("StoreHouse/StoreHouse.fxml"));
        storeHouse.setTitle("仓库管理页面");
        storeHouse.setScene(new Scene(sH));
        //由此进入仓库管理界面 其逻辑控制包含在StoreHouseController类中
        storeHouse.show();
        //本来还有个关闭伪登录界面的逻辑 懒得写了
    }
}
