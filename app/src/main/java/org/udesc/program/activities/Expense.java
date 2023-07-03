package org.udesc.program.model;

public class Expense {
    private String id;
    private String description;
    private String price;
    private String tag;
    private String mouthOfYears;

    public Expense()
    {
    }

    public Expense(String description, String price, String tag, String mouthOfYears)
    {
        this.description = description;
        this.price = price;
        this.tag = tag;
        this.mouthOfYears = mouthOfYears;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public String getMouthOfYears()
    {
        return mouthOfYears;
    }

    public void setMouthOfYears(String mouthOfYears)
    {
        this.mouthOfYears = mouthOfYears;
    }
}
