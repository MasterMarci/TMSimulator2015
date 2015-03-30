package main.gui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.midi.SysexMessage;

import dataModel.TuringMachine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TuringMachineSimulatorApplication extends Application {

	private Stage primaryStage;
	private MainViewController mainController;
	private TuringMachine tm = new TuringMachine();
	
	public TuringMachine getTm() {
		return tm;
	}

	public void setTm(TuringMachine tm) {
		this.tm = tm;
	}

	@Override
	public void start(Stage arg0) throws Exception {
		this.primaryStage = arg0;
		this.primaryStage.setTitle("TuringMachineSimulator");
		showMainScene();
		this.primaryStage.show();
		this.mainController.init();

	}

	private void showMainScene() {
		String path = this.getHostServices().resolveURI(this.getHostServices().getDocumentBase(),"src/res/Mainview.fxml");
		try {
			FXMLLoader loader = new FXMLLoader(new URL(path));
			AnchorPane pane = loader.load();
			this.mainController=loader.getController();
			this.primaryStage.setScene(new Scene(pane));
			this.mainController.setApplication(this);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void  main(String... args) {
		Application.launch(args);
	}

}
