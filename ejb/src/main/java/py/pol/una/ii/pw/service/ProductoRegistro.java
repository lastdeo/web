package py.pol.una.ii.pw.service;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import py.pol.una.ii.pw.model.Producto;

// The @Stateless annotation eliminates the need for manual transaction
// demarcation
@Stateless
public class ProductoRegistro {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Producto> productoEventSrc;

    public void register(Producto producto) throws Exception {

        log.info("Registrando " + producto.getNombre());
        em.persist(producto);
        productoEventSrc.fire(producto);
    }

    public void delete(Producto producto) throws Exception {

        log.info("Borrando " + producto.getNombre());
        em.remove(producto);
        productoEventSrc.fire(producto);
    }
}
