// Unit Test
package com.example.lambda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class WordToPdfTest {
    private WordToPdfLambda lambda;

    @BeforeEach
    void setUp() {
        lambda = new WordToPdfLambda();
    }

    @Test
    void testConvertWordToPdf() throws Exception {
        InputStream wordInput = new FileInputStream("sample.docx");
        File pdfFile = lambda.convertWordToPdf(wordInput);
        assertNotNull(pdfFile);
        assertTrue(pdfFile.exists());
    }
}
