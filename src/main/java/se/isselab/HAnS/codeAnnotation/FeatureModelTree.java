package se.isselab.HAnS.codeAnnotation;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FeatureModelTree {



    private String name;
    private ArrayList<FeatureModelTree> subfeatures;
    private boolean isOptional = false;
    private  int FeatureLine;
    private  int FeatureStart;
    private  int FeatureEnd;
    private ArrayList<FeatureLocation> location;
    private FeatureModelTree parent;
    public FeatureModelTree(FeatureModelTree parent) {
        subfeatures = new ArrayList<FeatureModelTree>();
        this.location = new ArrayList<>();
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
        location.add(p);
    }
    public ArrayList<FeatureLocation> getLocation() {
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
    public boolean getisOptional() {
        return isOptional;
    }

    public void setFeatureLine(int featureLine) {
        FeatureLine = featureLine;
    }
    public int getFeatureLine() {
        return FeatureLine;
    }

    public ArrayList<String> getDuplicates(){
        ArrayList<String> visited = new ArrayList<>();
        ArrayList<String> duplicates = new ArrayList<>();
        this.lookforduplicates(visited);
        for(int i = 0 ; i < visited.size() ; i++){
            String comp = visited.get(i);
            for(int j = 0 ; j < visited.size() ; j++){
                if(comp.equals(visited.get(j)) && j!=i ){
                    duplicates.add(comp);
                }
            }
        }
        return duplicates;
    }
    private void lookforduplicates(ArrayList<String> visited) {
        if(this.name != null) {
            visited.add(name);
        }
        for (FeatureModelTree fmt : subfeatures){
            fmt.lookforduplicates(visited);
        }
    }
    public ArrayList<String> getDuplicatesWithParrent(){
        ArrayList<String> duplicates = this.getDuplicates();
        ArrayList<String> replaced = new ArrayList<>();
        if (!duplicates.isEmpty()) {
        this.replaceduplicates(replaced,duplicates);
        }

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
            fmt.replaceduplicates(replaced,duplicates);
        }
    }

    public FeatureModelTree search(String name){
        return searchForTree(this,name);
    }

    private FeatureModelTree searchForTree(FeatureModelTree tree, String name) {

        if (tree.name != null) {
            if(tree.getName().equalsIgnoreCase(name)){
                return tree;
            }
        }
        if (tree.getSubfeatures().isEmpty()) {
            return null;
        } else {
            for (FeatureModelTree tree1 : tree.getSubfeatures()) {
                if(searchForTree(tree1, name)!= null){
                    return searchForTree(tree1, name);
                }

            }
        }
        return null;
    }

    public FeatureModelTree getChiled(String name){
        for (FeatureModelTree fmt : subfeatures) {
            if(fmt.name.equalsIgnoreCase(name)){
                return fmt;
            }
        }
        return null;
    }

    public int getFeatureEnd() {
        return FeatureEnd;
    }

    public int getFeatureStart() {
        return FeatureStart;
    }

    public void setFeatureEnd(int featureEnd) {
        FeatureEnd = featureEnd;
    }

    public void setFeatureStart(int featureStart) {
        FeatureStart = featureStart;
    }

    public void removeLocationFile(String uri){
        this.location.removeIf(fl -> fl.getLocation().equals(uri));
        for (FeatureModelTree fmt : subfeatures) {
            fmt.removeLocationFile(uri);
        }
    }

    public ArrayList<FeatureModelTree> getDuplicateTrees(){
        ArrayList<String> duplicatenames = this.getDuplicates();
        ArrayList<FeatureModelTree> duplicates = new ArrayList<>();
        this.lookforduplicatetrees(duplicates,duplicatenames);
        return duplicates;

    }
    private void lookforduplicatetrees(ArrayList<FeatureModelTree> duplicates, ArrayList<String> duplicatenames) {
        if(duplicatenames.contains(this.getName())) {
            duplicates.add(this);
        }
        for (FeatureModelTree fmt : subfeatures){
            fmt.lookforduplicatetrees(duplicates,duplicatenames);
        }
    }

}