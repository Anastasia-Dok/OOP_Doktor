package myfirstpackage;
public class MySecondClass{
	private int oA, oB;
//конструктор
	public MySecondClass(int oA, int oB) { 
		this.oA = oA;
		this.oB = oB;
	}

//Метод для получения значения первого операнда 
	public int getA() {
		return oA;
	}

//Метод для получения значения второго операнда 
	public int getB() {
		return oB;
	}

//Метод для модификации значения первого операнда 
	public void setA(int oA) {
		this.oA = oA;
	}

//Метод для модификации значения второго операнда 
	public void setB(int oB) {
		this.oB = oB;
	}

//Вычитание двух операндов
	public int sub() {
		return oA - oB;
	}


}