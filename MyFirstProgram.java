class MyFirstClass {
	public static void main(String[] s){
		MySecondClass o = new MySecondClass(0,0);
                for(int i = 1; i <= 8; ++i){
                        for(int j = 1; j <= 8; ++j){
				o.setA(i);
				o.setB(j);
				System.out.println("Numbers: " + o.getA() + ", " + o.getB());
				System.out.println("Add: " + o.add() );
				System.out.println("Sub: " + o.sub() );
				System.out.println("Mul: " + o.mul() );
				System.out.println("Div: " + o.div() );
				System.out.println("Mod: " + o.mod() );
				System.out.println("And: " + o.and() );
				System.out.println("Or: " + o.or() );
				System.out.println("Xor: " + o.xor() );
				System.out.println("Max: " + o.max() );
				System.out.println("Min: " + o.min() );
				System.out.println("сдвиг 1 числа на 1: " + o.sdvig1());
				System.out.println("сдвиг 2 числа на 1: " + o.sdvig2());
			}
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

//Сложение двух операндов
	public int add() {
		return oA + oB;
	}

//Вычитание двух операндов
	public int sub() {
		return oA - oB;
	}

//Умножение двух операндов 
	public int mul() {
		return oA * oB;
	}

//Деление двух операндов 
	public int div() {
		return oA / oB;
	}
//Взятие остатка от деления 
	public int mod() {
		return oA % oB;
	}

//Побитовое "И"

	public int and() {
		return oA & oB;
	}

//Побитовое "ИЛИ" 
	public int or() {
		return oA | oB;
	}

//Побитовое "Исключающее ИЛИ" 
	public int xor() {
		return oA ^ oB;
	}

//Выбор максимального из чисел 
	public int max() {
		return oA > oB ? oA : oB;
	}

//Выбор минимального из чисел
	public int min() {
		return oA < oB ? oA : oB;
	}
// побитовый сдвиг первого числа влево на 1
	public int sdvig1() {
		return oA <<= 1;
	}
// побитовый сдвиг второго числа влево на 1
	public int sdvig2() {
		return oB <<= 1;
	}
}
