
import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameServiceImpl implements GameService{
    @Override
    public void saveGame(String pathToSave, GameProgress gameProgress) {
        File file = new File(pathToSave);
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream(file))) {
            oot.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public void zipFiles(String pathToZip, String... pathToFiles) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathToZip))) {
            for (String pathToFile : pathToFiles) {
                File file = new File(pathToFile);
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        deleteFiles(pathToFiles);
    }
    @Override
    public void deleteFiles(String... pathToFile) {
        Arrays.stream(pathToFile)
                .map(File::new)
                .forEach(File::delete);
    }
    @Override
    public void openZip(String pathToZip, String pathToFolder) {
        File zipZile = new File(pathToZip);
        try (FileInputStream fis = new FileInputStream(zipZile);
             ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry entry;
            String fileName;
            while ((entry = zis.getNextEntry()) != null) {
                fileName = pathToFolder + File.separator + entry.getName();
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
                    for (int c = zis.read(); c != -1; c = zis.read()) {
                        bos.write(c);
                    }
                    bos.flush();
                    zis.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public GameProgress openProgress(String pathToFile) {
        File file = new File(pathToFile);
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameProgress;
    }
}
