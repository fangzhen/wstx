package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.AbstractCoordinatorProtocolService;
import info.fzhen.wstx.StateEnum;
import info.fzhen.wstx.coordinator.AbstractActivityCoordinatorContext;

public abstract class CoorService2Pc <T, E extends AbstractActivityCoordinatorContext, R extends StateEnum>
		extends AbstractCoordinatorProtocolService<T, E, R> {
	public abstract void prepared();
	public abstract void committed();
}
