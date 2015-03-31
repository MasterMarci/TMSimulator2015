package dataModel;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class TuringMachine {
	
	private ObservableList<Instruction> instructionList = FXCollections.observableArrayList(new ArrayList<Instruction>());
	private Tape tape = new Tape();

	private LinkedHashSet<Character> inputAlphabet = new LinkedHashSet<>();
	private LinkedHashSet<Character> tapeAlphabet = new LinkedHashSet<>();
	private LinkedHashSet<String> states = new LinkedHashSet<>();
	private int headPosition=0;
	private boolean machineRunning =false;
	private String currentState="";
	private String startState=null;
	private String endState =null;

	public Tape getTape() {
		return tape;
	}
	public void setTape(Tape tape) {
		this.tape = tape;
	}
	
	public int getHeadPosition() {
		return headPosition;
	}
	
	public void setHeadPosition(int headPosition) throws Exception {
		if(Math.abs(headPosition - this.headPosition) <= 1) {
			this.headPosition = headPosition;
		} else {
			throw new Exception("Tried to set invalid headpostion! Action aborted.");
		}
	}
	public ObservableList<Instruction> getInstructionList() {
		return instructionList;
	}
	public void setInstructionList(ObservableList<Instruction> instructionList) {
		this.instructionList = instructionList;
	}
	
	public LinkedHashSet<Character> getInputAlphabet() {
		return inputAlphabet;
	}
	
	public void setInputAlphabet(LinkedHashSet<Character> inputAlphabet) throws Exception {
		if(!machineRunning) {
			this.inputAlphabet = inputAlphabet;
		} else {
			throw new Exception("You can't change the input alphabet while running the machine!");
		}
	}
	public LinkedHashSet<Character> getTapeAlphabet() {
		return tapeAlphabet;
	}
	
	public void setTapeAlphabet(LinkedHashSet<Character> tapeAlphabet) throws Exception {
		if(!machineRunning) {
			this.tapeAlphabet = tapeAlphabet;
		} else {
			throw new Exception("You can't change the tape alphabet while running the machine!");
		}
	}
	
	public boolean isMachineRunning() {
		return machineRunning;
	}
	
	public void setMachineRunning(boolean isMachineRunning) {
		machineRunning = isMachineRunning;
	}
	
	public LinkedHashSet<String> getStates() {
		return states;
	}
	
	public void setStates(LinkedHashSet<String> states) throws Exception {
		if(!machineRunning) {
			this.states = states;
		} else {
			throw new Exception("You can't change the state set while running the machine!");
		}
	}
	
	public boolean validateStates() {
		
		if(!this.getStates().contains(this.getStartState()) || !this.getStates().contains(this.getEndState())) {
			return false;
		}		
		return true;
	}
	
	public boolean validateInstructionList() {
		for(char symbol : this.getTapeAlphabet()) {
			for(String state : this.getStates()) {
				if(getMatchingInstruction(state, symbol) == null && !state.equals(this.getEndState())) {
					return false;
				}
			}
		}
		
		for(char symbol : this.getInputAlphabet()) {
			for(String state : this.getStates()) {
				if(getMatchingInstruction(state, symbol) == null && !state.equals(this.getEndState())) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Instruction getMatchingInstruction(String state,char input) {
		for(Instruction i : this.instructionList) {
			if(i.getInput() == input && i.getBeginState().equals(state)) {
				return i;
			}
		}
		return null;
	}
	
	public void startMachine() throws Exception {
		if(!validateStates() || !validateInstructionList()) {
			throw new Exception("Invalid Declaration of a Turing machine");
		}
		this.setMachineRunning(true);
		this.setHeadPosition(0);
		currentState=this.getStartState();
		while(isMachineRunning() && !currentState.equals(endState)) {
			//Find next instruction to execute
			Instruction currentInstruction=getMatchingInstruction(currentState,tape.getSymbolAt(this.getHeadPosition()));
			System.out.println(tape.getContent());
			tape.setSymbolAtPosition(currentInstruction.getOutput(), this.getHeadPosition());
			
			//Set new head position according to the instruction
			switch(currentInstruction.getDirection()) {
				case LEFT:
					headPosition--;
					break;
				case NONE:
					break;
				case RIGHT:
					headPosition++;
					break;
			
			}
			//Change state at the end of instruction execution
			currentState=currentInstruction.getEndState();	
			
		}
		this.setMachineRunning(false);
	}
	public String getStartState() {
		return startState;
	}
	public void setStartState(String startState) throws Exception {
		if(!states.contains(startState))
			throw new Exception("StartState cannot be a unknown state!");
		this.startState = startState;
	}
	public String getEndState() {
		return endState;
	}
	public void setEndState(String endState) throws Exception {
		if(!states.contains(endState))
			throw new Exception("EndState cannot be a unknown state!");
		this.endState = endState;
	}
	
}
