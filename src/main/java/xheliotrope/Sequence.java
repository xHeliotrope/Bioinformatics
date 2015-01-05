package xheliotrope;

/**
 * rmoore8869
 * 12/19/2014
 */
public abstract class Sequence {
    public Sequence(){}
    public Sequence(String mySequence, String latinName, String commonName){
        this.mySequence = mySequence;
        this.latinName = latinName;
        this.commonName = commonName;
    }

    private String mySequence, latinName, commonName;

    public abstract String getFullName();
    public abstract String getLatinName();
    public abstract String getCommonName();
    public abstract String getSequenceString();
}
