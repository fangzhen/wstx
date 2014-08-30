package info.fzhen.wstx.coordinator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * private id that helps to fully qualify the protocol service endpoint.
 * @author fangzhen
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://www.fzhen.info/wstx")
public class PrivateIdType {
	@XmlValue
	protected String privateId;

	public PrivateIdType(){}
	public PrivateIdType(String pid){
		this.privateId = pid;
	}
	public String getPrivateId() {
		return privateId;
	}

	public void setPrivateId(String privateId) {
		this.privateId = privateId;
	}	
}
