package py.pol.una.ii.pw.service;

import py.pol.una.ii.pw.model.Producto;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class ProductoDelete {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Producto> productoEventSrc;

    public void delete(Producto producto) throws Exception {
        log.info("Eliminando " + producto.getNombre());
        em.remove(producto);
        productoEventSrc.fire(producto);
    }
}