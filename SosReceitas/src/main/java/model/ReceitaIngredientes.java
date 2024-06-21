package model;


public class ReceitaIngredientes {
	
	private int receita_id;
	private int ingrediente_id;
	
	
	public ReceitaIngredientes(Receitas rec, Ingredientes ing) {
		receita_id = rec.getId_receitas();
		ingrediente_id = ing.getId_ingredientes();
	}
	
	public ReceitaIngredientes(int id_rec,int id_ingre) {
		this.receita_id = id_rec;
		this.ingrediente_id = id_ingre;
	}
	
	
	/*
	 * Getters e setters
	 */
	
	
	public int getReceita_id() {
		return receita_id;
	}
	public void setReceita_id(int receita_id) {
		this.receita_id = receita_id;
	}
	public int getIngrediente_id() {
		return ingrediente_id;
	}
	public void setIngrediente_id(int ingrediente_id) {
		this.ingrediente_id = ingrediente_id;
	}
	
	
	

}
