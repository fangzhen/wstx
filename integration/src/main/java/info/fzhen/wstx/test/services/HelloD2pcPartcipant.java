package info.fzhen.wstx.test.services;

import info.fzhen.wstx.at.participant.At2pcPartService;
import info.fzhen.wstx.at.participant.At2pcParticipant;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Durable 2PC participant of hello service.
 */
public class HelloD2pcPartcipant implements At2pcParticipant {
	private static List<String> callLog = new ArrayList<>();

	@Override
	public At2pcPartService.Vote prepare() {
		System.out.println("Prepared");
		return At2pcPartService.Vote.Prepared;
	}

	@Override
	public void commit() {
		System.out.println("committed");
	}

	@Override
	public void rollback() {
		callLog.remove(callLog.size() - 1);
	}

	@Override
	public void unknown() {

	}

	public void log() {
		String accTime = DateFormat.getInstance().format(new Date());
		callLog.add(accTime);
	}
}
