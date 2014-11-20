package info.fzhen.wstx.test.services;

import info.fzhen.wstx.ba.cc.CcParticipant;

/**
 * demo business activity coordinator completion participant
 */
public class HelloBaCcParticipant implements CcParticipant{

	@Override
	public Vote complete() {
		return Vote.Completed;
	}

	@Override
	public void close() {

	}

	@Override
	public void cancel() {

	}

	@Override
	public void compensate() {

	}
}
