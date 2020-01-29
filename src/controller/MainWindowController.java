package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainWindowController {
    private Main main;
    private Stage primaryStage;
    private String file = "src/data/file.txt";
    private String reportFile = "src/data/reportFile.txt";

    private ObservableList<Employee> employeesList =
            FXCollections.observableArrayList();

    private List<Rectangle> rooms = new ArrayList<>();

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
    private Button reportButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField roomNumberTextField;
    @FXML
    private TextField workStartHourTextField;
    @FXML
    private TextField workEndHourTextField;

    @FXML
    private Rectangle room1;
    @FXML
    private Rectangle room2;
    @FXML
    private Rectangle room3;
    @FXML
    private Rectangle room4;
    @FXML
    private Rectangle room5;
    @FXML
    private Rectangle room6;
    @FXML
    private Rectangle room7;
    @FXML
    private Rectangle room8;
    @FXML
    private Rectangle room9;
    @FXML
    private Rectangle room10;
    @FXML
    private Rectangle room11;
    @FXML
    private Rectangle room12;
    @FXML
    private Rectangle room13;
    @FXML
    private Rectangle room14;
    @FXML
    private Rectangle room15;

    public void initialize() {
        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("firstName"));
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<Employee, String>("lastName"));
        roomNumberColumn.setCellValueFactory(
                new PropertyValueFactory<Employee, Integer>("roomNumber"));

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        rooms.add(room7);
        rooms.add(room8);
        rooms.add(room9);
        rooms.add(room10);
        rooms.add(room11);
        rooms.add(room12);
        rooms.add(room13);
        rooms.add(room14);
        rooms.add(room15);


        tableView.getSelectionModel().selectedItemProperty().
                addListener((ov, oldVal, newVal) -> {
                    firstNameTextField.setText(newVal.getFirstName());
                    lastNameTextField.setText(newVal.getLastName());
                    roomNumberTextField.setText(String.valueOf(newVal.getRoomNumber()));
                    workStartHourTextField.setText(String.valueOf(newVal.getWorkStartHour()));
                    workEndHourTextField.setText(String.valueOf(newVal.getWorkEndHour()));

                    if (newVal.getRoomNumber() <= rooms.size()) {
                        rooms.get(newVal.getRoomNumber() - 1).setFill(Color.RED);
                        try {
                            if (!newVal.getRoomNumber().equals(oldVal.getRoomNumber())
                                    && oldVal.getRoomNumber() <= rooms.size()) {
                                rooms.get(oldVal.getRoomNumber() - 1).setFill(Color.rgb(30, 150, 255));
                            }
                        } catch (NullPointerException e) {
                        }
                    }
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
                employeesList.add(
                        new Employee(oneLine[0],
                                oneLine[1],
                                Integer.parseInt(oneLine[2]),
                                Integer.parseInt(oneLine[3]),
                                Integer.parseInt(oneLine[4])));
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

    public void saveReportFile() {

        List<Employee> list = new ArrayList<>();
        list.addAll(employeesList);

        PrintWriter printer = null;

        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee one, Employee two) {
                return one.getWorkTime() < two.getWorkTime() ? -1 : (one.getWorkTime() > two.getWorkTime()) ? 1 : 0;
            }
        });

        try {
            printer = new PrintWriter(reportFile);

            for (Employee e : list) {
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
        tableView.setItems(employeesList);

    }

    @FXML
    public void handleLoadButton() {
        employeesList.clear();
        openDataFile();
    }

    @FXML
    public void handleSaveButton() {
        saveDataFile();
    }

    @FXML
    public void handleAddButton() {
        System.out.println("Add Button pressed.");

        if (!firstNameTextField.getText().isEmpty() &&
                !lastNameTextField.getText().isEmpty() &&
                !roomNumberTextField.getText().isEmpty() &&
                !workStartHourTextField.getText().isEmpty() &&
                !workEndHourTextField.getText().isEmpty()) {

            employeesList.add(new Employee(
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    Integer.parseInt(roomNumberTextField.getText()),
                    Integer.parseInt(workStartHourTextField.getText()),
                    Integer.parseInt(workEndHourTextField.getText()))
            );
        }
    }

    @FXML
    public void handleReportButton() {
        saveReportFile();
    }


}
