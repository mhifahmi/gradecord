package com.uts.gradecord.controller;

import com.uts.gradecord.model.Siswa;
import com.uts.gradecord.util.FileHandler;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {

    @FXML private TextField searchField;
    @FXML private TableView<Siswa> tableView;
    @FXML private TableColumn<Siswa, Number> colNo;
    @FXML private TableColumn<Siswa, String> colNim, colNama;
    @FXML private TableColumn<Siswa, Double> colMatematika, colIpa, colIndonesia, colRataRata;
    @FXML private TableColumn<Siswa, Void> colAction;
    @FXML private TextField totalNilaiField;
    @FXML private TextField totalRataField;

    private final ObservableList<Siswa> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        data.setAll(FileHandler.loadSiswa());
        setupTableColumns();
        tableView.setItems(data);
        updateTotalDanRataRata();
    }

    private void setupTableColumns() {
        colNo.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(cell.getValue()) + 1));
        colNim.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getNim()));
        colNama.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getNama()));
        colMatematika.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getMatematika()));
        colIpa.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getIpa()));
        colIndonesia.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getIndonesia()));
        colRataRata.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getRataRata()));

        colAction.setCellFactory(param -> createActionCell());
    }

    private TableCell<Siswa, Void> createActionCell() {
        return new TableCell<>() {
            private final Button btnEdit = new Button("Edit");
            private final Button btnDelete = new Button("Hapus");
            private final HBox pane = new HBox(5, btnEdit, btnDelete);

            {
                btnEdit.setOnAction(e -> openForm(getTableView().getItems().get(getIndex())));
                btnDelete.setOnAction(e -> onDelete(getIndex()));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        };
    }

//    Linear Search
    @FXML
    private void onSearch() {
        String keyword = searchField.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            data.setAll(FileHandler.loadSiswa());
            return;
        }

        List<Siswa> filtered = FileHandler.loadSiswa().stream()
                .filter(s -> s.getNama().toLowerCase().contains(keyword) || s.getNim().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        data.setAll(filtered);
    }

//    Binary Search
//    @FXML
//    private void onSearch() {
//    String keyword = searchField.getText().trim().toLowerCase();
//    if (keyword.isEmpty()) {
//        data.setAll(FileHandler.loadSiswa());
//        return;
//    }
//
//    List<Siswa> siswaList = new ArrayList<>(FileHandler.loadSiswa());
//
//    List<Siswa> result = new ArrayList<>();
//    if (keyword.matches("\\d+")) {
//        siswaList.sort(Comparator.comparing(s -> s.getNim().toLowerCase()));
//        Siswa found = binarySearchByNim(siswaList, keyword);
//        if (found != null) result.add(found);
//    } else {
//        siswaList.sort(Comparator.comparing(s -> s.getNama().toLowerCase()));
//        result = binarySearchByNama(siswaList, keyword);
//    }
//
//    data.setAll(result);
//}
//
//    private Siswa binarySearchByNim(List<Siswa> list, String targetNim) {
//        int left = 0, right = list.size() - 1;
//        while (left <= right) {
//            int mid = (left + right) / 2;
//            Siswa midSiswa = list.get(mid);
//            int cmp = midSiswa.getNim().compareToIgnoreCase(targetNim);
//            if (cmp == 0) return midSiswa;
//            else if (cmp < 0) left = mid + 1;
//            else right = mid - 1;
//        }
//        return null;
//    }
//
//    private List<Siswa> binarySearchByNama(List<Siswa> list, String keyword) {
//        List<Siswa> result = new ArrayList<>();
//        int left = 0, right = list.size() - 1;
//
//        while (left <= right) {
//            int mid = (left + right) / 2;
//            Siswa midSiswa = list.get(mid);
//            String nama = midSiswa.getNama().toLowerCase();
//
//            int cmp = nama.compareTo(keyword);
//            if (nama.contains(keyword)) {
//                // Cari ke kiri dan kanan
//                int l = mid;
//                while (l >= 0 && list.get(l).getNama().toLowerCase().contains(keyword)) {
//                    l--;
//                }
//                int r = mid;
//                while (r < list.size() && list.get(r).getNama().toLowerCase().contains(keyword)) {
//                    r++;
//                }
//                result.addAll(list.subList(l + 1, r));
//                break;
//            } else if (cmp < 0) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//
//        return result;
//    }

    @FXML
    private void onTambah(ActionEvent e) {
        openForm(null);
    }

    private void openForm(Siswa siswa) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uts/gradecord/form-view.fxml"));
            Scene scene = new Scene(loader.load());
            FormController controller = loader.getController();
            controller.setMainController(this);
            if (siswa == null){
                controller.addSingleForm(null);
            }else{
                controller.setSiswa(siswa);
            }

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(siswa == null ? "Tambah Data Siswa" : "Edit Data Siswa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void onDelete(int index) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin ingin menghapus data ini?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                data.remove(index);
                FileHandler.saveSiswa(data);
                updateTotalDanRataRata();
            }
        });
    }

    public void refreshTable() {
        data.setAll(FileHandler.loadSiswa());
        updateTotalDanRataRata();
    }

    private double hitungTotalNilaiRekursif(List<Siswa> siswaList, int index) {
        if (index >= siswaList.size()) {
            return 0;
        }
        Siswa s = siswaList.get(index);
        double total = s.getMatematika() + s.getIpa() + s.getIndonesia();
        return total + hitungTotalNilaiRekursif(siswaList, index + 1);
    }

    private void updateTotalDanRataRata() {
        List<Siswa> siswaList = tableView.getItems();
        double totalNilai = hitungTotalNilaiRekursif(siswaList, 0);
        int jumlahMapel = 3;
        int totalSiswa = siswaList.size();

        double rataRata = totalSiswa > 0 ? totalNilai / (totalSiswa * jumlahMapel) : 0;

        totalNilaiField.setText(String.format("%.2f", totalNilai));
        totalRataField.setText(String.format("%.2f", rataRata));
    }

}
