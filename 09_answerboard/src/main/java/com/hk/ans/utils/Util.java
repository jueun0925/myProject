package com.hk.ans.utils;

public class Util {
	private String arrowNbsp; //공백과 화살표를 출력할 문자열

	//set메서드에서 depth값을 받아서 공백과 화살표 만들어주기
	public void setArrowNbsp(String depth) {
		String nbsp="";
		int depthInt = Integer.parseInt(depth);
		
		for(int i=0;i<depthInt;i++) {
			nbsp+="&nbsp;&nbsp;&nbsp;&nbsp";
		}					//답글인 조건 : depth>0
		this.arrowNbsp=nbsp+(depthInt>0?"<img src=\"img/arrow_right.png\" alt=\"답글\">":"");
		
	}

	public String getArrowNbsp() {
		return arrowNbsp;
	}
	
	
}
