package dao;

/**
 * @author <hidden>
 */

public class FunctionMtd {

	/**
	 * FunctionMtd型のインスタンス
	 */
	private static FunctionMtd instance = new FunctionMtd();

	/**
	 * FunctionMtdのコンストラクタ
	 */
	private FunctionMtd() {
	}

	/**
	 * FunctionMtdのインスタンスを取得して返します。
	 * @return インスタンス
	 */
	public static FunctionMtd getInstance() {
		return instance;
	}

	/**
	 * 5桁のランダムなパスワードを自動生成して返します。
	 *
	 * @author <hidden>
	 * @return 自動生成した5桁の半角英数字パスワード(String型)
	 */
	public static String randomchar() {
		char[] charlist = new char[62];
		//配列に0-9 a-z A-Zを代入。

		String str = "";

		for (int i = 0; i < 10; ++i)
			charlist[i] = (char) ('0' + i);
		for (int i = 0; i < 26; ++i) {
			charlist[i + 10] = (char) ('a' + i);
			charlist[i + 36] = (char) ('A' + i);
		}
		char[] rndword = new char[11];
		for (int i = 0; i < 5; ++i) {
			//62文字からランダムに10文字取り出し、配列に代入
			rndword[i] = charlist[(int) (61 * Math.random())];
			str = str + rndword[i];
		}
		return str;
	}

}
