package net.laprun.sustainability.impactframework.manifest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class Input {
    private Instant timestamp;
    private int duration;
    private SortedMap<String, Object> values;

    public Input() {
    }

    public Input(Instant timestamp, int duration, Map<String, Object> values) {
        this.timestamp = timestamp;
        this.duration = duration;
        this.values = new TreeMap<>(values);
    }

    @JsonProperty
    public Instant timestamp() {
        return timestamp;
    }

    @JsonProperty
    public int duration() {
        return duration;
    }

    @JsonAnyGetter
    public Map<String, Object> values() {
        return this.values;
    }

    @JsonAnySetter
    public void setValue(String name, Object value) {
        if (this.values == null) this.values = new TreeMap<>();
        this.values.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Input input)) return false;
        return duration == input.duration && Objects.equals(timestamp, input.timestamp) && Objects.equals(values, input.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, duration, values);
    }
}
