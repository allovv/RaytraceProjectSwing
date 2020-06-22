package CoreGraphicsRT.Texturing;

import ColorRT.ColorARGB;
import ErrorRT.ErrorManager;
import Types.RealPoint3D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Texture {
    private BufferedImage texture; //текстура
    private int textureSizeX;
    private int textureSizeY;

    RealPoint3D vert1; //координаты полигона в пространстве
    RealPoint3D vert2;

    public Texture(String sourcePath, RealPoint3D vert1, RealPoint3D vert2) {
        this.vert1 = vert1;
        this.vert2 = vert2;

        //загрузка текстуры
        try {
            texture = ImageIO.read(getClass().getResourceAsStream(sourcePath)); //TODO: для jar-файла (важно, особый формат)
        } catch (IOException e) {
            ErrorManager.printError("Ошибка при загрузке текстуры");
        }
        textureSizeX = texture.getWidth() - 1;
        textureSizeY = texture.getHeight() - 1;
    }

    public ColorARGB getColor(RealPoint3D point) {
        //размеры полигона
        double lenX = vert2.x - vert1.x;
        double lenZ = vert2.z - vert1.z;
        //координаты точки на полигоне
        double pOnPolyX = (vert2.x - point.x) / lenX;
        double pOnPolyZ = (vert2.z - point.z) / lenZ;

        int coordX = (int) Math.round(pOnPolyX * textureSizeX);
        int coordY = (int) Math.round(pOnPolyZ * textureSizeY);

        int rgb = 0;
        if (0 <= coordX && coordX <= textureSizeX) {
            if (0 <= coordY && coordY <= textureSizeY) {
                rgb = texture.getRGB(coordX, coordY);
            }
        }
        return new ColorARGB(rgb);
    }
}
