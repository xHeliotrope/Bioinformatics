package com.xheliotrope.PhylogenyBuilder;

import com.xheliotrope.Alignment;
import com.xheliotrope.AlignmentMatrix;
import com.xheliotrope.Sequence;
import com.xheliotrope.SequenceType.AminoAcid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * rmoore8869
 * 1/3/2015
 */
public class FourSequencePhylogeny {


    public FourSequencePhylogeny(List<AminoAcid> sequenceList){
        this.sequenceList = sequenceList;
    }

    private List<AminoAcid> sequenceList;
    private Sequence seq1, seq2, seq3, seq4;
    private List<Alignment> alignmentList;
    private String closestNeighborName;

    public List<AminoAcid> getSequenceList(){
        return sequenceList;
    }

    private void populateSequences(){
        seq1 = sequenceList.get(0);
        seq2 = sequenceList.get(1);
        seq3 = sequenceList.get(2);
        seq4 = sequenceList.get(3);
    }

    private List<String> fetchNames(){
        populateSequences();
        List<String> alignmentNames = new ArrayList<>();
        alignmentNames.add(seq1.getFullName());
        alignmentNames.add(seq2.getFullName());
        alignmentNames.add(seq3.getFullName());
        alignmentNames.add(seq4.getFullName());
        return alignmentNames;
    }

    private List<Alignment> sequenceAlignmentList(){
        populateSequences();
        alignmentList = new ArrayList<>();
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

        List<String> alignmentNames = fetchNames();
        Alignment nextAlignment = alignments.get(1);

        String rootFirst = alignments.get(0).getSequence1().getFullName();
        String rootSecond = alignments.get(0).getSequence2().getFullName();
        String nextFirstName = nextAlignment.getSequence1().getFullName();
        String nextSecondName = nextAlignment.getSequence2().getFullName();

        alignmentNames.remove(rootFirst);
        alignmentNames.remove(rootSecond);
        int matches = closestNeighborFinder(rootFirst, rootSecond, nextFirstName, nextSecondName);
        alignmentNames.remove(closestNeighborName);
        if (matches == 1){
            return telescopeTreeVisual(rootFirst, rootSecond, closestNeighborName, alignmentNames.get(0));
        }
        else if(matches == 0) {
            return splitTreeVisual(rootFirst, rootSecond, nextFirstName, nextSecondName);
        }
        else{
            throw new IllegalStateException("Error, there maybe repeated Sequences in the sequence list");
        }
    }

    private int closestNeighborFinder(String rootFirst, String rootSecond, String nextFirstName, String nextSecondName){
        int matches = 0;
        if(rootFirst.equals(nextFirstName)){
            matches++;
            closestNeighborName = nextSecondName;
        }
        if(rootFirst.equals(nextSecondName)){
            matches++;
            closestNeighborName = nextFirstName;
        }
        if(rootSecond.equals(nextFirstName)){
            matches++;
            closestNeighborName = nextSecondName;
        }
        if(rootSecond.equals(nextSecondName)){
            matches++;
            closestNeighborName = nextFirstName;
        }
        return matches;
    }

    private String splitTreeVisual(String seqA1, String seqA2, String seqB1, String seqB2){
        StringBuilder visualBuild = new StringBuilder();
        visualBuild.append("            ___" + seqA1 + '\n');
        visualBuild.append("       ____|   " + '\n');
        visualBuild.append("      |    |___" + seqA2 + '\n');
        visualBuild.append(" _____|        " + '\n');
        visualBuild.append("      |     ___" + seqB1 + '\n');
        visualBuild.append("      |____|   " + '\n');
        visualBuild.append("           |___" + seqB2 + '\n' + '\n');

        visualBuild.append("Similarity score between top sequence pair: " + alignmentList.get(0).getScore() + '\n');
        visualBuild.append("Similarity score between bottom sequence pair: " + alignmentList.get(1).getScore() + '\n');
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
        visualBuild.append("    |__________" + seqC + '\n' +'\n');
        return visualBuild.toString();
    }

    public void outputPhylogenyResult(){
        System.out.println(determineTreeStructure(sequenceAlignmentList()));
    }

    public void lengthCheck(){
        System.out.println('\n' + "Alignment Scores for the Phylogeny: " + '\n');
        for(Alignment a : alignmentList){
            System.out.println(a.getScore() + " " + a.getSequence1().getFullName() + " and " +
                    a.getSequence2().getFullName());
        }
    }

}
