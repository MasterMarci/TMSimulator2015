package main.gui;

import java.util.regex.Pattern;

import sun.misc.Regexp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainViewController {
	
	TuringMachineSimulatorApplication application;
	
	public TuringMachineSimulatorApplication getApplication() {
		return application;
	}

	public void setApplication(TuringMachineSimulatorApplication application) {
		this.application = application;
	}

	@FXML
	TextField tapeInputTextField;
	
	@FXML
	private TextField inputAlphabetTextField;
	
	@FXML
	private TextField tapeAlphabetTextField;
	
	@FXML
	private TextField statesTextField;
	
	@FXML
	private Button startTMButton;
	
	@FXML
	private TableView<TableColumn<String, String>> instructionTable;
	
	@FXML
	private TableColumn<String, String> inputStateColumn;
	
	@FXML
	private TableColumn<String, String> outputStateColumn;
	
	@FXML
	private TableColumn<String, String> tapeInputColumn;
	
	@FXML
	private TableColumn<String, String> tapeOutputColumn;
	
	@FXML
	private TableColumn<String, String> headDirectionColumn;
	
	
	public void startTMButtonClicked() {
		
	}
	
	public void saveStatesFromTextFieldToStringList() {
		String dirtyStatelist=statesTextField.getText();
		String[] states=dirtyStatelist.split(",");
		for(String s:states) {
			
		}
		dirtyStatelist.trim();
	}
	

}
