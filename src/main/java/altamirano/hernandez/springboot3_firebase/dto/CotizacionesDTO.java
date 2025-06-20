package altamirano.hernandez.springboot3_firebase.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Objects;

public class CotizacionesDTO {
    @NotBlank(message = "El nombr es obligatorio")
    private String nombre;
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;
    @NotBlank(message = "El detalle es obligatorio")
    private String detalle;

    private String fecha;

    public CotizacionesDTO(String nombre, String telefono, String email, String ciudad, String direccion, String detalle, String fecha) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.detalle = detalle;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CotizacionesDTO that = (CotizacionesDTO) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(telefono, that.telefono) && Objects.equals(email, that.email) && Objects.equals(ciudad, that.ciudad) && Objects.equals(direccion, that.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, telefono, email, ciudad, direccion);
    }
}
