package model;
/**
 * @author <hidden>
 */
public class ChangeStr {
	/**
	 * フォーム内にタグが用いられていた際、任意の文字数に変換。
	 * タグの入力を行われないようにする。
	 *
	 * @author <hidden>
	 * @param input - 入力された値
	 * @return input - 変換後の文字列
	 */
	public static String changeStr(String input) {
		input = substitute(input, "&", "&amp;"); // &amp; ダブルクォーテーション内部
		input = substitute(input, "<", "&lt;"); // &lt;
		input = substitute(input, ">", "&gt;"); // &gt;
		input = substitute(input, "'", "''"); // ''
		input = substitute(input, "\\", "\\\\"); // \\\\

		return input;
	}

	/**
	 * フォーム内にタグが用いられていた際、任意の文字数に変換。
	 * タグの入力を行われないようにする。
	 *
	 * @author <hidden>
	 * @param input - 入力された値
	 * @param pattern - 指定対象文字
	 * @param replacement - 置換文字
	 * @return input - 変換後の文字列
	 */
	public static String substitute(String input, String pattern, String replacement) {
		// 置換対象文字列が存在する場所を取得
		int index = input.indexOf(pattern);
		// 置換対象文字列が存在しなければ終了
		if (index == -1) {
			return input;
		}
		// 処理を行うための StringBuffer
		StringBuffer buffer = new StringBuffer();
		buffer.append(input.substring(0, index) + replacement);
		if (index + pattern.length() < input.length()) {
			// 残りの文字列を再帰的に置換
			String rest = input.substring(index + pattern.length(), input.length());
			buffer.append(substitute(rest, pattern, replacement));
		}
		return buffer.toString();
	}

}
