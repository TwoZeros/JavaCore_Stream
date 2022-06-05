
public interface InstallService {
    void install();

    void createFile(String... fileNames);

    void createFolder(String... folderNames);

    String getPathToInstall();
}
