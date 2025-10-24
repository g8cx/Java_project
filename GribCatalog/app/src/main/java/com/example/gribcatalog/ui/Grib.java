package com.example.gribcatalog.ui;

public class Grib {
    private String name;
    private String shortDesc;
    private String longDesc;
    private int imageResId;

    public Grib(String name, String shortDesc, String longDesc, int imageResId) {
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public int getImageResId() {
        return imageResId;
    }
}