package com.xheliotrope;

import com.xheliotrope.SequenceType.AminoAcid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * rmoore8869
 * 12/19/2014
 */
public class Sequence {

    public Sequence(){}
    public Sequence(String mySequence, String fullName){
        this.mySequence = mySequence;
        this.fullName = fullName;
    }

    private String mySequence, fullName;


    public String getFullName() { return this.fullName;}
    public String getSequenceString(){
        return this.mySequence;
    }





}
