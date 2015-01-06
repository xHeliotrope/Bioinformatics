package com.xheliotrope.SequenceType;

import com.xheliotrope.Sequence;

/**
 * rmoore8869
 * 12/19/2014
 */

public class DNA extends Sequence {

    public DNA(String dnaString, String fullName){
        this.dnaString = dnaString;
        this.fullName = fullName;
    }
    private String dnaString, fullName;


    public String getSequenceString() { return this.dnaString; }
    public String getFullName() { return this.fullName; }


}
