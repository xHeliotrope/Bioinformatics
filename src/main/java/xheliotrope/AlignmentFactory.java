package xheliotrope;

import xheliotrope.SequenceType.*;
import java.io.*;

/**
 * rmoore8869
 * 12/19/2014
 */

public class AlignmentFactory {

    public AlignmentFactory(){}

    public String sequenceDataRead(String sequenceLocation) throws IOException {
        InputStream input = AlignmentFactory.class.getResourceAsStream(sequenceLocation);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder sequenceString = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null){
            sequenceString.append(line).append("\n");
        }
        return sequenceString.toString();
    }

    public void sequenceAlignmentOutput() throws IOException{
        Sequence columbiaAA = new AminoAcid(sequenceDataRead("sequences/EstrogenSequences/columbiaLivia.dat"));
        Sequence danioAA = new AminoAcid(sequenceDataRead("sequences/EstrogenSequences/danioRerio.dat"));
        Sequence rutilusAA = new AminoAcid(sequenceDataRead("sequences/EstrogenSequences/rutilusRutilus.dat"));
        Sequence torgoAA = new AminoAcid(sequenceDataRead("sequences/EstrogenSequences/torgoStracheliotus.dat"));

        new AlignmentMatrix(columbiaAA, torgoAA).viewAlignmentBetweenSequences();
        new AlignmentMatrix(rutilusAA, danioAA).viewAlignmentBetweenSequences();



    }

    public AminoAcid dnaConverter(DNA dna) {
        return new AminoAcid().dnaToAmino(dna);
    }

}
