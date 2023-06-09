package Logica;

import Entidades.Producto;
import GUI.pruebaVisual;
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
import java.util.ArrayList;

public class Control {
    ArrayList<Producto> productos = new ArrayList();

    public void ExtraerElementos() {
        String bendita = "https://www.benditabodega.cl";
//        for (int y = 1; y <= 13; y++) {
//            String pagina = "/search?q=northland&page="+y;
        String pagina = "/search?q=northland&page=1";
        String html = bendita + pagina;
        System.out.println("PAGINA 1");
        try {
            Document doc = Jsoup.connect(html).get();
            Elements elProducto;
//            for (int i = 0; i < 12; i++) {
            Elements subEl = doc.getElementsByClass("brand-name").get(0).getElementsByTag("a");
            Document docProducto = Jsoup.connect(bendita + subEl.attr("Href")).get();
//            System.out.println("Producto " + (i + 1));

            elProducto = docProducto.getElementsByClass("row");

            String nombreYID = extraerNombreyID(elProducto);

            String nombre = extraerNombre(nombreYID);

            Integer precio = extraerPrecio(elProducto);

            String marca = extraerMarca(elProducto);

            String ID = extraerID(nombreYID);

            ImageIcon imagen = extraerImagen(elProducto);

            System.out.println("Nombre: " + nombre);
            System.out.println("ID: " + ID);
            System.out.println("Marca: " + marca);
            System.out.println("Precio: " + precio);

//                productos.add(new Producto (nombre,marca,precio,ID,imagen));
//            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Fin");
        }
    }

    private ImageIcon extraerImagen(Elements elProducto) {
        String imagenSrc = elProducto.get(1).getElementsByClass("trsn w-100").attr("src");
        System.out.println(imagenSrc);
        ImageIcon img = null;
        try {
            URL url = new URL(imagenSrc);
            Image imagen = ImageIO.read(url).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            img = new ImageIcon(imagen);

        } catch (MalformedURLException e) {
            System.out.println("Hola");
        } catch (IOException e) {
            System.out.println("Error2");
        } catch (Exception e) {
            System.out.println(e);
        }
        return img;
    }

    private String extraerID(String nombreYID) {
        String ID = nombreYID.replaceAll("[a-zA-Z]", "");
        ID = ID.replaceAll(" ", "");
        return ID;
    }

    private String extraerNombreyID(Elements elProducto) {
        String nombreyID = elProducto.get(1).getElementsByClass("product-form_title page-title").text();
        return nombreyID;
    }

    private String extraerMarca(Elements elProducto) {
        String marca = elProducto.get(1).getElementsByClass("product-form_brand").text();
        return marca;
    }

    private static Integer extraerPrecio(Elements elProducto) {
        String precio = elProducto.get(1).getElementById("product-form-price").text();
        precio = precio.replaceAll("[$-.]", "");
        Integer prec = Integer.parseInt(precio);
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

    }
}
