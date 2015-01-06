package com.xheliotrope;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * rmoore8869
 * 12/20/2014
 */

public class RunAlignment {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application");
        AlignmentFactory alignmentFactory = (AlignmentFactory) context.getBean("alignmentFactory");
        alignmentFactory.sequenceAlignmentOutput();
    }


}
