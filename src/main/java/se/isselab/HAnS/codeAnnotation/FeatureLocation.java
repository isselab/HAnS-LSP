package se.isselab.HAnS.codeAnnotation;

enum type{
    File,
    Folder,
    other
}

public class FeatureLocation {



    private String location;
    private int lineBegin = -1;
    private int lineEnd = -1;
    private int charBegin;
    private int charEnd;
    private type resourceType;

    public FeatureLocation(String location, type resourceType) {
        this.location = location;
        this.resourceType = resourceType;
    }

    public FeatureLocation(String location, type resourceType, int lineBegin){
        this(location, resourceType);
        this.lineBegin = lineBegin;
        this.lineEnd = lineBegin;
    }

    public FeatureLocation(String location, type resourceType, int lineBegin, int lineEnd){
        this(location , resourceType);
        this.lineBegin = lineBegin;
        this.lineEnd = lineEnd;
    }
    public String getLocation() {
        return location;
    }
    public int getLineBegin() {
        return lineBegin;
    }
    public int getLineEnd() {
        return lineEnd;
    }
    public boolean isSingelLine(){
        return (lineBegin == lineEnd);
    }

    public boolean isFolder() {
        return (resourceType == type.Folder);
    }
    public type getType() {
        return resourceType;
    }

    public void setLineBegin(int lineBegin) {
        this.lineBegin = lineBegin;
    }
    public void setLineEnd(int lineEnd) {
        this.lineEnd = lineEnd;
    }
    public boolean hasLines(){
        return lineBegin != -1 && lineEnd != -1;
    }

    public int getCharBegin() {
        return charBegin;
    }
    public int getCharEnd() {
        return charEnd;
    }
    public void setCharBegin(int charBegin) {
        this.charBegin = charBegin;
    }
    public void setCharEnd(int charEnd) {
        this.charEnd = charEnd;
    }
}
