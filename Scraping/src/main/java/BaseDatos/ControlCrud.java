package BaseDatos;

import Entidades.Prenda;
import java.util.List;

public class ControlCrud {
    PrendaJpaController crudPrenda = new PrendaJpaController();
    
    public void crearPrenda(Prenda prenda){
        crudPrenda.create(prenda);
    }
    public List<Prenda> obtenerPrendas(){
        return crudPrenda.findPrendaEntities();
    }
}
