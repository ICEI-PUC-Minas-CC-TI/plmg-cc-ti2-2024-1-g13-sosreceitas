document.addEventListener('DOMContentLoaded', function() {
  const urlParams = new URLSearchParams(window.location.search);
  const recipeId = urlParams.get('id');

  console.log('ID da receita obtido da URL:', recipeId);

  if (recipeId) {
    fetch(`http://localhost:4567/receitas/${recipeId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Erro ao obter detalhes da receita');
        }
        return response.json();
      })
      .then(recipe => {
        console.log('Dados da receita recebidos:', recipe);

        if (typeof recipe !== 'object') {
          throw new Error('Resposta inválida do servidor');
        }

        const container = document.getElementById('recipe-detail');
        if (!container) {
          throw new Error('Elemento container não encontrado');
        }

        container.innerHTML = '';

        const receitaElement = document.createElement('div');
        receitaElement.classList.add('receita');

        const titulo = document.createElement('h2');
        titulo.textContent = recipe.titulo; 
        receitaElement.appendChild(titulo);

        const conteudo = document.createElement('p');
        conteudo.textContent = recipe.conteudo; 
        receitaElement.appendChild(conteudo);

        if (recipe.imagem_url) { 
          const imagem = document.createElement('img');
          imagem.src = recipe.imagem_url; 
          imagem.alt = recipe.titulo; 
          receitaElement.appendChild(imagem);
        }

        container.appendChild(receitaElement);
      })
      .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao obter detalhes da receita');
      });
  } else {
    alert('ID da receita não encontrado na URL');
  }
});
