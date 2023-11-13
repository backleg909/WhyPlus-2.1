package Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application {

    int[] currentMainStagePosition = new int[2];

    @Override
    public void start(Stage mainStage) throws Exception {

        int[] setMainStagePosition = new int[2];
        int[] mainStageSize = new int[2];

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindowInterfaceController.fxml"));

        // Sets the position of the main stage to the upper right portion of the screen
        Scene mainScene = new Scene(fxmlLoader.load());
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        setMainStagePosition[0] = (int) (bounds.getMinX() + (bounds.getWidth() - mainScene.getWidth()) * 0.6);
        setMainStagePosition[1] = (int) (bounds.getMinY() + (bounds.getHeight() - mainScene.getHeight()) * 0.2);

        mainStage.setX(setMainStagePosition[0]);
        mainStage.setY(setMainStagePosition[1]);
        mainStage.initStyle(StageStyle.DECORATED);
        mainStage.setScene(mainScene);
        mainStage.setTitle("WhyPlus 2.1");
        mainStage.setResizable(false);
        mainStage.setAlwaysOnTop(true);
        mainStage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon4.PNG")));
        mainStage.show();

        mainStage.setOnHidden(e -> Platform.exit());

        mainStageSize[0] = (int) mainStage.getWidth();
        mainStageSize[1] = (int) mainStage.getHeight();

        currentMainStagePosition[0] = (int) mainStage.getX();
        currentMainStagePosition[1] = (int) mainStage.getY();

        //This part tracks changes in the mainStage position
        mainStage.xProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {

                doChange(newValue);
            }
            private void doChange(Number newValue) {

                currentMainStagePosition[0] = newValue.intValue();
            }
        });
        mainStage.yProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {

                doChange(newValue);
            }
            private void doChange(Number newValue) {

                currentMainStagePosition[1] = newValue.intValue();
            }
        });
        // Passes current position and size of the main stage to the MainWindowController class
        MainWindowController mainWindowController = fxmlLoader.getController();
        mainWindowController.getCurrentMainStagePosition(currentMainStagePosition);
        mainWindowController.getMainStageSize(mainStageSize);
    }
    public void run() {
        launch();
    }
}
