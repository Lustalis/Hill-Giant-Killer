package hillgiantkiller.sk.general;

import java.util.*;

public class Combination<T> implements Iterable<List<T>>, Iterator<List<T>> {

	private final List<T> source;
	private final int[] arr;

	private Combination(int k, Collection<T> elements) {
		arr = new int[k];
		source = new ArrayList<T>(elements);
	}

	@SafeVarargs
	private Combination(int k, T... elements) {
		this(k, Arrays.asList(elements));
	}

	@SafeVarargs
	public static <T> Combination<T> create(int k, T... elements) {
		return new Combination<T>(k, elements);
	}

	public static <T> Combination<T> create(int k, Collection<T> elements) {
		return new Combination<T>(k, elements);
	}

	@Override
	public Iterator<List<T>> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return arr[0] < source.size() - arr.length;
	}

	@Override
	public List<T> next() {
		if (!hasNext())
			throw new NoSuchElementException();

		if (arr[1] == 0) {
			for (int i = 0; i < arr.length; i++)
				arr[i] = i;
		} else {
			int k = arr.length, n = source.size(), i = k - 1;

			for (; i > 0 && arr[i] == n - k + i; i--)
				;

			arr[i++]++;
			for (; i < k; arr[i] = arr[i++ - 1] + 1)
				;
		}

		ArrayList<T> ret = new ArrayList<T>(arr.length);
		for (int j : arr) {
			ret.add(source.get(j));
		}
		return ret;

	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
