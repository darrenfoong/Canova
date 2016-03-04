/*
 *
 *  *
 *  *  * Copyright 2015 Skymind,Inc.
 *  *  *
 *  *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *    you may not use this file except in compliance with the License.
 *  *  *    You may obtain a copy of the License at
 *  *  *
 *  *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  *    Unless required by applicable law or agreed to in writing, software
 *  *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *    See the License for the specific language governing permissions and
 *  *  *    limitations under the License.
 *  *
 *
 */

package org.canova.api.records.reader.impl;

import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.canova.api.records.reader.RecordReader;
import org.canova.api.split.FileSplit;
import org.canova.api.split.InputSplit;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

/**
 * Created by agibsonccc on 11/17/14.
 */
public class LineReaderTest {

    private static Logger log = LoggerFactory.getLogger(LineReaderTest.class);

    @Test
    public void testLineReader() throws Exception {
        File tmpdir = new File("tmpdir");
        tmpdir.mkdir();

        File tmp1 = new File("tmpdir/tmp1.txt");
        File tmp2 = new File("tmpdir/tmp2.txt");
        File tmp3 = new File("tmpdir/tmp3.txt");

        FileUtils.writeLines(tmp1, Arrays.asList("1","2","3"));
        FileUtils.writeLines(tmp2, Arrays.asList("4","5","6"));
        FileUtils.writeLines(tmp3, Arrays.asList("7","8","9"));

        InputSplit split = new FileSplit(tmpdir);

        RecordReader reader = new LineRecordReader();
        reader.initialize(split);

        int count = 0;
        while(reader.hasNext()) {
            assertEquals(1,reader.next().size());
            count++;
        }

        assertEquals(9, count);

        FileUtils.deleteDirectory(tmpdir);
    }


}
