package se.isselab.HAnS.codeAnnotation;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FeatureModelTree {


    private String name;
    private ArrayList<FeatureModelTree> subfeatures;
    private boolean isOptional = false;
    private  int FeatureLine;
    private FeatureLocation location;
    private FeatureModelTree parent;
    public FeatureModelTree(FeatureModelTree parent) {
        subfeatures = new ArrayList<FeatureModelTree>();
        this.parent = parent;
    }
    public FeatureModelTree(FeatureModelTree parent,String name) {
        this(parent);
        this.name = name;
    }
    public FeatureModelTree(FeatureModelTree parent,String name,int line, boolean isOptional) {
        this(parent);
        this.name = name;
        this.isOptional = isOptional;
        this.FeatureLine = line;
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
        subfeatures.add(new FeatureModelTree(this,name));
    }
    public synchronized void addSubFeatureTree(FeatureModelTree t) {
        subfeatures.add(t);
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
    public String PreorderNames(){
        String childrenrep = "";
        for (FeatureModelTree fmt : subfeatures){
            childrenrep += " " + fmt.PreorderNames();
        }
        return (name + " " + childrenrep );
    }
    public String toString(){
        String childrenrep = "";
        for (FeatureModelTree fmt : subfeatures){
            childrenrep += "\n" + fmt.toString();
        }
        return (name +"[ " + childrenrep + "]" );
    }
    private void addName(ArrayList<String> names, FeatureModelTree fmt) {
        names.add(this.name);
        for (int i = 0; i < fmt.getSubfeatures().size(); i++) {
            addName(names,fmt.getSubfeatures().get(i));
        }
    }

    public FeatureModelTree getParent() {
        return parent;
    }
    public boolean istlocationcorrect (){
        return location.getLineBegin() != -1 && location.getLineEnd() != -1;
    }
    public boolean checkTree(){
        if (subfeatures.isEmpty()){
            return location.hasLines();
            // we could also check for a name that ist not "undefinedName"
        }
        for (FeatureModelTree fmt:subfeatures){
           if(!fmt.checkTree() || !fmt.istlocationcorrect()){
               return false;
           }

        }
        return true;

    }
    public boolean getisOptional() {
        return isOptional;
    }

    public void setFeatureLine(int featureLine) {
        FeatureLine = featureLine;
    }
    public int getFeatureLine() {
        return FeatureLine;
    }

    public Optional<ArrayList<String>> getDuplicates(){
        ArrayList<String> visited = new ArrayList<>();
        ArrayList<String> duplicates = new ArrayList<>();
        this.lookforduplicates(visited,duplicates);
        return Optional.of(duplicates);
    }
    private void lookforduplicates(ArrayList<String> duplicates, ArrayList<String> visited) {
        if (visited.contains(name)){
            duplicates.add(name);
        }
        visited.add(name);
        for (FeatureModelTree fmt : subfeatures){
            fmt.lookforduplicates(duplicates,visited);
        }
    }
    public ArrayList<String> getDuplicatesWithParrent(){
        ArrayList<String> duplicates = new ArrayList<>();
        if (this.getDuplicates().isPresent()) {
        duplicates = this.getDuplicates().get();
        }
        ArrayList<String> replaced = new ArrayList<>();

        this.replaceduplicates(replaced,duplicates);

        return replaced;
    }
    private void replaceduplicates(ArrayList<String> replaced, ArrayList<String> duplicates) {
        if (duplicates.contains(name)){
            if(this.getParent()!=null){
                replaced.add(this.getParent().getName() + "::" + name);
            }
            else {
                replaced.add("::" + name);
            }


        }
        for (FeatureModelTree fmt : subfeatures){
            fmt.lookforduplicates(replaced,duplicates);
        }
    }
}