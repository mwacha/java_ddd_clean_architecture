package tk.mwacha.infrastructure.api.presenters;

import tk.mwacha.usecase.customer.list.OutputListCustomerDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class CustomerPresenter {

    public static String toXML(final OutputListCustomerDTO data) throws JAXBException {
        final var jaxbContext = JAXBContext.newInstance(OutputListCustomerDTO.class);

        final var jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final var sw = new StringWriter();

        jaxbMarshaller.marshal(data, sw);

        return sw.toString();
    }
}
