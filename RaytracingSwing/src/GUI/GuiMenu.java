package GUI;

import CoreGraphicsRT.AppSwing;
import CoreGraphicsRT.GraphicsCore;
import CoreGraphicsRT.ObjectsRT.SceneRT;
import ErrorRT.ErrorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GuiMenu {
    public static JMenuBar getMenuBar(Font fontApp) {
        //меню приложения
        JMenuBar menuBar = new JMenuBar();

        //пункт меню
        JMenu infoMenu = new JMenu("Информация");

        infoMenu.add(getMenuItemAuthor(fontApp));
        infoMenu.add(getMenuItemControl(fontApp));
        infoMenu.add(getMenuItemScene(fontApp));

        //пункт меню
        JMenu sceneMenu = new JMenu("Сцена");
        sceneMenu.add(getMenuItemLoad(fontApp));

        menuBar.add(infoMenu);
        menuBar.add(sceneMenu);
        return menuBar;
    }

    //--------------------------------------------
    private static JMenuItem getMenuItemControl(Font fontApp) {
        JMenuItem controlItem = new JMenuItem("Управление");
        controlItem.setFont(fontApp);
        controlItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null,
                        "Управление камерой \n" +
                                "\n" +
                                "Положение в горизонтальной плоскости: ← → ↑↓  \n" +
                                "\n" +
                                "   Влево ← \n" +
                                "   Вправо → \n" +
                                "   Вперед ↑ \n" +
                                "   Назад ↓ \n" +
                                "\n" +
                                "Положение в вертикальной плоскости: LShift + ...\n" +
                                "\n" +
                                "   Вверх LShift + X \n" +
                                "   Вниз LShift + Z",
                        "Управление", JOptionPane.PLAIN_MESSAGE);
            }
        });
        return controlItem;
    }
    private static JMenuItem getMenuItemScene(Font fontApp) {
        JMenuItem sceneItem = new JMenuItem("Как задать сцену?");
        sceneItem.setFont(fontApp);
        sceneItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null,
                        "Описание \n" +
                                "\n" +
                                "Сцена задается в файле формата XML  \n" +
                                "\n" +
                                "   Пример: \n" +
                                "\n" +
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                "<scene>\n" +
                                "   <objects>\n" +
                                "      <sphere x=\"-40\" y=\"10\" z=\"-40\" color=\"Silver\" radius=\"10\"/>\n" +
                                "      <sphere x=\"40\" y=\"10\" z=\"-40\" color=\"Silver\" radius=\"10\"/>\n" +
                                "      <sphere x=\"-40\" y=\"10\" z=\"40\" color=\"Silver\" radius=\"10\"/>\n" +
                                "      <sphere x=\"40\" y=\"10\" z=\"40\" color=\"Silver\" radius=\"10\"/>\n" +
                                "   </objects>\n" +
                                "   <lightings>\n" +
                                "      <light intensity=\"0.9\" x=\"0\" y=\"10\" z=\"0\"/>\n" +
                                "   </lightings>\n" +
                                "   <view>\n" +
                                "      <camera x=\"0\" y=\"30\" z=\"-180\"/>\n" +
                                "   </view>\n" +
                                "</scene>" +
                                "\n" +
                                "\n" +
                                "Доступные цвета: \n" +
                                "Black, White, Red, Green, Blue, Yellow, SpaceGrey, Silver",
                        "Как задать сцену", JOptionPane.PLAIN_MESSAGE);
            }
        });
        return sceneItem;
    }
    private static JMenuItem getMenuItemAuthor(Font fontApp) {
        JMenuItem authorItem = new JMenuItem("Авторство");
        authorItem.setFont(fontApp);
        authorItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null,
                        "Кузнецов алексей ©\n" +
                                "\n" +
                                "Вологодский государственный университет \n" +
                                "Факультет прикладной математики, компьютерных технологий и физики \n" +
                                "ПМ-31 \n" +
                                "2020",
                        "Авторство", JOptionPane.PLAIN_MESSAGE);
            }
        });
        return authorItem;
    }

    //--------------------------------------------------------------
    private static JMenuItem getMenuItemLoad(Font fontApp) {
        JMenuItem loadItem = new JMenuItem("Загрузить");
        loadItem.setFont(fontApp);
        loadItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setCurrentDirectory(new File(".")); //установка текущей директории как стартовой

                int ret = fileOpen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    fileOpen.getSelectedFile().getName(); //путь до файла
                    if (fileOpen.getSelectedFile().getName().endsWith("xml")) {
                        ErrorManager.printError("Выбран файл: " + fileOpen.getSelectedFile().getPath().toString());
                        File file = fileOpen.getSelectedFile();
                        if (file != null) {
                            SceneRT sceneRT = DomXML.parseXML(file);
                            GraphicsCore.setSceneRT(sceneRT);
                            AppSwing.reProcessing();
                        } else {
                            ErrorManager.printError("Файл невозможно открыть!");
                        }
                    } else {
                        ErrorManager.printError("Выбран неверный формат файла");
                    }
                }
            }
        });
        return loadItem;
    }

}
