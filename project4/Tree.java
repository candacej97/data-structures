package project4_new;

/**
 * This class should be used to create a Tree object. Using the correct parameters, 
 * the object should be created. If parameters are wrong, exceptions will be thrown.
 * This class defines the trees that are created with the variables taken from the file in 
 * NYCStreetTrees class.
 * 
 * @author Candace Johnson
 * @version 4/13/2017
 */

public class Tree implements Comparable<Tree>{

	private int tree_id;
	private int tree_dbh;
	private String treeStatus;
	private String treeHealth;
	private String spc_name;
	private int zipcode;
	private String boroname;
	private Double x_sp;
	private Double y_sp;
	

	/**This is the only constructor in this class.
	 * 
	 * Without these parameters, no Tree Object can be initialized because this constructor
	 * puts these parameters into setters right away.
	 * 
	 * @param tree_id is an integer that represents the tree id
	 * @param tree_dbh collects the integer that identifies the tree DBH
	 * @param status is a string that represents the status of the tree
	 * @param health is a string that represents the health of the tree
	 * @param spc_name is a string that represents the species name of the tree
	 * @param zipcode is an integer that represents the location of the tree by zipcode
	 * @param boroname is a string of the Borough that the tree is located
	 * @param x_sp is a double
	 * @param y_spc is a double 
	 * */
	
	protected Tree(int tree_id, int tree_dbh, String status, String health, String spc_name, 
			int zipcode, String boroname, Double x_sp, Double y_sp) {
		
		setTreeID(tree_id);
		setTreeDBH(tree_dbh);
		setTreeStatus(status);
		setTreeHealth(health);
		setSpecies(spc_name);
		setZip(zipcode);
		setBoro(boroname);
		
		setX(x_sp);
		setY(y_sp);
	}
	
	
	/**
	 * Sets the tree_id of this Tree object
	 * Checks if the integer entered is negative. 
	 * If so, it throws an exception. If not, it sets the tree_id.
	 * 
	 * @param tree_id is an integer that needs to be positive
	 * @throws IllegalArgumentEception
	 */
	
	private void setTreeID(int tree_id) throws IllegalArgumentException{
		if(tree_id > 0){
			this.tree_id = tree_id;
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Sets the tree_dbh of this Tree object
	 * Checks if the integer entered is negative or not.
	 * If so, it throws an exception. If not, it sets the tree_dbh.
	 * 
	 * @param tree_dbh is an integer that needs to be positive
	 * @throws IllegalArgumentException
	 */
	private void setTreeDBH(int tree_dbh) throws IllegalArgumentException{
		if(tree_dbh >= 0){
			this.tree_dbh = tree_dbh;
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Sets the treeStatus of this object
	 * Checks if the status is one of the acceptable values including empty string and null
	 * 
	 * @param treeStatus is a string of the current status of the tree
	 * @throws IllegalArgumentException
	 */
	private void setTreeStatus(String treeStatus) throws IllegalArgumentException{
		
		if (treeStatus == null) {
            this.treeStatus = treeStatus;
        }
		else {
			switch (treeStatus.toLowerCase()) {
		        case "alive": this.treeStatus = treeStatus; break;
		        case "dead": this.treeStatus = treeStatus; break;
		        case "stump": this.treeStatus = treeStatus; break;
		        case "": this.treeStatus = treeStatus; break;
		        default: 
		            throw new IllegalArgumentException();
			}
		}
		
	}
	
	/**
	 * Sets the treeHealth of this object
	 * Checks if the status is one of the acceptable values including empty string and null
	 * 
	 * @param health is a string of the current health of the tree
	 * @throws IllegalArgumentException
	 */
	private void setTreeHealth(String health) throws IllegalArgumentException{
		
		if (health == null) {
            this.treeHealth = health;
        }
		else{
			switch (health.toLowerCase()) {
		        case "good": this.treeHealth = health; break;
		        case "fair": this.treeHealth = health; break;
		        case "poor": this.treeHealth = health; break;
		        case "": this.treeHealth = health; break;
		        default: 
		            throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Sets the species name of the tree
	 * Checks that the string entered is not null and is indeed a string
	 * 
	 * @param spc_name is a string
	 * @throws IllegalArgumentException
	 * */
	void setSpecies(String spc_name) throws IllegalArgumentException{
		if(spc_name != null){
			this.spc_name = spc_name;
		}
		else{throw new IllegalArgumentException();}
	}
	
	/**
	 * Sets the zipcode of the tree
	 * Checks that the integer entered is not more than 5 digits
	 * 
	 * @param zipcode is an integer
	 * @return 
	 * @throws IllegalArgumentException
	 */
	
	private void setZip(int zipcode) throws IllegalArgumentException{
		if(zipcode > 99999 || zipcode < 00000){throw new IllegalArgumentException();}	
		else{this.zipcode = zipcode;}
	}
	
	/**
	 * Sets the borough name of this object
	 * Checks to make sure that the string entered is one of the acceptable answers
	 * 
	 * @param boroname is a string
	 * @throws IllegalArgumentException
	 */
		
	private void setBoro(String boroname) throws IllegalArgumentException{
		if (boroname == null)
			throw new IllegalArgumentException();
		
		switch (boroname.toLowerCase()) {
	        case "queens": this.boroname = boroname; break;
	        case "brooklyn": this.boroname = boroname; break;
	        case "bronx": this.boroname = boroname; break;
	        case "manhattan": this.boroname = boroname; break;
	        case "staten island": this.boroname = boroname; break;
	        default: 
	            throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * Sets the x_sp of this object
	 * Checks to make sure the double entered is not null
	 * 
	 * @param x_sp is a double
	 * @throws IllegalArgumentException
	 */
	
	private void setX(Double x_sp) throws IllegalArgumentException{
		if(x_sp == null){throw new IllegalArgumentException();}
		else{this.x_sp = x_sp;}
	}
	
	/**
	 * Sets the y_sp of this object
	 * Makes sure the double entered is not null
	 * 
	 * @param y_sp
	 * @throws IllegalArgumentException
	 */

	private void setY(Double y_sp) throws IllegalArgumentException{
		if(y_sp == null){throw new IllegalArgumentException();}
		else{this.y_sp = y_sp;}
	}

	
	/**
	 * @return tree_id for the use of other classes
	 */
	public int getTreeID() {
		return tree_id;
	}
	
	/**
	 * @return tree_dbh for the use of other classes
	 */

	public int getTreeDBH() {
		return tree_dbh;
	}
	
	/**
	 * @return spc_name for the use of other classes
	 */

	public String getSpecies() {
		return spc_name;
	}
	
	/**
	 * @return zipcode for the use of other classes
	 */
	public int getZipcode() {
		return zipcode;
	}

	/**
	 * @return boroname for the use of other classes
	 */
	public String getBoroname() {
		return boroname;
	}

	/**
	 * @return x_sp for the use of other classes
	 */
	public Double getX() {
		return x_sp;
	}

	/**
	 * @return y_sp for the use of other classes
	 */
	public Double getY() {
		return y_sp;
	}

	/**
	 * @return treeStatus for the use of other classes
	 */
	public String getTreeStatus() {
		return treeStatus;
	}
	
	/**
	 * @return treeHealth for the use of other classes
	 */
	public String getTreeHealth() {
		return treeHealth;
	}
	
	/**
	 * Overrides the equals method of the Object class.
	 * 
	 * This method takes obj and checks if the tree_id and the spc_name are the same.
	 * 
	 * If they are the same the method will return true, if the tree_id is the same and the
	 * spc_name is different we throw the exception.
	 * 
	 * @param obj which is casted to be a Tree object
	 * @return a boolean value
	 * @throws IllegalArgumentException
	 * */
	@Override
	public boolean equals(Object obj) throws IllegalArgumentException{
		try{
			if(this.tree_id == ((Tree) obj).getTreeID() && this.spc_name.equalsIgnoreCase(((Tree) obj).getSpecies())){
				return true;
			}
			else if(this.tree_id == ((Tree) obj).getTreeID() && !this.spc_name.equalsIgnoreCase(((Tree) obj).getSpecies())){
				throw new IllegalArgumentException();
			}
			else{return false;}
		}catch(IllegalArgumentException i){
			System.err.println("The two trees cannot be evaluated in equivalence.");
			return false;}
	}

	
	/**
	 * Overrides the compareTo method in the Comparable interface.
	 * If the spc_name is the same then we check the tree_id.
	 * If both are the same, return 0.
	 * 
	 * @param Tree object t
	 * @return -1, 0, 1
	 */
	@Override
	public int compareTo(Tree t) {
		int equ = 0;
		if(this.spc_name.equalsIgnoreCase(t.getSpecies())){
			if(this.tree_id == t.getTreeID()){equ = 0;}
			else if(this.tree_id > t.getTreeID()){equ = 1;}
			else if(this.tree_id < t.getTreeID()){equ = -1;}
			}
		else{
			equ = this.spc_name.compareToIgnoreCase(t.spc_name);
			}
		return equ;
	}
	
	/**
	 * Takes all of the parameters entered and set and format all of them into a coherent
	 * string that can be read.
	 * 
	 * @return String s
	 */
	
	@Override
	public String toString(){
		String s = String.format("ID #: %d%nDBH: %d%nSpecies Name: %s%nZipcode: %05d%n"
				+ "Borough: %s%nStatus: %s%nHealth: %s%nx_sp: %f%ny_sp: %f", this.tree_id,
				this.tree_dbh, this.spc_name, this.zipcode, this.boroname, this.treeStatus,
				this.treeHealth, this.getX(), this.getY());
		return s;
	}
	
	boolean sameName (Tree t) {
		if (this.spc_name.equalsIgnoreCase(t.getSpecies()))
			return true;
		return false;
	}
	
	int compareName (Tree t) {
		if (t == null)
			throw new NullPointerException();
		if (sameName(t))
			return 0;
		else
			// use the original compareTo(t) instead of overridden
			return (compareTo(t));
	}
}
