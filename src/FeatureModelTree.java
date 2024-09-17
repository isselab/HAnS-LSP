import java.util.ArrayList;

public class FeatureModelTree {
    String name;
    ArrayList<FeatureModelTree> subfeatures;
    ArrayList locations;
    public FeatureModelTree() {
        subfeatures = new ArrayList<FeatureModelTree>();
        Data = new ArrayList();
    }
    public FeatureModelTree(String name) {
        this();
        this.name = name;
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
}