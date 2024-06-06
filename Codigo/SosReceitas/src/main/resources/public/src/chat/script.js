function showRecipes() {
  const chatBox = document.querySelector(".chat-box");
  const outgoingMsg = document.createElement("li");
  outgoingMsg.classList.add("msg", "outgoing");
  outgoingMsg.innerHTML = `<ul class="recipes">
            <li class="recipe">
            <img src="../../assets/img/bolo-morango.webp" alt="" />
            <div class="recipe-content">
              <h3>Bolo de Morango</h3>
              <a
                >Saiba mais<span class="material-icons"
                  >chevron_right</span
                ></a
              >
            </div>
          </li>
          <li class="recipe">
            <img src="../../assets/img/geleia-morango.webp" alt="" />
            <div class="recipe-content">
              <h3>Geleia de Morango</h3>
              <a
                >Saiba mais<span class="material-icons"
                  >chevron_right</span
                ></a
              >
            </div>
          </li>
          <li class="recipe">
            <img src="../../assets/img/torta-morango.webp" alt="" />
            <div class="recipe-content">
              <h3>Torta de Morango</h3>
              <a
                >Saiba mais<span class="material-icons"
                  >chevron_right</span
                ></a
              >
            </div>
          </li>
          <li class="recipe">
            <img src="../../assets/img/mousse-morango.webp" alt="" />
            <div class="recipe-content">
              <h3>Mousse de Morango</h3>
              <a
                >Saiba mais<span class="material-icons"
                  >chevron_right</span
                ></a
              >
            </div>
          </li></ul>`;

  chatBox.appendChild(outgoingMsg);
  outgoingMsg.style.display = "none";

  setTimeout(function () {
    outgoingMsg.style.display = "block";
  }, 1000);
}

document.addEventListener("DOMContentLoaded", function () {
  const inputField = document.querySelector(".chat-input input[type='text']");
  const chatBox = document.querySelector(".chat-box");

  inputField.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
      event.preventDefault();

      const ingredients = inputField.value.trim();
      if (ingredients !== "") {
        const incomingMsg = document.createElement("li");
        incomingMsg.classList.add("msg", "incoming");
        incomingMsg.innerHTML = `<p class="ingredients"><b>Ingredientes: </b>${ingredients}</p>`;
        chatBox.appendChild(incomingMsg);

        inputField.value = "";

        showRecipes();
      }
    }
  });
});

document.getElementById("up-button").addEventListener("click", function () {
  document.getElementById("file-input").click();
});

document.getElementById("file-input").addEventListener("change", function () {
  const chatBox = document.querySelector(".chat-box");
  if (this.files && this.files[0]) {
    var arquivo = this.files[0];
    console.log("Arquivo selecionado:", arquivo);

    const reader = new FileReader();
    reader.onload = function (e) {
      const incomingMsg = document.createElement("li");
      incomingMsg.classList.add("msg", "incoming");
      incomingMsg.innerHTML = `<img class="up-photo" src="${e.target.result}" alt="ingredient"/>`;
      chatBox.appendChild(incomingMsg);
      showRecipes();
    };
    reader.readAsDataURL(arquivo);
  }
});

document.getElementById("cam-button").addEventListener("click", function () {
  if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
    navigator.mediaDevices
      .getUserMedia({ video: true })
      .then(function (stream) {
        var video = document.getElementById("video");
        video.srcObject = stream;
        video.play();

        video.style.transform = "scaleX(-1)";

        video.style.display = "block";
        document.getElementById("capture-button").style.display = "block";

        document
          .getElementById("capture-button")
          .addEventListener("click", function () {
            var canvas = document.createElement("canvas");
            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;
            var context = canvas.getContext("2d");
            context.drawImage(video, 0, 0, canvas.width, canvas.height);
            var imageDataURL = canvas.toDataURL("image/png");

            var chatBox = document.querySelector(".chat-box");
            var incomingMsg = document.createElement("li");
            incomingMsg.classList.add("msg", "incoming");
            incomingMsg.innerHTML = `<img class="cam-photo" src="${imageDataURL}" alt="captured-image"/>`;
            chatBox.appendChild(incomingMsg);
            showRecipes();

            video.pause();
            video.style.display = "none";
            document.getElementById("capture-button").style.display = "none";
          });
      })
      .catch(function (error) {
        console.error("Erro ao acessar a câmera:", error);
      });
  } else {
    alert("Seu navegador não suporta a API getUserMedia");
  }
});
/* TESTE METODO QUE RETORNA RECEITA POR INGREDIENTES*/
document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('ingredientes-form');

  form.addEventListener('submit', (event) => {
      event.preventDefault();
      
      const ingredientesInput = document.getElementById('ingredientes').value;
      const ingredientes = ingredientesInput.split(',').map(ingrediente => ingrediente.trim());
      
      fetch('http://localhost:4567/receitas/ingredientes', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(ingredientes)
      })
      .then(response => response.json())
      .then(data => {
          const container = document.getElementById('receitas-container');
          container.innerHTML = ''; // Limpar o conteúdo anterior
          
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
});
