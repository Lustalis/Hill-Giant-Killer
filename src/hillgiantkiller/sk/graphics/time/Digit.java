package hillgiantkiller.sk.graphics.time;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Digit {

	private int[] bits;
	private BufferedImage image;
	private Object lock = new Object();
	private Color color;

	public Digit(Color c) {
		this.color = c;
		this.bits = new int[HEIGHT];
	}

	public void setBits(int[] bits) {
		this.bits = Arrays.copyOf(bits, this.bits.length);
	}

	public void toggle(int x, int y) {
		if (y < 0 || y > bits.length || x < 0 || x > ROW_LEN)
			return;
		image = null;
		bits[y] ^= 0x1 << x;
	}

	public int[] getBits() {
		return bits;
	}

	public void step(int i) {
		if (i >= 0 && i < DIGITS.length)
			step(DIGITS[i]);
	}

	public void step(int[] destArr) {
		synchronized (lock) {
			image = null;

			for (int r = 0; r < this.bits.length; r++) {
				int dest = (r < destArr.length ? destArr[r] : 0);
				int bits = this.bits[r];
				if (bits == dest)
					continue;
				LinkedList<Range> destRanges = new LinkedList<Range>();
				Range cur = null;
				for (int c = 0; c <= ROW_LEN; ++c) {
					if ((dest & (0x1 << c)) != 0 && c < ROW_LEN) {
						if (cur == null) {
							cur = new Range(c, c + 1);
						} else {
							cur.e++;
						}
					} else {
						if (cur != null) {
							destRanges.add(cur);
							cur = null;
						}
					}
				}

				LinkedList<Range> sranges = new LinkedList<Range>();
				for (int c = 0; c <= ROW_LEN; ++c) {
					if ((bits & (0x1 << c)) != 0 && c < ROW_LEN) {
						if (cur == null) {
							cur = new Range(c, c + 1);
						} else {
							cur.e++;
						}
					} else {
						if (cur != null) {
							sranges.add(cur);
							cur = null;
						}
					}
				}

				if (sranges.size() == 0)
					sranges.add(new Range(ROW_LEN / 2, ROW_LEN / 2 + 1));

				int dlen = destRanges.size();
				int slen = sranges.size();
				ListIterator<Range> dback = destRanges.listIterator(dlen), sback = sranges.listIterator(slen);

				while (sback.hasPrevious()) {
					cur = sback.previous();
					if (cur.dest != null)
						break;

					Range end = (dback.hasPrevious() ? dback.previous() : null);
					if (end == null) {
						cur.dest = new Range(cur.getCenter(), cur.getCenter());
					} else {
						if (slen < dlen && cur.getSize() > 1) {
							Range prev = getPrev(sback);
							sback.next();
							Range next = getNext(sback);
							sback.previous();
							int size = cur.getSize();
							if (cur.e < ROW_LEN && (next == null || next.s > cur.e)) {
								Range toAdd = new Range(cur.e - size / 2, cur.e);
								cur.e -= size / 2;
								sback.next();
								sback.add(toAdd);
								sback.previous();
								toAdd.dest = end;
								slen++;
								continue;
							}
							if (cur.s > 0 && (prev == null || prev.e < cur.s)) {
								Range toAdd = new Range(cur.s, cur.s + size / 2);
								cur.s += size / 2;
								cur.dest = end;
								sback.add(toAdd);
								slen++;
								continue;
							}
						}
						cur.dest = end;
					}
				}

				int ret = 0;
				for (Range rng : sranges) {
					if (rng.getCenter() > rng.dest.getCenter()) {
						rng.s--;
						rng.e--;
					} else if (rng.getCenter() < rng.dest.getCenter()) {
						rng.s++;
						rng.e++;
					}

					if (rng.getSize() > rng.dest.getSize()) {
						if (rng.s > rng.dest.s) {
							rng.e--;
						} else {
							rng.s++;
						}
					} else if (rng.getSize() < rng.dest.getSize()) {
						if (rng.s > rng.dest.s) {
							rng.s--;
						} else {
							rng.e++;
						}
					}
					for (int i = rng.s; i < rng.e; i++) {
						ret |= 0x1 << i;
					}
				}
				this.bits[r] = ret;
			}
		}

	}

	private <T> T getPrev(ListIterator<T> it) {
		if (it.hasPrevious()) {
			T ret = it.previous();
			it.next();
			return ret;
		}
		return null;
	}

	private <T> T getNext(ListIterator<T> it) {
		if (it.hasNext()) {
			T ret = it.next();
			it.previous();
			return ret;
		}
		return null;
	}

	private class Range {
		public int s, e;
		public Range dest;

		public Range(int s, int e) {
			this.s = s;
			this.e = e;
		}

		public int getSize() {
			return e - s;
		}

		public int getCenter() {
			return getSize() / 2 + s;
		}

		@Override
		public String toString() {
			return "(" + s + ", " + e + ")";
		}
	}

	public BufferedImage getImage() {
		synchronized (lock) {
			if (image == null) {
				image = new BufferedImage(ROW_LEN, this.bits.length, BufferedImage.TYPE_INT_ARGB);
				int color = this.color.getRGB();
				for (int r = 0; r < this.bits.length; ++r) {
					int bits = this.bits[r];
					for (int v = 0x1, c = 0; c < ROW_LEN; v <<= 1, ++c) {
						if ((bits & v) == v) {
							// BIT on
							image.setRGB(c, r, color);
						} else {
							// BIT off
							image.setRGB(c, r, transparent);
						}
					}
				}
			}
			return image;
		}
	}

	private static final int transparent = new Color(0, 0, 0, 0).getRGB();
	private static final int ROW_LEN = 30;

	public static final int WIDTH = ROW_LEN, HEIGHT = 48;

	public static final int[] getBitsFor(int loc) {
		if (loc < DIGITS.length && loc >= 0)
			return DIGITS[loc];
		return new int[HEIGHT];
	}

	private static final int[][] DIGITS = new int[][] {
			{ 0, 0, 0, 1046528, 4193792, 16777088, 33554368, 67108832, 66586592, 132121584, 132121080, 264241656,
					264241400, 260047100, 528482556, 528482428, 528482428, 528482430, 520093822, 520093822, 520093822,
					520093822, 520093822, 520093822, 520093822, 520093822, 520093822, 520093822, 520093822, 520093822,
					520093822, 528482430, 528482428, 528482428, 260047100, 264241404, 264241400, 132121080, 132121592,
					66586608, 33431536, 33554400, 8388544, 4194048, 1047552, 0, 0, 0, },
			{ 0, 0, 0, 253952, 258048, 261120, 261888, 262016, 262112, 258032, 254968, 254200, 254072, 253976, 253952,
					253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952,
					253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952, 253952,
					253952, 134217712, 134217720, 134217720, 134217720, 0, 0, 0, 0, },
			{ 0, 0, 0, 261120, 2097024, 4194272, 8388600, 16777208, 33489400, 33292408, 33292312, 66584576, 66584576,
					66060288, 66060288, 66060288, 66060288, 33030144, 33030144, 33030144, 33292288, 16515072, 16646144,
					8257536, 4128768, 4161536, 2064384, 1032192, 516096, 520192, 260096, 130048, 65024, 32512, 16256,
					8128, 4064, 2032, 1008, 504, 134217724, 268435452, 268435452, 268435452, 134217720, 0, 0, 0, },
			{ 0, 0, 0, 523264, 2097024, 8388576, 16777200, 16777208, 33423864, 33030264, 66584600, 66060288, 66060288,
					66060288, 66060288, 66060288, 32505856, 33030144, 33292288, 16646144, 8323072, 4194176, 1048512,
					1048512, 8388544, 16777088, 33488896, 66846720, 133169152, 132120576, 132120576, 266338304,
					264241152, 264241152, 264241152, 266338304, 132120576, 133169152, 133169180, 66846844, 33489916,
					16777212, 8388600, 4194272, 524032, 0, 0, 0, },
			{ 0, 0, 0, 8323072, 16744448, 16744448, 16760832, 16769024, 16769024, 16642048, 16642048, 16578560,
					16578560, 16546816, 16530944, 16530944, 16523008, 16523008, 16519040, 16517056, 16517056, 16516064,
					16516064, 16515568, 16515320, 16515320, 16515196, 16515196, 16515134, 16515103, 536870911,
					536870911, 1073741823, 536870911, 536870910, 16515072, 16515072, 16515072, 16515072, 16515072,
					16515072, 16515072, 16515072, 16515072, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 33554400, 33554416, 33554416, 33554416, 33554416, 496, 496, 496, 496, 496, 496, 496, 496,
					496, 496, 496, 262128, 4194288, 8388592, 33554416, 33538528, 66846720, 133169152, 132120576,
					132120576, 264241152, 264241152, 264241152, 264241152, 264241152, 264241152, 130023424, 132120576,
					132120576, 66060288, 66584604, 33423612, 16777212, 8388604, 4194296, 524256, 0, 0, 0, },
			{ 0, 0, 0, 16744448, 67104768, 134216704, 134217216, 132251520, 100679552, 4032, 2016, 992, 1008, 496, 496,
					504, 248, 248, 248, 459004, 8386812, 33554172, 67108860, 134217724, 267390972, 266338812,
					264241404, 528482556, 528482556, 528482556, 528482556, 528482556, 528482556, 528482552, 528482552,
					528482808, 260047352, 264242160, 132121584, 133171184, 66854880, 33554368, 16777088, 4194048,
					1047552, 0, 0, 0, },
			{ 0, 0, 0, 0, 268435452, 536870908, 536870908, 536870908, 536870904, 260046848, 264241152, 130023424,
					132120576, 65011712, 66060288, 66060288, 33030144, 33030144, 16252928, 16515072, 8126464, 8257536,
					8257536, 4128768, 4128768, 2064384, 2064384, 1015808, 1032192, 1032192, 516096, 516096, 258048,
					258048, 129024, 129024, 129024, 64512, 64512, 32256, 32256, 16128, 16128, 8064, 8064, 0, 0, 0, },
			{ 0, 0, 0, 1046528, 4194048, 16777152, 33554400, 66981872, 66061296, 132121080, 132120824, 130023672,
					130023676, 130023676, 130023676, 132120824, 65012216, 66061304, 33032176, 16519136, 8339392,
					4194176, 1048320, 1047552, 4193792, 8388480, 33497024, 66848752, 133694456, 132121080, 264241404,
					264241276, 260046974, 528482430, 528482430, 528482430, 260046974, 264241278, 264241404, 132121084,
					133695480, 67108856, 33554416, 8388544, 2096896, 0, 0, 0, },
			{ 0, 0, 0, 1046528, 4193792, 16777088, 33554368, 66985952, 66586608, 132121584, 132121080, 264241400,
					264241404, 260047100, 260047100, 528482556, 528482556, 528482556, 528482556, 528482556, 528482556,
					528482808, 532677112, 535824376, 536870896, 536870880, 536870848, 530579200, 528742400, 260046848,
					260046848, 260046848, 260046848, 130023424, 130023424, 132120576, 65011712, 66060288, 33030144,
					16646160, 16744696, 8388600, 2097144, 1048560, 131008, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4194176, 8388480, 8388480, 4194176, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 253952, 258048, 520192, 520192, 520192, 258048, 253952, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 122880, 258048, 520192, 520192, 520192, 520192, 253952, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, }, };
	/*
	 * new int[][] { { 16777152, 16777152, 16777152, 16777152, 16777152,
	 * 16777152, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1057222719, 1057222719, 1057222719, 1057222719, 1057222719,
	 * 1057222719, 1057222719, 1057222719, 1057222719, 1057222719, 1057222719,
	 * 1057222719, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 16777152, 16777152, 16777152, 16777152, 16777152, 16777152 },
	 * { 258048, 258048, 258048, 258048, 258048, 258048, 262080, 262080, 262080,
	 * 262080, 262080, 262080, 258111, 258111, 258111, 258111, 258111, 258111,
	 * 258048, 258048, 258048, 258048, 258048, 258048, 258048, 258048, 258048,
	 * 258048, 258048, 258048, 258048, 258048, 258048, 258048, 258048, 258048,
	 * 258048, 258048, 258048, 258048, 258048, 258048, 1073741823, 1073741823,
	 * 1073741823, 1073741823, 1073741823, 1073741823 }, { 16777152, 16777152,
	 * 16777152, 16777152, 16777152, 16777152, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964608, 1056964608,
	 * 1056964608, 1056964608, 1056964608, 1056964608, 16515072, 16515072,
	 * 16515072, 16515072, 16515072, 16515072, 258048, 258048, 258048, 258048,
	 * 258048, 258048, 4032, 4032, 4032, 4032, 4032, 4032, 63, 63, 63, 63, 63,
	 * 63, 1073741823, 1073741823, 1073741823, 1073741823, 1073741823,
	 * 1073741823 }, { 16777152, 16777152, 16777152, 16777152, 16777152,
	 * 16777152, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964608, 1056964608, 1056964608, 1056964608, 1056964608,
	 * 1056964608, 16773120, 16773120, 16773120, 16773120, 16773120, 16773120,
	 * 1056964608, 1056964608, 1056964608, 1056964608, 1056964608, 1056964608,
	 * 1056964608, 1056964608, 1056964608, 1056964608, 1056964608, 1056964608,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 16777152, 16777152, 16777152, 16777152, 16777152, 16777152 }, { 16515072,
	 * 16515072, 16515072, 16515072, 16515072, 16515072, 16773120, 16773120,
	 * 16773120, 16773120, 16773120, 16773120, 16519104, 16519104, 16519104,
	 * 16519104, 16519104, 16519104, 16515135, 16515135, 16515135, 16515135,
	 * 16515135, 16515135, 1073741823, 1073741823, 1073741823, 1073741823,
	 * 1073741823, 1073741823, 16515072, 16515072, 16515072, 16515072, 16515072,
	 * 16515072, 16515072, 16515072, 16515072, 16515072, 16515072, 16515072,
	 * 16515072, 16515072, 16515072, 16515072, 16515072, 16515072 }, {
	 * 1073741823, 1073741823, 1073741823, 1073741823, 1073741823, 1073741823,
	 * 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 16777215, 16777215,
	 * 16777215, 16777215, 16777215, 16777215, 1056964608, 1056964608,
	 * 1056964608, 1056964608, 1056964608, 1056964608, 1056964608, 1056964608,
	 * 1056964608, 1056964608, 1056964608, 1056964608, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 16777152, 16777152,
	 * 16777152, 16777152, 16777152, 16777152 }, { 16773120, 16773120, 16773120,
	 * 16773120, 16773120, 16773120, 4032, 4032, 4032, 4032, 4032, 4032, 63, 63,
	 * 63, 63, 63, 63, 16777215, 16777215, 16777215, 16777215, 16777215,
	 * 16777215, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 16777152, 16777152, 16777152, 16777152, 16777152, 16777152 },
	 * { 1073741823, 1073741823, 1073741823, 1073741823, 1073741823, 1073741823,
	 * 1056964608, 1056964608, 1056964608, 1056964608, 1056964608, 1056964608,
	 * 16515072, 16515072, 16515072, 16515072, 16515072, 16515072, 16515072,
	 * 16515072, 16515072, 16515072, 16515072, 16515072, 258048, 258048, 258048,
	 * 258048, 258048, 258048, 258048, 258048, 258048, 258048, 258048, 258048,
	 * 4032, 4032, 4032, 4032, 4032, 4032, 4032, 4032, 4032, 4032, 4032, 4032 },
	 * { 16777152, 16777152, 16777152, 16777152, 16777152, 16777152, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 16777152,
	 * 16777152, 16777152, 16777152, 16777152, 16777152, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 16777152, 16777152,
	 * 16777152, 16777152, 16777152, 16777152 }, { 16777152, 16777152, 16777152,
	 * 16777152, 16777152, 16777152, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1056964671, 1056964671, 1056964671,
	 * 1056964671, 1056964671, 1056964671, 1073741760, 1073741760, 1073741760,
	 * 1073741760, 1073741760, 1073741760, 1056964608, 1056964608, 1056964608,
	 * 1056964608, 1056964608, 1056964608, 16515072, 16515072, 16515072,
	 * 16515072, 16515072, 16515072, 262080, 262080, 262080, 262080, 262080,
	 * 262080 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 * 16777152, 16777152, 16777152, 16777152, 16777152, 16777152, 0, 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, 0, 0, 0, 258048, 258048, 258048, 258048, 258048,
	 * 258048, 0, 0, 0, 0, 0, 0, 258048, 258048, 258048, 258048, 258048, 258048,
	 * 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	 */

}
