package my;

public class IO {

	private boolean out;

	public IO() {
		out = true;
	}

	public void switchTO(boolean o) {
		this.out = o;
	}

	public void o(String s) {
		if (out) {
			System.out.print(s);
		}
	}
	
	public void ol( ) {
		if (out) {
			System.out.println( );
		}
	}

	public void ol(String s) {
		if (out) {
			System.out.println(s);
		}

	}

	public void ol(double[] s,boolean f) {
		if (out) {
			if(f){
				for (int i = 0; i < s.length; i++) {
					System.out.print("[" + s[i] + "] ");
				}
				System.out.println();
			}else{
				for (int i = 0; i < s.length; i++) {
					System.out.print(s[i]);
				}
				System.out.println();
			}

		}

	}

	public void o(double[] s,boolean f) {
		if (out) {
			if(f){
				for (int i = 0; i < s.length; i++) {
					System.out.print("[" + s[i] + "] ");
				}
			}else{
				for (int i = 0; i < s.length; i++) {
					System.out.print(s[i]);
				}
			}
		}
	}
	
	public void o(double s,boolean f) {
		if (out) {
			if(f){
					System.out.print("[" + s + "] ");
					
			}else{
					System.out.print(s);
			}
		}
	}

	public void ol(double s,boolean f) {
		if (out) {
			if(f){
					System.out.print("[" + s + "] ");
					System.out.println();
			}else{
					System.out.print(s);
					System.out.println();
			}
		}
	}
	
	public void ol(double s) {
		if (out) {
			System.out.println("[" + s + "] ");
		}

	}

	public void o(double s) {
		if (out) {
			System.out.print("[" + s + "] ");
		}

	}
}
