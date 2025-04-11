import java.nio.file.*;
import java.nio.file.attribute.*;

public class SystemFolderManager {
    private static final String SYSTEM_FOLDER = "SuperAppRoot/System";

    public static void createProtectedSystemFolder() {
        try {
            // Получаем объект типа "Path" с помощью "Paths.get("путь")"
            Path systemPath = Paths.get(SYSTEM_FOLDER);

            if (!Files.exists(systemPath)) {
                // Создаём директорию "System"
                Files.createDirectory(systemPath);

                // Установка атрибутов защиты от записи/удаления
                AclFileAttributeView aclView = Files.getFileAttributeView(systemPath, AclFileAttributeView.class);

                // Проверяет, поддерживается ли AclFileAttributeView (например, на Windows с NTFS — да, на Linux — нет)
                if (aclView != null) {
                    AclEntry entry = AclEntry.newBuilder()                          // Создаёт новую ACL-запись,
                            .setType(AclEntryType.DENY)                             // которая запрещает (DENY) определённые действия.
                            .setPrincipal(aclView.getAcl().get(0).principal())      // Указывает, для какого пользователя (или группы) действует запрет.
                            .setPermissions(                                        // Устанавливает список запрещённых операций:
                                    AclEntryPermission.DELETE,                      // Удаление файла/папки
                                    AclEntryPermission.DELETE_CHILD,                // Добавление файлов/папок
                                    AclEntryPermission.WRITE_ACL,                   // Изменение владельца
                                    AclEntryPermission.WRITE_OWNER,                 // Изменение прав.
                                    AclEntryPermission.WRITE_ATTRIBUTES,
                                    AclEntryPermission.ADD_FILE,
                                    AclEntryPermission.ADD_SUBDIRECTORY)
                            .build();

                    // Устанавливаем новый список ACL — только с этой одной запрещающей записью.
                    // Это перезаписывает текущие разрешения, делая файл/папку "невозможными для изменения" указанным пользователем.
                    aclView.setAcl(java.util.List.of(entry));
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при создании защищенной папки: " + e.getMessage());
        }
    }
}