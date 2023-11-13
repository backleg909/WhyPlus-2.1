package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import static Main.AllCalculationMethods.*;
import static Main.GrowthRate.*;

public class PrismLayerWindowController implements Initializable {

    @FXML
    private Text firstLayerThicknessText;
    @FXML
    private Text finalLayerThicknessResultText;
    @FXML
    private Text firstLayerThicknessResultText;
    @FXML
    private Text totalThicknessText;
    @FXML
    private Text totalThicknessUnit;
    @FXML
    private Text firstLayerThicknessResultUnit;
    @FXML
    private Text firstLayerThicknessUnit;
    @FXML
    private Text finalLayerThicknessResultUnit;
    @FXML
    private Text totalThicknessResultText;
    @FXML
    private Text numberOfLayersText;
    @FXML
    private Text growthRateText;
    @FXML
    private Text finalLayerThicknessText;
    @FXML
    private Text finalLayerThicknessUnit;
    @FXML
    private Text totalThicknessResultUnit;
    @FXML
    private Text growthRateResultText;
    @FXML
    private Text prismPropertiesWarningText;
    @FXML
    private TextField totalThicknessField;
    @FXML
    private TextField finalLayerThicknessResultField;
    @FXML
    private TextField firstLayerThicknessField;
    @FXML
    private TextField firstLayerThicknessResultField;
    @FXML
    private TextField totalThicknessResultField;
    @FXML
    private TextField growthRateResult;
    @FXML
    private Button calculatePrismLayerButton;
    @FXML
    private MenuButton prismLayerOption;
    @FXML
    private Spinner<Double> growthRateSpinner;
    SpinnerValueFactory<Double> growthRateFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 2, 1.2,0.05);
    @FXML
    private Spinner<Integer> numberOfLayersSpinner;
    SpinnerValueFactory<Integer> numberOfLayersFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200, 10,1);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        growthRateSpinner.setValueFactory(growthRateFactory);
        growthRateSpinner.getEditor().setFont(Font.font("System", FontWeight.BOLD, 14));


        numberOfLayersSpinner.setValueFactory(numberOfLayersFactory);
        numberOfLayersSpinner.getEditor().setFont(Font.font("System", FontWeight.BOLD, 14));

        firstLayerThicknessResultField.setDisable(true);
        firstLayerThicknessResultText.setOpacity(0.5);
        firstLayerThicknessResultUnit.setOpacity(0.5);
    }

    String prismLayerChosenOption = "firstLayerThickAndTotalThickOption";

    @FXML
    void firstLayerThickAndTotalThickOption(ActionEvent event) {

        prismLayerChosenOption = "firstLayerThickAndTotalThickOption";

        growthRateResultText.setFill(Color.BLACK);
        prismPropertiesWarningText.setText("");

        firstLayerThicknessField.setDisable(false);
        firstLayerThicknessText.setOpacity(1);
        firstLayerThicknessUnit.setOpacity(1);
        totalThicknessField.setDisable(false);
        totalThicknessText.setOpacity(1);
        totalThicknessUnit.setOpacity(1);
        numberOfLayersSpinner.setDisable(false);
        numberOfLayersText.setOpacity(1);
        growthRateSpinner.setDisable(true);
        growthRateText.setOpacity(0.5);

        firstLayerThicknessResultField.setDisable(true);
        firstLayerThicknessResultField.setText("");
        firstLayerThicknessResultText.setOpacity(0.5);
        firstLayerThicknessResultUnit.setOpacity(0.5);
        finalLayerThicknessResultField.setText("");
        totalThicknessResultField.setDisable(true);
        totalThicknessResultField.setText("");
        totalThicknessResultText.setOpacity(0.5);
        totalThicknessResultUnit.setOpacity(0.5);
        growthRateResult.setDisable(false);
        growthRateResult.setText("");
        growthRateResultText.setOpacity(1);
    }
    @FXML
    void firstLayerThicknessOption(ActionEvent event) {

        prismLayerChosenOption = "firstLayerThicknessOption";

        growthRateResultText.setFill(Color.BLACK);
        prismPropertiesWarningText.setText("");

        firstLayerThicknessField.setDisable(false);
        firstLayerThicknessText.setOpacity(1);
        firstLayerThicknessUnit.setOpacity(1);
        totalThicknessField.setDisable(true);
        totalThicknessText.setOpacity(0.5);
        totalThicknessUnit.setOpacity(0.5);
        numberOfLayersSpinner.setDisable(false);
        numberOfLayersText.setOpacity(1);
        growthRateSpinner.setDisable(false);
        growthRateText.setOpacity(1);

        firstLayerThicknessResultField.setDisable(true);
        firstLayerThicknessResultField.setText("");
        firstLayerThicknessResultText.setOpacity(0.5);
        firstLayerThicknessResultUnit.setOpacity(0.5);
        finalLayerThicknessResultField.setText("");
        totalThicknessResultField.setDisable(false);
        totalThicknessResultField.setText("");
        totalThicknessResultText.setOpacity(1);
        totalThicknessResultUnit.setOpacity(1);
        growthRateResult.setDisable(true);
        growthRateResult.setText("");
        growthRateResultText.setOpacity(0.5);
    }
    @FXML
    void totalThickAndNumberOfLayersOption(ActionEvent event) {

        prismLayerChosenOption = "totalThickAndNumberOfLayersOption";

        growthRateResultText.setFill(Color.BLACK);
        prismPropertiesWarningText.setText("");

        firstLayerThicknessField.setDisable(true);
        firstLayerThicknessText.setOpacity(0.5);
        firstLayerThicknessUnit.setOpacity(0.5);
        totalThicknessField.setDisable(false);
        totalThicknessText.setOpacity(1);
        totalThicknessUnit.setOpacity(1);
        numberOfLayersSpinner.setDisable(false);
        numberOfLayersText.setOpacity(1);
        growthRateSpinner.setDisable(false);
        growthRateText.setOpacity(1);

        firstLayerThicknessResultField.setDisable(false);
        firstLayerThicknessResultField.setText("");
        firstLayerThicknessResultText.setOpacity(1);
        firstLayerThicknessResultUnit.setOpacity(1);
        finalLayerThicknessResultField.setText("");
        totalThicknessResultField.setDisable(true);
        totalThicknessResultField.setText("");
        totalThicknessResultText.setOpacity(0.5);
        totalThicknessResultUnit.setOpacity(0.5);
        growthRateResult.setDisable(true);
        growthRateResult.setText("");
        growthRateResultText.setOpacity(0.5);
    }
    //The following methods get the prism layer properties from the MainWindowController class
    public void setFirstLayerThickness(String firstLayerThickness) {

        firstLayerThicknessField.setText(firstLayerThickness);
    }
    public void setTotalThickness(double totalThickness) {

        double recommendedTotalThickness = totalThickness * 1.3333;

        totalThicknessField.setText(String.format(Locale.ROOT, "%.2e", recommendedTotalThickness));
    }
    @FXML
    void calculatePrismLayer(ActionEvent event) {

        double firstLayerThickness;
        double totalThickness;
        int numberOfLayers;
        double growthRate;
        String growthRateString;

        double[] growthRateAndFinalThickness;

        try {
            //The following two lines replace the decimal comma with decimal point of the value from the
            // growth spinner. So far I cannot find how to change decimal comma to point on the spinner :(
            growthRateString = Double.toString(growthRateSpinner.getValue());
            growthRate = Double.parseDouble(growthRateString.replace(",",".")) ;

            switch (prismLayerChosenOption) {

                case "firstLayerThickAndTotalThickOption":

                    growthRateResultText.setFill(Color.BLACK);

                    firstLayerThickness = Math.abs(Double.parseDouble(firstLayerThicknessField.getText()));
                    totalThickness = Math.abs(Double.parseDouble(totalThicknessField.getText()));
                    numberOfLayers = Math.abs(numberOfLayersSpinner.getValue());

                    //Passes the variables to the GrowthRate class
                    GrowthRate growthRateObj = new GrowthRate(firstLayerThickness, totalThickness, numberOfLayers);

                    growthRateAndFinalThickness = getGrowthRateAndFinalThickness();

                    if (growthRateAndFinalThickness[1] < 1) {

                        prismPropertiesWarningText.setText("Growth rate less than 1!");
                        prismPropertiesWarningText.setVisible(true);
                        growthRateResultText.setFill(Color.RED);
                        growthRateResult.setText("");
                        finalLayerThicknessResultField.setText("");
                    }
                    else if (growthRateAndFinalThickness[1] > 2.5) {

                        prismPropertiesWarningText.setText("Growth rate quite a bit over 2!");
                        prismPropertiesWarningText.setVisible(true);
                        growthRateResultText.setFill(Color.RED);
                        growthRateResult.setText("");
                        finalLayerThicknessResultField.setText("");
                    }
                    else {

                        growthRateResult.setText(String.format(Locale.ROOT,"%.2f", growthRateAndFinalThickness[1]));
                        finalLayerThicknessResultField.setText(String.format("%.4e", growthRateAndFinalThickness[0]));

                        prismPropertiesWarningText.setVisible(false);
                    }
                    break;

                case "firstLayerThicknessOption":

                    firstLayerThickness = Math.abs(Double.parseDouble(firstLayerThicknessField.getText()));
                    numberOfLayers = Math.abs(numberOfLayersSpinner.getValue());

                    double totalAndLastLayerThickness[];

                    totalAndLastLayerThickness = getTotalAndLastLayerThickness(growthRate, firstLayerThickness, numberOfLayers);

                    totalThicknessResultField.setText(String.format(Locale.ROOT, "%.2e", totalAndLastLayerThickness[0]));
                    finalLayerThicknessResultField.setText(String.format(Locale.ROOT, "%.2e", totalAndLastLayerThickness[1]));

                    break;

                case "totalThickAndNumberOfLayersOption":

                    double[] firstAndLastLayerThickness;

                    totalThickness = Math.abs(Double.parseDouble(totalThicknessField.getText()));
                    numberOfLayers = Math.abs(numberOfLayersSpinner.getValue());
                    growthRate = Math.abs(growthRateSpinner.getValue());

                    firstAndLastLayerThickness = getFirstAndLastLayerThickness(totalThickness, numberOfLayers, growthRate);

                    firstLayerThicknessResultField.setText(String.format(Locale.ROOT, "%.4e", firstAndLastLayerThickness[0]));
                    finalLayerThicknessResultField.setText(String.format(Locale.ROOT, "%.4e", firstAndLastLayerThickness[1]));

                    break;
            }
        }
        catch (Exception e) {

            firstLayerThicknessField.setText("﴾͡๏̯͡๏﴿");
            System.out.println(e);
        }
    }
}
