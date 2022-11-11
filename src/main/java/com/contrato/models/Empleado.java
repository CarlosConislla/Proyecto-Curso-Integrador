package com.contrato.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false, unique = true)
    private String nombre;

    private String apellido;

    @Column(length = 7)
    private String dni;

    private Integer edad;

    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate icontrato;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fcontrato;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getIcontrato() {
        return icontrato;
    }

    public void setIcontrato(LocalDate icontrato) {
        this.icontrato = icontrato;
    }

    public LocalDate getFcontrato() {
        return fcontrato;
    }

    public void setFcontrato(LocalDate fcontrato) {
        this.fcontrato = fcontrato;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Empleado(long id, String nombre, String apellido, String dni, Integer edad, String email, LocalDate icontrato, LocalDate fcontrato, Departamento departamento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.email = email;
        this.icontrato = icontrato;
        this.fcontrato = fcontrato;
        this.departamento = departamento;
    }

    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String dni, Integer edad, String email, LocalDate icontrato, LocalDate fcontrato, Departamento departamento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.email = email;
        this.icontrato = icontrato;
        this.fcontrato = fcontrato;
        this.departamento = departamento;
    }

    public Empleado(String nombre) {
        this.nombre = nombre;
    }
}
