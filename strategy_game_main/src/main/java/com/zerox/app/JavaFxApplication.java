package com.zerox.app;

import com.zerox.app.components.TestController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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

    private ConfigurableApplicationContext springContext;

    private static TestController testController;

    public static void main(String[] args) {
        launch(JavaFxApplication.class, args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(JavaFxApplication.class);
        testController = springContext.getBean(TestController.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Button button = new Button(testController.test().getBody());
        an.getChildren().add(button);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
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
