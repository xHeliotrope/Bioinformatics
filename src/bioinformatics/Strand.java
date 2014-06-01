/*
 Use freely(except on homework).
 */
package bioinformatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import static java.nio.charset.Charset.defaultCharset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Ryan Moore
 */

  public class Strand {
      
      public String elementstrand=new String();
      
      //creates a 2-D Array as a sort of dictionary for Default Strand Sequences  
      protected String[][] defaultStrandLoader(String[] defaultStrandNames,
                                                    String[] strandLocations){
        if(defaultStrandNames.length != strandLocations.length){
            System.out.println("Differing Number of Lengths and Locations, "
                                                        + "does not compute.");
                return null;
                }
        int strandAmounts = defaultStrandNames.length;
        String[][] defaultStrandSequences = new String[strandAmounts][2];
        for(int j = 0; j < strandLocations.length-1;j++){
            for(int i = 0; i < 2;i++){
                defaultStrandSequences[j][0] = defaultStrandNames[j];
                defaultStrandSequences[j][1] = strandLocations[j];
                }
            }
                return defaultStrandSequences;
        }
        
      //determines amount of new strands to be added
      //needs:more testing+its a bit wordy
      private int addStrandAmount() throws IOException{
          int strandAmount = 0;
          String newInput;
          boolean inputCondition1=true, inputCondition2=true;
          try {
            BufferedReader br = new BufferedReader
        (new InputStreamReader(System.in));
            System.out.println("Do you have new strands to add?"
                    + "(Enter Yes or No)");
            newInput = br.readLine();
                while(inputCondition1 == true){
                    if(newInput.equalsIgnoreCase("Yes")){
                        inputCondition1 = false;
                    while(inputCondition2 == true){
                        System.out.println("How many Strands?(Please enter an "
                                                    + "integer 5 or smaller)");
                        newInput = br.readLine();
                        strandAmount = Integer.parseInt(br.readLine());
                            if(strandAmount<=5 && strandAmount>0){
                                inputCondition2 = false;
                                }
                            else{
                                System.out.println("Please Enter a number "
                                                       + "between 1 and 5.");
                                }
                            }  
                            }
                    else if(newInput.equalsIgnoreCase("No")){
                        return strandAmount;
                        }
                    else{
                        continue;
                        }
                    }   
                return strandAmount;
                }
            catch(IOException e){
              e.getMessage();
                }
                return strandAmount;
            }
      
      //adds new Name/Strand pairs to Default Strands
      //calls addStrandAmount   
      private String[][] newDefaultStrands(String[][] Defaults) 
              throws IOException{
          String[][] newStrandArray;
          int addStrands = addStrandAmount();
          int newLength = Defaults.length+addStrands;
          
          if(addStrands == 0){
              return Defaults;
                }
          try{
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));
            newStrandArray = new String[newLength][2];
            for(int i = Defaults.length-1 ; i < newLength ; i++){
                for(int j = 0; j < 2; j++){
                    if(j==0){
                    System.out.println("Please Enter the Organism and "
                            + "Gene Name");
                    newStrandArray[i][j] = br.readLine();
                    }
                    else{
                        System.out.println("Please enter a working URL to the "
                                + ".dat strand data file.");
                    newStrandArray[i][j] = br.readLine();
                    }
                    j++;
                }
                i++;
            }
            return newStrandArray;
            }
             catch(IOException e){
                 e.getMessage();
                     }
            return Defaults;
        }
        
      //finds a filelocation from a filepath locally
        private String fileLocation(String type) throws IOException{
            String userinput;
            System.out.println("Enter a file path to load a(n) " + type + ".");
            try {
            BufferedReader brinput = new BufferedReader
                                            (new InputStreamReader(System.in));
            userinput = brinput.readLine();
            return userinput;
                }
            catch(InputMismatchException e){
                e.getMessage();
                }
            return null;
                    }
        
        //Retrieves Devault Strands to use(for Sequence Alignment) from URL
        private static String[] defaultFileLoader(String[] strandLocations) 
                throws IOException {
            try{
                String[] defaultStrandsFromURL =
                        new String[strandLocations.length];
                for(int i =0;i<strandLocations.length;i++){
                    String urlFileCapture = new Scanner(
                            new URL(strandLocations[i]).openStream(), "UTF-8")
                            .useDelimiter("\\A").next();
                    defaultStrandsFromURL[i] = urlFileCapture;
                    System.out.println("My string " + i + " is" + 
                            defaultStrandsFromURL[i]);
                }
                return defaultStrandsFromURL;
                }
            catch(IOException e1){
                e1.getMessage();
                }
            return null;
                }
        
        //When taking in fileLocation as a param, it returns the Strand from a 
        //file
        private String fileLoader(String filename, Charset encoding) 
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
                    return null;
                }
                }
        
        //Checks to see if the Strand contans the proper elements(from 
        // DNA/AA SubClasses)
        protected boolean StrandLoad(char[] elements,String type) 
                throws IOException{
            return Input
        (fileLoader(fileLocation(type),defaultCharset()), elements);
            }
        protected boolean Input(String elementcode,char[] elements){
            int j;
            for(int i=0;i<elementcode.length();i++){
                j = 0;
                while(j <elements.length+1){
                    if (j==elements.length) {
                        System.out.println("Input error, data outside base pairs");
                        return false;
                    }
                    if(elementcode.charAt(i) ==elements[j]){
                         j=elements.length+2;
                     }
                    j++;
                }
            }
            elementstrand=elementcode;
            return true; 
            }
        
        //Output used for DNA or AA Strands. May be removed
        protected void Output(String type){
            System.out.flush();
            System.out.print("The " + type + " is :" + elementstrand);
            System.out.flush();
            }
        
        //For AminoAcids, returns the same value, for DNA, returns the strand
        // after the upstream leader sequence has been cutoff
        protected String trimstrand(String startcode, int keysize){
            String cutstrand = new String();
            String temp;
            if(!startcode.equals("")){
                for (int i = 0; i < elementstrand.length(); i++){
                    temp = "";
                    for (int j = 0; j < keysize;j++){
                        temp += elementstrand.charAt(i+j);
                        }
                    if (temp.equals(startcode)){
                        for(int j=i;j<elementstrand.length();j++){
                            cutstrand+=elementstrand.charAt(j);
                            }
                        return cutstrand;
                        }
                    }
                }
            else{
                return this.elementstrand;       
                }
            return "No start code found";
            }
        
        //takes a DNA/AA Strand, converts it to AA/DNA
        protected String convertstrand(String cutstrand, 
                Map<String, String> Converter, int keysize, String[] stopcode){
           String temp;
           String toreturn="";
           if (stopcode.length == 0){
                    return cutstrand;
               }
           for(int i = 0; i < elementstrand.length(); i+=keysize){
                temp = "";
                for (int j = 0; j < keysize;j++){
                    temp += cutstrand.charAt(i+j);
                    }
                for(int j =0; j < stopcode.length; j++){
                    if (stopcode[j].equals(temp)){
                        return toreturn;
                        }
                    }
                toreturn += Converter.get(temp); 
                }
           return toreturn;
            }
        
        //Creates a Matrix for a Sequence Alignment(needs 2 AA/DNA Strands)
        private static int[][] alignmentMatrix(char[] elementstrand1, 
                char[] elementstrand2){
            int mismatch = -1;
            int match = 2;
            int gap = -1;
            int west, south, southwest;
            
            int[][] alignGrid= 
                    new int[elementstrand1.length][elementstrand2.length];
            
            for(int i=0; i<elementstrand1.length-1;i++){
                alignGrid[i][0]+=(gap*i);
                }
            for(int i=0; i<elementstrand2.length-1;i++){
                alignGrid[0][i]+=(gap*i);
                }
            for(int j=1;j<=elementstrand1.length-1;j++){
                for(int i=1; i<=elementstrand2.length-1;i++){
                    south = alignGrid[j-1][i]+gap;
                    west = alignGrid[j][i-1]+gap;
                    if(elementstrand1[j]==elementstrand2[i]){
                        southwest = alignGrid[j-1][i-1]+match;
                        }
                    else{
                        southwest = alignGrid[j-1][i-1]+mismatch;
                        }
                    if(southwest>=west&&southwest>=south){
                        alignGrid[j][i]+=southwest;
                        }
                    else if(west>=southwest&&west>south){
                        alignGrid[j][i]+=west;
                        }
                    else{
                        alignGrid[j][i]+=south;
                        }
                        }
                        }
            return alignGrid;
            }
        
        //finds the Max Position(i,j) from the alignment grid, returns these two 
        //as the first two indices of an int array
        private int[] gridMaxPosition(int[][] gridMax){
           int[] maxPosition = new int[2];
           int totalMax=0, iMax=0, jMax=0;
           for(int j=0; j<gridMax.length;j++){
               for(int i=0; i<gridMax[j].length;i++){
                   if(gridMax[j][i]>totalMax){
                       iMax = i;
                       jMax = j;
                   }
               }    
           }
           maxPosition[0]=jMax;
           maxPosition[1]=iMax;
           return maxPosition;
           }
        
        //outputs the aligned sequences with gaps
        //modelled after Needleman-Wunsch Algorithm
        private void seqAlignOutput(int[][] alignGrid, int[] maxPosition,
               char[] elementstrand1, char[] elementstrand2){
            int southwest, south, west, gridMaxJ, gridMaxI;
            gridMaxJ = maxPosition[0];
            gridMaxI = maxPosition[1];
            String alignedString1 = new String(), alignedString2 = new String();
            
            for(int j = gridMaxJ, i = gridMaxI; i>0 && j>0; ){
                    southwest = alignGrid[j-1][i-1];
                    south = alignGrid[j][i-1];
                    west = alignGrid[j-1][i];
                    
                   if(southwest>west && southwest>south){
                        alignedString1 +=elementstrand1[j];
                        alignedString2 +=elementstrand2[i];
                        j--;
                        i--;
                        }
                   else if(west>=southwest&&west>=south){
                        alignedString1 +=elementstrand1[j];
                        alignedString2 +="-";
                        j--;
                        }
                    else{
                        alignedString1 += "-";
                        alignedString2 +=elementstrand2[i];
                        i--;
                        }
                   if(i<0 || j<0){
                       break;
                   }
                }
            System.out.println("Sequence A: " +
                    new StringBuilder(alignedString1).reverse().toString());
            System.out.println("Sequence B: " +
                    new StringBuilder(alignedString2).reverse().toString());
            }
        
        
        //calls the alignmentMatrix, the gridMaxPosition, and seqAlignOutput
        //methods
        private void sequenceAlignment(char[] elementstrand1, 
               char[] elementstrand2){
           int[][] newMatrix = alignmentMatrix(elementstrand1, elementstrand2);
           int[] newMax = gridMaxPosition(newMatrix);
           seqAlignOutput(newMatrix, newMax, elementstrand1, elementstrand2);
            }
        
        //adds a '-' character in front of the elementstrand for the sequence
        //alignment matrix
        public char[] aminoStrandPrep(AminoAcids newAmino){
            int strandLength = newAmino.elementstrand.length();
            char[] newElementStrand = new char[strandLength+1];
            newElementStrand[0]='-';
            for(int i=0;i<strandLength;i++){
                newElementStrand[i]=elementstrand.charAt(i);
                }
            return newElementStrand;
            }
        //for converting DNA Sequence to Amino Acid, creates DNA and Amino
        //Instances, returns the completed strand
        public char[] aminoStrandFinal() throws IOException{
           try{
           DNA myDNA = new DNA();
           myDNA.DNALoad();
           AminoAcids myAmino;
           myAmino = myDNA.toAmino();
           char[] elementstrand1 = aminoStrandPrep(myAmino);
           return elementstrand1;
           }
           catch(IOException e){
               e.getMessage();
           }
           return null;
           }       
  }
