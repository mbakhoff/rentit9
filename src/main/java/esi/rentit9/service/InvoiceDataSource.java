package esi.rentit9.service;

import javax.activation.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;

public class InvoiceDataSource<T> implements DataSource {

    private final byte[] xmlData;

    public InvoiceDataSource(T invoice) throws JAXBException {
        ByteArrayOutputStream outb = new ByteArrayOutputStream();
        JAXBContext.newInstance(invoice.getClass())
                .createMarshaller()
                .marshal(invoice, outb);
        xmlData = outb.toByteArray();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(xmlData);
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public String getContentType() {
        return "application/xml";
    }

    @Override
    public String getName() {
        return "invoice.xml";
    }

}
