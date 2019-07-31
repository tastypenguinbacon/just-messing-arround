package io.tastypenguinbacon.xslt.transform;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.assertNotNull;

public class TransformingXSL {
    private TransformerFactory tf;

    @BeforeTest
    public void init() {
        tf = TransformerFactory.newInstance();
    }

    @Test
    public void transformerFactoryIsInitialized() {
        assertNotNull(tf);
    }

    @Test(dataProvider = "xsltAndXMLResources")
    public void identity(String xmlResource, String xsltResource) throws IOException, TransformerException {
        try (InputStream xml = getClass().getResourceAsStream(xmlResource);
             InputStream xslt = getClass().getResourceAsStream(xsltResource)) {
            assertNotNull(xml, "Source cannot be null: " + xsltResource);
            assertNotNull(xslt, "XSLT not null: " + xmlResource);
            Source xmlSource = new StreamSource(xml);
            Source xsltSource = new StreamSource(xslt);
            Transformer transformer = tf.newTransformer(xsltSource);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlSource, new StreamResult(System.out));
        }
    }

    @DataProvider
    public Object[][] xsltAndXMLResources() {
        return new Object[][] {
                {"/xslt/basic/xml.xml", "/xslt/basic/identity.xslt"},
                {"/xslt/basic/xml.xml", "/xslt/basic/order-entry.xslt"}
        };
    }
}
