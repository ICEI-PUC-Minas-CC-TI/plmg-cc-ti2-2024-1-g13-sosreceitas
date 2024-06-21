document.addEventListener('DOMContentLoaded', function() {
    // Obtém o ID da receita da URL
    const urlParams = new URLSearchParams(window.location.search);
    const recipeId = urlParams.get('id');
  
    // Verifica se o ID da receita foi encontrado
    if (recipeId) {
      fetch(`http://localhost:4567/delete/receitas/${recipeId}`, {
        method: 'POST'
      })
        .then(response => {
          if (response.ok) {
            alert('Receita removida com sucesso!');
            window.location.href = 'profile.html'; // Redireciona para a página inicial ou outra página apropriada
          } else {
            throw new Error('Erro ao remover receita');
          }
        })
        .catch(error => {
          console.error('Error:', error);
          alert('Receita removida com sucesso!');
        });
    } else {
      alert('ID da receita não encontrado na URL');
    }
  });
  