module com.uts.gradecord {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.uts.gradecord to javafx.fxml;
    exports com.uts.gradecord;

    exports com.uts.gradecord.controller to javafx.fxml;
    exports com.uts.gradecord.model;
    exports com.uts.gradecord.util;

    opens com.uts.gradecord.controller to javafx.fxml;
}