package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import static Main.AllCalculationMethods.*;

public class MainWindowController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    private Tab predefinedMatTab;
    @FXML
    private Tab CustomMatTab;
    @FXML
    private ToggleGroup toggleButtons;
    @FXML
    private ToggleButton airButton;
    @FXML
    private ToggleButton waterButton;
    @FXML
    private RadioButton externalFlowToggle;
    @FXML
    private ToggleGroup internalExternalFlow;
    @FXML
    private RadioButton internalFlowToggle;
    @FXML
    private RadioButton externalFlowToggle2;
    @FXML
    private ToggleGroup internalExternalFlow2;
    @FXML
    private RadioButton internalFlowToggle2;
    @FXML
    private RadioButton dynViscosityToggle;
    @FXML
    private ToggleGroup dynOrKinViscosityToggle;
    @FXML
    private RadioButton kinViscosityToggle;
    @FXML
    private Text temperatureText;
    @FXML
    private Text pressureText;
    @FXML
    private Text pressureUnit;
    @FXML
    private Text TurbOrLaminarBL;
    @FXML
    private Text TurbOrLaminarBL2;
    @FXML
    private Text FirstLayerThicknessWarning;
    @FXML
    private Text growthRateResultText;
    @FXML
    private Text kinOrDynViscosityUnitText;
    @FXML
    private Text velocityInputText;
    @FXML
    private Button calculateButton;
    @FXML
    private Button calculateButton2;
    @FXML
    private Button advancedButton;
    @FXML
    private Button advancedButton2;
    @FXML
    private Button helpButton;
    @FXML
    private Button helpButton2;
    @FXML
    private Button prismLayersButton1;
    @FXML
    private Button prismLayersButton2;
    @FXML
    private CheckBox CFXSolver;
    @FXML
    private CheckBox CFXSolver2;
    @FXML
    private TextField yPlusField;
    @FXML
    private TextField yPlusField2;
    @FXML
    private TextField velocityField;
    @FXML
    private TextField velocityField2;
    @FXML
    private TextField pressureField;
    @FXML
    private TextField temperatureField;
    @FXML
    private TextField charLengthField;
    @FXML
    private TextField charLengthField2;
    @FXML
    private TextField dynViscosityField;
    @FXML
    private TextField kinViscosityField;
    @FXML
    private TextField BLthicknessField;
    @FXML
    private TextField BLthicknessField2;
    @FXML
    private TextField densityField;
    @FXML
    private TextField densityFieldInput;
    @FXML
    private TextField firstLayerThicknessField;
    @FXML
    private TextField firstLayerThicknessField2;
    @FXML
    private TextField reynoldsNumField;
    @FXML
    private TextField reynoldsNumField2;
    @FXML
    private TextField viscosityInputField;

    @FXML
    void pressureDisplay(ActionEvent event) {}
    @FXML
    void temperatureDisplay(ActionEvent event) {}

    int[] currentMainStagePosition;
    public void getCurrentMainStagePosition(int[] mainStagePosition) {

        currentMainStagePosition = mainStagePosition;
    }
    int[] mainStageSize = new int[2];
    public void getMainStageSize(int[] stageSize) {

        mainStageSize = stageSize;
    }
    @FXML
    void openPrismLayersWindow(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("PrismLayersWindowController.fxml"));
        Scene prismLayersScene = new Scene(fxmlLoader4.load());

        Stage prismLayersStage = new Stage();
        prismLayersStage.initStyle(StageStyle.DECORATED);
        prismLayersStage.setScene(prismLayersScene);
        prismLayersStage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon4.PNG")));
        prismLayersStage.setTitle("WhyPlus 2.1");
        prismLayersStage.setResizable(false);
        prismLayersStage.setAlwaysOnTop(true);
        prismLayersStage.setX(currentMainStagePosition[0] - mainStageSize[0] + 80);
        prismLayersStage.setY(currentMainStagePosition[1]);
        prismLayersStage.show();

        //This passes results that can be used for calculating prism layer properties
        if (FlowProperties.firstLayerThickness != 0 || FlowProperties.boundaryLayerThickness != 0) {

            PrismLayerWindowController prismLayerWindowController = fxmlLoader4.getController();
            prismLayerWindowController.setFirstLayerThickness(String.format(Locale.ROOT, "%.2e", FlowProperties.firstLayerThickness));
            prismLayerWindowController.setTotalThickness(FlowProperties.boundaryLayerThickness);
        }
    }
    @FXML
    void openHelp(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("HelpWindowInterfaceController.fxml"));
        Scene helpScene = new Scene(fxmlLoader3.load());

        Stage helpStage = new Stage();
        helpStage.initStyle(StageStyle.DECORATED);
        helpStage.setScene(helpScene);
        helpStage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon4.PNG")));
        helpStage.setTitle("Why+ 2.1");
        helpStage.setResizable(false);
        helpStage.setAlwaysOnTop(false);
        helpStage.setX(currentMainStagePosition[0] - mainStageSize[0]);
        helpStage.setY(currentMainStagePosition[1]);
        helpStage.show();
    }
    @FXML
    void openAdvancedWindow(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("AdvancedWindowInterfaceController.fxml"));
        Scene secondScene = new Scene(fxmlLoader2.load());

        Stage secondStage = new Stage();
        secondStage.initStyle(StageStyle.DECORATED);
        secondStage.setScene(secondScene);
        secondStage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon4.PNG")));
        secondStage.setResizable(false);
        secondStage.setAlwaysOnTop(true);
        secondStage.setX(currentMainStagePosition[0] + mainStageSize[0] - 13);
        secondStage.setY(currentMainStagePosition[1] + mainStageSize[1] - 279);
        secondStage.show();

        //This passes the advanced fluid properties to the text field in the Advanced Flow Properties window
        AdvancedWindowController advancedWindowController = fxmlLoader2.getController();
        advancedWindowController.setTextFrictionalVelocity(String.format(Locale.ROOT, "%.4e", FlowProperties.getFrictionalVelocity()));
        advancedWindowController.setWallShearStressField(String.format(Locale.ROOT, "%.4e", FlowProperties.getWallShearStress()));
        advancedWindowController.setTextSkinFrictionCoefficient(String.format(Locale.ROOT, "%.4e", FlowProperties.getSkinFrictionCoefficient()));

        advancedButton.setDisable(true);
        advancedButton2.setDisable(true);
    }
    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////Following lines correspond the Predefined Material tab////////////
    @FXML
    void materialToggleButton(ActionEvent event) {

        if(event.getSource() == waterButton) {

            temperatureText.setText("Water Temperature");
            temperatureText.setFill(Color.BLACK);
            temperatureText.setFont(Font.font("System", FontWeight.NORMAL, 12));
            pressureField.setDisable(true);
            pressureText.setOpacity(0.5);
            pressureUnit.setOpacity(0.5);
        }
        if(event.getSource() == airButton) {

            temperatureText.setText("Air Temperature");
            temperatureText.setFont(Font.font("System", FontWeight.NORMAL, 12));
            temperatureText.setFill(Color.BLACK);
            pressureField.setDisable(false);
            pressureText.setText("Air Pressure");
            pressureText.setOpacity(1);
            pressureUnit.setOpacity(1);
        }
    }
    @FXML
    boolean CFXSolverCheck(ActionEvent event) {

        boolean isCFX;

        isCFX = CFXSolver.isSelected();
        return isCFX;
    }
    // Calculates using predefined materials - tab 1
    @FXML
    void calculate(ActionEvent event) {

        // Temporarily store all calculated values as some depend on each other
        double reynoldsNumber = 0;
        double skinFrictionCoefficient = 0;
        double wallShearStress = 0;
        double frictionalVelocity;
        double firstLayerThickness;
        double boundaryLayerThickness;
        boolean isTurbulent;
        double materialDensity = 0;
        double materialDynViscosity = 0;
        double materialKinViscosity = 0;

        try {
            // Gets input data and sets it into inputData class
            InputData inputData;
            inputData = new InputData(Double.parseDouble(temperatureField.getText()),
                                 Math.abs(Double.parseDouble(pressureField.getText())),
                                 Math.abs(Double.parseDouble(velocityField.getText())),
                                 Math.abs(Double.parseDouble(charLengthField.getText())),
                                 Math.abs(Integer.parseInt(yPlusField.getText())),
                                 CFXSolverCheck(event),
                                0,
                                0,
                                    0);

            // Resets text font of temperature text to normal after warning
            temperatureText.setFill(Color.BLACK);
            temperatureText.setFont(Font.font("System", FontWeight.NORMAL, 12));
            temperatureText.setText(temperatureText.getText().replace("(!)", ""));

            // Resets text font of velocity to normal after warning
            velocityInputText.setFill(Color.BLACK);
            velocityInputText.setFont(Font.font("System", FontWeight.NORMAL, 12));
            velocityInputText.setText("Free-stream Velocity");

            if(toggleButtons.getSelectedToggle() == waterButton){

                // Makes the text font red and bold to warn that the water temperature is outside valid values
                if (inputData.temperature < -0.15 + 273.15 || inputData.temperature > 99.85 + 273.15) {
                    temperatureText.setText(temperatureText.getText() + " (!)");
                    temperatureText.setFill(Color.RED);
                    temperatureText.setFont(Font.font("System", FontWeight.BOLD, 12));
                }
                reynoldsNumber = getReynoldsNumber(inputData.velocity,
                                                   inputData.charLength,
                                                   getKinViscosityWater(getDynViscosityWater(inputData.temperature), getDensityWater(inputData.temperature)));
                skinFrictionCoefficient = getSkinFrictionCoefficient(reynoldsNumber, internalFlowToggle.isSelected());
                wallShearStress = getWallShearStress(skinFrictionCoefficient, getDensityWater(inputData.temperature), inputData.velocity);

                FluidMaterialData fluidMaterialData;
                fluidMaterialData = new FluidMaterialData(
                        getDensityWater(inputData.temperature),
                        getDynViscosityWater(inputData.temperature),
                        getKinViscosityWater(getDynViscosityWater(inputData.temperature), getDensityWater(inputData.temperature)) );

                materialDensity = fluidMaterialData.matDensity;
                materialDynViscosity = fluidMaterialData.matDynViscosity;
                materialKinViscosity = fluidMaterialData.matKinViscosity;
            }
            if(toggleButtons.getSelectedToggle() == airButton){

                reynoldsNumber = getReynoldsNumber(inputData.velocity, inputData.charLength, getKinViscosityAir(inputData.temperature, inputData.pressure));
                skinFrictionCoefficient = getSkinFrictionCoefficient(reynoldsNumber, internalFlowToggle.isSelected());
                wallShearStress = getWallShearStress(skinFrictionCoefficient, getDensityAir(inputData.temperature, inputData.pressure), inputData.velocity);

                FluidMaterialData fluidMaterialData;
                fluidMaterialData = new FluidMaterialData(
                        getDensityAir(inputData.temperature, inputData.pressure),
                        getDynViscosityAir(inputData.temperature),
                        getKinViscosityAir(inputData.temperature,  inputData.pressure));

                // Makes Free-stream velocity text red as a warning - due to compressibility effects
                if (inputData.velocity > 100) {
                    velocityInputText.setText(velocityInputText.getText() + "(!)");
                    velocityInputText.setFill(Color.RED);
                    velocityInputText.setFont(Font.font("System", FontWeight.BOLD, 12));
                }
                materialDensity = fluidMaterialData.matDensity;
                materialDynViscosity = fluidMaterialData.matDynViscosity;
                materialKinViscosity = fluidMaterialData.matKinViscosity;
            }
            dynViscosityField.setText(String.format(Locale.ROOT, "%.4e", materialDynViscosity));
            kinViscosityField.setText(String.format(Locale.ROOT, "%.5e", materialKinViscosity));
            densityField.setText(String.format(Locale.ROOT, "%.2f", materialDensity));

            frictionalVelocity = getFrictionalVelocity(wallShearStress, materialDensity);
            firstLayerThickness = getFirstLayerThickness(CFXSolver.isSelected(),
                                                         inputData.targetYplus,
                                                         materialDynViscosity,
                                                         materialDensity,
                                                         frictionalVelocity);
            boundaryLayerThickness = getBoundaryLayerThickness(reynoldsNumber, inputData.charLength);
            isTurbulent = getIsTurbulent(reynoldsNumber);

            // Constructor for storing the temporary variables into the FlowProperties class
            FlowProperties flowProperties;
            flowProperties = new FlowProperties(
                    reynoldsNumber,
                    skinFrictionCoefficient,
                    wallShearStress,
                    frictionalVelocity,
                    firstLayerThickness,
                    boundaryLayerThickness,
                    isTurbulent);

            // The data is now read from the FlowProperties class and sent to the text fields
            reynoldsNumField.setText(String.format(Locale.ROOT, "%.4e", flowProperties.reynoldsNumber));    //Locale.ROOT sets decimal point instead of comma
            firstLayerThicknessField.setText(String.format(Locale.ROOT, "%.2e", flowProperties.firstLayerThickness));
            BLthicknessField.setText(String.format(Locale.ROOT, "%.2e", flowProperties.boundaryLayerThickness));

            if(flowProperties.firstLayerThickness > flowProperties.boundaryLayerThickness) {

                FirstLayerThicknessWarning.setVisible(true);
            }
            else {
                FirstLayerThicknessWarning.setVisible(false);
            }
            if(getIsTurbulent(flowProperties.reynoldsNumber)) {

                TurbOrLaminarBL.setText("Turbulent Boundary Layer");
            }
            else {
                TurbOrLaminarBL.setText("Laminar Boundary Layer");
            }
            advancedButton.setDisable(false);
        }
        catch(Exception e) {
            temperatureField.setText("﴾͡๏̯͡๏﴿");
            System.out.println(e);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////
    /////////////////Following lines correspond the Custom Material tab////////////
    @FXML
    void kinViscosityToggleSelected(ActionEvent event) {

        kinOrDynViscosityUnitText.setText("[m2/s]");
    }
    @FXML
    void dynViscosityToggleSelected(ActionEvent event) {

        kinOrDynViscosityUnitText.setText("[Pas]");
    }
    // Calculates using custom materials - tab 2
    @FXML
    void calculate2(ActionEvent event) {

        double density;
        double kinViscosity;
        double dynViscosity;
        double reynoldsNumber;
        double skinFrictionCoefficient;
        double wallShearStress;
        double frictionalVelocity;
        double firstLayerThickness;
        double boundaryLayerThickness;
        boolean isTurbulent;

        density = Math.abs(Double.parseDouble(densityFieldInput.getText()));

        if (dynOrKinViscosityToggle.getSelectedToggle() == dynViscosityToggle) {

            dynViscosity = Math.abs(Double.parseDouble(viscosityInputField.getText()));
            kinViscosity = dynViscosity / density;
        }
        else {
            kinViscosity = Math.abs(Double.parseDouble(viscosityInputField.getText()));
            dynViscosity = kinViscosity * density;
        }
        // Gets input data and sets it into inputData class
        InputData inputData2;
        inputData2 = new InputData(0,
                                     0,
                Math.abs(Double.parseDouble(velocityField2.getText())),
                Math.abs(Double.parseDouble(charLengthField2.getText())),
                Math.abs(Integer.parseInt(yPlusField2.getText())),
                CFXSolverCheck(event),
                kinViscosity,
                dynViscosity,
                density);
        try {
            reynoldsNumber = getReynoldsNumber(inputData2.velocity, inputData2.charLength, kinViscosity);
            skinFrictionCoefficient = getSkinFrictionCoefficient(reynoldsNumber, internalFlowToggle2.isSelected());
            wallShearStress = getWallShearStress(skinFrictionCoefficient, inputData2.density, inputData2.velocity);
            frictionalVelocity = getFrictionalVelocity(wallShearStress, inputData2.density);
            firstLayerThickness = getFirstLayerThickness(CFXSolver2.isSelected(),
                                                         inputData2.targetYplus,
                                                         inputData2.dynViscosity,
                                                         inputData2.density,
                                                         frictionalVelocity);
            boundaryLayerThickness = getBoundaryLayerThickness(reynoldsNumber, inputData2.charLength);
            isTurbulent = getIsTurbulent(reynoldsNumber);

            // Constructor for storing the temporary variables into the FlowProperties class
            FlowProperties flowProperties2;
            flowProperties2 = new FlowProperties(
                    reynoldsNumber,
                    skinFrictionCoefficient,
                    wallShearStress,
                    frictionalVelocity,
                    firstLayerThickness,
                    boundaryLayerThickness,
                    isTurbulent);

            // The data is now read from the FlowProperties class and sent to the text fields
            reynoldsNumField2.setText(String.format(Locale.ROOT, "%.4e", flowProperties2.reynoldsNumber));
            firstLayerThicknessField2.setText(String.format(Locale.ROOT, "%.2e", flowProperties2.firstLayerThickness));
            BLthicknessField2.setText(String.format(Locale.ROOT, "%.2e", flowProperties2.boundaryLayerThickness));

            if(getIsTurbulent(flowProperties2.reynoldsNumber)) {

                TurbOrLaminarBL2.setText("Turbulent Boundary Layer");
            }
            else {
                TurbOrLaminarBL2.setText("Laminar Boundary Layer");
            }
            advancedButton2.setDisable(false);
        }
        catch (Exception e) {
            viscosityInputField.setText("﴾͡๏̯͡๏﴿");
            System.out.println(e);
        }
    }
    public class InputData {    // A class to store input data

        private double temperature;
        private double pressure;
        private double velocity;
        private double charLength;
        private int targetYplus ;
        private boolean CFXSolver;
        private double dynViscosity;
        private double kinViscosity;
        private double density;

        // Constructor specifies that any inputData object requires a set of input data
        public InputData(double inputTemperature,
                         double inputPressure,
                         double inputVelocity,
                         double inputCharLength,
                         int inputTargetYplus,
                         boolean inputCFXSolver,
                         double inputKinViscosity,
                         double inputDynViscosity,
                         double inputDensity) {

            temperature = inputTemperature + 273.15;
            pressure = inputPressure;
            velocity = inputVelocity;
            charLength = inputCharLength;
            targetYplus = inputTargetYplus;
            CFXSolver = inputCFXSolver;
            kinViscosity = inputKinViscosity;
            dynViscosity = inputDynViscosity;
            density = inputDensity;
        }
}
public class FluidMaterialData {    // A class to store all calculated material data

    private double matDensity;
    private double matDynViscosity;
    private double matKinViscosity;

    public FluidMaterialData(
            double materialDensity,
            double materialDynViscosity,
            double materialKinViscosity) {

        matDensity = materialDensity;
        matDynViscosity = materialDynViscosity;
        matKinViscosity = materialKinViscosity;
    }
}
public static class FlowProperties { // A class to store all calculated flow properties

        private double reynoldsNumber;
        private static double skinFrictionCoefficient;
        private static double wallShearStress;
        private static double frictionalVelocity;
        private static double firstLayerThickness;
        private static double boundaryLayerThickness;
        boolean turbulentFlow;

        public FlowProperties(double flowReynoldsNumber,
                              double flowSkinFrictionCoefficient,
                              double flowFrictionalVelocity,
                              double flowWallShearStress,
                              double flowFirstLayerThickness,
                              double flowBoundaryLayerThickness,
                              boolean isTurbulentFlow) {

            reynoldsNumber = flowReynoldsNumber;
            skinFrictionCoefficient = flowSkinFrictionCoefficient;
            wallShearStress = flowWallShearStress;
            frictionalVelocity = flowFrictionalVelocity;
            firstLayerThickness = flowFirstLayerThickness;
            boundaryLayerThickness = flowBoundaryLayerThickness;
            turbulentFlow = isTurbulentFlow;
        }
    public static double getFrictionalVelocity() {

            return frictionalVelocity;
    }
    public static double getWallShearStress() {

            return wallShearStress;
    }
    public static double getSkinFrictionCoefficient() {

            return skinFrictionCoefficient;
        }
    }
}


