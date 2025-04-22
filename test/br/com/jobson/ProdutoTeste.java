package br.com.jobson;

import br.com.jobson.dao.ClienteDAO;
import br.com.jobson.dao.IClienteDAO;
import br.com.jobson.dao.IProdutoDAO;
import br.com.jobson.dao.ProdutoDAO;
import br.com.jobson.domain.Cliente;
import br.com.jobson.domain.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProdutoTeste {
    @Test
    public void cadastrarProduto() throws Exception {
        // Instanciando a interface
        IProdutoDAO produtoDAO = new ProdutoDAO();

        // Criando um objeto cliente
        Produto produto = new Produto();
        produto.setNome("Sabão");
        produto.setCodigo("21");
        produto.setPreco(45.78);

        // Cadastrando o cliente no banco
        Integer resultCadastro = produtoDAO.cadastrar(produto);

        // Verificando se o cliente foi criado
        Assert.assertTrue(resultCadastro == 1);

        // Verificando no banco se o cliente está cadastrado
        Produto produtoDB = produtoDAO.consultar(produto.getCodigo());

        // Validando o cliente cadastrado
        Assert.assertNotNull(produtoDB);
        Assert.assertNotNull(produtoDB.getId());
        Assert.assertEquals(produto.getCodigo(), produtoDB.getCodigo());
        Assert.assertEquals(produto.getNome(), produtoDB.getNome());
        Assert.assertEquals(produto.getPreco(), produtoDB.getPreco());

        // Exclui do banco o cliente cadastrado para teste
        Integer resultDel = produtoDAO.excluir(produtoDB);
        Assert.assertNotNull(resultDel);
    }

    @Test
    public void atualizarProduto() throws Exception {
        IProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();

        // Cadastrando o produto
        produto.setNome("Sabão");
        produto.setCodigo("21");
        produto.setPreco(45.78);
        Integer resultCadastro = produtoDAO.cadastrar(produto);
        Assert.assertTrue(resultCadastro == 1);

        // Buscar o produto no banco
        Produto produtoCadastrado = produtoDAO.consultar(produto.getCodigo());
        Assert.assertNotNull(produtoCadastrado);
        Assert.assertEquals(produto.getCodigo(), produtoCadastrado.getCodigo());
        Assert.assertEquals(produto.getNome(), produtoCadastrado.getNome());

        // Atualizar o produto
        produto.setPreco(20.15);
        Integer resultAtualizacao = produtoDAO.atualizar(produto);
        Assert.assertTrue(resultCadastro == 1);

        Produto produtoAtualizado = produtoDAO.consultar(produto.getCodigo());
        Assert.assertNotNull(produtoAtualizado);
        Assert.assertEquals(produto.getPreco(), produtoAtualizado.getPreco());

        // Limpando os dados de teste no banco
        Integer resultadoDelete = produtoDAO.excluir(produto);
        Assert.assertTrue(resultadoDelete == 1);
    }

    @Test
    public void buscarTodosOsProdutos() throws Exception {
        IProdutoDAO produtoDAO = new ProdutoDAO();

        // Cadastrando o primeiro produto
        Produto sabao = new Produto();
        sabao.setNome("Sabao");
        sabao.setCodigo("44");
        sabao.setPreco(45.55);
        Integer sabaoCadastrada = produtoDAO.cadastrar(sabao);
        Assert.assertTrue(sabaoCadastrada == 1);

        // Cadastrando o segundo produto
        Produto escova = new Produto();
        escova.setNome("Sabão");
        escova.setCodigo("45");
        escova.setPreco(10.15);
        Integer escovaCadastrado = produtoDAO.cadastrar(escova);
        Assert.assertTrue(escovaCadastrado == 1);

        // Consultando todos os produtos cadastrados
        List<Produto> lista = produtoDAO.buscarTodos();
        Assert.assertNotNull(lista);
        Assert.assertTrue(lista.size() >= 2);

        // Excluindo os produtos
        Integer sabaoExcluido = produtoDAO.excluir(sabao);
        Assert.assertTrue(sabaoExcluido == 1);

        Integer escovaExcluida = produtoDAO.excluir(escova);
        Assert.assertTrue(escovaExcluida == 1);
    }

    @Test
    public void excluirProduto() throws Exception {
        // Instanciando a interface
        IProdutoDAO produtoDAO = new ProdutoDAO();

        // Criando um objeto cliente
        Produto produto = new Produto();
        produto.setNome("Sabão");
        produto.setCodigo("21");
        produto.setPreco(50.5);

        // Cadastrando o cliente no banco
        Integer resultCadastro = produtoDAO.cadastrar(produto);

        // Verificando se o cliente foi criado
        Assert.assertTrue(resultCadastro == 1);

        // Verificando no banco se o cliente está cadastrado
        Produto produtoBD = produtoDAO.consultar(produto.getCodigo());

        // Exclui do banco o cliente cadastrado para teste
        Integer resultDel = produtoDAO.excluir(produtoBD);
        Assert.assertNotNull(resultDel);
    }
}
