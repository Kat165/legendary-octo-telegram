package test;

import com.company.Document;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    void writeHTML_constructor2() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Document("test").writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        assertTrue(result.contains("<html"));
        assertTrue(result.contains("</html>"));
        assertTrue(result.contains("<head"));
        assertTrue(result.contains("</head>"));
        assertTrue(result.contains("test"));
        assertTrue(result.contains("/>"));
        assertTrue(result.contains("<title>"));
        assertTrue(result.contains("</title>"));
        assertTrue(result.contains("<body>"));
        assertTrue(result.contains("</body>"));
    }

    @Test
    void writeHTML_constructor1() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Document().writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        assertTrue(result.contains("<html"));
        assertTrue(result.contains("</html>"));
        assertTrue(result.contains("<head"));
        assertTrue(result.contains("</head>"));
        assertTrue(result.contains("Tytul"));
        assertTrue(result.contains("/>"));
        assertTrue(result.contains("<title>"));
        assertTrue(result.contains("</title>"));
        assertTrue(result.contains("<body>"));
        assertTrue(result.contains("</body>"));
    }
}