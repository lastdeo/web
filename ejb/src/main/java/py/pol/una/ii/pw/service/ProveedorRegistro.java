package py.pol.una.ii.pw.service;

import py.pol.una.ii.pw.model.Proveedor;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class ProveedorRegistro {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Proveedor> proveedorEventSrc;

    public void register(Proveedor proveedor) throws Exception {
        log.info("Registrando " + proveedor.getNombre());
        em.persist(proveedor);
        proveedorEventSrc.fire(proveedor);
    }
}