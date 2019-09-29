
public class Test {
	
	public static void main(String[] args) {
		boolean[] array = new boolean[7];
		int times = 0;
		while (times < 7) {
			times++;
			int random = (int)( Math.random() * 7);
			
			while(array[random]) {
				random = (int)( Math.random() * 7);
			}
			StdOut.println("---------random----------");
			StdOut.println(random);
			array[random] = true;
			StdOut.println("---------array----------");
			for (int i = 0; i < 7; i++) {
				StdOut.print(array[i] + ", ");
			}
			StdOut.println();
		}
		
	}
}
