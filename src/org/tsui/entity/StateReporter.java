package org.tsui.entity;

/**
 * @author TsuiXh
 *	����xhr���󣬷��ط�������״̬
 */
public class StateReporter {
	
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param state	(success | failed)
	 */
	public StateReporter(String state) {
		super();
		this.state = state;
	}
	
}
