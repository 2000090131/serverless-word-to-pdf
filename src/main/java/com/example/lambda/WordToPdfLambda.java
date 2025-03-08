package com.example.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

public class WordToPdfLambda implements RequestHandler<S3Event, String> {
    private final AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();

    @Override
    public String handleRequest(S3Event event, Context context) {
        try {
            String bucketName = event.getRecords().get(0).getS3().getBucket().getName();
            String wordKey = event.getRecords().get(0).getS3().getObject().getKey();

            S3Object s3Object = s3Client.getObject(bucketName, wordKey);
            InputStream wordInputStream = s3Object.getObjectContent();

            String pdfKey = wordKey.replace(".docx", ".pdf");
            File pdfFile = convertWordToPdf(wordInputStream);

            s3Client.putObject(new PutObjectRequest(bucketName, pdfKey, pdfFile));
            return "Conversion successful: " + pdfKey;
        } catch (Exception e) {
            context.getLogger().log("Error: " + e.getMessage());
            return "Conversion failed";
        }
    }

    private File convertWordToPdf(InputStream wordInputStream) throws Exception {
        XWPFDocument document = new XWPFDocument(wordInputStream);
        File pdfFile = File.createTempFile("converted", ".pdf");

        Document pdfDocument = new Document();
        PdfWriter.getInstance(pdfDocument, new FileOutputStream(pdfFile));
        pdfDocument.open();
        pdfDocument.add(new Paragraph(document.getDocument().getText()));
        pdfDocument.close();
        
        return pdfFile;
    }
}
