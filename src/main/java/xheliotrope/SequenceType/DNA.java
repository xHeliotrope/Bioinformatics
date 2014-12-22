package xheliotrope.SequenceType;

import xheliotrope.Sequence;

/**
 * rmoore8869
 * 12/19/2014
 */

public class DNA extends Sequence {

    public DNA(String dnaString){
        this.dnaString = dnaString;
    }
    private String dnaString;

    public String getDNAString(){
        return this.dnaString;
    }


}
