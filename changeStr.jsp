<%!
	// 入力された値から対象の文字を置き換えるメソッド
	public static String changeStr(String input) {
		input = substitute(input, "&",  "&amp;");
	    input = substitute(input, "<",  "&lt;");
	    input = substitute(input, ">",  "&gt;");
	    input = substitute(input, "'", "''");
	    input = substitute(input, "\\", "\\\\");

		return input;
	}
	// 実際に置き換えを行っているメソッド
	public static String substitute(String input, String pattern, String replacement) {
	    // 置換対象文字列が存在する場所を取得
	    int index = input.indexOf(pattern);
	    // 置換対象文字列が存在しなければ終了
	    if(index == -1) {
	        return input;
	    }
	    // 処理を行うための StringBuffer
	    StringBuffer buffer = new StringBuffer();
	    buffer.append(input.substring(0, index) + replacement);
	    if(index + pattern.length() < input.length()) {
	        // 残りの文字列を再帰的に置換
	        String rest = input.substring(index + pattern.length(), input.length());
	        buffer.append(substitute(rest, pattern, replacement));
	    }
	    return buffer.toString();
	}
%>