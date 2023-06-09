package Main;

import Entidades.Prenda;
import GUI.MenuPrincipal;
import Logica.Control;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.ImageIcon;

public class Main {

    public static void main(String[] args) {
        Control c = new Control();
        c.ActualizarElementos();
        c.subirPrendas();
       // MenuPrincipal menu = new MenuPrincipal();
    }
}
