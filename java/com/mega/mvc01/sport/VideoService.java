package com.mega.mvc01.sport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.mvc01.JoinVO;
import com.mega.mvc01.JoinVideoUserlikeVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.UserVO;

@Service
public class VideoService implements Interface {

	@Autowired
	VideoDAO dao;
	
	
	@Override
	public List<VideoVO> list() {
		return dao.list();
	}
	@Override
	public List<VideoVO> list(String video_id) {
		return dao.list(video_id);
	}
	@Override
	public List<ChannelVO> list2(String channel_id) {
		return dao.list2(channel_id);
	}
	@Override
	public List<JoinVO> join() {
		return dao.join();
	}
	@Override
	public List<JoinVO> popular() {
		return dao.popular();
	}
	
	@Override
	public void delete() {
	}
	
	@Override
	public List<VideoVO> algorithm() {
		System.out.println("서비스 알고리즘 호출" +dao.list());
		return dao.list();
	}
	
	@Override
	public List<JoinVideoUserlikeVO> likepage(String user_id) {
		return dao.likepage(user_id);
	}
	
	@Override
	public void user_recordInsert(UserRecordVO vo) {
		dao.user_recordInsert(vo);
	}
	
	@Override
	public void SubscribeInsert(SubscribeVO vo) {
		dao.subscribeInsert(vo);
	}
	
	@Override
	public List<VideoVO> record_view(){
		return dao.record_view();
	}
	
	@Override
	public List<VideoVO> subscribeList(String user_id) {
		return dao.subscribeList(user_id);
	}
	
	@Override
	public void viewnumUpdate(String video_id) {
		dao.viewnumUpdate(video_id);
	}
	
	@Override
	public SubscribeVO subscribeBoolean(SubscribeVO vo) {
		return dao.subscribeBoolean(vo);
	}
	
	@Override
	public int selectSubscribe2(SubscribeVO vo) {
		return dao.selectSubscribe2(vo);
	}
	
	@Override
	public int startSubscribe(SubscribeVO vo) {
		int a = dao.selectSubscribe(vo);
					System.out.println("83번째줄" + a);
		if(a==0) {// 값이 없을때
			dao.insertSubscribe(vo);
		}else { //값이 있을때
			int b = dao.selectSubscribe2(vo); // subscribe 가 값이 있을때
			System.out.println("88번째줄" + b);
			if(b==0) {
				dao.updateSubscribe1(vo);// subscribe 1 
			}else {
				dao.updateSubscribe0(vo); // subscribe 0
			}
		}
		return dao.selectSubscribe2(vo); 
	}
	
	
	@Override
	public int like(UserLikeVO vo) {
		int likeIndex = dao.selectLikeindex(vo); //현재 likeIndex 체크
		System.out.println("service단 에서 like index값"+likeIndex);
		dao.updateLike1(vo); // likeIndex : update set likeIndex=1
		
		likeIndex = dao.selectLikeindex(vo); // 바뀐 likeindex 체크
		System.out.println("service단 에서 like index값 변경값"+likeIndex);		
		return likeIndex; // 바뀐 likeindex값 return
		}
	
	
	@Override
	public int dislike(UserLikeVO vo) {
		int a = dao.selectLike(vo);
		int b = dao.selectLikeindex(vo);
		System.out.println("114번째줄>>>b"+ b);
		if(a==0) { // 테이블이 없을때
			dao.insertUserLike(vo); // 값 넣기 like_index 0 대입
			b = 0;
			System.out.println("118번째줄>>>b"+ b);
			return b; 
		}else {// 테이블이 있을때
				dao.updateLike2(vo);
				b = dao.selectLikeindex(vo);
				System.out.println("122번째줄>>>b"+ b);
				return b;
			}
	}
	
	@Override
	public int selectLikeindex(UserLikeVO vo) {
		return dao.selectLikeindex(vo);
	}
	
	@Override
	public void insertUserLike(UserLikeVO vo) {
		dao.insertUserLike(vo);
	}
	
	
	@Override
	public List<VideoVO> recommandAlgorithm() throws Exception {

		List<VideoVO> list = new ArrayList<>();
		VideoVO	 vo = new VideoVO();
		list = dao.list(); // ArrayList에 DB값 저장
		
		Random r = new Random(); //랜덤객체 선언
		double s_y; // 조회수를 넣을 변수 선언
		double s_x; // 좋아요를 넣을 변수 선언
		double s_f; // 거리를 구할 변수 선언
		int[][] recommandArray= new int[100][2]; //이차원 배열 선언
		
		System.out.print("ArrayList"); // index >>
		System.out.println();
		
		for (int i = 0; i < list.size(); i++) {
			vo=list.get(i);
			recommandArray[i][0] = i; // 인덱스를 구성하는 과정
			
			s_y = vo.getPlay_num()/10000;
			s_x = vo.getLike_num()/100;
			
			s_f = Math.pow((s_x*s_x)+(s_y*s_y),0.5);// 거리를 구한것.
			
			recommandArray[i][1] = Math.round((float)(s_f) + r.nextFloat()*45); //math.round : 입력값을 반올림한 값과 가장 가까운 정수를 의미합니다.
			//r.nextFloat()*45로 구한거리 + 랜덤값을 넣어 같은 동영상이 나오지않게 함
			System.out.printf("%5d",recommandArray[i][0]);
		}
		System.out.println();
		for(int i = 0; i < list.size(); i++) {
			System.out.printf("%5d",recommandArray[i][1]);
		}
		System.out.println();
		System.out.println("QuickSort한 결과");
		
		QuickSort(recommandArray,0,99);
		
		for (int i = list.size(); i > 0; i--) {
		
		System.out.printf("%5d",recommandArray[i][0]);
	}
	System.out.println();
	for(int i = list.size(); i > 0; i--) {
		System.out.printf("%5d",recommandArray[i][1]);
	}
	System.out.println();
		
		
		
		
		int[] s_id = new int[100];
		
		
		for (int i = 0; i < 100; i++) {
			s_id[i] = recommandArray[99-i][0];
		}
		
		System.out.println("출력될 비디오 순서");
		for (int i = 0; i < 99; i++) {
			System.out.println("(먼저출력될순서) s_id["+i+"]>>>>> 인덱스값:"+s_id[i]+">>>>>>0과의 거리: "+recommandArray[99-i][1]);
		}
		
		int idx1 ;
		
		List<VideoVO> rcList = new ArrayList<>();
		for (int i = 5; i < 50; i++)// 배열
		{	
			idx1 = s_id[i];
			System.out.println("인덱스"+idx1);
			vo=list.get(idx1);
			rcList.add(list.get(idx1));
		}
		return rcList;
	}
	
	static void swap(int[][] a, int idx1, int idx2) {
		int[] t = {a[idx1][0],a[idx1][1]}; 
		a[idx1][0] = a[idx2][0];
		a[idx1][1] = a[idx2][1];
		a[idx2][0] = t[0];
		a[idx2][1] = t[1];
	}
	
	static void QuickSort(int[][] a, int l, int r) {
		int pl = l;
		int pr = r;
		int x = a[(pl + pr) / 2][1];
		
		do {
			while(a[pl][1] < x) pl++;
			while(a[pr][1] > x) pr--;
			if(pl <= pr) swap(a, pl++, pr--);
		}while(pl <= pr);
		
		if(l < pr) QuickSort(a, l, pr);
		if(pl < r) QuickSort(a, pl, r);
}
	
	
}
	
