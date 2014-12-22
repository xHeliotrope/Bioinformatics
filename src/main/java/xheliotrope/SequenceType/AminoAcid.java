package xheliotrope.SequenceType;

import xheliotrope.Sequence;
import java.util.*;

/**
 * rmoore8869
 * 12/19/2014
 */

public class AminoAcid extends Sequence {

    public AminoAcid(){}
    public AminoAcid(String aaString){
        this.aaString = aaString;
    }

    private String aaString;
    private String[] stopCodon = {"tag", "taa", "tga"};
    private final Map<String, String> codonMap = new HashMap<String,String>(){

        {   put("att", "i");
            put("atc", "i");
            put("ata", "i");
            put("ctt", "l");
            put("ctc", "l");
            put("cta", "l");
            put("ctg", "l");
            put("tta", "l");
            put("ttg", "l");
            put("gtt", "v");
            put("gtc", "v");
            put("gta", "v");
            put("gtg", "v");
            put("ttt", "f");
            put("ttc", "f");
            put("atg", "m");
            put("tgt", "c");
            put("tgc", "c");
            put("gct", "a");
            put("gcc", "a");
            put("gca", "a");
            put("gcg", "a");
            put("ggt", "g");
            put("ggc", "g");
            put("gga", "g");
            put("ggg", "g");
            put("cct", "p");
            put("ccc", "p");
            put("cca", "p");
            put("ccg", "p");
            put("act", "t");
            put("acc", "t");
            put("aca", "t");
            put("acg", "t");
            put("tct", "s");
            put("tcc", "s");
            put("tca", "s");
            put("tcg", "s");
            put("agt", "s");
            put("agc", "s");
            put("tat", "y");
            put("tac", "y");
            put("tgg", "w");
            put("caa", "q");
            put("cag", "q");
            put("aat", "n");
            put("aac", "n");
            put("cat", "h");
            put("cac", "h");
            put("gaa", "e");
            put("gag", "e");
            put("gat", "d");
            put("gac", "d");
            put("aaa", "k");
            put("aag", "k");
            put("cgt", "r");
            put("cgc", "r");
            put("cga", "r");
            put("cgg", "r");
            put("aga", "r");
            put("agg", "r");
        }
    };

    public String getAAString(){
        return this.aaString;
    }

    public AminoAcid dnaToAmino(DNA dna){
        String cutSequence = cutUpStreamSequence(dna.getDNAString());
        return convertStrand(cutSequence);
    }

    private String cutUpStreamSequence(String precutSequence){
        String codonCheck = "";
        for(int i = 0; i < precutSequence.length()-5; i++){
            codonCheck = precutSequence.substring(i, i+3);
            if(codonCheck.equals("atg")){
                return precutSequence.substring(i, precutSequence.length());
            }
        }
        System.out.println("Warning: Could not trim sequence");
        return precutSequence;
    }

    private AminoAcid convertStrand(String dnaString){
        String newAminoSequence = "";
        String codon = "";
        try {
            for (int i = 0; i < dnaString.length(); i += 3) {
                codon = dnaString.substring(i, i + 3);
                for (String terminator : stopCodon) {
                    if (codon.equals(terminator)) {
                        return new AminoAcid(newAminoSequence);
                    }
                }
                newAminoSequence += codonMap.get(codon);
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error in readable region of DNA");
        }
        System.out.println("Potential Error: never ran into a terminator codon during translation");
        return new AminoAcid(newAminoSequence);
    }

}
