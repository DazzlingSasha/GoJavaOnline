package ModuleLesson9;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptAndDecryptTheTextTest {
    private static EncryptAndDecryptTheText e;
    @BeforeClass
    public static void setUp(){
        e = new EncryptAndDecryptTheText();
    }
    @Test
    public void testEncrypt() throws Exception {
        String text = "abc";
        String afterEncrypt = "fed";
        String result = e.encrypt(text);
        assertEquals(afterEncrypt, result);
    }

    @Test
    public void testDecrypt() throws Exception {
        String text = "fed";
        String afterDecrypt = "abc";
        String result = e.decrypt(text);
        assertEquals(afterDecrypt, result);
    }
}