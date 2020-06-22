package GUI;

import javax.swing.*;
import java.awt.*;

public class GuiElements {
    public static JLabel getLabel(Font fontApp, String str) {
        //простое поле для вывода текста
        JLabel labelFPS = new JLabel();
        labelFPS.setText(str);
        labelFPS.setFont(fontApp);
        labelFPS.setHorizontalAlignment(SwingConstants.CENTER);
        labelFPS.setOpaque(true);
        return labelFPS;
    }

    public static JPanel getPanelContainer(Font fontApp, BorderLayout borderLayout, String str) {
        //простая панель для группировки элементов
        JPanel panelCont = new JPanel();
        panelCont.setFont(fontApp);
        panelCont.setLayout(borderLayout);
        panelCont.setBorder(BorderFactory.createTitledBorder(str));
        return panelCont;
    }
}
