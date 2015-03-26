package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import dataModel.HeadDirection;
import dataModel.Instruction;
import dataModel.Tape;
import dataModel.TuringMachine;

public class TestTuringMachine {
	@Test
	public void testSimpleLeftToRightProcess() throws Exception {
		TuringMachine tm = new TuringMachine();
		tm.getInputAlphabet().add(new Character('a'));
		tm.getInputAlphabet().add(new Character('b'));
		tm.getStates().add("Start");
		tm.getStates().add("End");
		
		tm.setStartState("Start");
		tm.setEndState("End");

		tm.getInstructionList().add(new Instruction("Start", "Start", 'a', 'b', HeadDirection.RIGHT));
		tm.getInstructionList().add(new Instruction("Start", "End", 'b', 'b', HeadDirection.LEFT));
		tm.getInstructionList().add(new Instruction("Start", "End", Tape.EMPTY, Tape.EMPTY, HeadDirection.NONE));
		tm.getTape().setContent(new StringBuilder("aaaa"));
		tm.startMachine();
		Assert.assertEquals("Replacement Instruction not correctly executed!", "bbbb¶", tm.getTape().getContent().toString());
		
	}
	
	@Test
	public void testBouncingLeftRightLeftProcess() throws Exception{
		TuringMachine tm = new TuringMachine();
		tm.getInputAlphabet().add(new Character('a'));
		tm.getInputAlphabet().add(new Character('b'));
		tm.getStates().add("Start");
		tm.getStates().add("End");
		
		tm.setStartState("Start");
		tm.setEndState("End");

		
		tm.getTapeAlphabet().add(new Character('#'));
		tm.setInstructionList(new ArrayList<Instruction>());
		tm.getInstructionList().add(new Instruction("Start","Start",'a','b',HeadDirection.RIGHT));
		tm.getInstructionList().add(new Instruction("Start", "Start", 'b', '#', HeadDirection.RIGHT));
		tm.getInstructionList().add(new Instruction("Start", "#", '#', '#', HeadDirection.NONE));
		tm.getInstructionList().add(new Instruction("Start", "Back", Tape.EMPTY, Tape.EMPTY, HeadDirection.LEFT));
		tm.getInstructionList().add(new Instruction("Back", "Back", 'b', '#', HeadDirection.LEFT));
		tm.getInstructionList().add(new Instruction("Back", "Back", 'a', 'b', HeadDirection.LEFT));
		tm.getInstructionList().add(new Instruction("Back", "Back", '#', '#', HeadDirection.LEFT));
		tm.getInstructionList().add(new Instruction("Back", "End", Tape.EMPTY, Tape.EMPTY, HeadDirection.RIGHT));
		Tape newTape = new Tape();
		newTape.setContent(new StringBuilder("babababbbbaaa"));
		tm.setTape(newTape);
		tm.getStates().add("Back");
		tm.startMachine();
		System.out.println(tm.getTape().getContent());
	}
	
}
