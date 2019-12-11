//此类为主类 作为整个程序的入口 整个程序均采用fxml布局＋controller的结构编写 将BObject包下的基类BasicObject以及其派生类实例化后进行一系列操作

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//继承应用类
public class Main extends Application {

    @Override
    //start函数为整个程序入口
    public void start(Stage primaryStage) throws Exception{
        //使用FXMLLoader类加载HomeView.fxml布局文件完成伪登录界面的加载
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        primaryStage.setTitle("XJBG图书馆媒体库管理系统 Beta 1.0");
        primaryStage.setScene(new Scene(root));
        //随便找张图设置为此窗体的图标
        Image image= new Image(this.getClass().getResource("test.png").toString(), 100, 150, false, false);
        primaryStage.getIcons().add(image);
        //由此进入整个程序 加载界面后开始调用伪登录界面文件 HomeView.fxml 的Controller类 HomeViewController
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
