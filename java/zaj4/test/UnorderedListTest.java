package test;

import com.company.UnorderedList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class UnorderedListTest {

    @Test
    void writeHTML_constructor1() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new UnorderedList().writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        assertTrue(result.contains("<ul"));
        assertTrue(result.contains("/ul>"));
    }

    @Test
    void writeHTML_constructor2() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new UnorderedList("lista").writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        assertTrue(result.contains("<ul"));
        assertTrue(result.contains("lista"));
        assertTrue(result.contains("/ul>"));
        assertTrue(result.contains("<li"));
        assertTrue(result.contains("/li>"));

    }
}