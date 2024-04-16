package com.idat.CluckinBells.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagenCategoria;

    public Categoria() {
        super();
    }

    public Categoria(Long id, String nombre, byte[] imagenCategoria) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.imagenCategoria = imagenCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagenCategoria() {
        return imagenCategoria;
    }

    public void setImagenCategoria(byte[] imagenCategoria) {
        this.imagenCategoria = imagenCategoria;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Categoria [id=");
        builder.append(id);
        builder.append(", nombre=");
        builder.append(nombre);
        builder.append("]");
        return builder.toString();
    }

}