package transfer.tap.criterion;

public class CombinedCriterion implements AbstractCriterion{
	
	private final AbstractCriterion[] criterions;
	private final boolean[] criterionsValue;
	private final boolean debug;
	
	public CombinedCriterion(boolean debug, AbstractCriterion... criterions) {
		this.criterions = criterions;
		this.criterionsValue = new boolean[this.criterions.length];
		this.debug = debug;
	}

	@Override
	public boolean check() {
		
		for (int i = 0; i < criterions.length; ++i) {
			criterionsValue[i] = criterions[i].check();
		}
		
		boolean returnValue = false;
		
		for (boolean value : criterionsValue) {
			if (value) {
				returnValue = true;
				break;
			}
		}
		
		if (debug) {
			System.out.print("CombinedCriterion: [");
			for (int i = 0; i < criterionsValue.length - 1; ++i) {
				System.out.print(criterionsValue[i]);
				System.out.print(" ");
			}
			System.out.print(criterionsValue[criterionsValue.length - 1]);
			System.out.print("] -> ");
			System.out.println(returnValue);
		}
		
		return returnValue;
	}

}
