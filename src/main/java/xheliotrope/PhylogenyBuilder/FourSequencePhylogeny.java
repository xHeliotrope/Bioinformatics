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

    public FourSequencePhylogeny(){}
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


    private String determineTreeStructure(List<Alignment> alignments) {
        String rootFirst = alignments.get(0).getSequence1().getFullName();
        if(rootFirst == null) System.out.println("im ok here");
        String rootSecond = alignments.get(0).getSequence2().getFullName();
        if(rootSecond == null) System.out.println("im ok here");
        Alignment nextAlignment = alignments.get(1);
        String nextFirstName = nextAlignment.getSequence1().getFullName();
        String nextSecondName = nextAlignment.getSequence2().getFullName();
        if (nextFirstName.equals(rootFirst) || nextFirstName.equals(rootSecond) || nextSecondName.equals(rootFirst) || nextSecondName.equals(rootSecond)) {
            return "test";
        } else {
            return splitTreeVisual(rootFirst, rootSecond, nextFirstName, nextSecondName);
        }
    }

    private String splitTreeVisual(String seqA1, String seqA2, String seqB1, String seqB2){
        StringBuilder visualBuild = new StringBuilder();
        visualBuild.append("            ___" + seqA1 + '\n');
        visualBuild.append("       ____|   " + '\n');
        visualBuild.append("      |    |___" + seqA2 + '\n');
        visualBuild.append(" _____|        " + '\n');
        visualBuild.append("      |     ___" + seqB1 + '\n');
        visualBuild.append("      |____|   " + '\n');
        visualBuild.append("           |___" + seqB2 + '\n');
        return visualBuild.toString();
    }

    private String telescopeTreeVisual(String seqA1, String seqA2, String seqB, String seqC){
        StringBuilder visualBuild = new StringBuilder();
        visualBuild.append("            ___" + seqA1 + '\n');
        visualBuild.append("        ___|   " + '\n');
        visualBuild.append(" ______|   |___" + seqA2 + '\n');
        visualBuild.append("    |  |       " + '\n');
        visualBuild.append("    |  |_______" + seqB + '\n');
        visualBuild.append("    |          " + '\n');
        visualBuild.append("    |__________" + seqC + '\n');
        return visualBuild.toString();
    }

    public void outputPhylogenyResult(){
        System.out.println(determineTreeStructure(sequenceAlignmentList()));
    }

}
