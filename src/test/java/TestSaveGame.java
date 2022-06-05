import org.junit.jupiter.api.*;

import java.io.File;

public class TestSaveGame {
    public static final String NAME_ROOT_FOLDER = "Test";
    public static final String FOLDER_FOR_SAVE = NAME_ROOT_FOLDER + File.separator + "savegames" + File.separator;
    public static final String PATH_SAVE1 = FOLDER_FOR_SAVE + "save1.dat";
    public static final String PATH_ARCHIV = FOLDER_FOR_SAVE + "zip.zip";
    public static final GameService gameService = new GameServiceImpl();

    @Test
    @DisplayName("zip archive created")
    public void testZip(){
        //before
        GameProgress gameProgressBeforeSave = new GameProgress(100, 5, 3, 30);
        new File(NAME_ROOT_FOLDER).mkdir();
        new File(FOLDER_FOR_SAVE).mkdir();
        //when
        gameService.saveGame(PATH_SAVE1, gameProgressBeforeSave);
        gameService.zipFiles(PATH_ARCHIV, PATH_SAVE1);
        //then
        Assertions.assertTrue(new File(PATH_ARCHIV).exists(), "Архив создан");
        Assertions.assertFalse(new File(PATH_SAVE1).exists(), "сохранение удалено после арихвирования");
        new File(PATH_ARCHIV).delete();
        new File(FOLDER_FOR_SAVE).delete();
        new File(NAME_ROOT_FOLDER).delete();

    }
    @Test
    @DisplayName("Save progress and open save work correct")
    public void testSave() {
        //before
        GameProgress gameProgressBeforeSave = new GameProgress(100, 5, 3, 30);
        new File(NAME_ROOT_FOLDER).mkdir();
        new File(FOLDER_FOR_SAVE).mkdir();
        //when
        gameService.saveGame(PATH_SAVE1, gameProgressBeforeSave);
        gameService.zipFiles(PATH_ARCHIV, PATH_SAVE1);
        gameService.openZip(PATH_ARCHIV, FOLDER_FOR_SAVE);
        GameProgress gameProgressAfterSave = gameService.openProgress(PATH_SAVE1);
        //then
        Assertions.assertEquals(gameProgressBeforeSave,gameProgressAfterSave);

        new File(PATH_ARCHIV).delete();
        new File(PATH_SAVE1).delete();
        new File(FOLDER_FOR_SAVE).delete();
        new File(NAME_ROOT_FOLDER).delete();
    }

}
