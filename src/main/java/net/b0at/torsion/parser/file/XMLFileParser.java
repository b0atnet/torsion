package net.b0at.torsion.parser.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;

import java.nio.file.Path;

public class XMLFileParser<T> extends JacksonFileParser<T> {
    public XMLFileParser(Path file) {
        super(file, "XML");
    }

    @Override
    protected ObjectMapper createObjectMapper() {
        return constructObjectMapper(new XmlFactory());
    }
}
