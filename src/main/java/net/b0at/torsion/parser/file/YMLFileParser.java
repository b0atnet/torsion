package net.b0at.torsion.parser.file;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import net.b0at.torsion.TorsionException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class YMLFileParser<T> extends FileStorageParser<T> {
    public YMLFileParser(Path file) {
        super(file);
    }

    @Override
    public Optional<T> load(Class<? extends T> clazz) {
        try (Reader bufferedReader = Files.newBufferedReader(this.file)) {
            YamlReader reader = new YamlReader(bufferedReader);
            return Optional.ofNullable(reader.read(clazz));
        } catch (IOException exception) { /* YAMLException is an IOException too for some reason */
            throw new TorsionException("Failed to read YAML file!", exception);
        }
    }

    @Override
    public void save(T object) {
        try (Writer bufferedWriter = Files.newBufferedWriter(this.file)) {
            YamlWriter writer = new YamlWriter(bufferedWriter);

            writer.getConfig().writeConfig.setIndentSize(2);
            writer.getConfig().writeConfig.setAutoAnchor(false);
            writer.getConfig().writeConfig.setWriteDefaultValues(true);
            writer.getConfig().writeConfig.setWriteRootTags(false);
            writer.getConfig().writeConfig.setWriteClassname(YamlConfig.WriteClassName.NEVER);

            writer.write(object);
            writer.close();
        } catch (IOException exception) {
            throw new TorsionException("Failed to write YAML file!", exception);
        }
    }
}
