package com.example.tarefaac2.service;

import com.example.tarefaac2.dto.ProdutoDTO;
import com.example.tarefaac2.model.Categoria;
import com.example.tarefaac2.model.Produto;
import com.example.tarefaac2.repository.CategoriaRepository;
import com.example.tarefaac2.repository.ProdutoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  public List<ProdutoDTO> obterTodos() {
    return produtoRepository
      .findAll()
      .stream()
      .map((Produto produto) -> {
        return ProdutoDTO
          .builder()
          .id(produto.getId())
          .nome(produto.getNome())
          .preco(produto.getPreco())
          .categoriaId(
            produto.getCategoria() == null ? 0L : produto.getCategoria().getId()
          )
          .build();
      })
      .collect(Collectors.toList());
  }

  public ProdutoDTO obterPorId(Long id) {
    Produto produto = produtoRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

    ProdutoDTO produtoDTO = new ProdutoDTO();
    produtoDTO.setId(produto.getId());
    produtoDTO.setNome(produto.getNome());
    produtoDTO.setPreco(produto.getPreco());
    produtoDTO.setCategoriaId(produto.getCategoria().getId());

    return produtoDTO;
  }

  public ProdutoDTO inserir(ProdutoDTO produto) {
    Categoria categoria = categoriaRepository
      .findById(produto.getCategoriaId())
      .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

    Produto produtoBanco = new Produto();
    produtoBanco.setNome(produto.getNome());
    produtoBanco.setPreco(produto.getPreco());
    produtoBanco.setCategoria(categoria);
    produtoRepository.save(produtoBanco);

    ProdutoDTO produtoDTO = new ProdutoDTO();
    produtoDTO.setId(produtoBanco.getId());
    produtoDTO.setNome(produtoBanco.getNome());
    produtoDTO.setPreco(produtoBanco.getPreco());
    produtoDTO.setCategoriaId(produtoBanco.getCategoria().getId());

    return produtoDTO;
  }

  public void editar(Long id, ProdutoDTO produto) {
    Produto produtoBanco = produtoRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    Categoria categoria = categoriaRepository
      .findById(Long.valueOf(produto.getCategoriaId()))
      .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

    produtoBanco.setNome(produto.getNome());
    produtoBanco.setPreco(produto.getPreco());
    produtoBanco.setCategoria(categoria);

    produtoRepository.save(produtoBanco);
  }

  public void excluir(Long id) {
    produtoRepository.deleteById(id);
  }
}
