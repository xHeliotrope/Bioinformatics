/*
 Use freely.
 */

package bioinformatics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ryan Moore
 */

   public class DNA extends Strand{
       
      //Included both the DNA and AA elements in arrays for error checking
      //purposes
      private static final char[] DNAelements = {'A','T','G','C'}; 
      private static final char[] Aminoelements = {'L','I','M','F','S','G','R',
          'H','V','Q','W','C','T','D','K','N','Y','E','P','A'};
      //Map used to convert DNA triple(Codon) into an Amino Acids
      private final Map<String, String> CodonMap = new HashMap<String,String>(){ 
          { put("ATT", "I");
            put("ATC", "I");
            put("ATA", "I");
            put("CTT", "L");
            put("CTC", "L");
            put("CTA", "L");
            put("CTG", "L");
            put("TTA", "L");
            put("TTG", "L");
            put("GTT", "V");
            put("GTC", "V");
            put("GTA", "V");
            put("GTG", "V");
            put("TTT", "F");
            put("TTC", "F");
            put("ATG", "M");
            put("TGT", "C");
            put("TGC", "C");
            put("GCT", "A");
            put("GCC", "A");
            put("GCA", "A");
            put("GCG", "A");
            put("GGT", "G");
            put("GGC", "G");
            put("GGA", "G");
            put("GGG", "G");
            put("CCT", "P");
            put("CCC", "P");
            put("CCA", "P");
            put("CCG", "P");
            put("ACT", "T");
            put("ACC", "T");
            put("ACA", "T");
            put("ACG", "T");
            put("TCT", "S");
            put("TCC", "S");
            put("TCA", "S");
            put("TCG", "S");
            put("AGT", "S");
            put("AGC", "S");
            put("TAT", "Y");
            put("TAC", "Y");
            put("TGG", "W");
            put("CAA", "Q");
            put("CAG", "Q");
            put("AAT", "N");
            put("AAC", "N");
            put("CAT", "H");
            put("CAC", "H");
            put("GAA", "E");
            put("GAG", "E");
            put("GAT", "D");
            put("GAC", "D");
            put("AAA", "K");
            put("AAG", "K");
            put("CGT", "R");
            put("CGC", "R");
            put("CGA", "R");
            put("CGG", "R");
            put("AGA", "R");
            put("AGG", "R");
            }
            };
        
        //sets the elementstrand to the DNA class
        public void SetTo(DNA setto){
            elementstrand=setto.elementstrand;
            }
        
        //Used to check that the elements of elementstrand are DNA elements
        public boolean DNALoad()throws IOException{
            return StrandLoad(DNAelements,"DNA Sequence");
            }
        
        //Trims the Upstream Leader Sequence of the elementstrand and
        //returns the DNA instance
        private DNA trim(){
            DNA trimmed = new DNA();
            trimmed.Input(trimstrand("ATG",3),DNAelements);
            return trimmed;
            }
        
        //translates the DNA instance to its corresponding AA sequence
        //returns the DNA instance, uses converstrand, Input methods
        private DNA translate(){
            String[] stop={"TAA","TAG","TGA"};
            DNA conversion = new DNA();
            conversion.Input(convertstrand(elementstrand,CodonMap,3,stop),Aminoelements);
            return conversion;
            }
        
        //instatiates AminoAcid and DNA objects, uses SetTo, trim, input methods
        //to modify and verify the returned Amino Acid instance
        public AminoAcids toAmino(){
            AminoAcids temp= new AminoAcids();
            DNA DNAtemp= new DNA();
            DNAtemp.SetTo(trim());
            DNAtemp.SetTo(DNAtemp.translate());
            temp.Input(DNAtemp.elementstrand,Aminoelements);
            return temp;
        }
        
        //correctly names the DNA type for Strand output method
        public void Output(){
            Output("DNA Sequence");
        }
    }
