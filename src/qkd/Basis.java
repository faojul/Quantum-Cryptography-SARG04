package qkd;

public enum Basis {
	ORTHOGONAL,
	DIAGONAL;
	
	public static Basis random() {
		int i = (int)(Math.random()*2);
		if(i==0)
			return Basis.ORTHOGONAL;
		else
			return Basis.DIAGONAL;
	}
	public static Basis flip(Basis basis){
		if(basis==Basis.ORTHOGONAL){
			return Basis.DIAGONAL;
		}
		else {
			return Basis.ORTHOGONAL;
		}
		
	}
}
