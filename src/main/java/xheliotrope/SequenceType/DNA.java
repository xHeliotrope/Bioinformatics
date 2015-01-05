package xheliotrope.SequenceType;

import xheliotrope.Sequence;

/**
 * rmoore8869
 * 12/19/2014
 */

public class DNA extends Sequence {

    public DNA(String dnaString, String latinName, String commonName){
        this.dnaString = dnaString;
        this.latinName = latinName;
        this.commonName = commonName;
    }
    private String dnaString, latinName, commonName;

    public String getFullName() { return latinName + " (" + commonName + ")"; }
    public String getLatinName() { return this.latinName; }
    public String getCommonName(){
        return this.commonName;
    }
    public String getSequenceString(){
        return this.dnaString;
    }


}
