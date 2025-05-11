package com.uts.gradecord.controller;

import com.uts.gradecord.model.Siswa;
import com.uts.gradecord.util.FileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormController {

    @FXML private VBox formContainer;
    @FXML private Button addRowBtn;
    @FXML private Label actionLabel;

    private Siswa siswa; // untuk mode edit
    private MainController mainController;

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    public void setSiswa(Siswa siswa) {
        this.siswa = siswa;
        if (siswa != null) {
            addSingleForm(siswa);
            addRowBtn.setVisible(false);
        }
    }

    @FXML
    public void initialize() {
//        if (siswa == null) {
//            addSingleForm(null);
//        }
    }

    @FXML
    private void onAddRow() {
        addSingleForm(null);
    }

    public void addSingleForm(Siswa data) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uts/gradecord/form-row.fxml"));
            Node node = loader.load();

            FormRowController rowController = loader.getController();
            rowController.setParentController(this);
            if (data != null) {
                rowController.setData(data);
                actionLabel.setVisible(false);
                rowController.setDeleteButtonVisible(false);
            }else{
                actionLabel.setVisible(true);
                rowController.setDeleteButtonVisible(true);
            }

            node.setUserData(rowController);
            formContainer.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeForm(Node node) {
        formContainer.getChildren().remove(node);
    }

    @FXML
    private void onSubmit() {
        List<Siswa> entries = new ArrayList<>();

        for (Node node : formContainer.getChildren()) {
            FormRowController controller = (FormRowController) node.getUserData();
            try {
                Siswa siswa = controller.getInputData();
                entries.add(siswa);
            } catch (IllegalArgumentException ex) {
                showAlert(ex.getMessage());
                return;
            }
        }

        List<Siswa> current = FileHandler.loadSiswa();
        if (siswa != null) {
            // Edit mode
            current.removeIf(s -> s.getNim().equals(siswa.getNim()));
            current.add(entries.getFirst());
        } else {
            for (Siswa s : entries) {
                current.removeIf(existing -> existing.getNim().equals(s.getNim()));
                current.add(s);
            }
        }

        FileHandler.saveSiswa(current);
        mainController.refreshTable();
        ((Stage) formContainer.getScene().getWindow()).close();
    }

    @FXML
    private void onCancel() {
        ((Stage) formContainer.getScene().getWindow()).close();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        alert.show();
    }
}
