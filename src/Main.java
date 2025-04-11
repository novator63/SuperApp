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
            // Определяем объект для каталога
            File rootDir = new File(rootPath);

            // Если каталога не существует, создаём его
            if (!rootDir.exists()) {
                rootDir.mkdir(); // boolean mkdir(): создает новый каталог и при удачном создании возвращает значение true
            }

            SystemFolderManager.createProtectedSystemFolder();
            WorkingFoldersManager.createWorkingFolders();
        });
    }
}