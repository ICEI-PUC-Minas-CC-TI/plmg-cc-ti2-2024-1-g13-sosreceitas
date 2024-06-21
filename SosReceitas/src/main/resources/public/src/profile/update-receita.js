document.getElementById('update-recipe-form').addEventListener('submit', function(event) {
  event.preventDefault();
  const recipeId = document.getElementById('update-recipe-id').value;
  const title = document.getElementById('update-recipe-title').value;
  const content = document.getElementById('update-recipe-content').value;
  const image = document.getElementById('update-recipe-image').value;
  const currentUser = JSON.parse(localStorage.getItem('currentUser'));
  const userId = currentUser.id_user;

  fetch(`http://localhost:4567/update/receitas/${recipeId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      id_receitas: recipeId,
      id_user: userId,
      titulo_receitas: title,
      conteudo_receitas: content,
      imagem: image
    })
  })
  .then(response => {
    if (response.ok) {
      alert('Receita atualizada com sucesso!');
      window.location.reload();
    } else {
      return response.text().then(text => { throw new Error(text); });
    }
  })
  .catch(error => {
    console.error('Erro:', error);
    alert('Erro ao atualizar a receita.');
  });
});
