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
public class AminoAcids extends Strand {  
         private static final char[] DNAelements = {'A','T','G','C'}; 
         private static final char[] Aminoelements = {'L','I','M','F','S','G','R','H','V',
            'Q','W','C','T','D','K','N','Y','E','P','A'};
        private static final Map<String, String> CodonMap= new HashMap<String,String>(){{ 
        put("I","([ATT][ATC][ATA])");
        put("L", "([CTT][CTC][CTA][CTG][TTA][TTG])");
        put("V", "([GTT][GTC][GTA][GTG])");
        put("F", "([TTT][TTC])");
        put("M", "ATG");
        put("C", "([TGT][TGC])");
        put("A", "([GCT][GCC][GCA][GCG])");
        put("G", "([GGT][GGC][GGA][GGG])");
        put("P", "([CCT][CCC][CCA][CCG])");
        put("T", "([ACT][ACC][ACA][ACG])");
        put("S", "([TCT][TCC][TCA][TCG])");
        put("Y", "([TAT][TAC])");
        put("W", "TGG");
        put("Q", "([CAA][CAG])");
        put("N", "([AAT][AAC])");
        put("H", "([CAT][CAC])");
        put("E", "([GAA][GAG])");
        put("D", "([GAT][GAG])");
        put("K", "([AAA][AAG])");
        put("R", "([CGT][CGC][CGA][CGG][AGA][AGG])");
        put("STOP", "([TAA][TAG][TGA])");
        }};
        
        public void Equals(AminoAcids input){
            elementstrand=input.elementstrand;
            }
        protected boolean AminoLoad()throws IOException{
            return StrandLoad(Aminoelements,"Amino Sequence");
            }
        
        public AminoAcids trim(){
            AminoAcids trimmed = new AminoAcids();
            trimmed.elementstrand = trimstrand("",1);
            return trimmed;
            }
        
        public AminoAcids translate(){
            AminoAcids conversion = new AminoAcids();
            String[] stop={""};
            conversion.elementstrand = convertstrand(elementstrand, CodonMap,1,stop);
            return conversion;
            }
        
        public DNA convert(){
            DNA temp = new DNA();
            temp.Input(trim().translate().elementstrand,DNAelements);
            return temp;
        }
        public void Output(){
            Output("Amino Acid String");
        }
        /*needs checking*/
        public char[] alignmentPrep(String elementstrand){
            int j = elementstrand.length();
            int k = j+1;
            int f = j-1;
            int i = 0;
            int h = 1;
            char[] aminoAlignment = new char[k];

            while(i<j){
                aminoAlignment[h]=elementstrand.charAt(i);
                i++;
                h++;
                }
                return aminoAlignment;
}
        public AminoAcids aaPrep(){
            AminoAcids temp = new AminoAcids();
            temp.alignmentPrep(elementstrand);
            return temp;
            
        }

        // endcheck
}
