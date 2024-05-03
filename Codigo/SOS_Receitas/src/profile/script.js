userLogado = JSON.parse(localStorage.getItem("userLogado"));
const userName = document.getElementById("userName");
userName.innerHTML = userLogado.nome;

const editBtn = document.getElementById("edit-btn");
editBtn.addEventListener("click", () => {
  const novoNome = prompt("Digite o novo nome:");

  if (novoNome !== null && novoNome.trim() !== "") {
    userLogado.nome = novoNome;
    userName.innerHTML = userLogado.nome;
    localStorage.setItem("userLogado", JSON.stringify(userLogado));
  }
});

const userRecipes = [
  { name: "Bolo de Chocolate", likes: 25 },
  { name: "Lasanha", likes: 30 },
  { name: "Frango Assado", likes: 15 },
];



function renderRecipes() {
  const recipesList = document.getElementById("recipes");
  recipesList.innerHTML = "";

  userRecipes.forEach((recipe) => {
    const li = document.createElement("li");
    li.textContent = `${recipe.name} - ${recipe.likes} curtidas`;
    recipesList.appendChild(li);
  });
}

renderRecipes();

document.getElementById("logout-btn").addEventListener("click", () => {
  const confirmacao = confirm("Deseja realmente sair?");
  if (confirmacao) {
    setTimeout(() => {
      window.location.href = "../../index.html";
    }, 1000);
    localStorage.removeItem("userLogado");
  }
});

document.getElementById("go-back").addEventListener("click", () => {
  window.location.href = "../chat/index.html";
});

document
  .getElementById("add-recipe-btn")
  .addEventListener("click", function () {});

function adjustAddRecipeButtonPosition() {
  const recipesList = document.querySelector(".recipes-list");
  const addRecipeButton = document.getElementById("add-recipe-btn");
}

window.addEventListener("load", adjustAddRecipeButtonPosition);

window.addEventListener("resize", adjustAddRecipeButtonPosition);
