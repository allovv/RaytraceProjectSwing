package GUI;

import ColorRT.ColorARGB;
import ColorRT.ColorConst;
import ColorRT.ColorName;
import CoreLightingRT.Lighting;
import CoreLightingRT.LightingType;
import CoreGraphicsRT.ObjectsRT.Camera;
import CoreGraphicsRT.ObjectsRT.Object;
import CoreGraphicsRT.ObjectsRT.SceneRT;
import CoreGraphicsRT.ObjectsRT.Sphere;
import CoreGraphicsRT.ObjectsRT.Plane;
import Types.RealPoint3D;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ErrorRT.ErrorManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DomXML {
    private static ArrayList<Object> objects;
    private static ArrayList<Lighting> lighting;
    private static Camera camera;

    //Получение фабрики, чтобы после получить билдер документов
    private static DocumentBuilderFactory factory;
    //Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева
    private static DocumentBuilder builder;
    //Документ с данными
    private static Document document;

    public static SceneRT parseXML(File file) {
        objects = new ArrayList<Object>();
        lighting = new ArrayList<Lighting>();
        //-----------------------------------------------------
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            ErrorManager.printError("ParserConfigurationException");
        }
        try {
            document = builder.parse(file);
        } catch (IOException e) {
            ErrorManager.printError("IOException");
        } catch (SAXException e) {
            ErrorManager.printError("SAXException");
        }

        try {
            //-----------------------------------------------------
            //Получение списка всех элементов sphere внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
            NodeList sphereElements = document.getDocumentElement().getElementsByTagName("sphere");

            // Перебор всех элементов sphere
            for (int i = 0; i < sphereElements.getLength(); i++) {
                Node sphere = sphereElements.item(i);

                // Получение атрибутов каждого элемента
                NamedNodeMap attributes = sphere.getAttributes();

                // Добавление сферы. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
                int x = Integer.parseInt(attributes.getNamedItem("x").getNodeValue());
                int y = Integer.parseInt(attributes.getNamedItem("y").getNodeValue());
                int z = Integer.parseInt(attributes.getNamedItem("z").getNodeValue());
                double rad = Double.parseDouble(attributes.getNamedItem("radius").getNodeValue());
                ColorName color = ColorConst.getColorName(attributes.getNamedItem("color").getNodeValue());
                objects.add(new Sphere(new RealPoint3D(x, y, z), new ColorARGB(ColorConst.getColor(color)), rad));
            }

            //-----------------------------------------------------

            NodeList lightElements = document.getDocumentElement().getElementsByTagName("light");
            for (int i = 0; i < lightElements.getLength(); i++) {
                Node light = lightElements.item(i);
                NamedNodeMap attributes = light.getAttributes();

                int x = Integer.parseInt(attributes.getNamedItem("x").getNodeValue());
                int y = Integer.parseInt(attributes.getNamedItem("y").getNodeValue());
                int z = Integer.parseInt(attributes.getNamedItem("z").getNodeValue());
                double intensity = Double.parseDouble(attributes.getNamedItem("intensity").getNodeValue());
                lighting.add(new Lighting(LightingType.point, intensity, new RealPoint3D(x, y, z)));
            }

            int x = 0;
            int y = 0;
            int z = 0;
            NodeList cameraElements = document.getDocumentElement().getElementsByTagName("camera");
            for (int i = 0; i < cameraElements.getLength(); i++) {
                Node cameraItem = cameraElements.item(i);
                NamedNodeMap attributes = cameraItem.getAttributes();

                x = Integer.parseInt(attributes.getNamedItem("x").getNodeValue());
                y = Integer.parseInt(attributes.getNamedItem("y").getNodeValue());
                z = Integer.parseInt(attributes.getNamedItem("z").getNodeValue());
            }
            camera = new Camera(new RealPoint3D(x, y, z));

            objects.add(new Plane(new RealPoint3D(-500, 0, -200), new RealPoint3D(500, 0, 1000), "/img/tex3.png")); //TODO: Особая форма фути до файла! (для jar)
            //lighting.add(new Lighting(LightingType.directional, 0.9, new RealPoint3D(-3, -8, -8)));

            return new SceneRT(objects, lighting, camera);
        } catch (Exception e) {
            ErrorManager.printError(e.toString());
            ErrorManager.printError("Ошибка при загрузке сцены (возможно неправильный формат XML)");
        }
        return new SceneRT();
    }

}