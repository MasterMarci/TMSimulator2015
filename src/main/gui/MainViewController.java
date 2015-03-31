package main.gui;

import java.util.regex.Pattern;

import dataModel.HeadDirection;
import dataModel.Instruction;
import dataModel.TuringMachine;
import sun.misc.Regexp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
		this.getApplication().setTm(new TuringMachine());
		startStateChoiceBox.getItems().clear();
		endStateChoiceBox.getItems().clear();
		
		startStateChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (arg0, arg1, arg2) -> {
			if(arg0.getValue() != null && arg0.getValue().length() != 0) {
					IsStartStateDefined=true;
			}
		});
		
		endStateChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (arg0, arg1, arg2) -> {
			if(arg0.getValue() != null && arg0.getValue().length() != 0) {
					IsEndStateDefined=true;
			}		
		});
		
		tapeInputColumn.setCellValueFactory(new PropertyValueFactory<Instruction, String>("input"));
		tapeOutputColumn.setCellValueFactory(new PropertyValueFactory<Instruction, String>("output"));
		inputStateColumn.setCellValueFactory(new PropertyValueFactory<Instruction, String>("beginState"));
		outputStateColumn.setCellValueFactory(new PropertyValueFactory<Instruction, String>("endState"));
		headDirectionColumn.setCellValueFactory(new PropertyValueFactory<Instruction, String>("direction"));
		instructionTable.setItems(getApplication().getTm().getInstructionList());
		
		//getApplication().getTm().getInstructionList().add(new Instruction("Start", "End", 'a', 'b', HeadDirection.RIGHT));
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
	private TableView<Instruction> instructionTable;
	
	@FXML
	private TableColumn<Instruction, String> inputStateColumn;
	
	@FXML
	private TableColumn<Instruction, String> outputStateColumn;
	
	@FXML
	private TableColumn<Instruction, String> tapeInputColumn;
	
	@FXML
	private TableColumn<Instruction, String> tapeOutputColumn;
	
	@FXML
	private TableColumn<Instruction, String> headDirectionColumn;
	
	@FXML
	private ChoiceBox<String> startStateChoiceBox;	
	
	@FXML
	private ChoiceBox<String> endStateChoiceBox;
	
	@FXML
	private ComboBox stateInputNewInstruction;
	
	@FXML
	private ComboBox stateOutputNewInstruction;
	
	@FXML
	private ComboBox tapeInputNewInstruction;
	
	@FXML
	private ComboBox tapeOutputNewInstruction;
	
	@FXML
	private ChoiceBox headDirectionNewInstruction;
	
	@FXML
	private Button addInstructionButton;
	
	@FXML
	public void startTMButtonClicked() {
		try {
			getApplication().getPane().setDisable(true);
			this.getApplication().getTm().startMachine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getApplication().getPane().setDisable(false);
		}
		
	}
	
	private void saveStatesFromTextFieldToTMStates() {
		String dirtyStatelist=statesTextField.getText();
		if (!dirtyStatelist.isEmpty()) {
			String[] states = dirtyStatelist.trim().split(",");
			for (String s : states) {
				this.getApplication().getTm().getStates().add(s);
			}
			disableStartEndStateChoiceBox(false);
		} else {
			this.getApplication().getTm().getStates().clear();
			disableStartEndStateChoiceBox(true);
			startTMButton.setDisable(true);
		}
	}
	
	private void saveInputAlphabetFromTextFieldToTMIAlphabet() {
		String dirtyAlphabetlist=inputAlphabetTextField.getText();
		if (!dirtyAlphabetlist.isEmpty()) {
			String[] symbols = dirtyAlphabetlist.trim().split(",");
			for (String s : symbols) {
				this.getApplication().getTm().getInputAlphabet()
						.add(s.charAt(0));
			}
		} else {
			this.getApplication().getTm().getInputAlphabet().clear();
			startTMButton.setDisable(true);
		}
	}
	
	private void saveTapeAlphabetFromTextFieldToTMIAlphabet() {
		String dirtyAlphabetlist=tapeAlphabetTextField.getText();
		if (!dirtyAlphabetlist.isEmpty()) {
			String[] symbols = dirtyAlphabetlist.trim().split(",");
			for (String s : symbols) {
				this.getApplication().getTm().getInputAlphabet()
						.add(s.charAt(0));
			}
		} else {
			this.getApplication().getTm().getTapeAlphabet().clear();
			startTMButton.setDisable(true);
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
	
	private void disableInstructionUI(boolean b) {
		headDirectionNewInstruction.setDisable(b);
		stateInputNewInstruction.setDisable(b);
		stateOutputNewInstruction.setDisable(b);
		tapeInputNewInstruction.setDisable(b);
		tapeOutputNewInstruction.setDisable(b);
		addInstructionButton.setDisable(b);
	}
	
	private void disableStartEndStateChoiceBox(boolean b) {
		startStateChoiceBox.setDisable(b);
		endStateChoiceBox.setDisable(b);
		if(b)
			addStatesToChoiceBoxesForStartandEndState();
		else {
			startStateChoiceBox.getItems().clear();
			endStateChoiceBox.getItems().clear();
		}
	}

}
