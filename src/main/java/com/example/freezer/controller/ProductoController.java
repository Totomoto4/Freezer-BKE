package com.example.freezer.controller;

import java.util.List;
import java.util.Optional;

import com.example.freezer.dto.CargaTemperaturaRequest;
import com.example.freezer.dto.GrupoProductoRequest;
import com.example.freezer.dto.ProductoRequest;
import com.example.freezer.model.GrupoProducto;
import com.example.freezer.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.freezer.model.RegistroTemperatura;
import com.example.freezer.service.ProductoService;

@RestController
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/productos/{prodID}")
    public ResponseEntity<List<RegistroTemperatura>> getRegistrosTemperaturaById(@PathVariable Long prodID) {
        List<RegistroTemperatura> registros = productoService.getHistorialByProducto(prodID);
        if (registros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(registros);
    }


    @GetMapping("/organizacion/{orgID}")
    public ResponseEntity<List<Producto>> getProductosByOrganizacion (@PathVariable Long orgID) {
        List<Producto> productos = productoService.getProductosByOrganizacion(orgID);
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/organizacion/temperatura/{orgID}")
    public ResponseEntity<List<RegistroTemperatura>> getRegistrosByOrganizacion (@PathVariable Long orgID) {
        List<RegistroTemperatura> registros  = productoService.getRegistrosByOrganizacion(orgID);
        if (registros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(registros);
    }

    @PostMapping("/temperatura")
    public ResponseEntity<String> cargarTemperaturas(@RequestBody CargaTemperaturaRequest cargaTemperaturaRequest){

        productoService.registrarTemperaturasByGrupo(cargaTemperaturaRequest);

        return ResponseEntity.ok("Registros cargados con exito");
    }

    @PostMapping("/nuevoProducto")
    public ResponseEntity<Producto> cargarProducto(@RequestBody ProductoRequest productoRequest){
        Producto producto = productoService.cargarProducto(productoRequest);

        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = productoService.eliminarProducto(id);

        if (eliminado) {
            return ResponseEntity.noContent().build(); // 204 No Content si se elimina exitosamente
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found si el producto no existe
        }
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest productoRequest) {
        Optional<Producto> productoActualizado = productoService.actualizarProducto(id, productoRequest);

        return productoActualizado
                .map(ResponseEntity::ok) // 200 OK con el producto actualizado
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found si el producto no existe
    }

    @PostMapping("/grupo")
    public ResponseEntity<GrupoProducto> crearGrupoProducto(@RequestBody GrupoProductoRequest grupoProductoRequest){
        GrupoProducto grupoCreado = productoService.cargarGrupoProducto(grupoProductoRequest);

        return ResponseEntity.ok(grupoCreado);
    }

}
