package com.zerox;

import com.zerox.engine.KeyPolling;
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

    private static String fxml;
    private static Class<?> clazz;

    private ConfigurableApplicationContext springContext;

    public void gameStart(String[] args) {
        logger.info("gameStart()");
        launch(JavaFxApplication.class, args);
    }

    public JavaFxApplication() {
    }

    public JavaFxApplication(String fxml, Class<?> clazz) {
        JavaFxApplication.fxml = fxml;
        JavaFxApplication.clazz = clazz;
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
//        URL fxmlUrl = clazz.getResource("/com/zerox/" + fxml);
        URL fxmlUrl = clazz.getResource(fxml);
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        KeyPolling.getInstance().pollScene(scene);

        primaryStage.setScene(scene);
        // 不可改变窗口大小尺寸
        primaryStage.setResizable(false);
        primaryStage.setTitle("javaFX game");
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
