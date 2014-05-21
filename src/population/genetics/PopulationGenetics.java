package population.genetics;

/* import java.util.Scanner; */

/*outputs random transition/transversion rates of a random sequence
 *DNA length, Sample Size and mutation rate is hardcoded
 *
 *  -rmoore
 */
public class PopulationGenetics {

    static String[] basepairs = {"A", "T", "C", "G"};
    static int ts = 0, tv = 0, PopLength;
    
    public static String RandomBasePair (String[] args) {
        int i = (int)(Math.random()*(4));
        return basepairs[i];
    }
    
     public static String MutateBasePair (String[] RandBasepair) {
        int i = (int)(Math.random()*(3));
        return RandBasepair[i];
    }
     
    public static String[] RandomStrand (int Length){
        String[] Strand = new String[Length];
        for(int i=0;i<Length;i++){
            Strand[i] = RandomBasePair(basepairs);
        }
        return Strand;
    }
    
    public static String[][] RandomPopulation (int Size, int Length){
        String[][] Population = new String[Size][Length];
        for(int i=0; i<Size; i++){
            Population[i]=RandomStrand(Length);
        }
        return Population;
    }
    
    public static String[][] Reproduction (String[][] Population){
        return Population;
    }
    
    public static String[][] Mutate (String[][] Population, int i, int j)
    
    {
        if ("A".equals(Population[i][j])){
            String[] Pairs = {"C","T","G"};
            Population[i][j] = MutateBasePair(Pairs);
            if ("G".equals(Population[i][j])){
                ts++;
                }
            else{
                tv++;
            }
        }
        else if ("C".equals(Population[i][j])){
            String[] Pairs= {"A","T","G"};
            Population[i][j] = MutateBasePair(Pairs);
            if ("T".equals(Population[i][j])){
                ts++;
                }
            else{
                tv++;
            }
        }
        else if ("T".equals(Population[i][j])){
            String[] Pairs= {"C","A","G"};
            Population[i][j] = MutateBasePair(Pairs);
            if ("C".equals(Population[i][j])){
                ts++;
                }
            else{
                tv++;
            }
        }
        else{
            String[] Pairs= {"C","T","A"};
            Population[i][j] = MutateBasePair(Pairs);
            if ("A".equals(Population[i][j])){
                ts++;
                }
            else{
                tv++;
            }
        }    
        return Population;
              
               }
    
    public static String[][] Mutation (String[][] Population, double MRate){
        for(int i=0;i<Population.length;i++){
            for(int j=0;j<Population[i].length;j++){
                if (Math.random()<MRate){
                    Population = Mutate(Population, i,j);
                }
            }          
        }
        return Population; 
     
    }
    
    
    public static void main(String[] args){
   
      String[][] Population = RandomPopulation(12,PopLength);
      Population = Mutation(Population, .1);
       
      double tsRate = ts / Population.length;
      double tvRate = tv / Population.length;
      System.out.println("Total Number of Transitions: "+ ts); 
      System.out.println("Total Number of Transversions: " + tv);
      System.out.println("The Transition Rate is: " + tsRate + " per sequence");
      System.out.println("The Transversion Rate is: " + tvRate + " per sequence");
      System.out.println("The Size of the Population is: " + Population.length);
      System.out.println("The Length of the DNA is: " + Population[0].length);
      
      
       
      }  
    }

    

