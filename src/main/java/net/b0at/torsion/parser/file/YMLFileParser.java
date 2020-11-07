package net.b0at.torsion.parser.file;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.*;
import java.util.Optional;

public class YMLFileParser<T> extends FileStorageParser<T> {
    public YMLFileParser(File file) {
        super(file);
    }

    @Override
    public Optional<T> load(Class<T> clazz) {
        try {
            YamlReader reader = new YamlReader(new FileReader(this.file));
            return Optional.ofNullable(reader.read(clazz));
        } catch (YamlException | FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void save(T object) {
        try {
            YamlWriter writer = new YamlWriter(new FileWriter(this.file));
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
