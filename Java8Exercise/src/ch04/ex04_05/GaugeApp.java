package ch04.ex04_05;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GaugeApp extends Application {

	@Override
	public void start(Stage stage) {
		HBox hBox = new HBox(10);
		BorderPane gaugePane = new BorderPane();
		Button smaller = new Button("Smaller");
		Button larger = new Button("Larger");
		Rectangle gauge = new Rectangle(100, 20);
		
		smaller.setOnAction(event -> {
			double val = gauge.widthProperty().get();
			gauge.widthProperty().set(val - 4);
		});
		smaller.disableProperty().bind(observe(
				t -> t.doubleValue() <= 0, gauge.widthProperty()));

		
		larger.setOnAction(event -> {
			double val = gauge.widthProperty().get();
			gauge.widthProperty().set(val + 4);
		});
		larger.disableProperty().bind(observe(
				t -> t.doubleValue() >= 100, gauge.widthProperty()));
		
		gaugePane.setMinWidth(120);
		gaugePane.setPadding(new Insets(2));
		gaugePane.setLeft(gauge);
		
		
		hBox.getChildren().addAll(smaller, gaugePane, larger);
		hBox.setPadding(new Insets(5));
		stage.setScene(new Scene(hBox));
		stage.setTitle("ex04_05");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t){
		return Bindings.createObjectBinding(() -> f.apply(t.getValue()), t);
	}
	
	public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u){
		return Bindings.createObjectBinding(() -> f.apply(t.getValue(), u.getValue()), t, u);
	}
	
}
