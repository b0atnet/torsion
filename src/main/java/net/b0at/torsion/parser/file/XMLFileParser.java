package net.b0at.torsion.parser.file;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import net.b0at.torsion.TorsionException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class XMLFileParser<T> extends FileStorageParser<T> {
    public XMLFileParser(Path file) {
        super(file);
    }

    @Override
    public Optional<T> load(Class<? extends T> clazz) {
        try (Reader reader = Files.newBufferedReader(this.file)) {
            XmlMapper xmlMapper = new XmlMapper();
            return Optional.of(xmlMapper.readValue(reader, clazz));
        } catch (IOException exception) {
            throw new TorsionException("Failed to read XML file!", exception);
        }
    }

    @Override
    public void save(T object) {
        try (Writer writer = Files.newBufferedWriter(this.file)) {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(writer, object);
        } catch (IOException exception) {
            throw new TorsionException("Failed to save XML file!", exception);
        }
    }
}
