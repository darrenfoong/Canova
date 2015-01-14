package org.nd4j.api.formats.output.impl;

import org.nd4j.api.conf.Configuration;
import org.nd4j.api.exceptions.CanovaException;
import org.nd4j.api.formats.output.OutputFormat;
import org.nd4j.api.records.writer.RecordWriter;
import org.nd4j.api.records.writer.impl.LineRecordWriter;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by agibsonccc on 1/11/15.
 */
public class SVMLightOutputFormat implements OutputFormat {
    @Override
    public RecordWriter createWriter(Configuration conf) throws CanovaException {
        String outputPath = conf.get(OutputFormat.OUTPUT_PATH,".");
        try {
            return new LineRecordWriter(new File(outputPath));
        } catch (FileNotFoundException e) {
            throw new CanovaException(e);
        }
    }
}