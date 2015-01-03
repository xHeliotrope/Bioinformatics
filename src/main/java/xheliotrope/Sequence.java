package xheliotrope;

/**
 * rmoore8869
 * 12/19/2014
 */
public abstract class Sequence {
    public Sequence(){}
    public Sequence(String mySequence, String sequenceName){
        this.sequenceName = sequenceName;
    }

    private String sequenceName;

    public String getSequenceName(){
        return this.sequenceName;
    }

    public abstract String getSequenceString();
}
