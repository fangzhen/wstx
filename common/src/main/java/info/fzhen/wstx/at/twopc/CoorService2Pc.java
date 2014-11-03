package info.fzhen.wstx.at.twopc;

import info.fzhen.wstx.at.AtProtocolServiceCoor;
import info.fzhen.wstx.coordinator.AbstractActivityCoordinatorContext;

public abstract class CoorService2Pc <T, E extends AbstractActivityCoordinatorContext>
		extends AtProtocolServiceCoor<T, E> {
	public abstract void prepare();
	public abstract void prepared();
	public abstract void commit();
	public abstract void committed();
}
