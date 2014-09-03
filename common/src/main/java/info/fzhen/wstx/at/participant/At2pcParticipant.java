package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.participant.Participant;

public interface At2pcParticipant extends Participant{
    public At2pcPartService.Vote prepare ();
    public void commit ();
    public void rollback ();
    public void unknown ();
}
