package xheliotrope;


import xheliotrope.PhylogenyBuilder.FourSequencePhylogeny;

import java.io.IOException;

/**
 * rmoore8869
 * 12/20/2014
 */
public class RunAlignment {

    static AlignmentFactory aFactory = new AlignmentFactory();

    public static void main(String[] args) throws IOException {
        aFactory.sequenceAlignmentOutput();
    }


}
