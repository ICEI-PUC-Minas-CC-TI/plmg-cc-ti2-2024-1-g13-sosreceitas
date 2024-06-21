package model;

public class Ingredientes {
	
	private int id_ingredientes;
	private String nome;
	
	
	/*
	 * Construtores:
	 */
	public Ingredientes(){
	        this.id_ingredientes = -1;
	        this.nome = "";
	    }
	
    public Ingredientes(int id_ingredientes, String nome) {
        this.id_ingredientes = id_ingredientes;
        this.nome = nome;
    }
    
    public Ingredientes( String nome) {
        this.nome = nome;
    }
    
    
    
    

    // Getters e setters
    public int getId_ingredientes() {
        return id_ingredientes;
    }

    public void setId_ingredientes(int id_ingredientes) {
        this.id_ingredientes = id_ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    /*
     * Meotodo para fazer string do objeto
     */
    @Override
    public String toString() {
        return "Ingredientes[" +
                "id_ingredientes=" + id_ingredientes +
                ", nome='" + nome + '\'' +
                ']';
    }
    
    /*
     * metodo para transformar em Json:
     */
    @Override
	public boolean equals(Object obj) {
		return (this.getId_ingredientes() == ((Ingredientes) obj).getId_ingredientes());
	}	

	

}
