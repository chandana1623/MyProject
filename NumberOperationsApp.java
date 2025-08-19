import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NumberOperationsApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Input fields
        TextField numberInput1 = new TextField();
        numberInput1.setPromptText("Enter first number");

        TextField numberInput2 = new TextField();
        numberInput2.setPromptText("Enter second number");

        // Buttons
        Button addButton = new Button("Add");
        Button subtractButton = new Button("Subtract");
        Button multiplyButton = new Button("Multiply");
        Button divideButton = new Button("Divide");
        Button squareButton = new Button("Square First");

        // Result label
        Label resultLabel = new Label("Result will appear here.");

        // Event handlers
        addButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(numberInput1.getText());
                double b = Double.parseDouble(numberInput2.getText());
                resultLabel.setText("Sum: " + (a + b));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter valid numbers.");
            }
        });

        subtractButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(numberInput1.getText());
                double b = Double.parseDouble(numberInput2.getText());
                resultLabel.setText("Difference: " + (a - b));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter valid numbers.");
            }
        });

        multiplyButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(numberInput1.getText());
                double b = Double.parseDouble(numberInput2.getText());
                resultLabel.setText("Product: " + (a * b));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter valid numbers.");
            }
        });

        divideButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(numberInput1.getText());
                double b = Double.parseDouble(numberInput2.getText());
                if (b == 0) {
                    resultLabel.setText("Cannot divide by zero.");
                } else {
                    resultLabel.setText("Quotient: " + (a / b));
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter valid numbers.");
            }
        });

        squareButton.setOnAction(e -> {
            try {
                double a = Double.parseDouble(numberInput1.getText());
                resultLabel.setText("Square: " + (a * a));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a valid number.");
            }
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        HBox inputBox = new HBox(10, numberInput1, numberInput2);
        HBox buttonBox1 = new HBox(10, addButton, subtractButton);
        HBox buttonBox2 = new HBox(10, multiplyButton, divideButton, squareButton);

        layout.getChildren().addAll(inputBox, buttonBox1, buttonBox2, resultLabel);

        // Scene and Stage
        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setTitle("Number Operations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
