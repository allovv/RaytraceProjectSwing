package Types;
import ColorRT.*;

public class Pixel {
    public int x;
    public int y;
    public ColorARGB color;

    public Pixel() {
        x = 0;
        y = 0;
        color = new ColorARGB();
    }

    public Pixel(int x, int y, ColorARGB color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void setFields(int x, int y, ColorARGB color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
