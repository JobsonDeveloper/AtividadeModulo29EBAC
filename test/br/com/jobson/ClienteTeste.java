package br.com.jobson;

import br.com.jobson.dao.ClienteDAO;
import br.com.jobson.dao.IClienteDAO;
import br.com.jobson.domain.Cliente;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;

public class ClienteTeste {

    @Test
    public void cadastrarCliente() throws Exception {
        // Instanciando a interface
        IClienteDAO clienteDAO = new ClienteDAO();

        // Criando um objeto cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Jobson");
        cliente.setCodigo("44");

        // Cadastrando o cliente no banco
        Integer resultCadastro = clienteDAO.cadastrar(cliente);

        // Verificando se o cliente foi criado
        Assert.assertTrue(resultCadastro == 1);

        // Verificando no banco se o cliente está cadastrado
        Cliente clienteBD = clienteDAO.consultar(cliente.getCodigo());

        // Validando o cliente cadastrado
        Assert.assertNotNull(clienteBD);
        Assert.assertNotNull(clienteBD.getId());
        Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        Assert.assertEquals(cliente.getNome(), clienteBD.getNome());

        // Exclui do banco o cliente cadastrado para teste
        Integer resultDel = clienteDAO.excluir(clienteBD);
        Assert.assertNotNull(resultDel);
    }

    @Test
    public void atualizarCliente() throws Exception {
        IClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();

        // Cadastrando o cliente
        cliente.setCodigo("68");
        cliente.setNome("Carlos");
        Integer resultCadastro = clienteDAO.cadastrar(cliente);
        Assert.assertTrue(resultCadastro == 1);

        // Buscar o cliente no banco
        Cliente clienteCadastrado = clienteDAO.consultar(cliente.getCodigo());
        Assert.assertNotNull(clienteCadastrado);
        Assert.assertEquals(cliente.getCodigo(), clienteCadastrado.getCodigo());
        Assert.assertEquals(cliente.getNome(), clienteCadastrado.getNome());

        // Atualizar o cliente
        cliente.setNome("Josefa");
        Integer resultAtualizacao = clienteDAO.atualizar(cliente);
        Assert.assertTrue(resultCadastro == 1);
        Assert.assertNotNull(clienteCadastrado);

        Cliente clienteAtualizado = clienteDAO.consultar(cliente.getCodigo());
        Assert.assertNotNull(clienteAtualizado);
        Assert.assertEquals(cliente.getNome(), clienteAtualizado.getNome());

        // Limpando os dados de teste no banco
        Integer resultadoDelete = clienteDAO.excluir(cliente);
        Assert.assertTrue(resultadoDelete == 1);
    }

    @Test
    public void buscarTodosOsClientes() throws Exception {
        IClienteDAO clienteDAO = new ClienteDAO();

        // Cadastrando o primeiro cliente
        Cliente maria = new Cliente();
        maria.setNome("Maria");
        maria.setCodigo("44");
        Integer mariaCadastrada = clienteDAO.cadastrar(maria);
        Assert.assertTrue(mariaCadastrada == 1);

        // Cadastrando o segundo cliente
        Cliente jobson = new Cliente();
        jobson.setNome("Jobson");
        jobson.setCodigo("45");
        Integer jobsonCadastrado = clienteDAO.cadastrar(jobson);
        Assert.assertTrue(jobsonCadastrado == 1);

        // Consultando todos os clientes cadastrados
        List<Cliente> lista = clienteDAO.buscarTodos();
        Assert.assertNotNull(lista);
        Assert.assertTrue(lista.size() >= 2);

        // Excluindo os clientes
        Integer mariaExcluida = clienteDAO.excluir(maria);
        Assert.assertTrue(mariaExcluida == 1);

        Integer jobsonExcluido = clienteDAO.excluir(jobson);
        Assert.assertTrue(jobsonExcluido == 1);
    }

    @Test
    public void excluirCliente() throws Exception {
        // Instanciando a interface
        IClienteDAO clienteDAO = new ClienteDAO();

        // Criando um objeto cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Jobson");
        cliente.setCodigo("44");

        // Cadastrando o cliente no banco
        Integer resultCadastro = clienteDAO.cadastrar(cliente);

        // Verificando se o cliente foi criado
        Assert.assertTrue(resultCadastro == 1);

        // Verificando no banco se o cliente está cadastrado
        Cliente clienteBD = clienteDAO.consultar(cliente.getCodigo());

        // Exclui do banco o cliente cadastrado para teste
        Integer resultDel = clienteDAO.excluir(clienteBD);
        Assert.assertNotNull(resultDel);
    }
}
