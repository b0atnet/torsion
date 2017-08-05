package net.b0at.torsion.parser.file;

import com.google.common.collect.ImmutableMap;
import net.b0at.torsion.parser.StorageParser;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Jordin on 8/4/2017.
 * Jordin is still best hacker.
 */
public class FileParserFactory {
    private static final Map<String, Class<? extends FileStorageParser>> PARSER_MAP = ImmutableMap.<String, Class<? extends FileStorageParser>>builder()
            .put("json", JSONFileParser.class)
            .put("yml", YMLFileParser.class)
            .build();

    public static StorageParser of(File file) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return from(file, FilenameUtils.getExtension(file.getAbsolutePath()).toLowerCase());
    }

    public static <T> StorageParser from(File file, String extension) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (extension.equals("xml")) {
            throw new RuntimeException("Please use a better file type/extension.");
        }

        Class<? extends FileStorageParser> parserClass = PARSER_MAP.getOrDefault(extension, NullFileParser.class);

        return parserClass.getDeclaredConstructor(File.class).<T>newInstance(file);
    }
}
