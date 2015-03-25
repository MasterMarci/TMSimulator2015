package test;

import org.junit.Assert;
import org.junit.Test;
import dataModel.HeadDirection;
import dataModel.Instruction;
import dataModel.Tape;
import dataModel.TuringMachine;

public class TestTuringMachine {
	@Test
	public void testTMInstances() throws Exception {
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
}
