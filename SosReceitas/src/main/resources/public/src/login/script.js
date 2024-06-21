document.getElementById('butao').addEventListener('click', entrar);

function entrar() {
  const usuario = document.getElementById('usuario').value;
  const senha = document.getElementById('senha').value;

  console.log(`Tentativa de login com usuário: ${usuario} e senha: ${senha}`);

  fetch('http://localhost:4567/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: new URLSearchParams({
      email_user: usuario,
      senha_user: senha,
    }),
  })
  .then(response => {
    console.log('Resposta recebida:', response);
    if (response.status === 401) {
      alert('Usuário ou senha incorretos!'); // Alerta para usuário ou senha incorretos
      return null;
    } else if (response.ok) {
      return response.json();
    } else {
      throw new Error(`Erro na solicitação: ${response.status} - ${response.statusText}`);
    }
  })
  .then(data => {
    if (data) {
      console.log('Dados recebidos:', data);
      alert('Login realizado com sucesso!'); // Alerta para login realizado com sucesso
      localStorage.setItem('currentUser', JSON.stringify(data));
      window.location.href = '../chat/index.html';
    }
  })
  .catch(error => {
    console.error('Erro:', error);
    alert(`Erro: ${error.message}`); // Alerta para outros erros
  });
}
