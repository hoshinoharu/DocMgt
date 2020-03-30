package com.rehoshi.docmgt.domain.entities;

public class FileWrapper {
    /**
     * 文件内容
     */
    private String content ;
    /**
     * 文件在服务器上的位置
     */
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
