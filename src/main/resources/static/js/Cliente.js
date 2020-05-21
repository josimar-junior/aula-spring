class Cliente {
	
	constructor() {
		this.init();
	}
	
	init() {
		document.querySelectorAll('.btn-excluir').forEach(btnExcluir => {
			btnExcluir.addEventListener('click', event => {
				
				const nome = btnExcluir.dataset.nome;
				const url = btnExcluir.dataset.url;
				
				const excluir = confirm(`Deseja realmente excluir o cliente ${nome}?`);
				
				if(excluir) {
					fetch(url, {
						method: 'delete'
					})
					.then(res => {
						if(res.status == 200) {
							alert('Cliente excluído com sucesso');
							window.location.reload();
						} else {
							alert('Erro');
						}
					})
					.catch(e => {
						alert('Erro ', e);
					});
				}
				
			});
		});
	}
}

new Cliente();