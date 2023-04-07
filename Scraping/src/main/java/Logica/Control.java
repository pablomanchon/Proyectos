package Logica;

import BaseDatos.ControlCrud;
import Entidades.Prenda;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Element;

public class Control {

    private ControlCrud crudPrenda = new ControlCrud();
    private List<Prenda> prendas = new ArrayList();

    public void ActualizarElementos() {
        String bendita = "https://www.benditabodega.cl";
        for (int y = 1; y <= 2; y++) {
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

                    Double precio = extraerPrecio(elProducto);

                    String marca = extraerMarca(elProducto).toUpperCase();

                    String ID = extraerID(nombreYID);

                    System.out.println(nombre);
                    System.out.println(ID);
                    ArrayList<String> caracteristicas = extraerCaracteristicas(elProducto);

                    Icon imagen = extraerImagen(elProducto);

                    prendas.add(new Prenda(nombre, marca, precio, imagen, caracteristicas));
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

    private Icon extraerImagen(Elements elProducto) {
        String imagenSrc = elProducto.get(1).getElementsByClass("trsn w-100").attr("src");
        Icon img = null;
        try {
            URL url = new URL(imagenSrc);
            Image imagen = ImageIO.read(url);
            img = new ImageIcon(imagen);
        } catch (IOException e) {
            System.out.println(e);
        }
        return img;
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
                caracteristicas.add("- " + element.text());
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        for (String aux : caracteristicas) {
            System.out.println(aux);
        }
        return caracteristicas;
    }

    private String extraerNombreyID(Elements elProducto) {
        String nombreyID = elProducto.get(1).getElementsByClass("product-form_title page-title").text();
        return nombreyID;
    }

    private String extraerMarca(Elements elProducto) {
        String marca = elProducto.get(1).getElementsByClass("product-form_brand").text();
        return marca;
    }

    private static Double extraerPrecio(Elements elProducto) {
        String precio = elProducto.get(1).getElementById("product-form-price").text();
        precio = precio.replaceAll("[$-.]", "");
        Double prec = Double.valueOf(precio);
        return prec;
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
