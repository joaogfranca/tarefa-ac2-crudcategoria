package com.example.tarefaac2;

import com.example.tarefaac2.model.Categoria;
import com.example.tarefaac2.model.Produto;
import com.example.tarefaac2.repository.CategoriaRepository;
import com.example.tarefaac2.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tarefaac2Application {

  public static void main(String[] args) {
    SpringApplication.run(Tarefaac2Application.class, args);
  }

  @Bean
  public CommandLineRunner initRunner(
    @Autowired ProdutoRepository produtoRepository,
    @Autowired CategoriaRepository categoriaRepository
  ) {
    return args -> {
      Produto itemSkateElétrico = new Produto("Skate Elétrico", 300.0);
      Produto itemMáquinaCafé = new Produto("Máquina de Café", 90.0);
      Produto itemDrone = new Produto("Drone", 200.0);
      Produto itemCadeiraDePraia = new Produto("Cadeira de Praia", 25.0);
      Produto itemTecladoRetroiluminado = new Produto(
        "Teclado Retroiluminado",
        50.0
      );

      produtoRepository.save(itemSkateElétrico);
      produtoRepository.save(itemMáquinaCafé);
      produtoRepository.save(itemDrone);
      produtoRepository.save(itemCadeiraDePraia);
      produtoRepository.save(itemTecladoRetroiluminado);

      Categoria catEsportes = new Categoria("Categoria de Esportes");
      Categoria catCozinha = new Categoria("Categoria de Cozinha");
      Categoria catGadgets = new Categoria("Categoria de Gadgets");
      Categoria catLazer = new Categoria("Lazer");
      Categoria catEletronicos = new Categoria("Categoria Eletrônicos");

      categoriaRepository.save(catEsportes);
      categoriaRepository.save(catCozinha);
      categoriaRepository.save(catGadgets);
      categoriaRepository.save(catLazer);
      categoriaRepository.save(catEletronicos);

      Produto foundSkate = produtoRepository.findById(1L).get();
      foundSkate.setCategoria(catEsportes);
      produtoRepository.save(foundSkate);

      Produto foundMáquinaCafé = produtoRepository.findById(2L).get();
      foundMáquinaCafé.setCategoria(catCozinha);
      produtoRepository.save(foundMáquinaCafé);

      Produto foundDrone = produtoRepository.findById(3L).get();
      foundDrone.setCategoria(catGadgets);
      produtoRepository.save(foundDrone);

      Produto foundCadeiraDePraia = produtoRepository.findById(4L).get();
      foundCadeiraDePraia.setCategoria(catLazer);
      produtoRepository.save(foundCadeiraDePraia);

      Produto foundTecladoRetroiluminado = produtoRepository.findById(5L).get();
      foundTecladoRetroiluminado.setCategoria(catEletronicos);
      produtoRepository.save(foundTecladoRetroiluminado);

      System.out.println(
        "Listando produtos com valor superior ao parâmetro fornecido:"
      );
      List<Produto> produtosMaisCaros = produtoRepository.findByPrecoGreaterThan(
        50.0
      );
      for (Produto produto : produtosMaisCaros) {
        System.out.println(
          "Produto: " + produto.getNome() + " - Preço: " + produto.getPreco()
        );
      }

      System.out.println(
        "Listando produtos com valor igual ou inferior ao parâmetro fornecido:"
      );
      List<Produto> produtosMaisBaratosOuIguaisA40 = produtoRepository.findByPrecoLessThanEqual(
        40.0
      );
      for (Produto produto : produtosMaisBaratosOuIguaisA40) {
        System.out.println(
          "Produto: " + produto.getNome() + " - Preço: " + produto.getPreco()
        );
      }

      System.out.println(
        "Listando produtos cujo nome começa com o parâmetro fornecido:"
      );
      List<Produto> produtosQueComecamComTeclado = produtoRepository.findByNomeStartingWith(
        "Teclado"
      );
      for (Produto produto : produtosQueComecamComTeclado) {
        System.out.println("Produto: " + produto.getNome());
      }

      System.out.println(
        "Listando categorias cujo nome começa com o parâmetro fornecido:"
      );
      List<Categoria> categoriasQueComecamComCategoria = categoriaRepository.findByNomeStartingWith(
        "Categoria"
      );
      for (Categoria categoria : categoriasQueComecamComCategoria) {
        System.out.println("Categoria: " + categoria.getNome());
      }

      System.out.println(
        "Exibindo uma categoria pelo identificador e os produtos relacionados a ela:"
      );
      Categoria categoriaEncontrada = categoriaRepository.findCategoryById(2L);
      for (Produto produto : categoriaEncontrada.getProdutos()) {
        System.out.println(
          "Produto da categoria encontrada: " +
          produto.getNome() +
          " - Preço: " +
          produto.getPreco()
        );
      }
    };
  }
}
