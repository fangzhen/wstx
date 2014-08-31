package info.fzhen.wstx.at.coordinator;

public enum AtProtocol {
	COMPLETION("http://docs.oasis-open.org/ws-tx/wsat/2006/06/Completion"),
	VOLATILE2PC("http://docs.oasis-open.org/ws-tx/wsat/2006/06/Volatile2PC"),
	DURABLE2PC("http://docs.oasis-open.org/ws-tx/wsat/2006/06/Durable2PC");
	
	private String text;
	private AtProtocol(String text){
		this.text = text;
	}
	
	@Override
	public String toString(){
		return text;
	}
	public String getText(){
		return text;
	}
	public static AtProtocol fromString(String text){
		if (text != null){
			for (AtProtocol p : AtProtocol.values()){
				if (text.equals(p.text)){
					return p;
				}
			}
		}
		throw new IllegalArgumentException("No protocol of " + text + "defined in wsat coordiantion type");
	}
}
