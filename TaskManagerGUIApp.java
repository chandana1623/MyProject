import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerGUIApp extends Application {

    private DoublyTaskList taskList = new DoublyTaskList();
    private ListView<String> taskView = new ListView<>();
    private Label statusLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TextField nameInput = new TextField();
        nameInput.setPromptText("Task name");

        TextField priorityInput = new TextField();
        priorityInput.setPromptText("Priority (1-5)");

        Button addBtn = new Button("Add Task");
        Button deleteBtn = new Button("Delete Task");
        Button completeBtn = new Button("Mark Completed");
        Button statsBtn = new Button("Show Statistics");

        TextField aiCommandInput = new TextField();
        aiCommandInput.setPromptText("Type: add Buy milk priority 2");
        Button aiCommandBtn = new Button("Run AI Command");

        // Handlers
        addBtn.setOnAction(e -> {
            String name = nameInput.getText();
            String prioStr = priorityInput.getText();
            try {
                int p = Integer.parseInt(prioStr);
                taskList.addTask(name, p);
                statusLabel.setText("Task added.");
                refreshTaskView();
            } catch (NumberFormatException ex) {
                statusLabel.setText("Invalid priority.");
            }
        });

        deleteBtn.setOnAction(e -> {
            taskList.deleteTask(nameInput.getText());
            statusLabel.setText("Delete attempted.");
            refreshTaskView();
        });

        completeBtn.setOnAction(e -> {
            taskList.markTaskCompleted(nameInput.getText());
            statusLabel.setText("Complete attempted.");
            refreshTaskView();
        });

        statsBtn.setOnAction(e -> {
            showStatisticsDialog();
        });

        aiCommandBtn.setOnAction(e -> {
            String result = processAICommand(aiCommandInput.getText());
            statusLabel.setText(result);
            refreshTaskView();
        });

        HBox inputBox = new HBox(10, nameInput, priorityInput);
        HBox buttonBox = new HBox(10, addBtn, deleteBtn, completeBtn, statsBtn);
        VBox aiBox = new VBox(5, aiCommandInput, aiCommandBtn);

        VBox root = new VBox(15, inputBox, buttonBox, aiBox, taskView, statusLabel);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 450);
        stage.setScene(scene);
        stage.setTitle("AI Task Manager");
        stage.show();
    }

    private void refreshTaskView() {
        taskView.getItems().clear();
        for (String s : taskList.getTaskDisplayStrings()) {
            taskView.getItems().add(s);
        }
    }

    private void showStatisticsDialog() {
        int[] stats = taskList.getStats();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Task Statistics");
        alert.setHeaderText("Here are your task statistics:");
        alert.setContentText("Total tasks: " + stats[0] +
                "\nCompleted: " + stats[1] +
                "\nPending: " + stats[2]);
        alert.showAndWait();
    }

    private String processAICommand(String cmd) {
        cmd = cmd.toLowerCase().trim();

        if (cmd.startsWith("add ")) {
            try {
                String[] parts = cmd.split("priority");
                if (parts.length == 2) {
                    String taskName = parts[0].substring(4).trim();
                    int prio = Integer.parseInt(parts[1].trim());
                    taskList.addTask(taskName, prio);
                    return "Task added via AI command.";
                }
            } catch (Exception e) {
                return "Invalid add command format.";
            }
        } else if (cmd.startsWith("delete ")) {
            String taskName = cmd.substring(7).trim();
            taskList.deleteTask(taskName);
            return "Delete command executed.";
        } else if (cmd.startsWith("complete ")) {
            String taskName = cmd.substring(9).trim();
            taskList.markTaskCompleted(taskName);
            return "Complete command executed.";
        } else if (cmd.equals("stats") || cmd.contains("show stats")) {
            showStatisticsDialog();
            return "Showing stats.";
        }

        return "Unknown command.";
    }
}
