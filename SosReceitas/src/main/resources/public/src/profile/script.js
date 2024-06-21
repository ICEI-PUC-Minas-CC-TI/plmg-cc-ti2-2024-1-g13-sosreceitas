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
  alert("Saindo da conta...");
  window.location.href = '../../index.html'
});

document.getElementById("home-btn").addEventListener("click", () => {
  alert("Voltando para a tela inicial...");
  window.location.href = '../chat/index.html'
});

document
  .getElementById("add-recipe-btn")
  .addEventListener("click", function () {
    alert("Redirecionar para a página de formulário de receita...");
  });

function adjustAddRecipeButtonPosition() {
  const recipesList = document.querySelector(".recipes-list");
  const addRecipeButton = document.getElementById("add-recipe-btn");

  addRecipeButton.style.marginTop = `${recipesList.offsetHeight}px`;
}

window.addEventListener("load", adjustAddRecipeButtonPosition);

window.addEventListener("resize", adjustAddRecipeButtonPosition);
