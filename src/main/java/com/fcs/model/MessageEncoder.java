package com.fcs.model;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by Lucare.Feng on 2016/9/6.
 */
public class MessageEncoder implements Encoder.Text<Person>{
    public String encode(Person person) throws EncodeException {
        JAXBContext jaxbContext = null;
        StringWriter st = null;
        try {
            jaxbContext = JAXBContext.newInstance(Person.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            st = new StringWriter();
            marshaller.marshal(person, st);
            System.out.println("OutGoing XML " + st.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return st.toString();
    }

    public void init(EndpointConfig endpointConfig) {

    }

    public void destroy() {

    }
}
