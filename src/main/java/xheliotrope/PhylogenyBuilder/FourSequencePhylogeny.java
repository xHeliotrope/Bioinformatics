package xheliotrope.PhylogenyBuilder;

import xheliotrope.Alignment;
import xheliotrope.AlignmentMatrix;
import xheliotrope.Sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * rmoore8869
 * 1/3/2015
 */
public class FourSequencePhylogeny {

    private Sequence seq1, seq2, seq3, seq4;

    public FourSequencePhylogeny(Sequence seq1, Sequence seq2, Sequence seq3, Sequence seq4){
        this.seq1 = seq1;
        this.seq2 = seq2;
        this.seq3 = seq3;
        this.seq4 = seq4;
    }

    private List<Alignment> sequenceAlignmentList(){
        List<Alignment> alignmentList = new ArrayList<>();
        alignmentList.add(new AlignmentMatrix(seq1, seq2).alignmentFromMatrix());
        alignmentList.add(new AlignmentMatrix(seq1, seq3).alignmentFromMatrix());
        alignmentList.add(new AlignmentMatrix(seq1, seq4).alignmentFromMatrix());
        alignmentList.add(new AlignmentMatrix(seq2, seq3).alignmentFromMatrix());
        alignmentList.add(new AlignmentMatrix(seq2, seq4).alignmentFromMatrix());
        alignmentList.add(new AlignmentMatrix(seq3, seq4).alignmentFromMatrix());

        Collections.sort(alignmentList, new Comparator<Alignment>() {
            public int compare(Alignment o1, Alignment o2) {
                return o1.getScore() > o2.getScore() ? -1 : o1.getScore() == o2.getScore() ? 0 : 1;
            }
        });

        return alignmentList;
    }





    public void splitTreeVisual(String seqA1, String seqA2, String seqB1, String seqB2){


    }


}
