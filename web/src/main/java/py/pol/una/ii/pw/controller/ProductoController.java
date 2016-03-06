package py.pol.una.ii.pw.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import py.pol.una.ii.pw.model.Producto;
import py.pol.una.ii.pw.service.ProductoDelete;
import py.pol.una.ii.pw.service.ProductoRegistro;

// The @Model stereotype is a convenience mechanism to make this a
// request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class ProductoController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private ProductoRegistro productoRegistration;

    @Inject
    private ProductoDelete productoDel;

    private Producto newProducto;

    @Produces
    @Named
    public Producto getNewProducto() {

        return newProducto;
    }

    public void register() throws Exception {

        try {
            productoRegistration.register(newProducto);
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Registered!",
                    "Registration successful"));
            initNewProducto();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, "Registration Unsuccessful");
            facesContext.addMessage(null, m);
        }
    }

    public void delete() throws Exception {

        System.out.println("Entro al borrar de controller");
        try {
            productoRegistration.delete(newProducto);
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Deleted!", "Se borro"));
            initNewProducto();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, "No se borro");
            facesContext.addMessage(null, m);
        }
    }

    @PostConstruct
    public void initNewProducto() {

        newProducto = new Producto();
    }

    private String getRootErrorMessage(Exception e) {

        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
}
