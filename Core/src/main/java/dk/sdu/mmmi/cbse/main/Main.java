package dk.sdu.mmmi.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {


    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        for(String beanName : context.getBeanDefinitionNames()){
            System.out.println(beanName);
        }

        GameLoop game = context.getBean(GameLoop.class);
        game.start(window);
        game.render();
    }
}
