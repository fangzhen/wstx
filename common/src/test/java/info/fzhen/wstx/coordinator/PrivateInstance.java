package info.fzhen.wstx.coordinator;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlSeeAlso({PrivateIdType.class})
public class PrivateInstance {
	
	@XmlAnyElement(lax = true)
	protected List<Object> any;
	
	public List<Object> getAny() {
		if (any == null) {
            any = new ArrayList<Object>();
        }
		return any;
	}

}
