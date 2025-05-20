package GUI;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.*;

public class InterestTableGUI extends Application {
	private TextArea displayArea;
	private TextField credits, costPerCredit;
	private Slider slider;
	
	@Override /* Method in Application class */
	public void start(Stage primaryStage) {
		int sceneWidth = 600, sceneHeight = 300;
		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
		
		/* Setting pane properties */
		FlowPane pane = new FlowPane();
		pane.setHgap(horSpaceBetweenNodes);
		pane.setVgap(verSpaceBetweenNodes);
		pane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
					    paneBorderBottom, paneBorderLeft));
		
		/* Adding GUI elements */
		displayArea = new TextArea();
		displayArea.setPrefSize(sceneWidth -40, sceneHeight * 3/5);
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		ScrollPane scrollPane = new ScrollPane(displayArea);
		pane.getChildren().addAll(scrollPane);
		
		Label creditsLabel = new Label("Principal:");
		credits = new TextField();
		pane.getChildren().addAll(creditsLabel, credits);
		
		Label costPerCreditLabel = new Label("Rate(Percentage):");
		costPerCredit = new TextField();
		pane.getChildren().addAll(costPerCreditLabel, costPerCredit);
		
		Label sliderLabel = new Label("Number of Years:");
		slider = new Slider();
		slider.setMin(1);
		slider.setMax(25);
		slider.setValue(1);
		slider.setMajorTickUnit(4);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		pane.getChildren().addAll(sliderLabel, slider);

		Button button1 = new Button("SimpleInterest");
		button1.setOnAction(new ButtonHandler());
		Button button2 = new Button("CompoundInterest");
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				double creditsValue = Double.parseDouble(credits.getText());
				double costPerCreditValue = Double.parseDouble(costPerCredit.getText());
				displayArea.setText(InterestTableCalculations.compoundInterest
						(creditsValue, costPerCreditValue, slider.getValue()));
			}
		});
		Button button3 = new Button("BothInterest");
		button3.setOnAction(e -> {
			double creditsValue = Double.parseDouble(credits.getText());
			double costPerCreditValue = Double.parseDouble(costPerCredit.getText());
			displayArea.setText(InterestTableCalculations.bothInterest
					(creditsValue, costPerCreditValue, slider.getValue()));
		});
		pane.getChildren().add(button1);
		pane.getChildren().add(button2);
		pane.getChildren().add(button3);
		
		/* Display the stage */
		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			double creditsValue = Double.parseDouble(credits.getText());
			double costPerCreditValue = Double.parseDouble(costPerCredit.getText());
			displayArea.setText(InterestTableCalculations.simpleInterest
					(creditsValue, costPerCreditValue, slider.getValue()));
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
