package net.b0at.torsion.parser.file;

import com.google.common.collect.ImmutableMap;
import net.b0at.torsion.parser.StorageParser;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Map;

public final class FileParserFactory {
    private static final Map<String, Class<? extends FileStorageParser>> PARSER_MAP = ImmutableMap.<String, Class<? extends FileStorageParser>>builder()
            .put("json", JSONFileParser.class)
            .put("xml", XMLFileParser.class)
            .put("yml", YMLFileParser.class)
            .build();

    private FileParserFactory() {
    }

    public static StorageParser<?> of(Path file) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return from(file, FilenameUtils.getExtension(file.toString()).toLowerCase());
    }

    public static StorageParser<?> from(Path file, String extension) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        @SuppressWarnings("unchecked")
        Class<? extends FileStorageParser<?>> parserClass = (Class<FileStorageParser<?>>) PARSER_MAP.getOrDefault(extension, NullFileParser.class);

        return parserClass.getDeclaredConstructor(Path.class).newInstance(file);
    }
}
