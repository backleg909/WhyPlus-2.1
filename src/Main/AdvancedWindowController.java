package Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class AdvancedWindowController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

        @FXML
        private TextField frictionalVelocityField;

        @FXML
        private TextField skinFrictionCoeffField;

        @FXML
        private TextField wallShearStressField;

    public void setTextFrictionalVelocity(String frictionalVelocityText) {

        frictionalVelocityField.setText(frictionalVelocityText);
    }
    public void setTextSkinFrictionCoefficient(String skinFrictionCoefficientText) {

        skinFrictionCoeffField.setText(skinFrictionCoefficientText);
    }
    public void setWallShearStressField(String wallShearStressText) {

        wallShearStressField.setText(wallShearStressText);
    }
}
