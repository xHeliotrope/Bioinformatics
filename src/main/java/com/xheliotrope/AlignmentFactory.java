package com.xheliotrope;

import com.xheliotrope.PhylogenyBuilder.FourSequencePhylogeny;
import com.xheliotrope.SequenceType.*;
import java.io.*;
import java.util.List;

/**
 * rmoore8869
 * 12/19/2014
 */

public class AlignmentFactory {

    public AlignmentFactory(FourSequencePhylogeny fourSequencePhylogeny){
        this.fourSequencePhylogeny = fourSequencePhylogeny;
    }

    private FourSequencePhylogeny fourSequencePhylogeny;
    private List<AminoAcid> sequenceTetrad;

    public void sequenceAlignmentOutput() throws IOException {
        sequenceTetrad = fourSequencePhylogeny.getSequenceList();
        System.out.println("For Estrogen Sequences: ");
        FourSequencePhylogeny fourSeq = new FourSequencePhylogeny(sequenceTetrad);
        fourSeq.outputPhylogenyResult();
        fourSeq.lengthCheck();
    }

    public AminoAcid dnaConverter(DNA dna) {
        String dnaString = dna.getSequenceString();
        String fullName = dna.getFullName();
        return new AminoAcid(dnaString, fullName).dnaToAmino(dna);
    }

}
