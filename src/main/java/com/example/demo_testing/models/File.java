package com.example.demo_testing.models;

public class File extends Store{
    private String parentName;
    private String content;

    public File(String name, User owner, String content) {
        super(name, owner);
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    @Override
    public void propagatePermission() {}
    public void delete() {
        isDeleted = true;
    }
}
