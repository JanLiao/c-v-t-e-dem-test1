package gui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年6月19日 下午4:26:57 
*/
public class SplitTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		final SplitPane splitPane = new SplitPane();
	    splitPane.setOrientation(Orientation.HORIZONTAL);
	    splitPane.setDividerPositions(0.3f, 0.9f);
	    splitPane.getItems().add(new StackPane(new Label("Left")));
	    splitPane.getItems().add(new StackPane(new Label("Right")));
		final Scene scene = new Scene(splitPane, 800, 500, Color.WHITE);
		stage.setTitle("JFX ListView Demo ");
		scene.getStylesheets().add(ListViewDemo.class.getResource("jfoenix-components.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
		splitPane.lookupAll(".split-pane-divider").stream()
        .forEach(div ->  div.setMouseTransparent(true) );
	}

	public static void main(String[] args) {
		launch(args);
	}
}
