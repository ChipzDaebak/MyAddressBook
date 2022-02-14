package com.hfad.myaddressbook.model;

public class Timezone {
    private String offset, description;

    public Timezone(String offset, String description) {
        this.offset = offset;
        this.description = description;
    }

    public String getOffset() {
        return offset;
    }

    public String getDescription() {
        return description;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
