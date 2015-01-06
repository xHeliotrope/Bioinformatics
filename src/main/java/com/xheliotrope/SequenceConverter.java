package com.xheliotrope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * rmoore8869
 * 1/6/2015
 */
public class SequenceConverter {

    public SequenceConverter(String sequencePath){
        this.sequencePath = sequencePath;
    }

    private String sequencePath;
    private String sequenceString;

    private String sequenceDataRead(String myLocation) throws IOException {
        InputStream input = AlignmentFactory.class.getResourceAsStream(myLocation);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder sequenceString = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sequenceString.append(line).append("\n");
        }
        return sequenceString.toString();
    }

    public String sequenceDataConverter() throws IOException {
        this.sequenceString = sequenceDataRead(sequencePath);
        return this.sequenceString;
        }

}
