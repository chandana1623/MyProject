import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NumberOperationsWithAIApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField numberInput1 = new TextField();
        numberInput1.setPromptText("Enter first number");

        TextField numberInput2 = new TextField();
        numberInput2.setPromptText("Enter second number");

        styleTextField(numberInput1);
        styleTextField(numberInput2);

        Button addButton = createStyledButton("Add");
        Button subtractButton = createStyledButton("Subtract");
        Button multiplyButton = createStyledButton("Multiply");
        Button divideButton = createStyledButton("Divide");
        Button squareButton = createStyledButton("Square First");

        Label resultLabel = new Label("Result will appear here.");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #333;");

        // AI Input: natural language command input field
        TextField aiInput = new TextField();
        aiInput.setPromptText("Type command like 'add 5 7' or 'square 9'");
        styleTextField(aiInput);
        Button aiProcessButton = createStyledButton("Process AI Command");

        // Existing buttons event handlers (same as before)
        addButton.setOnAction(e -> calculateAndShow(numberInput1, numberInput2, "add", resultLabel));
        subtractButton.setOnAction(e -> calculateAndShow(numberInput1, numberInput2, "subtract", resultLabel));
        multiplyButton.setOnAction(e -> calculateAndShow(numberInput1, numberInput2, "multiply", resultLabel));
        divideButton.setOnAction(e -> calculateAndShow(numberInput1, numberInput2, "divide", resultLabel));
        squareButton.setOnAction(e -> calculateAndShow(numberInput1, null, "square", resultLabel));

        // AI process button action
        aiProcessButton.setOnAction(e -> {
            String command = aiInput.getText().trim().toLowerCase();
            if(command.isEmpty()) {
            resultLabel.setText("Please enter a command.");
            return;
        }
        String[] parts = command.split("\\s+");
        try {
                String op = parts[0];
                double a, b;
            switch (op) {
                case "add":
                    if (parts.length != 3) throw new IllegalArgumentException();
                    a = Double.parseDouble(parts[1]);
                    b = Double.parseDouble(parts[2]);
                    resultLabel.setText("Sum: " + (a + b));
                    break;
                case "subtract":
                    if (parts.length != 3) throw new IllegalArgumentException();
                    a = Double.parseDouble(parts[1]);
                    b = Double.parseDouble(parts[2]);
                    resultLabel.setText("Difference: " + (a - b));
                    break;
                case "multiply":
                    if (parts.length != 3) throw new IllegalArgumentException();
                    a = Double.parseDouble(parts[1]);
                    b = Double.parseDouble(parts[2]);
                    resultLabel.setText("Product: " + (a * b));
                    break;
                case "divide":
                    if (parts.length != 3) throw new IllegalArgumentException();
                    a = Double.parseDouble(parts[1]);
                    b = Double.parseDouble(parts[2]);
                    if (b == 0) resultLabel.setText("Cannot divide by zero.");
                    else resultLabel.setText("Quotient: " + (a / b));
                    break;
                case "square":
                    if (parts.length != 2) throw new IllegalArgumentException();
                    a = Double.parseDouble(parts[1]);
                    resultLabel.setText("Square: " + (a * a));
                    break;
                default:
                    resultLabel.setText("Unknown command.");
            }
        } catch (Exception ex) {
            resultLabel.setText("Invalid command format.");
        }
    });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 30; -fx-background-color: #e8f0fe;");

        HBox inputBox = new HBox(15, numberInput1, numberInput2);
        inputBox.setAlignment(Pos.CENTER);

        HBox buttonBox1 = new HBox(15, addButton, subtractButton);
        buttonBox1.setAlignment(Pos.CENTER);

        HBox buttonBox2 = new HBox(15, multiplyButton, divideButton, squareButton);
        buttonBox2.setAlignment(Pos.CENTER);

        HBox aiBox = new HBox(15, aiInput, aiProcessButton);
        aiBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(inputBox, buttonBox1, buttonBox2, aiBox, resultLabel);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Number Operations with AI Command");
        primaryStage.setScene(scene);
        primaryStage.show();
}

    private void calculateAndShow(TextField n1, TextField n2, String operation, Label label) {
    try {
            double a = Double.parseDouble(n1.getText());
            double b = (n2 != null) ? Double.parseDouble(n2.getText()) : 0;
        switch (operation) {
            case "add": label.setText("Sum: " + (a + b)); break;
            case "subtract": label.setText("Difference: " + (a - b)); break;
            case "multiply": label.setText("Product: " + (a * b)); break;
            case "divide":
                if (b == 0) label.setText("Cannot divide by zero.");
                else label.setText("Quotient: " + (a / b));
                break;
            case "square": label.setText("Square: " + (a * a)); break;
        }
    } catch (NumberFormatException e) {
        label.setText("Invalid input. Please enter valid numbers.");
    }
}

    private void styleTextField(TextField textField) {
    textField.setStyle("-fx-font-size: 14px; -fx-background-radius: 5px; -fx-padding: 5 10;");
    textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
        if(newVal) {
            textField.setEffect(new DropShadow(5, Color.LIGHTBLUE));
        } else {
            textField.setEffect(null);
        }
    });
}

    private Button createStyledButton(String text) {
        Button button = new Button(text);
    button.setStyle("-fx-background-color: #4285f4; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;");
    button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3367d6; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
    button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4285f4; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
    button.setPrefWidth(150);
    button.setEffect(new DropShadow(3, Color.GRAY));
    return button;
}

    public static void main(String[] args) {
    launch(args);
}
}
