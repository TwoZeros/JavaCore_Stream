public interface GameService {

    void saveGame(String pathToSave, GameProgress gameProgress);

    void zipFiles(String pathToZip, String... pathToFiles);

    void deleteFiles(String... pathToFile);

    void openZip(String pathToZip, String pathToFolder);

    GameProgress openProgress(String pathToFile);
}
