package net.b0at.torsion.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.SerializedName;
import net.b0at.torsion.FileStorage;
import net.b0at.torsion.Storage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Jordin on 8/4/2017.
 * Jordin is still best hacker.
 */
public class StorageTest {

    public static void main(String[] args) throws IOException {
        FileStorage.setBaseDirectory(new File("./testing/test/123/dir"));
        Storage<TestClass> storage = FileStorage.of(TestClass.class, "fuke.invalid-extension");


        TestClass testClass = storage.load();
        testClass.testDouble = 5;
        storage.save(testClass);
    }

    public static class TestClass {
        @SerializedName("test-int")
        public int testInt = -1;

        @SerializedName("test-double")
        public double testDouble = 251.125156;

        @SerializedName("test-string")
        public String testString = "";

        @SerializedName("test-list")
        public List<String> testList = ImmutableList.of("Testing", "Arrays", "Of", "Strings", "To", "And", "From", "Configurations");

        @SerializedName("map-test")
        public Map<String, Integer> testMap = ImmutableMap.<String, Integer>builder()
                .put("test", 5)
                .put("testing", 2)
                .put("11", 11)
                .build();

    }
}
