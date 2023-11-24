package com.example.tarefaac2.repository;

import com.example.tarefaac2.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
  List<Produto> findByPrecoGreaterThan(Double valor);

  List<Produto> findByPrecoLessThanEqual(Double valor);

  List<Produto> findByNomeStartingWith(String nome);
}
