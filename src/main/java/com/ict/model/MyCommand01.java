package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class MyCommand01 implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 실제 DB 처리후 MyController01로 되돌아 간다.
		// DB 처리 (전체 정보 가져오기)
		List<VO> list = DAO.getListAll();
		
		// xml로 만들자
		// String과 StringBuffer의 차이
		// String 은 변경 할 수가 없다.
		// StringBuffer 기존의 정보를 더해준다.
		
		// xml 만드는 방법이다.
		// 1. <?xml version=\"1.0\" encoding=\"UTF-8\"?> 이 무조건 있어야 한다.
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<members>");
		// 보통 바깥을 복수 명사로
		// 안쪽은 단수 명사로 만든다.
		for (VO k : list) {
			sb.append("<member>");
			sb.append("<m_idx>"+k.getM_idx()+"</m_idx>");
			sb.append("<m_id>"+k.getM_id()+"</m_id>");
			sb.append("<m_pw>"+k.getM_pw()+"</m_pw>");
			sb.append("<m_name>"+k.getM_name()+"</m_name>");
			sb.append("<m_reg>"+k.getM_reg().substring(0, 10)+"</m_reg>");
			sb.append("<m_addr>"+k.getM_addr()+"</m_addr>");
			sb.append("</member>");
		}
		sb.append("</members>");
		
		// 일반적인 MVC는 뷰 경로를 작성하지만
		// Ajax는 만들어진 정보를 내보낸다.
		
		return sb.toString();
	}
}
