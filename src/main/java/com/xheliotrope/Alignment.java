package com.xheliotrope;

/**
 * rmoore8869
 * 1/3/2015
 */
public class Alignment {

    public Alignment(Sequence sequence1, Sequence sequence2, int score){
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.score = score;
    }
    private int score;
    private Sequence sequence1, sequence2;

    public int getScore(){
        return this.score;
    }
    public Sequence getSequence1(){
        return this.sequence1;
    }
    public Sequence getSequence2(){
        return this.sequence2;
    }
}
