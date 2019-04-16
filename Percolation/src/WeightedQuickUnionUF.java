
public class WeightedQuickUnionUF {
	private int[] id;
	private int[] size;
	private int count;

	public WeightedQuickUnionUF(int n) {
		id = new int[n];
		size = new int[n];
		count = n;
		for (int i = 0; i < n; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	public int find(int p) {
		while (p != id[p]) {
			p = id[id[p]];
		}
		return p;
	}

	public Boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public void union(int p, int q) {
		int pr = find(p), qr = find(q);

		if (pr == qr) {
			return;
		}

		if (size[pr] > size[qr]) {
			id[qr] = pr;
			size[pr] += size[qr];
		} else {
			id[pr] = qr;
			size[qr] += size[pr];
		}
		count--;
	}

	public int count() {
		return count;
	}

}
