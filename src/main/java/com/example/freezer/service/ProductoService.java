package com.example.freezer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.freezer.dao.EmpleadoDAO;
import com.example.freezer.dao.GrupoProductoDAO;
import com.example.freezer.dto.CargaTemperaturaRequest;
import com.example.freezer.model.Empleado;
import com.example.freezer.model.GrupoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freezer.dao.ProductoDAO;
import com.example.freezer.dao.RegistroTemperaturaDAO;
import com.example.freezer.model.Producto;
import com.example.freezer.model.RegistroTemperatura;

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

    public List<RegistroTemperatura> getHistorialByProducto(Long prodID){
        Optional<Producto> productoBuscado = productoDAO.findById(prodID);

        return registroTemperaturaDAO.findRegistroTemperaturasByProductoOrderByFechaHora(productoBuscado.get());
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

