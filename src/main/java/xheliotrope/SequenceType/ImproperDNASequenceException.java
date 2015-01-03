package xheliotrope.SequenceType;

/**
 * rmoore8869
 * 12/21/2014
 */
public class ImproperDNASequenceException extends Exception {

    public ImproperDNASequenceException(){
        System.out.println("No stop codon found.");
    }
}
