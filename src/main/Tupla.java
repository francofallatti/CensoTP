package main;

public class Tupla<E1,E2> {
	private E1 e1;
	private E2 e2;
	
	public Tupla(E1 e1, E2 e2){
	this.e1= e1;
	this.e2 = e2;
	}

	public E1 getE1() {
		return e1;
	}

	public E2 getE2() {
		return e2;
	}
	
}
