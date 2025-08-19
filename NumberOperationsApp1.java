import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NumberOperationsApp1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Input fields
        TextField numberInput1 = new TextField();
        numberInput1.setPromptText("Enter first number");

        TextField numberInput2 = new TextField();
        numberInput2.setPromptText("Enter second number");

        styleTextField(numberInput1);
        styleTextField(numberInput2);

        // Buttons
        Button addButton = createStyledButton("Add");
        Button subtractButton = createStyledButton("Subtract");
        Button multiplyButton = createStyledButton("Multiply");
        Button divideButton = createStyledButton("Divide");
        Button squareButton = createStyledButton("Square First");

        // Result label
        Label resultLabel = new Label("Result will appear here.");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #333;");

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
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30; -fx-background-color: #e8f0fe;");

        HBox inputBox = new HBox(15, numberInput1, numberInput2);
        inputBox.setAlignment(Pos.CENTER);

        HBox buttonBox1 = new HBox(15, addButton, subtractButton);
        buttonBox1.setAlignment(Pos.CENTER);

        HBox buttonBox2 = new HBox(15, multiplyButton, divideButton, squareButton);
        buttonBox2.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(inputBox, buttonBox1, buttonBox2, resultLabel);

        // Scene and Stage
        Scene scene = new Scene(layout, 500, 300);
        primaryStage.setTitle("Number Operations with Effects");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Adds basic style and glow to TextFields
    private void styleTextField(TextField textField) {
        textField.setStyle("-fx-font-size: 14px; -fx-background-radius: 5px; -fx-padding: 5 10;");
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                textField.setEffect(new DropShadow(5, Color.LIGHTBLUE));
            } else {
                textField.setEffect(null);
            }
        });
    }

    // Creates a styled button with hover effects
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #4285f4; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;");
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #3367d6; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #4285f4; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));

        button.setPrefWidth(100);
        button.setEffect(new DropShadow(3, Color.GRAY));

        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
