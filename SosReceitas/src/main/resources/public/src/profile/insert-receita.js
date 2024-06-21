document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('insert-recipe-form').addEventListener('submit', function(event) {
      event.preventDefault();

       // Obtém os dados do usuário armazenados no localStorage
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const userId = currentUser.id_user;
  
      // Coleta os dados do formulário
      const formData = new FormData();
      formData.append('id_receitas', document.getElementById('recipe-id').value);
      formData.append('id_user', userId);
      formData.append('titulo_receitas', document.getElementById('recipe-title').value);
      formData.append('conteudo_receitas', document.getElementById('recipe-steps').value);
      formData.append('imagem', document.getElementById('recipe-image-url').value); // Obtém a URL da imagem
  
      // Envia os dados para o backend
      fetch('http://localhost:4567/receitas', { // Substitua 4567 pela porta do seu servidor backend
          method: 'POST',
          body: new URLSearchParams(formData)
        })
        .then(response => {
          if (response.ok) {
            alert('Receita inserida com sucesso!');
          } else {
            throw new Error('Erro ao inserir receita');
          }
          window.location.href = 'profile.html';
        })
        
    });
  });
  