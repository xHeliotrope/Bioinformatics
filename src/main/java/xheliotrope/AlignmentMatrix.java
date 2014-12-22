package xheliotrope;

/**
 * rmoore8869
 * 12/20/2014
 */

public class AlignmentMatrix {

    public AlignmentMatrix(Sequence sequence1, Sequence sequence2){
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
    }

    private Sequence sequence1, sequence2;


    //Creates a Matrix for a Sequence Alignment(needs 2 AA/DNA Strands)
    private int[][] alignmentMatrix(char[] elementstrand1,
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
}
