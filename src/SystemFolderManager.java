import java.nio.file.*;
import java.nio.file.attribute.*;

public class SystemFolderManager {
    private static final String SYSTEM_FOLDER = "SuperAppRoot/System";

    public static void createProtectedSystemFolder() {
        try {
            Path systemPath = Paths.get(SYSTEM_FOLDER);

            if (!Files.exists(systemPath)) {
                Files.createDirectory(systemPath);

                // Установка атрибутов защиты от записи/удаления
                AclFileAttributeView aclView = Files.getFileAttributeView(
                        systemPath, AclFileAttributeView.class);

                if (aclView != null) {
                    AclEntry entry = AclEntry.newBuilder()
                            .setType(AclEntryType.DENY)
                            .setPrincipal(aclView.getAcl().get(0).principal())
                            .setPermissions(
                                    AclEntryPermission.DELETE,
                                    AclEntryPermission.DELETE_CHILD,
                                    AclEntryPermission.WRITE_ACL,
                                    AclEntryPermission.WRITE_OWNER,
                                    AclEntryPermission.WRITE_ATTRIBUTES,
                                    AclEntryPermission.ADD_FILE,
                                    AclEntryPermission.ADD_SUBDIRECTORY)
                            .build();

                    aclView.setAcl(java.util.List.of(entry));
                }

                System.out.println("Защищенная папка System создана");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при создании защищенной папки: " + e.getMessage());
        }
    }
}