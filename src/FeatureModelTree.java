package se.isselab.HAnS.codeAnnotation;

import java.nio.file.Path;
import java.util.ArrayList;

public class FeatureModelTree {
    private String name;
    private ArrayList<FeatureModelTree> subfeatures;
    private FeatureLocation location;
    public FeatureModelTree() {
        subfeatures = new ArrayList<FeatureModelTree>();
    }
    public FeatureModelTree(String name) {
        this();
        this.name = name;
    }
    public FeatureModelTree(String name, FeatureLocation location) {
        this();
        this.name = name;
        this.location = location;
    }
    public synchronized void append(FeatureModelTree fmt) {
        subfeatures.add(fmt);
    }
    public String getName() {
        return name;
    }
    //TODO
    public synchronized String visualize() {
        return "";
    }
    public ArrayList<FeatureModelTree> getSubfeatures() {
        return subfeatures;
    }
    public synchronized  boolean isSubFeature(FeatureModelTree fmt) {
        return subfeatures.contains(fmt);
    }
    public synchronized void addSubFeature(String name) {
        subfeatures.add(new FeatureModelTree(name));
    }
    public synchronized void rename(String name) {
            this.name=name;
    }
    public synchronized void addlocation(FeatureLocation p) {
        this.location = p;
    }
    public FeatureLocation getLocation() {
        return location;
    }
}