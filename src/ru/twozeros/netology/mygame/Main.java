package ru.twozeros.netology.mygame;

import java.io.File;

public class Main {
    public static final String FOLDER_FOR_SAVE = "Game" + File.separator + "savegames" + File.separator;
    public static final String PATH_SAVE1 = FOLDER_FOR_SAVE + "save1.dat";
    public static final String PATH_SAVE2 = FOLDER_FOR_SAVE + "save2.dat";
    public static final String PATH_SAVE3 = FOLDER_FOR_SAVE + "save3.dat";
    public static final String PATH_ARCHIV = FOLDER_FOR_SAVE + "zip.zip";

    public static void main(String[] args) {
        Installer installer = new Installer();
        installer.install();
        GameProgress gameProgress1 = new GameProgress(100, 5, 3, 30);
        GameProgress gameProgress2 = new GameProgress(100, 5, 5, 50);
        GameProgress gameProgress3 = new GameProgress(100, 5, 6, 40);
        GameUtils.saveGame(PATH_SAVE1, gameProgress1);
        GameUtils.saveGame(PATH_SAVE2, gameProgress2);
        GameUtils.saveGame(PATH_SAVE3, gameProgress3);
        GameUtils.zipFiles(PATH_ARCHIV, PATH_SAVE1, PATH_SAVE2, PATH_SAVE3);


    }
}
