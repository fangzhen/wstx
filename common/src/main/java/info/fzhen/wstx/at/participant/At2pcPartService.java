package info.fzhen.wstx.at.participant;

import info.fzhen.wstx.StateEnum;

public class At2pcPartService extends AtProtocolServicePart<At2pcParticipant>{
    public static enum State implements StateEnum {
        None,
        Active,
        Preparing,
        Prepared,
        PrepareSuccess,
        Committing
    }
}
