package altamirano.hernandez.springboot3_firebase.controllers;

import altamirano.hernandez.springboot3_firebase.dto.CotizacionesDTO;
import altamirano.hernandez.springboot3_firebase.services.CotizacionesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fire-base")
public class FireBaseController {
    @Autowired
    private CotizacionesService cotizacionesService;

    @GetMapping("")
    public ResponseEntity<?> findAllCotizaciones() {
        Map<String, Object> json = new HashMap<>();
        List<Map<String, Object>> cotizaciones = cotizacionesService.findAllCotizaciones();
        System.out.println(cotizaciones);

        if (cotizaciones.isEmpty()) {
            json.put("error", "No se encontraron cotizaciones");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
        }
        json.put("cotizaciones", cotizaciones);
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCotizacionById(@PathVariable String id) {
        Map<String, Object> cotizacion = cotizacionesService.findCotizacionById(id);
        System.out.println(cotizacion);
        return ResponseEntity.status(HttpStatus.OK).body(cotizacion);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findCotizacionByEmail(@PathVariable String email) {
        boolean existeEmail = cotizacionesService.existeEmail(email);
        if (existeEmail) {
            return ResponseEntity.status(HttpStatus.OK).body(existeEmail);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(existeEmail);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveCotizacion(@Valid @RequestBody CotizacionesDTO cotizacion, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Map<String, Object> json = new HashMap<>();
            cotizacion.setFecha(java.time.LocalDateTime.now().toString());
            boolean saveCotizacion = cotizacionesService.saveCotizacion(cotizacion);
            if (saveCotizacion) {
                json.put("msg", "Cotizacion guardada correctamente");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } else {
                json.put("msg", "Error al guardar Cotizacion");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCotizacion(@Valid @RequestBody CotizacionesDTO cotizacion, @PathVariable String id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Map<String, Object> json = new HashMap<>();
            cotizacion.setFecha(java.time.LocalDateTime.now().toString());
            boolean updateCotizacion = cotizacionesService.updateCotizacion(id, cotizacion);
            if (updateCotizacion) {
                json.put("msg", "Cotizacion actualizada correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } else {
                json.put("msg", "Error al actualizar Cotizacion");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> updateCotizacion(@PathVariable String id) {
        boolean eliminiacion = cotizacionesService.deleteCotizacion(id);
        Map<String, Object> json = new HashMap<>();
        if (eliminiacion) {
            json.put("msg", "Cotizacion eliminada correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } else {
            json.put("msg", "Error al eliminar Cotizacion");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
        }
    }
}
