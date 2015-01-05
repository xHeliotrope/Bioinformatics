package xheliotrope;

import xheliotrope.PhylogenyBuilder.FourSequencePhylogeny;
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
        Sequence columbiaAA = new AminoAcid(sequenceDataRead("sequences/EstrogenSequences/columbiaLivia.dat"),
                "Columbia Livia", "Rock Dove");
        Sequence danioAA = new AminoAcid(sequenceDataRead("sequences/EstrogenSequences/danioRerio.dat"),
                "Danio Rerio", "Zebra Fish");
        Sequence rutilusAA = new AminoAcid(sequenceDataRead("sequences/EstrogenSequences/rutilusRutilus.dat"),
                "Rutilus Rutilus", "Common Roach[Fish]");
        Sequence torgoAA = new AminoAcid(sequenceDataRead("sequences/EstrogenSequences/torgoStracheliotus.dat"),
                "Torgo Stracheliotus", "Lappet-Faced Vulture");

        FourSequencePhylogeny fourSeq = new FourSequencePhylogeny(columbiaAA, danioAA, rutilusAA, torgoAA);
        fourSeq.outputPhylogenyResult();
    }

    public AminoAcid dnaConverter(DNA dna) {
        return new AminoAcid().dnaToAmino(dna);
    }

}
