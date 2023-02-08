package com.ict.db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class DAO {
	// 실제 사용하는 클래스는 SqlSession이래
	private static SqlSession ss;

	// 싱글턴 패턴으로 만들어보자(동기화 처리 해야하니까)
	// 어제 동기화와 비동기화에 대해서 배웠지 복습하고
	// 한번 만들어진 객체를 재사용하게 하는 방식이래

	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}

	// DB 처리하는 메서드들
	// 전체 정보 가져오기
	public static List<VO> getListAll() {
		List<VO> list = getSession().selectList("members.list");
		return list;
	}

	// 아이디 중복 체크
//	public static String getIdChk(String m_id) {
//		String result = "사용가능";
//		List<VO> list = getSession().selectList("members.idchk", m_id);
//		if(list.size()>0) {
//			result = "사용불가";
//		}
//		return result;
//	}
	
	// 아이디 중복 체크
	public static String getIdChk(String m_id) {
		String result = "1";
		List<VO> list = getSession().selectList("members.idchk", m_id);
		if(list.size()>0) {
			result = "0";
		}
		return result;
	}


}
