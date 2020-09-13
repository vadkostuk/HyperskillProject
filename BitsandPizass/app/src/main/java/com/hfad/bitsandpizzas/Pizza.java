package com.hfad.bitsandpizzas;

public class Pizza {
    private String name;
    private int descriptionOfPizza;
    private int imageResourceId;
    public static final Pizza[] pizzas = {
        new Pizza("Diavolo",R.drawable.diavolo , R.string.create_order),
        new Pizza("Funghi",R.drawable.funghi , R.string.pasta_tab)
    };

    private Pizza(String name,int imageResourceId , int descriptionOfPizza){
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.descriptionOfPizza = descriptionOfPizza;
    }
    public String getName(){
        return name;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }
    public int getDescriptionOfPizza(){
        return descriptionOfPizza;
    }

}
