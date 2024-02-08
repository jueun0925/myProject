package com.hk.ans.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.ans.dtos.AnsDto;
import com.hk.config.SqlMapConfig;

public class AnsDao extends SqlMapConfig{
	
	private String namespace="com.hk.ans.";
	
	//1.글 목록 조회하기
//	public List<AnsDto> getAllList(){
//		List<AnsDto> list = new ArrayList();
//		
//		SqlSession sqlSession=null; //쿼리를 실행시켜주는 객체
//		
//		try {
//			//sqlSessionFactory객체의 openSession을 통해
//			//sqlSession객체를 구한다					true : autocommit 설정 //안넣으면 실행은 됐는데 commit이 안되나봄
//			sqlSession=getSqlSessionFactory().openSession(true);
//			
//			//sqlSession객체에서 쿼리실행 메서드를 통해 쿼리실행
//			//쿼리실행메서드(쿼리이름,파라미터)
//			list=sqlSession.selectList(namespace+"boardList");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			sqlSession.close();
//		}
//		
//		return list;
//	}
	
	//1.글목록 조회하기[페이징처리]
	public List<AnsDto> getAllList(String pnum){
		List<AnsDto> list = new ArrayList();
		
		SqlSession sqlSession=null; //쿼리를 실행시켜주는 객체
		
		Map<String,String>map = new HashMap<>();
		map.put("pnum", pnum); //페이지 번호 저장
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			list=sqlSession.selectList(namespace+"boardList",map);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	//1-2.페이지수 구하기
	public int getPCount() {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count = sqlSession.selectOne(namespace+"getPCount");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return count;
	}
	
	//2.새글추가하기
	public boolean insertBoard(AnsDto dto) {
		int count = 0;
		
		SqlSession sqlSession=null;
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			
			//파라미터 타입 :DTO,배열,Map(파라미터 기본적으로는 map으로 전달함)
			//					값 한개(int,string 해당타입으로 정의)
			count = sqlSession.insert(namespace+"insertBoard",dto);
			
		} catch (Exception e) {
			e.printStackTrace(); //이거 없으면 오류 출력이 안됨
		}finally{
			sqlSession.close();
		}
		
		return count>0? true:false;
	}
	
	//3.상세조회
	public AnsDto getBoard(int seq) {
		AnsDto dto = null;
		
		SqlSession sqlSession=null;
		
		//Map에 담아서 파라미터 전달하기
		Map<String, Integer> map = new HashMap<>();
		
		map.put("seq", seq);
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
//			dto = sqlSession.selectOne(namespace+"getBoard", seq);
			dto = sqlSession.selectOne(namespace+"getBoard", map);
			
		} catch (Exception e) {
			e.printStackTrace(); //이거 없으면 오류 출력이 안됨
		}finally{
			sqlSession.close();
		}
		
		return dto;
	}
	
	
	//4.수정하기
	public boolean updateBoard(AnsDto dto) {
		int count = 0;		
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count = sqlSession.update(namespace+"updateBoard",dto);
			
		} catch (Exception e) {
			e.printStackTrace(); //이거 없으면 오류 출력이 안됨
		}finally{
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	
	//5.조회수
	public boolean readCount(int seq){
		int count = 0;
		
		SqlSession sqlSession=null;
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			
			//파라미터 타입 :DTO,배열,Map(파라미터 기본적으로는 map으로 전달함)
			//					값 한개(int,string 해당타입으로 정의)
			count = sqlSession.update(namespace+"readCount", seq);
			
		} catch (Exception e) {
			e.printStackTrace(); //이거 없으면 오류 출력이 안됨
		}finally{
			sqlSession.close();
		}
		
		return count>0? true:false;
	}
	
	//6.삭제하기
	public boolean mulDel(String[] seqs) {
		int count = 0;
		SqlSession sqlSession=null;
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs", seqs);
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count = sqlSession.update(namespace+"mulDel",map);
			
		} catch (Exception e) {
			e.printStackTrace(); //이거 없으면 오류 출력이 안됨
		}finally{
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	//7.답글추가하기: update문, insert문 실행 --> 트랜잭션 처리 필요
	public boolean replyBoard(AnsDto dto) {
		int count=0;
		
		SqlSession sqlSession = null;
		
		try {									//autoCommit:false로 지정 : 트랜잭션 처리를 위해
			sqlSession=getSqlSessionFactory().openSession(false);
			
			//같은 그룹에서 부모의 스택보다 큰 글들의 step에 +1을 해준다
			sqlSession.update(namespace+"replyUpdate",dto);
			
			//insert문 실행
			count = sqlSession.insert(namespace+"replyInsert",dto);
			
			sqlSession.commit();//쿼리가 정상처리 되었다면 DB에 반영
		} catch (Exception e) {
			sqlSession.rollback();//여러작업중 실패가 있으면 모두 되돌린다
			
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	
	
	
	
	
	
	public void test() {
		//쿼리를 실행시킬 수 있는 객체
		SqlSession sqlSession=getSqlSessionFactory().openSession();
		//쿼리를 실행한다
		List<AnsDao> list =sqlSession.selectList("boardList"); //"boardList"라는 이름으로 되어있는 쿼리를 실행시켜서 list에 담겠단뜻
	}
}
