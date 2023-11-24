package com.example.tarefaac2.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_categorias")
@Builder
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "cat_nome", length = 200, nullable = false)
  private String nome;

  @OneToMany(mappedBy = "categoria")
  private List<Produto> produtos;

  public Categoria(String nome) {
    this.nome = nome;
  }
}
