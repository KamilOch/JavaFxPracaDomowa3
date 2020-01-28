package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MainWindowController {
    private Main main;
    private Stage primaryStage;
    private String file = "src/data/file.txt";

    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, Integer> roomNumberColumn;
    @FXML
    private Button loadButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button addButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField roomNumberTextField;

    private ObservableList<Employee> employeesList =
            FXCollections.observableArrayList();

    private void setTable() {
        employeesList.add(new Employee("Charlie", "Brown", 10));
        employeesList.add(new Employee("Jan", "Kowalski", 20));
        employeesList.add(new Employee("Homer", "Simpson", 40));
        employeesList.add(new Employee("Stefan", "Nowak", 30));
    }

    public void initialize() {
        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("firstName"));
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("lastName"));
        roomNumberColumn.setCellValueFactory(
                new PropertyValueFactory<Employee, Integer>("roomNumber"));

        tableView.getSelectionModel().selectedItemProperty().
                addListener((ov, oldVal, newVal) -> {

                    firstNameTextField.setText(newVal.getFirstName());
                    lastNameTextField.setText(newVal.getLastName());
                    roomNumberTextField.setText(String.valueOf(newVal.getRoomNumber()));
                    System.out.println(newVal.getFirstName() + " " +
                            newVal.getLastName());
                });
    }

    public void openDataFile() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] oneLine;
            while ((line = bufferedReader.readLine()) != null) {
                oneLine = line.split("\\s+");
                employeesList.add(new Employee(oneLine[0], oneLine[1], Integer.parseInt(oneLine[2])));
            }
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDataFile() {
        PrintWriter printer = null;

        try {
            printer = new PrintWriter(file);

            for (Employee e : employeesList) {
                printer.println(e.toString());
            }
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setMain(Main main, Stage primaryStage) {
        this.main = main;
        this.primaryStage = primaryStage;
        // setTable();
        tableView.setItems(employeesList);

    }

    @FXML
    public void handleLoadButton() {
        System.out.println("Load Button pressed.");
        //setTable();
        openDataFile();
    }

    @FXML
    public void handleSaveButton() {
        System.out.println("Save Button pressed.");
        saveDataFile();
    }

    @FXML
    void handleAddButton() {
        System.out.println("Add Button pressed.");

    }

}
