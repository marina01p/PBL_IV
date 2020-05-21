package com.pbl.ladderlogic;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class Controller {
    @FXML private Spinner spn1;
    @FXML private Spinner spn2;
    @FXML private Spinner spn3;
    @FXML private Spinner spn4;
    @FXML private Spinner spn5;
    @FXML private Spinner spn6;
    @FXML private Spinner spn7;
    @FXML private Spinner spn8;
    @FXML private Button contact;
    @FXML private Button open;
    @FXML private Button closed;
    @FXML private Button coil;
    @FXML private Button active;
    @FXML private Button inactive;
    @FXML private TextField displayhere;
    @FXML private Button and;
    @FXML private Button or;
    @FXML private Button end;
    @FXML private ComboBox I;
    @FXML private ComboBox M;
    @FXML private ComboBox Q;
    @FXML private ComboBox M1;
    @FXML private TextArea output;
    @FXML private MenuItem close;
    @FXML private AnchorPane mainBorderPane;

    @FXML
    private void handleButtonAction(ActionEvent event) {


        if(event.getSource() == open){
            I.setDisable(false);
            M.setDisable(false);
            displayhere.setText(displayhere.getText() + "open contact ");
        }
        else if(event.getSource() == closed){
            I.setDisable(false);
            M.setDisable(false);
            displayhere.setText(displayhere.getText() + "closed contact ");
        }
        else if(event.getSource() == active){
            Q.setDisable(false);
            M1.setDisable(false);
            displayhere.setText(displayhere.getText() + "active coil ");
        }
        else if(event.getSource() == inactive){
            Q.setDisable(false);
            M1.setDisable(false);
            displayhere.setText(displayhere.getText() + "inactive coil ");
        }
        else if(event.getSource() == and){
            contact.setDisable(false);
            open.setDisable(false);
            closed.setDisable(false);
            coil.setDisable(false);
            active.setDisable(false);
            inactive.setDisable(false);
            and.setDisable(true);
            or.setDisable(true);
            end.setDisable(true);
            displayhere.setText(displayhere.getText() + " & ");
        }
        else if(event.getSource() == or){
            contact.setDisable(false);
            open.setDisable(false);
            closed.setDisable(false);
            coil.setDisable(false);
            active.setDisable(false);
            inactive.setDisable(false);
            and.setDisable(true);
            or.setDisable(true);
            end.setDisable(true);
            displayhere.setText(displayhere.getText() + " ) V ( ");
        }
        else if(event.getSource() == I){
            I.setDisable(true);
            M.setDisable(true);
            contact.setDisable(true);
            open.setDisable(true);
            closed.setDisable(true);
            and.setDisable(false);
            or.setDisable(false);
            end.setDisable(false);
            displayhere.setText(displayhere.getText() + I.getValue());
        }
        else if(event.getSource() == M){
            I.setDisable(true);
            M.setDisable(true);
            contact.setDisable(true);
            open.setDisable(true);
            closed.setDisable(true);
            and.setDisable(false);
            or.setDisable(false);
            end.setDisable(false);
            displayhere.setText(displayhere.getText() + M.getValue());
        }
        else if(event.getSource() == Q){
            Q.setDisable(true);
            M1.setDisable(true);
            contact.setDisable(true);
            open.setDisable(true);
            closed.setDisable(true);
            coil.setDisable(true);
            active.setDisable(true);
            inactive.setDisable(true);
            and.setDisable(false);
            or.setDisable(false);
            end.setDisable(false);
            displayhere.setText(displayhere.getText() + Q.getValue());
        }
        else if(event.getSource() == M1){
            Q.setDisable(true);
            M1.setDisable(true);
            contact.setDisable(true);
            open.setDisable(true);
            closed.setDisable(true);
            coil.setDisable(true);
            active.setDisable(true);
            inactive.setDisable(true);
            and.setDisable(false);
            or.setDisable(false);
            end.setDisable(false);
            displayhere.setText(displayhere.getText() + M1.getValue());
        }
        else if(event.getSource() == end){
            output.setText(output.getText());
            displayhere.setText("( " + displayhere.getText() + " )");
        }

        try {
            FileWriter fstream = new FileWriter("in.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("I " + spn1.getValue() + " " +
                    spn2.getValue() + " " +
                    spn3.getValue() + " " +
                    spn4.getValue() + " " +
                    spn5.getValue() + " " +
                    spn6.getValue() + " " +
                    spn7.getValue() + " " +
                    spn8.getValue() + "\n" + "start ( " + displayhere.getText() + " ) end");
            out.close();
        }

        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    @FXML
    private void handleButtonActionResult(ActionEvent event){
        File file = new File("out.txt");
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                output.appendText(input.nextLine());
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        if(event.getSource() == close){
            Platform.exit();
        }
    }

    @FXML
    public void showHelpDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Help");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("help.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the help dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            com.pbl.ladderlogic.HelpController controller = fxmlLoader.getController();
        }

    }

}


