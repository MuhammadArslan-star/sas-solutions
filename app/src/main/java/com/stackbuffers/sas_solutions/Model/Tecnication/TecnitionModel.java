package com.stackbuffers.sas_solutions.Model.Tecnication;

import java.io.Serializable;

public class TecnitionModel implements Serializable {
    int id;
    String name;
    String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
