package com.hfad.starbuzz;

public class Drink {
    private String name;
    private String description;
    private int imageResourcesId;

    public static final Drink[] drinks={
            new Drink("Latte","A couple of espresso shots with a steamed milk",R.drawable.latte),
            new Drink("Cappuccino","Espresso,hot milk,and a steamed milk foam",R.drawable.cappuccino),
            new Drink("Espresso","Highest quality beans roasted and brewed fresh",R.drawable.espresso)
    };
    private Drink(String name, String description,int ImageResourcesId){
        this.name = name;
        this.description = description;
        this.imageResourcesId = ImageResourcesId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourcesId() {
        return imageResourcesId;
    }

  public String toString() {
        return this.name;
  }
}
