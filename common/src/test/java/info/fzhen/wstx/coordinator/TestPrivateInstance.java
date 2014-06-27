package info.fzhen.wstx.coordinator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;

import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.MetadataType;
import org.apache.cxf.ws.addressing.ReferenceParametersType;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


@XmlRootElement
@XmlType(name = "type1")
class Type1{
	@XmlElement
	int t1;
}

@XmlRootElement(name = "EndpointReference")
class EndpointReferenceTypeWrapper  extends EndpointReferenceType{
	@XmlElement
	Type1 type1;
}

public class TestPrivateInstance {
	@Test
	public void testM() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(
					EndpointReferenceTypeWrapper.class, Type1.class, 
					AttributedURIType.class, ReferenceParametersType.class,MetadataType.class );
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			EndpointReferenceTypeWrapper w = new EndpointReferenceTypeWrapper();
			AttributedURIType a = new AttributedURIType();
			a.setValue("xxxxxxxxxxxx");
//			w.setAddress(a);
			w.type1 = new Type1();
			jaxbMarshaller.marshal(w, os);
			System.out.println(os);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			EndpointReferenceTypeWrapper cxfEpr = (EndpointReferenceTypeWrapper) jaxbUnmarshaller
					.unmarshal(new ByteArrayInputStream(os.toByteArray()));
		} catch (JAXBException e) {

			e.printStackTrace();
		}

	}

	// @Test
	public void TestMarshall() {
		PrivateInstance pi = new PrivateInstance();
		List<Object> eles = pi.getAny();

		PrivateIdType pit = new PrivateIdType();
		pit.setPrivateId("<para> xxx</para>");
		// eles.add(pit);

		JAXBElement<PrivateIdType> jaxbElement = new JAXBElement<PrivateIdType>(
				new QName("para2"), PrivateIdType.class, pit);
		eles.add(jaxbElement);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(
					PrivateInstance.class, PrivateIdType.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(pi, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void TestUnmarshall() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(
					PrivateInstance.class, PrivateIdType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			PrivateInstance pi = (PrivateInstance) jaxbUnmarshaller
					.unmarshal(System.in);
			System.out.println(pi.getAny());
			for (Object o : pi.getAny()) {
				System.out.println(o.getClass());
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	@Test
	public void TestAny() {
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(PrivateInstance.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			PrivateInstance pi = (PrivateInstance) jaxbUnmarshaller
					.unmarshal(System.in);
			System.out.println(jaxbContext);
			System.out.println(pi.getAny());
			for (Object o : pi.getAny()) {
				System.out.println(o.getClass());
			}

			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(pi, System.out);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	//	@Test
	public void TestMarshal2() {
		PrivateInstance pi = new PrivateInstance();
		List<Object> any = pi.getAny();
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document dom = db.newDocument();
			Element ele = dom.createElement("ele1");
			any.add(ele);
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(PrivateInstance.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(pi, System.out);
		} catch (JAXBException e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testJAXBele() throws JAXBException{
		PrivateIdType pit = new PrivateIdType();
		pit.setPrivateId("11234");
		JAXBContext ctx = JAXBContext.newInstance(PrivateIdType.class);
		Marshaller marshaller = ctx.createMarshaller();
		DOMResult res = new DOMResult();
		marshaller.marshal(pit, res);
		Element elt = ((Document) res.getNode()).getDocumentElement();
		
		A a = new A();
		ctx = JAXBContext.newInstance(A.class, B.class, elt.getClass(), PrivateIdType.class);
		marshaller = ctx.createMarshaller();
		JAXBElement<Element> je = new JAXBElement<Element>(new QName("hhh"), (Class<Element>) elt.getClass(), elt);
//		a.getAny().add(je);
//		a.getAny().add(pit);
		B b = new B();
		JAXBElement je2 = new JAXBElement(new QName("b"), B.class, b);
		a.getAny().add(je2);
//		a.getAny().add(b);
		DOMResult res2 = new DOMResult();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(a, res2);
		marshaller.marshal(a,  System.out);
		Node node = res2.getNode();

		ctx = JAXBContext.newInstance(A.class, B.class, String.class);
		Unmarshaller um = ctx.createUnmarshaller();
		Object obj = um.unmarshal(node);
		System.out.println(obj);
	}
	
	@XmlRootElement
	public static class A{
		@XmlAnyElement(lax = true)
		protected List<Object> any;
	    public List<Object> getAny() {
	        if (any == null) {
	            any = new ArrayList<Object>();
	        }
	        return this.any;
	    }
	}
	@XmlRootElement
	public static class B{
		@XmlElement
		public String val = "classB";
	}
}
