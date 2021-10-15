package com.zerox;

import com.zerox.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.net.URL;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 15:52
 * @Description:
 * @ModifiedBy: zhuxi
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class JavaFxApplication extends Application {
    private static final Logger logger = LoggerFactory.getLogger(JavaFxApplication.class);

    private static ConfigurableApplicationContext springContext;

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }

    public static void main(String[] args) {
        launch(JavaFxApplication.class, args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(JavaFxApplication.class);
        logger.info("JavaFxApplication inited!");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // path 不以’/'开头时，默认是从此类所在的包下取资源；
        // path 以’/'开头时，则是从 ClassPath 根下获取
//        URL resource = getClass().getResource("/com/zerox/fxml/mainView.fxml");
        URL resource = getClass().getResource("fxml/mainView.fxml");
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("javaFX MVC test");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        logger.info("JavaFxApplication started!");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        SpringApplication.exit(springContext);
        // SpringApplication.exit()底层调用链：
        // SpringApplication.exit() -> close()
        // -> AbstractApplicationContext.close() -> doClose()
        // -> DefaultLifecycleProcessor.onClose() -> stopBeans()
        // -> DefaultLifecycleProcessor.LifecycleGroup.stop()
        // -> DefaultLifecycleProcessor.doStop() -> stop()
        //
        // springContext.stop(); 对应的实现类调用链其实就是:
        // ConfigurableApplicationContext.stop() -> AbstractApplicationContext.stop() -> DefaultLifecycleProcessor.stop()
        //
        // 感觉还是用 SpringApplication.exit() 完善一点
        // 但 springContext.stop() 的日志里会多一行 Tomcat 停止的记录：Stopping service [Tomcat]
//        springContext.stop();
        logger.info("JavaFxApplication stopped!");
    }
}
