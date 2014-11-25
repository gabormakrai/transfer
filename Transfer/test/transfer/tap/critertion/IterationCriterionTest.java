package transfer.tap.critertion;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.tap.criterion.IterationCriterion;

public class IterationCriterionTest {

	@Test
	public void testCheck() {
		IterationCriterion criterion = new IterationCriterion(10, true);
		int iteration = 0;
		while(criterion.check()) {
			++iteration;
		}
		assertEquals(10, iteration);
	}

}
