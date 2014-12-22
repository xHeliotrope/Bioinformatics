package xheliotrope;

import xheliotrope.SequenceType.*;

import java.io.IOException;

/**
 * rmoore8869
 * 12/20/2014
 */
public class RunAlignment {

    static AlignmentFactory aFactory = new AlignmentFactory();
    public static void main(String[] args) throws IOException {
        DNA dna = new DNA(aFactory.sequenceDataRead("sequences/testSequences/notNullRegularNucleicSequence.dat"));
        AminoAcid fromDNA = aFactory.dnaConverter(dna);
        System.out.println(fromDNA.getAAString());
    }

}
