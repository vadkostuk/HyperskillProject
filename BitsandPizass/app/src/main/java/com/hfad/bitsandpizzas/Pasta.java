package com.hfad.bitsandpizzas;

public class Pasta {
    private String name;
    private int imageResourceId;

    public static final Pasta[] pastas={
            new Pasta("Bolognese",R.drawable.bolognese),
            new Pasta("Carbonara",R.drawable.carbonara),
            new Pasta("Capreze",R.drawable.capreze)
    };

    private Pasta(String name, int imageResourceId){
        this.name=name;
        this.imageResourceId = imageResourceId;
    }

    public String getName(){
        return name;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }
}
