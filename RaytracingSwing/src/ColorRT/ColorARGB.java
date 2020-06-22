package ColorRT;

public class ColorARGB {
    private int argb;
    private int a;
    private int r;
    private int g;
    private int b;

    //----------------------------------------------------------------------------------------
    public ColorARGB() {
        this.a = 0;
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.argb = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8)  | ((b & 0xFF) << 0);
    }

    public ColorARGB(ColorARGB color) {
        this.a = color.a;
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.argb = color.argb;
    }

    public ColorARGB(int argb) {
        this.a = (argb >> 24) & 0xFF;
        this.r = (argb >> 16) & 0xFF;
        this.g = (argb >> 8) & 0xFF;
        this.b = (argb >> 0) & 0xFF;
        this.argb = argb;
    }

    public ColorARGB(int a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
        this.argb = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8)  | ((b & 0xFF) << 0);
    }
    //----------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------
    public int getArgb() { return argb; }
    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }

    public void multByNumber(double num) {
        r *= num;
        g *= num;
        b *= num;
        argb = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8)  | ((b & 0xFF) << 0);
    }

    public void addColor(ColorARGB color) {
        r += color.r;
        g += color.g;
        b += color.b;
        argb = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8)  | ((b & 0xFF) << 0);
    }
    //----------------------------------------------------------------------------------------
}
