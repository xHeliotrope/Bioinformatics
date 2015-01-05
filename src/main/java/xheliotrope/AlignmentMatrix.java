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

    private int[][] alignmentGrid(char[] elementstrand1, char[] elementstrand2) {
        int mismatch = -4;
        int match = 8;
        int gap = -3;
        int west, south, southwest;
        int[][] alignGrid= new int[elementstrand1.length][elementstrand2.length];

        for (int i = 0; i < elementstrand1.length-1; i++) {
            alignGrid[i][0] = gap*i;
        }

        for (int i = 0; i < elementstrand2.length-1; i++) {
            alignGrid[0][i] = gap*i;
        }

        for(int j = 1; j <= elementstrand1.length-1; j++) {
            for(int i = 1; i <= elementstrand2.length-1; i++) {
                south = alignGrid[j-1][i]+gap;
                west = alignGrid[j][i-1]+gap;
                southwest = alignGrid[j-1][i-1];
                southwest += (elementstrand1[j] == elementstrand2[i]) ? match : mismatch;
                alignGrid[j][i] = (southwest > west && southwest > south) ? southwest : (west >= south) ? west : south;
            }
        }
        return alignGrid;
    }

    private void seqAlignOutput(int[][] alignGrid,
                                char[] elementstrand1, char[] elementstrand2){

        int southwest, south, west;
        StringBuilder alignedString1 = new StringBuilder();
        StringBuilder alignedString2 = new StringBuilder();

        for(int j = alignGrid.length-1, i = alignGrid[0].length-1; i>0 && j>0; ){
            southwest = alignGrid[j-1][i-1];
            south = alignGrid[j][i-1];
            west = alignGrid[j-1][i];

            if(southwest>west && southwest>south){
                alignedString1.append(elementstrand1[j]);
                alignedString2.append(elementstrand2[i]);
                j--;
                i--;
            }
            else if(west>=southwest&&west>=south){
                alignedString1.append(elementstrand1[j]);
                alignedString2.append("-");
                j--;
            }
            else{
                alignedString1.append("-");
                alignedString2.append(elementstrand2[i]);
                i--;
            }
            if(i < 0 || j < 0){
                break;
            }
        }
        System.out.println("Sequence A: " +
                alignedString1.reverse());
        System.out.println("Sequence B: " +
                alignedString2.reverse());
    }

    public Alignment alignmentFromMatrix(){
        char[] compareSequence1 = sequence1.getSequenceString().toCharArray();
        char[] compareSequence2 = sequence2.getSequenceString().toCharArray();
        int[][] alignmentGrid = alignmentGrid(compareSequence1, compareSequence2);
        int score = alignmentGrid[compareSequence1.length-1][compareSequence2.length-1];
        return new Alignment(sequence1, sequence2, score);
    }
    public void viewAlignmentBetweenSequences(){
        char[] compareSequence1 = sequence1.getSequenceString().toCharArray();
        char[] compareSequence2 = sequence2.getSequenceString().toCharArray();
        int[][] alignmentGrid = alignmentGrid(compareSequence1, compareSequence2);
        seqAlignOutput(alignmentGrid, compareSequence1, compareSequence2);
        System.out.println("and the score is: " + alignmentGrid[compareSequence1.length-1][compareSequence2.length-1]);
    }
}
