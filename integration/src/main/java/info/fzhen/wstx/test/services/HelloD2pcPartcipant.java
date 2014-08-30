package info.fzhen.wstx.test.services;

import info.fzhen.wstx.participant.at.Durable2PCParticipant;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Durable 2PC participant of hello service.
 */
public class HelloD2pcPartcipant extends Durable2PCParticipant {
    private static List<String> callLog;

    @Override
    public Vote prepare() {
        System.out.println("Prepared");
        return Vote.Prepared;
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {
        callLog.remove(callLog.size()-1);
    }

    @Override
    public void unknown() {

    }

    public void log(){
        String accTime = DateFormat.getInstance().format(new Date());
        callLog.add(accTime);
    }
}
