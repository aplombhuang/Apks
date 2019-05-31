package team4.drugapp;

/**
 * Created by APLOMB on 4/15/2017.
 * This is the drug class for drug objects.
 * It stores: drug brand name, drug ingredient name, drug amount, drug dose(pills per time), drug refuills, drug description, drug use condition;
 * methods: there will be getter and setter for all the information above, there will also be a updater method for drug amount and drug refill;
 */

public class Drugs {

    private String brand_Name = "";
    private String drug_ID = "";
    private String struct_Name = "";
    private int drug_Amount = 0;
    private int drug_Dose = 0;
    private String drug_Descript = "";
    private String drug_Condition = "";
    private int drug_Refill = 0;

    public Drugs(){update_Amount();}

    public void setBrand_Name(String str){brand_Name = str;}

    public String getBrand_Name() {
        return brand_Name;
    }

    public void setDrug_ID (String str){drug_ID = str;}

    public String getDrug_ID(){return drug_ID;}

    public void setStruct_Name(String str){struct_Name = str;}

    public String getStruct_Name() {
        return struct_Name;
    }

    public void setDrug_Amount(int n){drug_Amount = n;}

    public int getDrug_Amount(){return drug_Amount;}

    public void setDrug_Refill(int n){drug_Refill = n;}

    public int getDrug_Refill(){return drug_Refill;}

    public void setDrug_Dose(int n){drug_Dose = n;}

    public int getDrug_Dose() {
        return drug_Dose;
    }

    public void setDrug_Descript(String str){ drug_Descript = str;}

    public String getDrug_Descript() {
        return drug_Descript;
    }

    public void setDrug_Condition(String str) {drug_Condition = str;}

    public String getDrug_Condition() {
        return drug_Condition;
    }

    public void update_Amount(){drug_Amount = drug_Amount - drug_Dose;}

    public void update_Refill(){drug_Refill--;}
}
