package ColorRT;

public class ColorConst {
    public static ColorARGB Black = new ColorARGB(255, 0, 0, 0);
    public static ColorARGB White = new ColorARGB(255, 255, 255, 255);
    public static ColorARGB Red = new ColorARGB(255, 255, 0, 0);
    public static ColorARGB Green = new ColorARGB(255, 0, 255, 0);
    public static ColorARGB Blue = new ColorARGB(255, 0, 0, 255);
    public static ColorARGB Yellow = new ColorARGB(255, 255, 255, 0);

    public static ColorARGB Sun = new ColorARGB(255, 252, 102, 0);
    public static ColorARGB Mercury = new ColorARGB(255, 138, 134, 135);
    public static ColorARGB Venus = new ColorARGB(255, 216, 122, 53);
    public static ColorARGB Earth = new ColorARGB(255, 42, 62, 87);
    public static ColorARGB Mars = new ColorARGB(255, 195, 74, 37);
    public static ColorARGB Jupiter = new ColorARGB(255, 215, 197, 173);
    public static ColorARGB Saturn = new ColorARGB(255, 197, 161, 124);
    public static ColorARGB Uranus = new ColorARGB(255, 114, 114, 114);
    public static ColorARGB Neptune = new ColorARGB(255, 93, 137, 208);

    public static ColorARGB SpaceGrey = new ColorARGB(255, 37, 40, 42);
    public static ColorARGB Silver = new ColorARGB(255, 204, 204, 204);

    public static ColorARGB backgroundColor = Black;

    //-----------------------------------------------------------------
    public static ColorARGB getColor(ColorName color) {
        switch (color) {
            case Black:
                return ColorConst.Black;
            case White:
                return ColorConst.White;
            case Red:
                return ColorConst.Red;
            case Green:
                return ColorConst.Green;
            case Blue:
                return ColorConst.Blue;
            case Yellow:
                return ColorConst.Yellow;
            case SpaceGrey:
                return ColorConst.SpaceGrey;
            case Silver:
                return ColorConst.Silver;
            default:
                return backgroundColor;
        }
    }

    public static ColorName getColorName(String string) {
        switch (string) {
            case "Black":
                return ColorName.Black;
            case "White":
                return ColorName.White;
            case "Red":
                return ColorName.Red;
            case "Green":
                return ColorName.Green;
            case "Blue":
                return ColorName.Blue;
            case "Yellow":
                return ColorName.Yellow;
            case "SpaceGrey":
                return ColorName.SpaceGrey;
            case "Silver":
                return ColorName.Silver;
            default:
                return ColorName.Black;
        }
    }
    //-----------------------------------------------------------------
}
