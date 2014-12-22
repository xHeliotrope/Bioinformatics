package xheliotrope;

import xheliotrope.SequenceType.*;
import java.io.*;

/**
 * rmoore8869
 * 12/19/2014
 */

public class AlignmentFactory {

    public String sequenceDataRead(String sequenceLocation) throws IOException {
        InputStream input = AlignmentFactory.class.getResourceAsStream(sequenceLocation);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        return reader.readLine();
    }

    public AminoAcid dnaConverter(DNA dna) {
        return new AminoAcid().dnaToAmino(dna);
    }

}
