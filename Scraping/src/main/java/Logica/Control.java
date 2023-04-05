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

                    ImageIcon imagen = extraerImagen(elProducto);

                    System.out.println("Nombre: " + nombre);
                    System.out.println("ID: " + ID);
                    System.out.println("Marca: " + marca);
                    System.out.println("Precio: " + precio);

                    prendas.add(new Prenda(nombre, marca, precio, imagen));
                }
            } catch (IOException e) {
                System.out.println(e);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Fin");
            }
        }
    }
    
    public void extraerPrendas(){
        prendas.clear();
        prendas = crudPrenda.obtenerPrendas();
        prendas = prendas.stream()
                .sorted(Comparator.comparing(Prenda::getNombre))
                .collect(Collectors.toList());
    }
    
    public List<Icon> extraerIconos(){
        extraerPrendas();
        List<Icon> iconos = new ArrayList();
        for (Prenda prenda : prendas) {
            ImageIcon imagen = prenda.getImagen();
            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
            iconos.add(icono);
        }
        return iconos;
    }
    
    public void subirPrendas(){
        for (Prenda prenda : prendas) {
            crudPrenda.crearPrenda(prenda);
        }
    }

    private ImageIcon extraerImagen(Elements elProducto) {
        String imagenSrc = elProducto.get(1).getElementsByClass("trsn w-100").attr("src");
        System.out.println(imagenSrc);
        ImageIcon img = null;
        try {
            URL url = new URL(imagenSrc);
            Image imagen = ImageIO.read(url);
            img = new ImageIcon(imagen);

        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
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
