package ru.twozeros.netology.mygame;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Installer {

    private final Logger logger;
    private final String STR_SUCCESS = "Успешно cоздан";
    private final String FILE_EXISTS = "Файл не был создан, так как уже существует";
    private final String FOLDER_EXISTS = "Каталог не был создан,так уже существует";
    private final String STR_ERROR = "Ошибка создания";
    public static final String INSTALL_END = "Установка завершена";
    private final String pathToInstall;

    public Installer(String path) {
        this.logger = new Logger(new File("Game", "temp.txt"));
        pathToInstall = path;
    }

    public Installer() {
        this.logger = new Logger();
        pathToInstall = "Game";
    }

    public void install() {
        createFolder("scr",
                "scr" + File.separator + "main",
                "scr" + File.separator + "test",
                "res",
                "res" + File.separator + "dramables",
                "res" + File.separator + "vectors",
                "res" + File.separator + "icons",
                "savegames",
                "temp");
        createFile("scr" + File.separator + "main" + File.separator + "Main.java",
                "scr" + File.separator + "main" + File.separator + "Utils.java",
                "temp" + File.separator + "temp.txt");
        logger.addToLog(INSTALL_END);
        logger.setFileForSave(new File(pathToInstall, "temp" + File.separator + "temp.txt"));
        logger.writeToFile();
    }

    public void createFile(String... fileNames) {
        Arrays.stream(fileNames)
                .map(file -> new File(pathToInstall, file))
                .forEach(file -> {
                    try {
                        if (file.createNewFile()) logger.addToLog(STR_SUCCESS, file.getName(), file.getCanonicalPath());
                        else if (file.exists()) logger.addToLog(FILE_EXISTS, file.getName(), file.getCanonicalPath());
                        else logger.addToLog(STR_ERROR, file.getName());
                    } catch (IOException e) {
                        logger.addToLog(STR_ERROR, file.getName());
                    }
                });
    }

    public void createFolder(String... folderNames) {
        Arrays.stream(folderNames)
                .map(file -> new File(pathToInstall, file))
                .forEach(file -> {
                    try {
                        if (file.mkdir()) logger.addToLog(STR_SUCCESS, file.getName(), file.getCanonicalPath());
                        else if (file.exists()) logger.addToLog(FOLDER_EXISTS, file.getName(), file.getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
