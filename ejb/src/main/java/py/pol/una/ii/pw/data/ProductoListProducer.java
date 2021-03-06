package py.pol.una.ii.pw.data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import py.pol.una.ii.pw.model.Producto;

@RequestScoped
public class ProductoListProducer {

    @Inject
    private ProductoRepository productoRepository;

    private List<Producto> productos;

    // @Named provides access the return value via the EL variable name "productos" in the UI (e.g.,
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Producto> getProductos() {
        return productos;
    }

    public void onProductoListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Producto producto) {
        retrieveAllProductosOrderedByName();
    }

    @PostConstruct
    public void retrieveAllProductosOrderedByName() {
        productos = productoRepository.findAllOrderedByName();
    }
}
