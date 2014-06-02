/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.InputMismatchException;
import java.util.Map;

  
  public class Strand {
      
      static String bpfile = "C:\\Users\\rmoor_000\\Documents\\NetBeans"
            + "Projects\\Population Genetics\\src\\population\\genetics\\"
            + "bpdata3.dat";
      
        public String elementstrand=new String();
      
        public int Length(){
            return elementstrand.length();
            }
        
        private static String fileGrabber(String type) throws IOException{
            try {
            String def = "Default";
            String userinput;
            System.out.println("Enter a file path to load a(n) "+ type +" or enter 'default'");
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
        
         private static String fileLoader(String filename, Charset encoding) 
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
        
        protected boolean StrandLoad(char[] elements,String type)throws IOException{
            return Input(fileLoader(fileGrabber(type),defaultCharset()), elements);
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
        protected void Output(String type){
            System.out.flush();
            System.out.print("The " + type + " is :" + elementstrand);
            System.out.flush();
        }

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
            
       protected String convertstrand(String cutstrand, Map<String, String> Converter, int keysize, String[] stopcode){
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
       
       protected static void twoSequenceAlignment(char[] elementstrand1, 
                char[] elementstrand2){
            int mismatch = -1;
            int match = 2;
            int gap = -1;
            int west, south, southwest;
            int gridMax=0, gridMaxJ =0, gridMaxI=0;
            
            String alignedString1 = new String(), alignedString2 = new String();
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
                        if(alignGrid[j][i]>gridMax){
                            gridMax = alignGrid[j][i];
                            gridMaxJ=j;
                            gridMaxI=i;
                        }
                        }
                    else if(west>=southwest&&west>south){
                        alignGrid[j][i]+=west;
                        if(alignGrid[j][i]>gridMax){
                            gridMax = alignGrid[j][i];
                            gridMaxJ=j;
                            gridMaxI=i;
                        }
                        }
                    else{
                        alignGrid[j][i]+=south;
                        if(alignGrid[j][i]>gridMax){
                            gridMax = alignGrid[j][i];
                            gridMaxJ=j;
                            gridMaxI=i;
                        }
                        }
                        }
                        }
        
            for(int j = gridMaxJ, 
                i = gridMaxI; i>0 && j>0; ){
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
                      
            System.out.println("Sequence A: " + new StringBuilder(alignedString1).reverse().toString());
            System.out.println("Sequence B: " + new StringBuilder(alignedString2).reverse().toString());
            }
       
       
       
        public static void main(String[] args)throws IOException{
           try{ 
           DNA myDNA = new DNA();
           myDNA.DNALoad();
           AminoAcids myAmino;
           myAmino = myDNA.toAmino();
           myAmino.Output();
           myAmino.aaPrep();
           }
            catch(FileNotFoundException e){
                System.out.println(e.getMessage());
           }
       
  } 
  }

    

    
        


