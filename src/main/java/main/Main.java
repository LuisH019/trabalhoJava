/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;

import console.Console;
import produto.dao.ProdutoDao;
import produto.Produto;

/**
 *
 * @author Pichau
 */
public class Main {
    private final int CADASTRAR = 1;
    private final int MOSTRAR = 2;
    private final int ALTERAR = 3;
    private final int APAGAR = 4;
    private final int SAIR = 0;
    private Console ler;
    private ProdutoDao dao;
    
    public Main() {
        ler = new Console ();
        dao = new ProdutoDao();
    }
    public static void main (String[] args){
        Main programa = new Main();
        programa.executar();
    }
    public void executar (){
        int escolha = 0;
	int removido = 0;
	int alterado = 0;
        String separar = "\n------------------------------------------------------------------------";
        do {
            escolha = ler.readInt("Digite '1' para cadastrar um produto\n" + 
                    "Digite '2' para ver os produtos cadastrados\n" + 
                    "Digite '3' para alterar os dados de um produto cadastrado\n" +
                    "Digite '4' para apagar um produto cadastrado\n"+
                    "Digite '0' para sair do programa");
            if (escolha == CADASTRAR){
                String nome = ler.readLine("\nDigite o nome do produto: ");
		int qntd = ler.readInt("Digite a quantidade de unidades do produto: ");
		String marca = ler.readLine ("Digite a marca do produto:  ");
		Produto produto = new Produto();
                produto.setNome(nome);
		produto.setQntd(qntd);
		produto.setMarca(marca);
		dao.cadastrar(produto);
                System.out.println("\nOs dados foram cadastrados com sucesso." + separar + separar);
            }
            else if (escolha == MOSTRAR){
                List<Produto> lista = dao.mostrar();
		for (Produto produto : lista) {
                    System.out.println("\nNome do produto: " + produto.getNome());
                    System.out.println("Quantidade do produto: " + produto.getQntd());
                    System.out.println("Marca do produto: " + produto.getMarca()) ;
                    System.out.println("ID do produto: " + produto.getId());
                    System.out.println(separar);
                }
            }
            else if (escolha == ALTERAR){
                alterado = ler.readInt("\nDigite o ID do produto: ");
                if (dao.getProdutosBd().containsKey(alterado)){
                    String nome = ler.readLine("Digite o novo nome: ");
                    int qntd = ler.readInt("Digite a quantidade do produto: ");
                    String marca = ler.readLine ("Digite a marca do produto:  ");
                    Produto produto = new Produto();
                    produto.setNome(nome);
                    produto.setQntd(qntd);
                    produto.setMarca(marca);
                    dao.alterar(produto, alterado);
                    System.out.println("\nOs dados foram alterados com sucesso." + separar + separar);
                }
                else {
                    System.out.println ("\nERRO: ID inexistente." + separar + separar);
                }
            }
            else if (escolha == APAGAR){
                
                removido = ler.readInt("\nDigite o ID do produto: ");
		if (dao.getProdutosBd().containsKey(removido)){
                    Produto produto = new Produto();
                    System.out.println ("Nome do produto: " + dao.getProdutosBd().get(removido).getNome());
                    dao.apagar(removido);
                    System.out.println("\nOs dados foram apagados com sucesso." + separar + separar);
                }
                else {
                    System.out.println ("\nERRO: ID inexistente." + separar + separar );
                }
            }
        }
        while (escolha != SAIR);
        System.out.println("\nO programa foi encerrado com sucesso");
    }
}
