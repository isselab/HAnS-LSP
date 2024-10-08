package se.isselab.HAnS.codeAnnotation;

import java.nio.file.Path;

public class FeatureLocation {
    private Path location;
    private int lineBegin = -1;
    private int lineEnd = -1;

    public FeatureLocation(Path location) {
        this.location = location;
    }

    public FeatureLocation(Path location, int lineBegin){
        this(location);
        this.lineBegin = lineBegin;
        this.lineEnd = lineBegin;
    }

    public FeatureLocation(Path location, int lineBegin, int lineEnd){
        this(location);
        this.lineBegin = lineBegin;
        this.lineEnd = lineEnd;
    }
    public Path getLocation() {
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

    public void setLineBegin(int lineBegin) {
        this.lineBegin = lineBegin;
    }
    public void setLineEnd(int lineEnd) {
        this.lineEnd = lineEnd;
    }
    public boolean hasLines(){
        return lineBegin != -1 && lineEnd != -1;
    }
}
