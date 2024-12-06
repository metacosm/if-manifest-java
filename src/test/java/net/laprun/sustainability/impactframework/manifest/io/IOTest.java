package net.laprun.sustainability.impactframework.manifest.io;

import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import net.laprun.sustainability.impactframework.manifest.Child;
import net.laprun.sustainability.impactframework.manifest.Initialize;
import net.laprun.sustainability.impactframework.manifest.Input;
import net.laprun.sustainability.impactframework.manifest.Manifest;
import net.laprun.sustainability.impactframework.manifest.Metadata;
import net.laprun.sustainability.impactframework.manifest.Plugin;
import net.laprun.sustainability.impactframework.manifest.Tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IOTest {

  @Test
  void roundtripShouldWork() throws IOException {
    final var initialize = new Initialize(Map.of("plugin",
        new Plugin("plugin-name", "https://github.com/metacosm/if-manifest-java", "export")));
    final var metadata = new Metadata("manifest", "description", Set.of("tag1", "tag2"));

    final var child = new Child(null, null);
    child.add(new Input(Instant.now(), 500, Map.of("key1", "value11", "key2", "value21")));
    child.add(new Input(Instant.now(), 600, Map.of("key2", "value22", "key1", "value12")));

    final var manifest = new Manifest(metadata, initialize, new Tree(Map.of("child", child)));
    final var file = Files.createTempFile("if-manifest-export-", ".imf.yml").toFile();
    // final var file = new File("test.imf.yml");
    IO.toYAMLFile(manifest, file);
    final var actual = IO.fromYAMLFile(file);
    assertEquals(manifest.metadata(), actual.metadata());
    assertEquals(manifest.initialize(), actual.initialize());
    assertEquals(manifest.tree().children().get("child"), actual.tree().children().get("child"));
    assertEquals(manifest, actual);
  }
}
