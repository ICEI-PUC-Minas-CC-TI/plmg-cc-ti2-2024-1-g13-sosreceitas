document.addEventListener('DOMContentLoaded', () => {
  fetch('http://localhost:4567/receitas')
      .then(response => response.json())
      .then(data => {
          const container = document.getElementById('receitas-container');
          data.forEach(receita => {
              const receitaElement = document.createElement('div');
              receitaElement.classList.add('receita');

              const titulo = document.createElement('h2');
              titulo.textContent = receita.titulo_receitas;
              receitaElement.appendChild(titulo);

              const conteudo = document.createElement('p');
              conteudo.textContent = receita.conteudo_receitas;
              receitaElement.appendChild(conteudo);

              if (receita.imagem) {
                  const imagem = document.createElement('img');
                  imagem.src = receita.imagem;
                  imagem.alt = receita.titulo_receitas;
                  receitaElement.appendChild(imagem);
              }

              container.appendChild(receitaElement);
          });
      })
      .catch(error => {
          console.error('Erro ao carregar receitas:', error);
      });
});