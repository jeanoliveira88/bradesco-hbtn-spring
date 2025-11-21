package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // GET - Listar produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    // POST - Adicionar produto
    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoService.adicionarProduto(produto);
    }

    // PUT - Atualizar produto
    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.atualizarProduto(id, produto);
    }

    // DELETE - Deletar produto
    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Long id) {
        boolean removido = produtoService.deletarProduto(id);
        return removido ? "Produto removido com sucesso!" : "Produto não encontrado!";
    }
}

