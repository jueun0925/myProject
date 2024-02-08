package com.hk.motong.utils;

import java.util.HashMap;
import java.util.Map;

public class Paging {


	
	public static Map<String, Integer> pagingValue(int pcount,String pNum,int pageRange){
		Map<String, Integer> map=new HashMap<String, Integer>();
		
		int pNumber=Integer.parseInt(pNum);

		int pageEndNum=((pNumber-1)/pageRange+1)==1?pageRange:((pNumber-1)/pageRange+1)*pageRange;    
		
		int prePageNum=pageEndNum-pageRange==0?1:pageEndNum-pageRange;
		
		int nextPageNum=pageEndNum>=pcount?pcount:pageEndNum+1;
	
		int startPage=pageEndNum-(pageRange-1);
		int endPage=pageEndNum>pcount?pcount:pageEndNum;
		
		map.put("prePageNum", prePageNum);
		map.put("nextPageNum", nextPageNum);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		
		return map;
	}
}