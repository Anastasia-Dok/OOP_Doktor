class MyFirstClass {
    public static void main(String[] s) {
        MySecondClass o = new MySecondClass(2, 0);
        System.out.println(o.sub());
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setA(i);
                o.setB(j);
                System.out.print(o.sub());
                System.out.print(" ");
            }
            System.out.println();
        }

    }
}
class MySecondClass{
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
