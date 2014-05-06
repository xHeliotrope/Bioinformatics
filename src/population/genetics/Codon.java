/*
 * Converts a (as of now) Hardcoded Codon to an AminoAcid
 * using HashMap method
 * -rmoore
 */

package population.genetics;

import java.util.HashMap;

public class Codon {
   
    static String DNACodon = "ATG";
    static HashMap<String, String> AminoConverter = new HashMap();
    
    public static HashMap CodonMap(HashMap<String, String> AminoConverter){
        AminoConverter.put("AAT", "I");
        AminoConverter.put("ATC", "I");
        AminoConverter.put("ATA", "I");
        AminoConverter.put("CTT", "L");
        AminoConverter.put("CTC", "L");
        AminoConverter.put("CTA", "L");
        AminoConverter.put("CTG", "L");
        AminoConverter.put("TTA", "L");
        AminoConverter.put("TTG", "L");
        AminoConverter.put("GTT", "V");
        AminoConverter.put("GTC", "V");
        AminoConverter.put("GTA", "V");
        AminoConverter.put("GTG", "V");
        AminoConverter.put("TTT", "F");
        AminoConverter.put("TTC", "F");
        AminoConverter.put("ATG", "--START--M");
        AminoConverter.put("TGT", "C");
        AminoConverter.put("TGC", "C");
        AminoConverter.put("GCT", "A");
        AminoConverter.put("GCC", "A");
        AminoConverter.put("GCA", "A");
        AminoConverter.put("GCG", "A");
        AminoConverter.put("GGT", "G");
        AminoConverter.put("GGC", "G");
        AminoConverter.put("GGA", "G");
        AminoConverter.put("GGG", "G");
        AminoConverter.put("CCT", "P");
        AminoConverter.put("CCC", "P");
        AminoConverter.put("CCA", "P");
        AminoConverter.put("CCG", "P");
        AminoConverter.put("ACT", "T");
        AminoConverter.put("ACC", "T");
        AminoConverter.put("ACA", "T");
        AminoConverter.put("ACG", "T");
        AminoConverter.put("TCT", "S");
        AminoConverter.put("TCC", "S");
        AminoConverter.put("TCA", "S");
        AminoConverter.put("TCG", "S");
        AminoConverter.put("AGT", "S");
        AminoConverter.put("AGC", "S");
        AminoConverter.put("TAT", "Y");
        AminoConverter.put("TAC", "Y");
        AminoConverter.put("TGG", "W");
        AminoConverter.put("CAA", "Q");
        AminoConverter.put("CAG", "Q");
        AminoConverter.put("AAT", "N");
        AminoConverter.put("AAC", "N");
        AminoConverter.put("CAT", "H");
        AminoConverter.put("CAC", "H");
        AminoConverter.put("GAA", "E");
        AminoConverter.put("GAG", "E");
        AminoConverter.put("GAT", "D");
        AminoConverter.put("GAC", "D");
        AminoConverter.put("AAA", "K");
        AminoConverter.put("AAG", "K");
        AminoConverter.put("CGT", "R");
        AminoConverter.put("CGC", "R");
        AminoConverter.put("CGA", "R");
        AminoConverter.put("CGG", "R");
        AminoConverter.put("AGA", "R");
        AminoConverter.put("AGG", "R");
        AminoConverter.put("TAA", "STOP");
        AminoConverter.put("TAG", "STOP");
        AminoConverter.put("TGA", "STOP");
        return AminoConverter;
    }
    
    public static void main(String[] args){
        HashMap CodonMap;
        CodonMap = Codon.CodonMap(AminoConverter);
  
    System.out.println(CodonMap.get(DNACodon));
    
}
}
