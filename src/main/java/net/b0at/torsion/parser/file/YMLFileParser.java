package net.b0at.torsion.parser.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.nio.file.Path;

public class YMLFileParser<T> extends JacksonFileParser<T> {
    public YMLFileParser(Path file) {
        super(file, "YAML");
    }

    @Override
    protected ObjectMapper createObjectMapper() {
        return JacksonFileParser.constructObjectMapper(new YAMLFactory().configure(YAMLGenerator.Feature.WRITE_DOC_START_MARKER, false));
    }
}
