package test;

import com.company.Paragraph;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest {

    @Test
    void writeHTML_constructor2() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Paragraph("test").writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        assertTrue(result.contains("<p"));
        assertTrue(result.contains("test"));
        assertTrue(result.contains("/p>"));
    }

    @Test
    void writeHTML_constructor1() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Paragraph().writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        assertTrue(result.contains("<p"));
        assertTrue(result.contains("Jakis tekst"));
        assertTrue(result.contains("/p>"));
    }
}