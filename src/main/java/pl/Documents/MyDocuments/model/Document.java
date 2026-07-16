package pl.Documents.MyDocuments.model;

import java.util.Date;

public class Document {
    private String name;
    private Type type;
    private String location;
    private Date created;
    private Date modified;

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }
}
