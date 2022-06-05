
import org.junit.jupiter.api.*;

import java.io.File;

public class TestInstallService {

    private static final InstallService installService = new Installer();
    private static String pathToInstall;

    @BeforeAll
    public static void init() {
        pathToInstall = installService.getPathToInstall();
    }

    @Test
    @DisplayName("Several files success created")
    public void successCreateFile() {
        //before
        File file1 = new File(pathToInstall + File.separator + "test.txt");
        File file2 = new File(pathToInstall + File.separator + "tool.txt");
        file1.delete();
        file2.delete();
        //when
        installService.createFile("test.txt","tool.txt");
        //then
        Assertions.assertTrue(file1.exists());
        Assertions.assertTrue(file1.exists());

        file1.delete();
        file2.delete();
    }

    @Test
    @DisplayName("Several folder success created")
    public void successCreateFolder() {
        //before
        String folderName1 = "folder1";
        String folderName2 = folderName1 + File.separator + "folder2";
        File folder1 = new File(pathToInstall + File.separator + folderName1);
        File folder2 = new File(pathToInstall + File.separator + folderName2);
        folder2.delete();
        folder1.delete();
        //when
        installService.createFolder(folderName1, folderName2);
        //then
        Assertions.assertTrue(folder1.exists());
        Assertions.assertTrue(folder2.exists());

        folder2.delete();
        folder1.delete();
    }

}
