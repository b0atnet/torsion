package net.b0at.torsion.parser.file;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.*;

/**
 * Created by Jordin on 8/4/2017.
 * Jordin is still best hacker.
 */
public class YMLFileParser<T> extends FileStorageParser<T> {
    public YMLFileParser(File file) {
        super(file);
    }

    @Override
    public T load(Class<T> clazz) {
        T loaded = null;

        try {
            YamlReader reader = new YamlReader(new FileReader(getFile()));
            loaded = reader.read(clazz);
        } catch (YamlException | FileNotFoundException exception) {
            exception.printStackTrace();
        }

        if (loaded == null) {
            try {
                loaded = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
        return loaded;
    }

    @Override
    public void save(T object) {
        try {
            YamlWriter writer = new YamlWriter(new FileWriter(getFile()));
            writer.getConfig().writeConfig.setIndentSize(2);
            writer.getConfig().writeConfig.setAutoAnchor(false);
            writer.getConfig().writeConfig.setWriteDefaultValues(true);
            writer.getConfig().writeConfig.setWriteRootTags(false);
            writer.getConfig().writeConfig.setWriteClassname(YamlConfig.WriteClassName.NEVER);

            writer.write(object);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
