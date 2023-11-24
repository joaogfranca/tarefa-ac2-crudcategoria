package com.example.tarefaac2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "tbl_produtos")
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "prod_name", length = 200, nullable = false)
  private String nome;

  @Column(name = "prod_price", nullable = false)
  private Double preco;

  @ManyToOne
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;

  public Produto(String nome, Double preco) {
    this.nome = nome;
    this.preco = preco;
  }
}
