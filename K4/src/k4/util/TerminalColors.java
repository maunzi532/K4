package k4.util;

public class TerminalColors
{
	/**
	 * Primary Console Colors
	 * 00 000000
	 * 01 800000
	 * 02 008000
	 * 03 808000
	 * 04 000080
	 * 05 800080
	 * 06 008080
	 * 07 c0c0c0
	 *
	 * Bright Console Colors
	 * 08 808080
	 * 09 ff0000
	 * 10 00ff00
	 * 11 ffff00
	 * 12 0000ff
	 * 13 ff00ff
	 * 14 00ffff
	 * 15 ffffff
	 */
	public static final int[] termColors0 = new int[]
			{
					0x000000, 0x800000, 0x008000, 0x808000,
					0x000080, 0x800080, 0x008080, 0xc0c0c0,
					0x808080, 0xff0000, 0x00ff00, 0xffff00,
					0x0000ff, 0xff00ff, 0x00ffff, 0xffffff
			};

	/**
	 * Terminal Emulator Colors
	 * 0 black    2e3436  555753
	 * 1 red      cc0000  ef2929
	 * 2 green    4e9a06  8ae234
	 * 3 brown    c4a000  fce94f
	 * 4 blue     3465a4  729fcf
	 * 5 magenta  75507b  ad7fa8
	 * 6 cyan     06989a  34e2e2
	 * 7 white    d3d7cf  eeeeec
	 */
	public static final int[] termColors1 = new int[]
			{
					0x2e3436, 0xcc0000, 0x4e9a06, 0xc4a000,
					0x3465a4, 0x75507b, 0x06989a, 0xd3d7cf,
					0x555753, 0xef2929, 0x8ae234, 0xfce94f,
					0x729fcf, 0xad7fa8, 0x34e2e2, 0xeeeeec
			};
}