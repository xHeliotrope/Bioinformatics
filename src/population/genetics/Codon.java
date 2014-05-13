/*
 * Hashmap for Codons and associated Amino Acids
 * 
 * -rmoore
 */

package population.genetics;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Codon{
    static String bpfile = "C:\\Users\\rmoor_000\\Documents\\NetBeans"
            + "Projects\\Population Genetics\\src\\population\\genetics\\"
            + "bpdata.dat";
     
    private static final Map<String, String> cMap;
        static {
        Map<String, String> CodonMap = new HashMap<>(); 
        CodonMap.put("AAT", "I");
        CodonMap.put("ATC", "I");
        CodonMap.put("ATA", "I");
        CodonMap.put("CTT", "L");
        CodonMap.put("CTC", "L");
        CodonMap.put("CTA", "L");
        CodonMap.put("CTG", "L");
        CodonMap.put("TTA", "L");
        CodonMap.put("TTG", "L");
        CodonMap.put("GTT", "V");
        CodonMap.put("GTC", "V");
        CodonMap.put("GTA", "V");
        CodonMap.put("GTG", "V");
        CodonMap.put("TTT", "F");
        CodonMap.put("TTC", "F");
        CodonMap.put("ATG", "--START--M");
        CodonMap.put("TGT", "C");
        CodonMap.put("TGC", "C");
        CodonMap.put("GCT", "A");
        CodonMap.put("GCC", "A");
        CodonMap.put("GCA", "A");
        CodonMap.put("GCG", "A");
        CodonMap.put("GGT", "G");
        CodonMap.put("GGC", "G");
        CodonMap.put("GGA", "G");
        CodonMap.put("GGG", "G");
        CodonMap.put("CCT", "P");
        CodonMap.put("CCC", "P");
        CodonMap.put("CCA", "P");
        CodonMap.put("CCG", "P");
        CodonMap.put("ACT", "T");
        CodonMap.put("ACC", "T");
        CodonMap.put("ACA", "T");
        CodonMap.put("ACG", "T");
        CodonMap.put("TCT", "S");
        CodonMap.put("TCC", "S");
        CodonMap.put("TCA", "S");
        CodonMap.put("TCG", "S");
        CodonMap.put("AGT", "S");
        CodonMap.put("AGC", "S");
        CodonMap.put("TAT", "Y");
        CodonMap.put("TAC", "Y");
        CodonMap.put("TGG", "W");
        CodonMap.put("CAA", "Q");
        CodonMap.put("CAG", "Q");
        CodonMap.put("AAT", "N");
        CodonMap.put("AAC", "N");
        CodonMap.put("CAT", "H");
        CodonMap.put("CAC", "H");
        CodonMap.put("GAA", "E");
        CodonMap.put("GAG", "E");
        CodonMap.put("GAT", "D");
        CodonMap.put("GAC", "D");
        CodonMap.put("AAA", "K");
        CodonMap.put("AAG", "K");
        CodonMap.put("CGT", "R");
        CodonMap.put("CGC", "R");
        CodonMap.put("CGA", "R");
        CodonMap.put("CGG", "R");
        CodonMap.put("AGA", "R");
        CodonMap.put("AGG", "R");
        CodonMap.put("TAA", "STOP");
        CodonMap.put("TAG", "STOP");
        CodonMap.put("TGA", "STOP");
        cMap = Collections.unmodifiableMap(CodonMap);
    }
    
    public static String fileTranslator(String newfile) throws IOException {
        try {
        BufferedReader bpInput = new BufferedReader(new FileReader(newfile));
        String line;
        StringBuilder bpStringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        
        //Code taken from unknown user at stackoverflow.com
        while((line = bpInput.readLine())!= null){
            bpStringBuilder.append(line);
            bpStringBuilder.append(ls);
            return bpStringBuilder.toString();
        }
        // /endcopy
            }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
        }
    /*public static String[] codonCreator(String codonString){
        String start_position = cMap.get("ATG");
        String quit_position1 = cMap.get("TAA");
        String quit_position2 = cMap.get("TAG");
        String quit_position3 = cMap.get("TGA");
        
        
        
        return codonCreator;
    } */
    
    public static void main(String[] args) throws IOException{
        
        String bpFiler = fileTranslator(bpfile);
        System.out.println(bpFiler);
        System.out.println(cMap.get("AAA"));
                    
      
    
}
}

