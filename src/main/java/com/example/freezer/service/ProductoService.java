package com.example.freezer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.freezer.dao.*;
import com.example.freezer.dto.CargaTemperaturaRequest;
import com.example.freezer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoDAO productoDAO;

    @Autowired
    private RegistroTemperaturaDAO registroTemperaturaDAO;

    @Autowired
    private GrupoProductoDAO grupoProductoDAO;

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private LocacionDAO locacionDAO;

    @Autowired
    private OrganizacionDAO organizacionDAO;

    public List<RegistroTemperatura> getHistorialByProducto(Long prodID){
        Optional<Producto> productoBuscado = productoDAO.findById(prodID);

        return registroTemperaturaDAO.findRegistroTemperaturasByProductoOrderByFechaHora(productoBuscado.get());
    }


    public List<Producto> getProductosByOrganizacion (Long orgID){
        //Obtener todos los grupos de la organizacion
        Optional<Organizacion> organizacion = organizacionDAO.findById(orgID);

        if (organizacion.isEmpty()){
            throw new RuntimeException();
        }

        List<Locacion> locaciones = locacionDAO.findAllByOrganizacion(organizacion.get());
        List<GrupoProducto> grupos = new ArrayList<>();
        for (Locacion locacion : locaciones){
            grupos.addAll(grupoProductoDAO.findGrupoProductoByLocacion(locacion));
        }

        List<Producto> productosDeLaOrganizacion = new ArrayList<>();
        for (GrupoProducto grupo : grupos){
            productosDeLaOrganizacion.addAll(productoDAO.findProductosByGrupoProducto(grupo));
        }

        return productosDeLaOrganizacion;
    }


    public boolean registrarTemperaturasByGrupo(CargaTemperaturaRequest cargaTemperaturaRequest){
        Optional<GrupoProducto> grupoProducto = grupoProductoDAO.findById(cargaTemperaturaRequest.getGrupoID());
        Optional<Empleado> empleado = empleadoDAO.findById(cargaTemperaturaRequest.getEmpleadoID());

        if (grupoProducto.isEmpty() || empleado.isEmpty()){
            throw new RuntimeException();
        }


        List<Producto> productosAActualizar = productoDAO.findProductosByGrupoProducto(grupoProducto.get());
        for (Producto producto : productosAActualizar){
            RegistroTemperatura registroTemperatura = new RegistroTemperatura();
            registroTemperatura.setProducto(producto);
            registroTemperatura.setTemperatura(cargaTemperaturaRequest.getTemperatura());
            registroTemperatura.setEmpleado(empleado.get());
            registroTemperatura.setFechaHora(LocalDateTime.now());

            registroTemperaturaDAO.save(registroTemperatura);
        }

        return true;
    }

}

