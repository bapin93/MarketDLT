package models;
/**
 * 
 */

/**
 * @author andres
 *
 */
public abstract class AAnalysisObject {
	
	private boolean _outcome;
	
	public AAnalysisObject() {
		
	}
	
	public boolean getOutcome() {
		return _outcome;
	}
	
	public void setOutcome(final boolean outcome) {
		_outcome = outcome;
	}
	
}
