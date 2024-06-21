document.addEventListener('DOMContentLoaded', function() {
    // Fetching all recipes
    fetch('http://localhost:4567/receitas')
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('receitas-container');

            data.forEach(receita => {
                const receitaElement = document.createElement('div');
                receitaElement.classList.add('recipe-item');

                if (receita.imagem) {
                    const imagem = document.createElement('img');
                    imagem.src = receita.imagem;
                    imagem.alt = receita.titulo_receitas;
                    receitaElement.appendChild(imagem);
                }

                const detalhes = document.createElement('div');
                detalhes.classList.add('recipe-details');
                receitaElement.appendChild(detalhes);

                const titulo = document.createElement('h2');
                titulo.textContent = receita.titulo_receitas;
                detalhes.appendChild(titulo);

                const saibaMaisLink = document.createElement('a');
                saibaMaisLink.textContent = 'Saiba mais';
                saibaMaisLink.href = `../chat/saibaMais.html?id=${receita.id_receitas}`;
                detalhes.appendChild(saibaMaisLink);

                const favButton = document.createElement('button');
                favButton.innerHTML = '<img src="../../assets/img/starr.png" alt="Star Icon" style="width: 20px; height: 20px; margin-right: 8px;">Favorito';
                favButton.classList.add('fav-button');

                favButton.addEventListener('click', () => {
                    const userId = JSON.parse(localStorage.getItem('currentUser')).id_user;
                    const recipeId = receita.id_receitas;

                    if (userId && recipeId) {
                        fetch('http://localhost:4567/favoritos', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify({ userId: userId, recipeId: recipeId })
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Erro ao adicionar aos favoritos');
                            }
                            alert('Receita adicionada aos favoritos com sucesso!');
                        })
                        .catch(error => {
                            console.error('Erro:', error);
                            alert('Erro ao adicionar aos favoritos');
                        });
                    } else {
                        alert('Usuário ou receita não identificado.');
                    }
                });

                detalhes.appendChild(favButton);
                container.appendChild(receitaElement);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar receitas:', error);
        });
});
