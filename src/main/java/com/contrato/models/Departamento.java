package com.contrato.models;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 45, nullable = false, unique = true)
    private String nombre;

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

    public Departamento() {
    }

    public Departamento(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Departamento(long id) {
        this.id = id;
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

}
