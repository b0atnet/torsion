package net.b0at.torsion.test;

import com.google.gson.annotations.SerializedName;
import net.b0at.torsion.FileStorage;
import net.b0at.torsion.Storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jordin on 8/4/2017.
 * Jordin is still best hacker.
 */
public class StorageTest {

    public static void main(String[] args) throws IOException {
        Storage<TestClass> storage = FileStorage.of(TestClass.class, "test.xml");

        TestClass testClass = storage.load().orElse(new TestClass());

        testClass.testInt = 5;
        testClass.values.put("foo", 10);
        testClass.values.put("bar", 20);

        storage.save(testClass);
    }

    public static class TestClass {
        @SerializedName("test-int")
        public int testInt = -1;

        public int testInt2 = -7;

        public Map<String, Integer> values = new HashMap<>();
    }
}
