import java.io.File;

public class WorkingFoldersManager {
    private static final String[] FOLDERS = {
            "Documents",
            "Images",
            "Music",
            "Videos",
            "Downloads"
    };

    public static void createWorkingFolders() {
        for (String folder : FOLDERS) {
            File dir = new File("SuperAppRoot/" + folder);
            if (!dir.exists()) {
                dir.mkdir();
            }
        }
    }
}