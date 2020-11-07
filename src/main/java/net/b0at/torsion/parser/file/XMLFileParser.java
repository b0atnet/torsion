package net.b0at.torsion.parser.file;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class XMLFileParser<T> extends FileStorageParser<T> {
    public XMLFileParser(File file) {
        super(file);
    }

    @Override
    public Optional<T> load(Class<T> clazz) {
        XmlMapper xmlMapper = new XmlMapper();

        try {
            return Optional.of(xmlMapper.readValue(this.file, clazz));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void save(T object) {
        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            xmlMapper.writeValue(this.file, object);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
