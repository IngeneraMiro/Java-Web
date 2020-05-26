package softuni.workshop.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {
    <O> void exportToXml(O object, String filePath) throws JAXBException;

    <O> O importFromXml(Class<O> klazz, String filePath) throws JAXBException, FileNotFoundException;
}
