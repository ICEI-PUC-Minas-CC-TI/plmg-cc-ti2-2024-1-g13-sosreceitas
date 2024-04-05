document
  .getElementById("recipe-form")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    var title = document.getElementById("recipe-title").value;
    var ingredients = document.getElementById("recipe-ingredients").value;
    var image = document.getElementById("recipe-image").value;
    var preparation = document.getElementById("recipe-preparation").value;

    if (!title || !ingredients || !image || !preparation) {
      alert("Por favor, preencha todos os campos do formulário.");
      return;
    }

    console.log("Título:", title);
    console.log("Ingredientes:", ingredients);
    console.log("Imagem:", image);
    console.log("Preparação:", preparation);

    document.getElementById("recipe-form").reset();
  });

document
  .getElementById("add-recipe-btn")
  .addEventListener("click", function () {
    const title = document.getElementById("recipe-title").value.trim();
    const ingredients = document
      .getElementById("recipe-ingredients")
      .value.trim();
    const preparation = document
      .getElementById("recipe-preparation")
      .value.trim();

    if (title === "" || ingredients === "" || preparation === "") {
      alert("Por favor, preencha todos os campos antes de postar a receita.");
    } else {
      alert("Receita postada com sucesso!");
      history.back();
    }
  });

document.getElementById("back-btn").addEventListener("click", function () {
  alert("Voltando para a página anterior...");
  history.back();
});
