/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.DetalleRuta;
import com.ec.entidad.Ruta;
import com.ec.servicio.ServiceCheckPoint;
import com.ec.servicio.ServicioRutas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author Omar
 */
public class CheckPoint {

    private List<Ruta> listRoutes = new ArrayList<Ruta>();
    ServicioRutas routeService = new ServicioRutas();
    ServiceCheckPoint checkPointService = new ServiceCheckPoint();
    private DetalleRuta checkPoint = new DetalleRuta();
    private Ruta routeSelected;
    private String action = "create";
    @Wire
    Window windowCheckPoint;

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor1") Ruta ruta, @ExecutionArgParam("valor") DetalleRuta valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        findAllRoutes();
        if (valor != null) {
            this.checkPoint = valor;
            routeSelected = valor.getIdRuta();
            action = "update";
        } else {
            this.checkPoint = new DetalleRuta();
            routeSelected = ruta;
            this.checkPoint.setIdRuta(routeSelected);
            action = "create";
        }
    }

    @Command
    public void saveCheckPoint() {
        if (checkPoint.getIdRuta() != null
                && checkPoint.getDetrNombre() != null
                && checkPoint.getDetrLatitud() != null
                && checkPoint.getDetrLongitud() != null
                && checkPoint.getDetrTiempoReal() != null) {
            if (action.equals("create")) {
                checkPointService.create(checkPoint);
                windowCheckPoint.detach();
            } else {
                checkPointService.update(checkPoint);
                windowCheckPoint.detach();
            }
        } else {
            Clients.showNotification("Verifique la informacion requerida",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }

    private void findAllRoutes() {
        listRoutes = routeService.findAllRoutes();
    }

    public List<Ruta> getListRoutes() {
        return listRoutes;
    }

    public void setListRoutes(List<Ruta> listRoutes) {
        this.listRoutes = listRoutes;
    }

    public DetalleRuta getDetailRoute() {
        return checkPoint;
    }

    public void setDetailRoute(DetalleRuta checkPoint) {
        this.checkPoint = checkPoint;
    }

    public Ruta getRouteSelected() {
        return routeSelected;
    }

    public void setRouteSelected(Ruta routeSelected) {
        this.routeSelected = routeSelected;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public DetalleRuta getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(DetalleRuta checkPoint) {
        this.checkPoint = checkPoint;
    }

}
