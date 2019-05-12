package com.example.aalizade.mbazar_base_app.network.models.credit;

/**
 * Created by sbayatani on 4/22/2018.
 */

public class UploadFileModel {

    private String fileKey;
    private String name;
    private String filePath;
    private String chunks;
    private String chunk;

    public UploadFileModel(String fileKey, String name, String filePath, String chunks, String chunk) {
        this.fileKey = fileKey;
        this.name = name;
        this.filePath = filePath;
        this.chunks = chunks;
        this.chunk = chunk;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getChunks() {
        return chunks;
    }

    public void setChunks(String chunks) {
        this.chunks = chunks;
    }

    public String getChunk() {
        return chunk;
    }

    public void setChunk(String chunk) {
        this.chunk = chunk;
    }

//    @Override
//    public String toString() {
//        return "{\"fileKey\":"+fileKey+",\"name\":"+name+",\"filePath\":"+filePath+",\"chunks\":"+chunks+",\"chunk\":"+chunk+"}";
//    }


    @Override
    public String toString() {
        return "{" +
                "\"fileKey\":\"" + fileKey + '\"' +
                ", \"name\":\"" + name + '\"' +
                ", \"filePath\":\"" + filePath + '\"' +
                ", \"chunks\":\"" + chunks + '\"' +
                ", \"chunk\":\"" + chunk + '\"' +
                '}';
    }
}
