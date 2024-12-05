package net.laprun.sustainability.impactframework.manifest.io;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.laprun.sustainability.impactframework.manifest.Manifest;

import java.io.File;
import java.io.IOException;

public class IO {
    private final static ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());
    static {
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP));
    }

    public static void toYAMLFile(Manifest manifest, File file) {
        try {
            MAPPER.writeValue(file, manifest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Manifest fromYAMLFile(File file) {
        try {
            return MAPPER.readValue(file, Manifest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
