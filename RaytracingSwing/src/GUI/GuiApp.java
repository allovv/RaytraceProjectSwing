package GUI;

import Constants.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GuiApp {
    protected static JLabel labelFPS = new JLabel();
    protected static JLabel errorLabel= new JLabel();
    protected static JFrame appFrame;
    protected static PixelWriter pixelWriter;

    //----------------------------------------------------------------------------------------
    public GuiApp(BufferedImage img) {
        Font fontApp = new Font("Verdana", Font.PLAIN, 11); //шрифт

        //получаем надписи с информацией
        labelFPS = GuiElements.getLabel(fontApp, "FPS");
        errorLabel = GuiElements.getLabel(fontApp, "Error (окно для вывода ошибок)");

        //меню
        JMenuBar menu = GuiMenu.getMenuBar(fontApp);

        pixelWriter = new PixelWriter(img, new Dimension(Const.APP_WIDTH, Const.APP_HEIGHT));

        //контейнер для компановки объектов
        JPanel panelContainer = GuiElements.getPanelContainer(fontApp, new BorderLayout(), "mainContainer");
        panelContainer.add(labelFPS, BorderLayout.NORTH);
        panelContainer.add(errorLabel, BorderLayout.SOUTH);
        panelContainer.add(pixelWriter, BorderLayout.CENTER);

        //-------------------------
        appFrame = createGUI();
        appFrame.setJMenuBar(menu);
        appFrame.add(panelContainer, BorderLayout.CENTER);
        appFrame.addKeyListener(new KeyListener() {
            //обработка нажатий для окна отрисовки
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                CatchAction.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                CatchAction.reProcessing();
            }
        });

        setVisibleGUI(appFrame);
    }
    //----------------------------------------------------------------------------------------
    public static void setError(String str) { //отобразить ошибку
        errorLabel.setText(str);
    }
    public static void setFPS(String str) { //отобразить fps
        labelFPS.setText(str);
    }
    public void draw(BufferedImage img) { pixelWriter.draw(img);} //отобразить отрендеренное изображение

    //----------------------------------------------------------------------------------------
    private static class PixelWriter extends JPanel {
        BufferedImage graphicBuffer; //инициализируем графический буфер
        Dimension dimension;

        public PixelWriter(BufferedImage img, Dimension dimension) {
            graphicBuffer = img; //передаем графическому драйверу ссылку на исходный графический буффер

            this.dimension = dimension;
            this.setLayout(null);
            this.setSize(dimension);
            this.setMinimumSize(dimension);
            this.setMaximumSize(dimension);
            this.setPreferredSize(dimension);

            this.setBorder(BorderFactory.createTitledBorder("Display"));
            this.setBackground(Color.black);
        }
        //---------------------------------------

        @Override
        protected void paintComponent(Graphics g) {
            //super.paintComponent(g);
            g.drawImage(graphicBuffer, 0, 0, null);
        }
        public void draw(BufferedImage graphicBuffer) {
            this.graphicBuffer = graphicBuffer;
            this.repaint(); //перерисовать (перерисовывается данный объект, вызывается метод paintComponent)
        }
    }
    //----------------------------------------------------------------------------------------

    private static JFrame createGUI() {
        //инструмент для работы с GUI
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        //окно приложения
        JFrame appFrame = new JFrame();

        appFrame.setTitle("RaytraceSwing");
        appFrame.setDefaultCloseOperation(appFrame.EXIT_ON_CLOSE);
        appFrame.setResizable(false); //произвольное изменение размера окна
        appFrame.setLocationRelativeTo(null); //заголовок по центру

        appFrame.setMaximumSize(dimension); //ограничиваем размеры окна

        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels(); //оформление основного фрейма
        for (UIManager.LookAndFeelInfo lookAndFeel : lookAndFeelInfos) {
            System.out.println(lookAndFeel);
        }
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //оформление основного фрейма
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"); //оформление основного фрейма
            } catch (Exception e2) {
                appFrame.setDefaultLookAndFeelDecorated(true);
            }
        }

        appFrame.setLocation(dimension.width / 2 - Const.APP_WIDTH / 2, dimension.height / 2 - Const.APP_HEIGHT / 2); //расположение окна приложения
        appFrame.setLayout(new BorderLayout());

        return appFrame;
    }

    private static void setVisibleGUI(JFrame appFrame) {
        appFrame.pack(); //изменение размера формы (устанавливает минимально возможный размер, подгоняет под предпочтительный размер)
        appFrame.setVisible(true); //формирование окна
    }
}
