package com.uts.gradecord.controller;

import com.uts.gradecord.model.Siswa;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FormRowController {

    @FXML private TextField nimField, namaField, matField, ipaField, indoField;
    @FXML private Button deleteButton;
    private FormController parentController;

    public void setParentController(FormController controller) {
        this.parentController = controller;
        ((Node) nimField.getParent()).setUserData(this); // Simpan referensi controller
    }

    public void setData(Siswa siswa) {
        nimField.setText(siswa.getNim());
        namaField.setText(siswa.getNama());
        matField.setText(String.valueOf(siswa.getMatematika()));
        ipaField.setText(String.valueOf(siswa.getIpa()));
        indoField.setText(String.valueOf(siswa.getIndonesia()));
    }

    public Siswa getInputData() {
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        if (nim.isEmpty() || nama.isEmpty()) throw new IllegalArgumentException("NIM dan Nama wajib diisi.");

        try {
            double mat = Double.parseDouble(matField.getText().trim());
            double ipa = Double.parseDouble(ipaField.getText().trim());
            double indo = Double.parseDouble(indoField.getText().trim());
            return new Siswa(nim, nama, mat, ipa, indo);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Nilai harus berupa angka.");
        }
    }

    @FXML
    private void onDelete() {
        parentController.removeForm((Node) nimField.getParent());
    }

    public void setDeleteButtonVisible(boolean visible) {
        deleteButton.setVisible(visible);
    }
}
