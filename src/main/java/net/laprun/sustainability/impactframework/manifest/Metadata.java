package net.laprun.sustainability.impactframework.manifest;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public record Metadata(String name,String description,SortedSet<String>tags){public Metadata(String name,String description,Set<String>tags){this(name,description,new TreeSet<>(tags));}}
