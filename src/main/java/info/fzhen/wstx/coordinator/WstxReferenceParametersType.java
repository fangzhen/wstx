package info.fzhen.wstx.coordinator;

import javax.xml.bind.annotation.XmlElement;

import org.apache.cxf.ws.addressing.ReferenceParametersType;


public class WstxReferenceParametersType extends ReferenceParametersType{
	@XmlElement
	protected PrivateInstanceType privateInstance;

	public PrivateInstanceType getPrivateInstance() {
		return privateInstance;
	}

	public void setPrivateInstance(PrivateInstanceType privateInstance) {
		this.privateInstance = privateInstance;
	}
}
