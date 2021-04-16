package net.b0at.torsion.parser.file;

import com.google.common.collect.ImmutableMap;
import net.b0at.torsion.parser.StorageParser;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Map;

public class FileParserFactory {
    private static final Map<String, Class<? extends FileStorageParser>> PARSER_MAP = ImmutableMap.<String, Class<? extends FileStorageParser>>builder()
            .put("json", JSONFileParser.class)
            .put("xml", XMLFileParser.class)
            .put("yml", YMLFileParser.class)
            .build();

    public static StorageParser of(Path file) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return from(file, FilenameUtils.getExtension(file.toString()).toLowerCase());
    }

    public static <T> StorageParser from(Path file, String extension) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<? extends FileStorageParser> parserClass = PARSER_MAP.getOrDefault(extension, NullFileParser.class);

        return parserClass.getDeclaredConstructor(File.class).newInstance(file);
    }
}
