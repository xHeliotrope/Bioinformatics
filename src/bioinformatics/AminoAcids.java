/*
 Use freely.
 */

package bioinformatics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *@author Ryan Moore
 */

public class AminoAcids extends Strand {
         //Included both the DNA and AA elements in arrays for error checking
         //purposes
         private static final char[] DNAelements = {'A','T','G','C'}; 
         private static final char[] Aminoelements = {'L','I','M','F','S','G','R','H','V',
            'Q','W','C','T','D','K','N','Y','E','P','A'};
         
         //default strand data later used for creating a dictionary lookup for
         //a 2D String Array

        
         //Inverse of the DNA/AA CodonMap. More of a reference(note: using this 
         //map may result in a super lengthy sequence)
         private static final Map<String, String> inverseCodonMap= new HashMap<String,String>(){{ 
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
        
         //equivalent to the DNA SetTo method, verifies input of the AA 
         //elementstrand
         public void Equals(AminoAcids input){
             elementstrand=input.elementstrand;
             }
         
         //Used to check that the Amino sequence are of Amino elements 
         protected boolean AminoLoad()throws IOException{
             return StrandLoad(Aminoelements,"Amino Sequence");
             }
                
         //equivalent to the DNA trim method for AminoAcid object, used for 
         //consistency given the mehods needed in the strand class(nothing
         //actually gets trimmed)
         public AminoAcids trim(){
             AminoAcids trimmed = new AminoAcids();
             trimmed.elementstrand = trimstrand("",1);
             return trimmed;
             }
        
         //translates the AA object to DNA using the map(theoretical more than
         //practical, given that DNA can't necessarily be determined given only
         //an AA sequence. returns AA object
         public AminoAcids translate(){
             AminoAcids conversion = new AminoAcids();
             String[] stop={""};
             conversion.elementstrand = convertstrand(elementstrand, inverseCodonMap,1,stop);
             return conversion;
             }
         
         //finishes the AA to DNA conversion
         public DNA convert(){
             DNA temp = new DNA();
             temp.Input(trim().translate().elementstrand,DNAelements);
             return temp;
             }
             
         //necessary     
         public void Output(){
             Output("Amino Acid String");
             }
         
         //creates a 2D String Array for AA sequences to match the AA name to 
         //its sequence
         public AminoAcids defaultAminoLoader(){
             AminoAcids temp = new AminoAcids();
             if(defaultStrandNames.length != strandLocations.length){
                 System.out.println("Differing Number of Lengths and Locations, "
                         + "does not compute.");
                 return null;
                 }
             int strandAmounts = defaultStrandNames.length;
             String[][] defaultStrandSequences = new String[strandAmounts][2];
             for(int j = 0; j < strandLocations.length;j++){
                 for(int i = 0; i < 2;i++){
                     defaultStrandSequences[j][0] = defaultStrandNames[j];
                     defaultStrandSequences[j][1] = strandLocations[j];
                 }
             }
             aminoGrid = defaultStrandSequences;
             return temp;
         }
        
}
