package com.xheliotrope;
import org.junit.Assert;
import org.junit.Test;
import xheliotrope.*;
import xheliotrope.SequenceType.*;
import java.io.IOException;

/**
 * rmoore8869
 * 12/16/2014
 */

public class AlignmentTest{

    AlignmentFactory aFactory = new AlignmentFactory();

    private boolean isValidNucleicAcid(String dna) {
        boolean valid = true;
        char[] fastCheck = dna.toCharArray();
        for (char elements: fastCheck) {
            valid = (elements == 'a' ||
                     elements == 'c' ||
                     elements == 't' ||
                     elements == 'g' );
            if (!valid) {
                break;
            }
        }
        return valid;
    }

    private boolean isValidAminoAcid(String aminoAcid) {
        boolean valid = true;
        char[] fastCheck = aminoAcid.toCharArray();
        for (char elements: fastCheck)
        {
            valid = (elements == 'a' ||
                    elements == 'c' ||
                    elements == 'd' ||
                    elements == 'e' ||
                    elements == 'f' ||
                    elements == 'g' ||
                    elements == 'h' ||
                    elements == 'i' ||
                    elements == 'k' ||
                    elements == 'l' ||
                    elements == 'm' ||
                    elements == 'n' ||
                    elements == 'p' ||
                    elements == 'q' ||
                    elements == 'r' ||
                    elements == 's' ||
                    elements == 't' ||
                    elements == 'v' ||
                    elements == 'w' ||
                    elements == 'y');
            if (!valid) {
                break;
            }
        }
        return valid;
    }

    private void dnaInputChecker(String testLocation) throws IOException {
        String sequenceString = aFactory.sequenceDataRead(testLocation);
        Assert.assertTrue(isValidNucleicAcid(sequenceString));
    }

    private void aminoInputChecker(String testLocation) throws IOException {
        String sequenceString = aFactory.sequenceDataRead(testLocation);
        Assert.assertTrue(isValidAminoAcid(sequenceString));
    }

    @Test
    public void dnaInputTest() throws IOException {
        Throwable caught = null;
        try {
            dnaInputChecker("sequences/testSequences/notNullRegularNucleicSequence.dat");
            aminoInputChecker("sequences/testSequences/notNullAminoAcidSequence.dat");
        }
        catch(NullPointerException e){
            System.out.println("Tried to read empty file error.");
            caught = e;
        }
       Assert.assertSame(null, caught);
    }

    @Test
    public void dnaToAaTest() throws IOException {
        DNA dna1 = new DNA(aFactory.sequenceDataRead("sequences/testSequences/notNullRegularNucleicSequence.dat"));
        AminoAcid fromDNA1 = aFactory.dnaConverter(dna1);
        System.out.println("Length of AA String: " + fromDNA1.getSequenceString().length() + '\n'
                + "Length of DNA String: " + dna1.getSequenceString().length());
        Assert.assertTrue(fromDNA1.getSequenceString().length()*3 <= dna1.getSequenceString().length());
    }

    @Test(expected = ImproperDNASequenceException.class)
    public void dnaToAaWithImproperLengthTest() throws IOException{
        System.out.println("In Test with Errors 1: {");
        DNA dna2 = new DNA(aFactory.sequenceDataRead("sequences/testSequences/withImproperLengthNucleicSequence.dat"));
        AminoAcid fromDNA1 = aFactory.dnaConverter(dna2);
        System.out.println("}");
    }

}
