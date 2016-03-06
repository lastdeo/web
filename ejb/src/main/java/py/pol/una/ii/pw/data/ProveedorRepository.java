package py.pol.una.ii.pw.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import py.pol.una.ii.pw.model.Proveedor;

@ApplicationScoped
public class ProveedorRepository {

    @Inject
    private EntityManager em;

    public Proveedor findById(Long id) {
        return em.find(Proveedor.class, id);
    }

    public List<Proveedor> findByDireccion(String direccion) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Proveedor> criteria = cb.createQuery(Proveedor.class);
        Root<Proveedor> proveedor = criteria.from(Proveedor.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(proveedor).where(cb.equal(proveedor.get(Proveedor_.proveedor), proveedor));
        criteria.select(proveedor).where(cb.equal(proveedor.get("direccion"), direccion));
        return em.createQuery(criteria).getResultList();
    }

    public List<Proveedor> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Proveedor> criteria = cb.createQuery(Proveedor.class);
        Root<Proveedor> proveedor = criteria.from(Proveedor.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(proveedor).orderBy(cb.asc(proveedor.get(Proveedor_.nombre)));
        criteria.select(proveedor).orderBy(cb.asc(proveedor.get("nombre")));
        return em.createQuery(criteria).getResultList();
    }
}