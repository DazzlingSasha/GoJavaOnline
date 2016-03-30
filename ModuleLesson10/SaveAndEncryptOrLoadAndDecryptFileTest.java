package ModuleLesson10;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

//@RunWith(value = Parameterized.class)
public class SaveAndEncryptOrLoadAndDecryptFileTest {
    private static SaveAndEncryptOrLoadAndDecryptFile saveLoadFile;
    private static String file = "c:\\111.txt";

    @Before
    public void setUp() throws Exception {
        saveLoadFile = new SaveAndEncryptOrLoadAndDecryptFile();
    }

    @Test(timeout = 2000)
    public void testSaveEncryptFile() throws Exception {
        saveLoadFile.saveEncryptFile(file);
    }

    @Test
    public void testLoadDecryptFile() throws Exception {
        String result = saveLoadFile.loadDecryptFile(file);
        assertEquals("123", result);
    }
//    @Parameterized.Parameters
//    public static List<Object[]> isEmptyData() {
//        return Arrays.asList(new Object[][]{
//                {null, true},
//                {"", true},
//                {" ", false},
//                {"some string", false},
//        });
//    }
}