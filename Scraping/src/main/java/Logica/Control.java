package Logica;

import BaseDatos.ControlCrud;
import Entidades.ColorDistance;
import Entidades.Prenda;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Element;

public class Control {

    private ControlCrud crudPrenda = new ControlCrud();
    private List<Prenda> prendas = new ArrayList();

    public void ActualizarElementos() {
        String bendita = "https://www.benditabodega.cl";
        for (int y = 1; y <= 3; y++) {
            String pagina = "/search?q=northland&page=" + y;
            String html = bendita + pagina;
            System.out.println("PAGINA " + y);
            try {
                Document doc = Jsoup.connect(html).get();
                Elements elProducto;
                for (int i = 0; i < 12; i++) {
                    Elements subEl = doc.getElementsByClass("brand-name").get(i).getElementsByTag("a");
                    Document docProducto = Jsoup.connect(bendita + subEl.attr("Href")).get();
                    System.out.println("Producto " + (i + 1));

                    elProducto = docProducto.getElementsByClass("row");

                    String nombreYID = extraerNombreyID(elProducto);

                    String nombre = extraerNombre(nombreYID);

                    Float precio = extraerPrecio(elProducto);

                    String marca = extraerMarca(elProducto).toUpperCase();

                    String ID = extraerID(nombreYID);

                    List<String> caracteristicas = extraerCaracteristicas(elProducto);

                    List<String> imagenes = extraerImagen(elProducto);

                    prendas.add(new Prenda(nombre, marca, precio, imagenes, caracteristicas));
                }
            } catch (IOException e) {
                System.out.println(e);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Fin");
            }
        }
    }

    public List<Prenda> extraerPrendas() {
        prendas.clear();
        prendas = crudPrenda.obtenerPrendas();
        prendas = prendas.stream()
                .sorted(Comparator.comparing(Prenda::getNombre))
                .collect(Collectors.toList());
        return prendas;
    }

    public void subirPrendas() {
        for (Prenda prenda : prendas) {
            crudPrenda.crearPrenda(prenda);
        }
    }

    private List<String> extraerImagen(Elements elProducto) {
        Elements fotosEl = elProducto.get(1).getElementsByClass("trsn w-100");
        List<String> fotos = new ArrayList<>();
        for(Element foto : fotosEl) {
            fotos.add(foto.attr("src"));
        }
       /* String imagenSrc = elProducto.get(1).getElementsByClass("trsn w-100").attr("src");
        try {
            URL url = new URL(imagenSrc);
            extraerColor(url);
        } catch (IOException e) {
            System.out.println(e);
        }*/
        return fotos;
    }

    private void extraerColor(URL url) throws IOException {
        Color[][] colores = null;
        try {
            BufferedImage imaa = ImageIO.read(url);
            int width = imaa.getWidth();
            int height = imaa.getHeight();
            colores = new Color[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    colores[i][j] = new Color(imaa.getRGB(i, j));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    /*    verificarColores(colores);*/
    }

    public double colorDistance(Color c1, Color c2) {
        double r1 = c1.getRed();
        double g1 = c1.getGreen();
        double b1 = c1.getBlue();
        double r2 = c2.getRed();
        double g2 = c2.getGreen();
        double b2 = c2.getBlue();
        double distance = Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(g1 - g2, 2) + Math.pow(b1 - b2, 2));
        return distance;
    }

    public List<Color> searchByColor(Color targetColor, Color[][] colors, int k) {
        List<Color> result = new ArrayList<>();
        List<ColorDistance> colorDistances = new ArrayList<>();
        for (int x = 0; x < colors.length - 1; x++) {
            for (int y = 0; y < colors[x].length - 1; y++) {
                Color color = colors[x][y];
                double distance = colorDistance(targetColor, color);
                colorDistances.add(new ColorDistance(color, distance));
            }
        }
        Collections.sort(colorDistances, Comparator.comparingDouble(ColorDistance::getDistance));
        for (int i = 0; i < k; i++) {
            result.add(colorDistances.get(i).getColor());
        }
        return result;
    }

   /* private void verificarColores(Color[][] colores) {
        if (verificarNegro(colores)) {
            System.out.println("Esta prenda es negra");
        } else if (verificarNaranja(colores)) {
            System.out.println("Esta prenda es Naranja");
        } else if (verificarRojo(colores)) {
            System.out.println("Esta prenda es roja");
        } else if (verificarAzul(colores)) {
            System.out.println("Esta prenda es azul");
        } else if (verificarVerde(colores)) {
            System.out.println("Esta prenda es verde");
        } else if (verificarBlanco(colores)) {
            System.out.println("Esta prenda es blanca");
        } else {
            System.out.println("Color no definido");
        }
    }*/

    public boolean verificarNegro(Color[][] colores) {
        Color negro = new Color(100, 100, 100);
        int k = 100; // Buscar los colores más cercanos
        List<Color> closestColors = searchByColor(negro, colores, k);
        
        int contador = 0;
        int negroBlue = negro.getBlue();
        int negroRed = negro.getRed();
        int negroGreen = negro.getGreen();
        for (Color color : closestColors) {
            System.out.println(color.getRGB() + "|" + negro.getRGB());
            if(color.getRGB()<=negro.getRGB())
                contador++;
        }
        System.out.println(contador);
        return contador > 60;
    }

    public boolean verificarRojo(Color[][] colores) {
        Color rojo = new Color(255, 0, 0);
        int k = 800 * 800; // Buscar los colores más cercanos
        List<Color> closestColors = searchByColor(rojo, colores, k);
        int contador = 0;
        int rojoBlue = rojo.getBlue();
        int rojoRed = rojo.getRed();
        int rojoGreen = rojo.getGreen();
        for (Color color : closestColors) {
            int colorBlue = color.getBlue();
            int colorRed = color.getRed();
            int colorGreen = color.getGreen();
            if ((colorRed > colorBlue)) {
                if (colorRed > colorGreen) {
                    contador++;
                }
            }
        }
        System.out.println(contador);
        return contador > 358000;
    }

    public boolean verificarVerde(Color[][] colores) {
        Color verde = new Color(0, 255, 0);
        int k = 800 * 800; // Buscar los colores más cercanos
        List<Color> closestColors = searchByColor(verde, colores, k);
        int contador = 0;
        for (Color color : closestColors) {
            int colorBlue = color.getBlue();
            int colorRed = color.getRed();
            int colorGreen = color.getGreen();
            if (colorGreen > colorRed) {
                if (colorGreen > colorBlue) {
                    contador++;
                }
            }
        }
        System.out.println(contador);
        return contador > 20000;
    }

    public boolean verificarNaranja(Color[][] colores) {
        Color verde = new Color(0, 255, 0);
        int k = 800 * 800; // Buscar los colores más cercanos
        List<Color> closestColors = searchByColor(verde, colores, k);
        int contador = 0;
        for (Color color : closestColors) {
            int colorBlue = color.getBlue();
            int colorRed = color.getRed();
            int colorGreen = color.getGreen();
            if (colorRed > colorGreen && colorGreen > colorBlue) {
                contador++;
            }
        }
        System.out.println(contador);
        return contador > 200000;
    }

    public boolean verificarAzul(Color[][] colores) {
        Color azul = new Color(0, 0, 255);
        int k = 800 * 800; // Buscar los colores más cercanos
        List<Color> closestColors = searchByColor(azul, colores, k);
        int contador = 0;
        int azulBlue = azul.getBlue();
        int azulRed = azul.getRed();
        int azulGreen = azul.getGreen();
        for (Color color : closestColors) {
            int colorBlue = color.getBlue();
            int colorRed = color.getRed();
            int colorGreen = color.getGreen();
            if (colorBlue > colorRed) {
                if (colorBlue > colorGreen) {
                    contador++;
                }
            }
        }
        System.out.println(contador);
        return contador > 200000;
    }

    public boolean verificarBlanco(Color[][] colores) {
        Color target = new Color(255, 0, 0);
        int k = 800 * 800; // Buscar los colores más cercanos
        List<Color> closestColors = searchByColor(target, colores, k);
        int contador = 0;
        int targetBlue = target.getBlue();
        int targetRed = target.getRed();
        int targetGreen = target.getGreen();
        for (Color color : closestColors) {
            int colorBlue = color.getBlue();
            int colorRed = color.getRed();
            int colorGreen = color.getGreen();
            if ((colorBlue + (targetBlue / 2)) > 120) {
                if ((colorRed + (targetRed / 2)) > 120) {
                    if ((colorGreen + (targetGreen / 2)) > 120) {
                        contador++;
                    }
                }
            }
        }
        System.out.println(contador);
        return contador > 320000;
    }

    public Icon extraerIcono(ImageIcon imagen) {
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        return icono;
    }

    private String extraerID(String nombreYID) {
        String ID = nombreYID.replaceAll("[a-zA-Z]", "");
        ID = ID.replaceAll(" ", "");
        return ID;
    }

    private ArrayList<String> extraerCaracteristicas(Elements elProducto) {
        ArrayList<String> caracteristicas = new ArrayList();

        Elements descripcion = elProducto.get(1).getElementById("collapseDescription").getElementsByTag("ul");

        try {
            Elements c = descripcion.get(0).getElementsByTag("li");
            for (Element element : c) {
                caracteristicas.add(element.text());
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return caracteristicas;
    }

    private String extraerNombreyID(Elements elProducto) {
        return elProducto.get(1).getElementsByClass("product-form_title page-title").text();
    }

    private String extraerMarca(Elements elProducto) {
        return elProducto.get(1).getElementsByClass("product-form_brand").text();
    }

    private static Float extraerPrecio(Elements elProducto) {
        String precio = Objects.requireNonNull(elProducto.get(1).getElementById("product-form-price")).text();
        precio = precio.replaceAll("[$-.]", "");
        return Float.valueOf(precio);
    }

    private static String extraerNombre(String nombreYID) {
        String nombre = nombreYID.replaceAll("[0-9-'-']", "");
        nombre = nombre.replaceAll("NORTHLAND ", "");
        nombre = nombre.replaceAll("NL ", "");
        return nombre;
    }

    //    }
    public void mostrarProductos() {
        prendas = crudPrenda.obtenerPrendas();
        for (Prenda prenda : prendas) {
            System.out.println(prenda.getNombre());
        }
    }

}
