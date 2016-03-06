package py.pol.una.ii.pw.data;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import py.pol.una.ii.pw.model.Producto;

@ApplicationScoped
public class ProductoRepository {

    @Inject
    private EntityManager em;

    public Producto findById(Long id) {

        return em.find(Producto.class, id);
    }

    public List<Producto> findByProveedor(String proveedor) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = cb.createQuery(Producto.class);
        Root<Producto> producto = criteria.from(Producto.class);
        // Swap criteria statements if you would like to try out type-safe
        // criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(producto).where(cb.equal(producto.get(Producto_.proveedor),
        // proveedor));
        criteria.select(producto).where(
                cb.equal(producto.get("proveedor"), proveedor));
        return em.createQuery(criteria).getResultList();
    }

    public List<Producto> findAllOrderedByName() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = cb.createQuery(Producto.class);
        Root<Producto> producto = criteria.from(Producto.class);
        // Swap criteria statements if you would like to try out type-safe
        // criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(producto).orderBy(cb.asc(producto.get(Producto_.nombre)));
        criteria.select(producto).orderBy(cb.asc(producto.get("nombre")));
        return em.createQuery(criteria).getResultList();
    }
}
