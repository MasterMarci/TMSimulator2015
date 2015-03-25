package dataModel;

import java.util.ArrayList;

public class TuringMachine {
	
	private ArrayList<Instruction> instructionList = new ArrayList<Instruction>();
	private Tape tape = new Tape();

	private ArrayList<Character> inputAlphabet = new ArrayList<Character>();
	private ArrayList<Character> tapeAlphabet = new ArrayList<Character>();
	private ArrayList<String> states = new ArrayList<String>();
	private int headPosition=0;
	private boolean IsMachineRunning =false;
	private String currentState="";
	private String startState="";
	private String endState ="";

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
	public ArrayList<Instruction> getInstructionList() {
		return instructionList;
	}
	public void setInstructionList(ArrayList<Instruction> instructionList) {
		this.instructionList = instructionList;
	}
	
	public ArrayList<Character> getInputAlphabet() {
		return inputAlphabet;
	}
	
	public void setInputAlphabet(ArrayList<Character> inputAlphabet) throws Exception {
		if(!IsMachineRunning) {
			this.inputAlphabet = inputAlphabet;
		} else {
			throw new Exception("You can't change the input alphabet while running the machine!");
		}
	}
	public ArrayList<Character> getTapeAlphabet() {
		return tapeAlphabet;
	}
	
	public void setTapeAlphabet(ArrayList<Character> tapeAlphabet) throws Exception {
		if(!IsMachineRunning) {
			this.tapeAlphabet = tapeAlphabet;
		} else {
			throw new Exception("You can't change the tape alphabet while running the machine!");
		}
	}
	
	public boolean isIsMachineRunning() {
		return IsMachineRunning;
	}
	
	public void setIsMachineRunning(boolean isMachineRunning) {
		IsMachineRunning = isMachineRunning;
	}
	
	public ArrayList<String> getStates() {
		return states;
	}
	
	public void setStates(ArrayList<String> states) throws Exception {
		if(!IsMachineRunning) {
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
				if(getMatchingInstruction(state, symbol) == null) {
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
		this.setIsMachineRunning(true);
		this.setHeadPosition(0);
		currentState=this.getStartState();
		while(IsMachineRunning && !currentState.equals(endState)) {
			//Find next instruction to execute
			Instruction currentInstruction=getMatchingInstruction(currentState,tape.getSymbolAt(this.getHeadPosition()));
			
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
		this.setIsMachineRunning(false);
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
