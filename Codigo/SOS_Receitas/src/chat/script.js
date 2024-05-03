function showRecipes(ingredients) {
  const storedRecipes = localStorage.getItem("recipes");

  if (storedRecipes) {
    displayRecipes(JSON.parse(storedRecipes), ingredients);
  } else {
    fetch("../../recipes.json")
      .then((response) => response.json())
      .then((data) => {
        localStorage.setItem("recipes", JSON.stringify(data));
        displayRecipes(data, ingredients);
      })
      .catch((error) => console.error("Erro ao carregar receitas:", error));
  }
}

function displayRecipes(data, ingredients) {
  const chatBox = document.querySelector(".chat-box");
  const outgoingMsg = document.createElement("li");
  outgoingMsg.classList.add("msg", "outgoing");

  const filteredRecipes = data.recipes.filter((recipe) => {
    return recipe.ingredients.some((ingredient) =>
      ingredients ? ingredient.name.includes(ingredients) : false
    );
  });

  if (filteredRecipes.length === 0) {
    outgoingMsg.innerHTML = `Nenhuma receita encontrada com esses ingredientes.</b>`;
    chatBox.appendChild(outgoingMsg);
    return;
  }

  const recipesList = document.createElement("ul");
  recipesList.classList.add("recipes");

  filteredRecipes.forEach((recipe) => {
    const recipeItem = document.createElement("li");
    recipeItem.classList.add("recipe");

    const recipeContent = `
      <img src="${recipe.picture}" alt="" />
      <div class="recipe-content">
        <h3>${recipe.name}</h3>
        <a href="/src/recipe/index.html?id=${recipe.id}">Saiba mais<span class="material-icons">chevron_right</span></a>
      </div>`;

    recipeItem.innerHTML = recipeContent;
    recipesList.appendChild(recipeItem);
  });

  outgoingMsg.appendChild(recipesList);
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

        const ingredientsFilter = ingredients.toLowerCase();

        showRecipes(ingredientsFilter);
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
