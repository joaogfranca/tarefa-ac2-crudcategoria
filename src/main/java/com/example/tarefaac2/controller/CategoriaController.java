package com.example.tarefaac2.controller;

import com.example.tarefaac2.dto.CategoriaDTO;
import com.example.tarefaac2.model.Categoria;
import com.example.tarefaac2.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ac2/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping
  public List<CategoriaDTO> obterTodos() {
    return categoriaService.obterTodos();
  }

  @GetMapping("{id}")
  public CategoriaDTO obterPorId(@PathVariable Long id) {
    return categoriaService.obterPorId(id);
  }

  @PostMapping
  public Categoria inserir(@RequestBody CategoriaDTO categoria) {
    return categoriaService.inserir(categoria);
  }

  @PutMapping("{id}")
  public void editar(
    @PathVariable Long id,
    @RequestBody CategoriaDTO categoria
  ) {
    categoriaService.editar(id, categoria);
  }

  @DeleteMapping("{id}")
  public void excluir(@PathVariable Long id) {
    this.categoriaService.excluir(id);
  }
}
