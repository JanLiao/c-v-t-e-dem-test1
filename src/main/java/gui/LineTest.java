package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年6月19日 下午5:14:14 
*/
public class LineTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		StackPane pane = new StackPane(new Label("Right"));
		Line LV1 = LineBuilder.create()
	            .strokeWidth(2)
	            .stroke(Color.FORESTGREEN)
	            .build();
		
		pane.getChildren().add(LV1);
		final Scene scene = new Scene(pane, 800, 500, Color.WHITE);
		stage.setTitle("JFX ListView Demo ");
		scene.getStylesheets().add(ListViewDemo.class.getResource("jfoenix-components.css").toExternalForm());
		stage.setScene(scene);
		//stage.setResizable(false);
		stage.show();
		LV1.setStartX(100);
		LV1.setEndX(100);
		LV1.setStartY(0);
		LV1.setEndY(scene.getHeight());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
