package xheliotrope.SequenceType;

import xheliotrope.Sequence;

/**
 * rmoore8869
 * 12/19/2014
 */

public class DNA extends Sequence {

    public DNA(String dnaString, String name){
        this.dnaString = dnaString;
        this.name = name;
    }
    private String dnaString;
    private String name;

    public String getName(){
        return this.name;
    }
    public String getSequenceString(){
        return this.dnaString;
    }


}
