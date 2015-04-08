package com.fewstreet.openlpdb;

/**
 * Created by Peter on 4/8/2015.
 */
public class PlateData {
    public PlateData(String plateString) {
        setPlateString(plateString);
    }
    private String plateString;

    public String getPlateString(){
        return plateString;
    }
    public void setPlateString(String plateString) {
        this.plateString = plateString;
    }

    public String toString(){
        return getPlateString();
    }
}
