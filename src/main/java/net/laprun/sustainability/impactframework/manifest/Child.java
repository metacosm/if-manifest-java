package net.laprun.sustainability.impactframework.manifest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.Nulls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class Child {
    @JsonProperty
    @JsonSetter(nulls = Nulls.SKIP)
    private Pipeline pipeline;
    @JsonProperty
    @JsonSetter(nulls = Nulls.SKIP)
    private Map<String, Object> defaults;
    @JsonProperty
    private List<Input> inputs;
    @JsonUnwrapped
    @JsonSetter(nulls = Nulls.SKIP)
    private SortedMap<String, Child> children;

    public Child() {
        this(null, null);
    }

    public Child(Pipeline pipeline, Map<String, Object> defaults) {
        this.pipeline = pipeline;
        this.defaults = defaults;
    }

    public Child(List<Input> inputs) {
        this.inputs = inputs;
    }

    public void add(Input input) {
        if(inputs == null) inputs = new ArrayList<>();
        inputs.add(input);
    }

    public void add(String childName, Child child) {
        if(children == null) children = new TreeMap<>();
        children.put(childName, child);
    }

    @JsonProperty
    public List<Input> inputs() {
        return inputs;
    }

    @JsonProperty
    public SortedMap<String, Child> children() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Child child)) return false;
        return Objects.equals(pipeline, child.pipeline) && Objects.equals(defaults, child.defaults) && Objects.equals(inputs, child.inputs) && Objects.equals(children, child.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pipeline, defaults, inputs, children);
    }
}
