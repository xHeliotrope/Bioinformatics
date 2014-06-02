/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package population.genetics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rmoor_000
 */
   public class DNA extends Strand{
      private static final char[] DNAelements = {'A','T','G', 'C'}; 
      private static final char[] Aminoelements = {'L','I','M','F','S','G','R','H','V',
            'Q','W','C','T','D','K','N','Y','E','P','A'};
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
      
        public void SetTo(DNA setto){
            elementstrand=setto.elementstrand;
            }
        public boolean DNALoad()throws IOException{
            return StrandLoad(DNAelements,"DNA Sequence");
            }
        
        private DNA trim(){
            DNA trimmed = new DNA();
            trimmed.Input(trimstrand("ATG",3),DNAelements);
            return trimmed;
            }
        
        private DNA translate(){
            String[] stop={"TAA","TAG","TGA"};
            DNA conversion = new DNA();
            conversion.Input(convertstrand(elementstrand,CodonMap,3,stop),Aminoelements);
            return conversion;
            }
        
        public AminoAcids toAmino(){
            AminoAcids temp= new AminoAcids();
            DNA DNAtemp= new DNA();
            DNAtemp.SetTo(trim());
            DNAtemp.SetTo(DNAtemp.translate());
            temp.Input(DNAtemp.elementstrand,Aminoelements);
            return temp;
        }
        public void Output(){
            Output("DNA Sequence");
        }
    }







    

