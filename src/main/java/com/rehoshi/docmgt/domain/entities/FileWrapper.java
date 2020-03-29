package com.rehoshi.docmgt.domain.entities;

public class FileWrapper {
    private String content ;
    private String pathAtServer ;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPathAtServer() {
        return pathAtServer;
    }

    public void setPathAtServer(String pathAtServer) {
        this.pathAtServer = pathAtServer;
    }
}
