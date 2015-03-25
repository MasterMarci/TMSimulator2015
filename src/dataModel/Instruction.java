package dataModel;

public class Instruction {
	private String beginState;
	private String endState;
	private char input;
	private char output;
	private HeadDirection direction;
	public char getInput() {
		return input;
	}
	public void setInput(char input) {
		this.input = input;
	}
	public char getOutput() {
		return output;
	}
	public void setOutput(char output) {
		this.output = output;
	}
	public HeadDirection getDirection() {
		return direction;
	}
	public void setDirection(HeadDirection direction) {
		this.direction = direction;
	}
	public String getBeginState() {
		return beginState;
	}
	public void setBeginState(String beginState) {
		this.beginState = beginState;
	}
	public String getEndState() {
		return endState;
	}
	public void setEndState(String endState) {
		this.endState = endState;
	}
	
	public Instruction(String beginState, String endState, char input,
			char output, HeadDirection direction) {
		super();
		this.beginState = beginState;
		this.endState = endState;
		this.input = input;
		this.output = output;
		this.direction = direction;
	}
	
}
