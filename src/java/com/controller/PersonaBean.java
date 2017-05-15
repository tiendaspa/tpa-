package com.controller;

import com.controller.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.commons.lang.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@ManagedBean
@RequestScoped
public class PersonaBean {

    private Persona persona = new Persona();
    private static List<Persona> lista = new ArrayList();

    public List<Persona> getLista() {
        return lista;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setLista(List<Persona> lista) {
        this.lista = lista;
    }

    public void agregar() {
        this.lista.add(persona);
        notificarPUSH();
    }

    public void notificarPUSH() {

        String summary = "Nuevo Elemento";
        String detail = "Se agrego a la lista";
        String CHANNEL = "/notify";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml(summary), StringEscapeUtils.escapeHtml(detail)));
    }
}
