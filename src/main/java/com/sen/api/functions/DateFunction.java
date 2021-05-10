package com.sen.api.functions;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sen.api.utils.StringUtil;

public class DateFunction  implements Function{

	@Override
	public String execute(String[] args) {
		//如果args长度为0 或，args第一个字符串数组为空
		if (args.length == 0 ||StringUtil.isEmpty(args[0])) {
			// 以字符串的类型 返回当前时间戳
			return String.format("%s", new Date().getTime());
		} else {
			//返回当前日期，以yyyy-mm-dd 形式
			return getCurrentDate("yyyy-MM-dd");
		}
	}

	private String getCurrentDate(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String str = format.format(new Date());
		return str;
	}
	
	@Override
	public String getReferenceKey() {
		// TODO Auto-generated method stub
		return "date";
	}

}
