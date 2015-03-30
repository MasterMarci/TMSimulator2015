package main.gui;

import java.util.regex.Pattern;

import sun.misc.Regexp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainViewController {
	
	private boolean IsStartStateDefined=false;
	
	private boolean IsEndStateDefined=false;
	
	private Thread machineSimulatorThread;
	
	private TuringMachineSimulatorApplication application;
	
	public TuringMachineSimulatorApplication getApplication() {
		return application;
	}

	public void setApplication(TuringMachineSimulatorApplication application) {
		this.application = application;
	}
	
	public void init() {
		startStateChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				if(arg0.getValue() != null && arg0.getValue().length() != 0) {
					IsStartStateDefined=true;
				}
				
			}
		});
		
		endStateChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				if(arg0.getValue() != null && arg0.getValue().length() != 0) {
					IsEndStateDefined=true;
				}
				
			}
		});
	}

	@FXML
	private TextField tapeInputTextField;
	
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
	
	@FXML
	private ChoiceBox startStateChoiceBox;	
	
	@FXML
	private ChoiceBox endStateChoiceBox;
	
	@FXML
	private ComboBox stateInputNewInstruction;
	
	@FXML
	private ComboBox stateOutputNewInstruction;
	
	@FXML
	private ComboBox tapeInputNewInstruction;
	
	@FXML
	private ComboBox tapeOutputNewInstruction;
	
	@FXML
	private ComboBox headDirectionNewInstruction;
	
	@FXML
	private Button addInstructionButton;
	
	@FXML
	public void startTMButtonClicked() {
		
	}
	
	private void saveStatesFromTextFieldToTMStates() {
		String dirtyStatelist=statesTextField.getText();
		String[] states=dirtyStatelist.trim().split(",");
		for(String s:states) {
			this.getApplication().getTm().getStates().add(s);
		}
	}
	
	private void saveInputAlphabetFromTextFieldToTMIAlphabet() {
		String dirtyAlphabetlist=inputAlphabetTextField.getText();
		String[] symbols=dirtyAlphabetlist.trim().split(",");
		for(String s:symbols) {
			this.getApplication().getTm().getInputAlphabet().add(s.charAt(0));
		}
	}
	
	private void saveTapeAlphabetFromTextFieldToTMIAlphabet() {
		String dirtyAlphabetlist=tapeAlphabetTextField.getText();
		String[] symbols=dirtyAlphabetlist.trim().split(",");
		for(String s:symbols) {
			this.getApplication().getTm().getInputAlphabet().add(s.charAt(0));
		}
	}
	
	private void saveTapeFromTextFieldToTMTape() {
		this.getApplication().getTm().getTape().setContent(new StringBuilder(tapeInputTextField.getText()));
	}
	
	private void addStatesToChoiceBoxesForStartandEndState() {
		for (String s:this.getApplication().getTm().getStates()) {
			startStateChoiceBox.getItems().add(s);
			endStateChoiceBox.getItems().add(s);
		}
	}
	
	
	

}
