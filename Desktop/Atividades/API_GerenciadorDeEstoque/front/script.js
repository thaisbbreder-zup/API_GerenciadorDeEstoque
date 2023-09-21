document.addEventListener('DOMContentLoaded', () => {
    const cadastrarButton = document.getElementById('cadastrarButton');
    const formulario = document.getElementById('produtoForm');
    const produtoFormModal = document.getElementById('produtoFormModal');
    const produtoTable = document.getElementById('produtoTable');

    // Função para buscar e exibir os produtos na tabela
    function carregarProdutos() {
        fetch('http://localhost:8080/produto')
            .then(response => response.json())
            .then(produtos => {
                // Limpa a tabela antes de atualizá-la
                produtoTable.innerHTML = '';

                // Adiciona cada produto à tabela
                produtos.forEach(produto => {
                    const newRow = produtoTable.insertRow(-1);
                    const cellNome = newRow.insertCell(0);
                    const cellDescricao = newRow.insertCell(1);
                    const cellQuantidade = newRow.insertCell(2);
                    const cellPrecoUnitario = newRow.insertCell(3);

                    cellNome.innerHTML = produto.nome
                    cellDescricao.innerHTML = produto.descricao;
                    cellQuantidade.innerHTML = produto.quantidadeEstoque;
                    cellPrecoUnitario.innerHTML = produto.precoUnitario;
                });
            })
            .catch(error => console.error('Erro ao buscar produtos:', error));
    }

    // Atualiza os produtos quando a página é carregada
    carregarProdutos();
    
 // Enviar os dados do produto do modal
 produtoFormModal.addEventListener('submit', function(event) {
    event.preventDefault();

    const data = {
        nome: document.getElementById('nomeModal').value,
        descricao: document.getElementById('descricaoModal').value,
        quantidadeEstoque: parseInt(document.getElementById('quantidadeEstoqueModal').value),
        precoUnitario: parseFloat(document.getElementById('precoUnitarioModal').value),
    };

    if (data.nome === '' || data.descricao === '' || isNaN(data.quantidadeEstoque) || isNaN(data.precoUnitario) ) {
        alert('Preencha todos os campos corretamente!');
    } else {
        // Limpa o formulário e fecha o modal
        produtoFormModal.reset();
        const myModal = new bootstrap.Modal(document.getElementById('modalCadastro'));
        myModal.hide();

        // Enviar os dados do produto
        fetch('http://localhost:8080/produto', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(novoProduto => {
                console.log('Produto cadastrado:', novoProduto);
                // Atualiza os produtos após cadastrar um novo
                carregarProdutos();
            })
            .catch(error => console.error('Erro ao cadastrar produto:', error));
    }
});
});


