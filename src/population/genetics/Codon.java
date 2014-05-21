
/*
 * Hashmap for Codons and associated Amino Acids
 * 
 * -rmoore
 */

package population.genetics;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import static java.nio.charset.Charset.defaultCharset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class Codon{
    static String bpfile = "C:\\Users\\rmoor_000\\Documents\\NetBeans"
            + "Projects\\Population Genetics\\src\\population\\genetics\\"
            + "bpdata3.dat";
    
    
    
    
    private static final Map<String, String> cMap;
        static {
        Map<String, String> CodonMap = new HashMap<>(); 
        CodonMap.put("ATT", "I");
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
        CodonMap.put("ATG", "M");
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
        
        public static String aminoconvert(String aminoStartCut){
            String aminostring = new String();
            int modified_length = aminoStartCut.length()-4;
            for(int i = 0; i < modified_length; i+=3){
                String temp = new String();
                temp += aminoStartCut.charAt(i);
                temp += aminoStartCut.charAt(i+1);
                temp += aminoStartCut.charAt(i+2);
                if ("TAA".equals(temp) || "TAG".equals(temp) || 
                        "TGA".equals(temp)){
                    return aminostring;
                    }
                else{
                    aminostring += cMap.get(temp); 
                    }
                    }
            
            return aminostring;
                }
        
        public static String codingregion(String bpairs){
            int startindex = bpairs.indexOf("ATG");
            String aminoStartCut = new String();
            while (startindex < bpairs.length()){
                aminoStartCut += Character.toString(bpairs.charAt(startindex));
                startindex++;
                }
            return aminoStartCut;
            }
        
        public static String fileTranslator(String filename, Charset encoding) 
                throws IOException {
            try {
                byte[] encoded = Files.readAllBytes(Paths.get(filename));
                String withspaces = new String(encoded, encoding);
                String nospaces = withspaces.replaceAll("\\r|\\n", "");
                return nospaces;
                }
                catch(NoSuchFileException e){
                    System.out.println(e.getMessage());
                    System.out.println("hey there isn't a file");
                    System.exit(0);
                }
                return null;
                }
        
        public static String fileGrabber() throws IOException{
            try {
            String def = "Default";
            String userinput;
            System.out.println("Enter a file path or enter 'default'");
            BufferedReader brinput = new BufferedReader
                                            (new InputStreamReader(System.in));
            userinput = brinput.readLine();
            if (userinput.equalsIgnoreCase(def)){
                userinput = bpfile;
                }   
            return userinput;
                }
            catch(InputMismatchException e){
                e.getMessage();
                }
            return null;
                    }
        
    public static void main(String[] args) throws IOException{
        try {
            Charset encoding = defaultCharset();
            String firstpath = fileGrabber();
            String bpairs = fileTranslator(firstpath, encoding);
            String aminostring = codingregion(bpairs);
            String finishedprod = aminoconvert(aminostring);
            System.out.print(finishedprod + "\n                                A");
            System.out.flush();
            }
        
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            
            }
        }
        }
