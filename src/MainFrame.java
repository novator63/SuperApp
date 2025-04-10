import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        // Настройка главного окна
        setTitle("Суперапп"); // some comment
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание строки меню
        JMenuBar menuBar = new JMenuBar();

        // Меню "Файл"
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Меню "О программе"
        JMenu aboutMenu = new JMenu("Справка");
        JMenuItem aboutItem = new JMenuItem("О программе");
        aboutItem.addActionListener(e -> showAboutDialog());
        aboutMenu.add(aboutItem);

        // Добавление меню в строку меню
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        setJMenuBar(menuBar);

        // Контекстное меню
        JPopupMenu popupMenu = createContextMenu();
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
                "Суперапп\n\n" +
                        "Операционная система: Linux Ubuntu\n" +
                        "Язык программирования: Java\n\n" +
                        "Разработчик:\n" +
                        "Пачкалов Кирилл Максимович\n" +
                        "Группа: ПрИ-32",
                "О программе",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private JPopupMenu createContextMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        // Пункты контекстного меню
        JMenuItem newItem = new JMenuItem("Создать");
        JMenuItem copyItem = new JMenuItem("Копировать");
        JMenuItem pasteItem = new JMenuItem("Вставить");
        JMenuItem deleteItem = new JMenuItem("Удалить");

        popupMenu.add(newItem);
        popupMenu.add(copyItem);
        popupMenu.add(pasteItem);
        popupMenu.addSeparator();
        popupMenu.add(deleteItem);

        return popupMenu;
    }
}