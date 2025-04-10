import java.io.File;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Инициализация графического интерфейса
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);

            // Создание структуры папок
            String rootPath = "SuperAppRoot";
            File rootDir = new File(rootPath);

            if (!rootDir.exists()) {
                rootDir.mkdir();
                System.out.println("Корневая директория создана");
            }

            SystemFolderManager.createProtectedSystemFolder();
            WorkingFoldersManager.createWorkingFolders();
        });
    }
}