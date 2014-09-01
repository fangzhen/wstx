package info.fzhen.wstx.util;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class JAXBUtils {
	
	public static class PreferredMapper extends NamespacePrefixMapper {  
        @Override  
        public String getPreferredPrefix(String namespaceUri,  
                String suggestion, boolean requirePrefix) {  
            return "wstx";  
        }
    }
	
	public static <T> Element marshal2Element(Class<T> cls, Object obj) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(new Class[] { cls });
			Marshaller marshaller = ctx.createMarshaller();
			NamespacePrefixMapper mapper = new PreferredMapper();  
	        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", mapper);  
			DOMResult res = new DOMResult();
			marshaller.marshal(obj, res);
			Element elt = ((Document) res.getNode()).getDocumentElement();
            return elt;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
        return null;
	}

	public static String getStringFromDoc(Document doc) {
		DOMImplementationLS domImplementation = (DOMImplementationLS) doc
				.getImplementation();
		LSSerializer lsSerializer = domImplementation.createLSSerializer();
		return lsSerializer.writeToString(doc);
	}

	public static String getStringFromDomSource(DOMSource domSource) {
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			StreamResult result = new StreamResult(new StringWriter());
			transformer.transform(domSource, result);
			return result.getWriter().toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
