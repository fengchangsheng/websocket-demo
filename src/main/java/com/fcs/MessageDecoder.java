package com.fcs;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by Lucare.Feng on 2016/9/6.
 */
public class MessageDecoder implements Decoder.Text<Person> {
    public Person decode(String s) throws DecodeException {
        System.out.println("Incoming XML " + s);
        Person person = null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Person.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(s);
            person = (Person) unmarshaller.unmarshal(reader);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return person;
    }

    public boolean willDecode(String s) {
        return false;
    }

    public void init(EndpointConfig endpointConfig) {

    }

    public void destroy() {

    }
}
