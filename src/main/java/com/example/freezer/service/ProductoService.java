package com.example.freezer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.freezer.dao.*;
import com.example.freezer.dto.CargaTemperaturaRequest;
import com.example.freezer.dto.GrupoProductoRequest;
import com.example.freezer.dto.ProductoRequest;
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
        for (GrupoProducto grupo : grupos) {
            List<Producto> productos = productoDAO.findProductosByGrupoProducto(grupo);

            // Filtrar solo productos activos
            for (Producto producto : productos) {
                if (producto.isActivo()) {
                    productosDeLaOrganizacion.add(producto);
                }
            }
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

    public List<RegistroTemperatura> getRegistrosByOrganizacion(Long orgID) {

        List<Producto> productos = this.getProductosByOrganizacion(orgID);

        List<RegistroTemperatura> registroTemperaturas = new ArrayList<>();
        for (Producto producto : productos){
            registroTemperaturas.addAll(registroTemperaturaDAO.findRegistroTemperaturasByProductoOrderByFechaHora(producto));
        }

        return registroTemperaturas;
    }

    public Producto cargarProducto(ProductoRequest productoRequest) {
        Optional<GrupoProducto> grupoProducto = grupoProductoDAO.findById(productoRequest.getGrupoID());

        if (grupoProducto.isEmpty()){
            throw new RuntimeException();
        }

        Producto productoNuevo = new Producto();
        productoNuevo.setActivo(true);
        productoNuevo.setNombre(productoRequest.getNombre());
        productoNuevo.setGrupoProducto(grupoProducto.get());

        productoDAO.save(productoNuevo);

        return productoNuevo;
    }

    public boolean eliminarProducto(Long id) {
        Optional<Producto> producto = productoDAO.findById(id);

        if (producto.isPresent()) {
            Producto p = producto.get();
            p.setActivo(false);  // Cambia el estado de "activo" a false
            productoDAO.save(p);  // Guarda el producto actualizado

            System.out.println("Eliminando producto");
            return true;
        }
        return false;
    }

    public Optional<Producto> actualizarProducto(Long id, ProductoRequest productoRequest) {
        Optional<Producto> productoExistente = productoDAO.findById(id);
        Optional<GrupoProducto> grupoProductoBuscado = grupoProductoDAO.findById(productoRequest.getGrupoID());

        if (productoExistente.isPresent() && grupoProductoBuscado.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNombre(productoRequest.getNombre());
            producto.setGrupoProducto(grupoProductoBuscado.get());

            productoDAO.save(producto);
            return Optional.of(producto);
        }

        return Optional.empty();
    }

    public GrupoProducto cargarGrupoProducto(GrupoProductoRequest grupoProductoRequest) {

        Optional<Locacion> locacion = locacionDAO.findById(grupoProductoRequest.getLocacionID());

        if(locacion.isEmpty()){
            throw new RuntimeException("No se encontro la locacion");
        }

        GrupoProducto nuevoGrupo = new GrupoProducto();
        nuevoGrupo.setNombre(grupoProductoRequest.getNombre());
        nuevoGrupo.setLocacion(locacion.get());
        nuevoGrupo.setCategoria(GrupoProducto.CategoriaAlmacenamiento.valueOf(grupoProductoRequest.getCategoria().toUpperCase()));

        grupoProductoDAO.save(nuevoGrupo);

        return nuevoGrupo;
    }

    public List<GrupoProducto> getGruposByOrganizacion(Long id) {
        //Obtener todos los grupos de la organizacion
        Optional<Organizacion> organizacion = organizacionDAO.findById(id);

        if (organizacion.isEmpty()){
            throw new RuntimeException();
        }

        List<Locacion> locaciones = locacionDAO.findAllByOrganizacion(organizacion.get());
        List<GrupoProducto> grupos = new ArrayList<>();
        for (Locacion locacion : locaciones){
            grupos.addAll(grupoProductoDAO.findGrupoProductoByLocacion(locacion));
        }

        return grupos;
    }
}

