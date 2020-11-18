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
		System.out.println("���� �˰��� ȣ��" +dao.list());
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
					System.out.println("83��°��" + a);
		if(a==0) {// ���� ������
			dao.insertSubscribe(vo);
		}else { //���� ������
			int b = dao.selectSubscribe2(vo); // subscribe �� ���� ������
			System.out.println("88��°��" + b);
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
		int likeIndex = dao.selectLikeindex(vo); //���� likeIndex üũ
		System.out.println("service�� ���� like index��"+likeIndex);
		dao.updateLike1(vo); // likeIndex : update set likeIndex=1
		
		likeIndex = dao.selectLikeindex(vo); // �ٲ� likeindex üũ
		System.out.println("service�� ���� like index�� ���氪"+likeIndex);		
		return likeIndex; // �ٲ� likeindex�� return
		}
	
	
	@Override
	public int dislike(UserLikeVO vo) {
		int a = dao.selectLike(vo);
		int b = dao.selectLikeindex(vo);
		System.out.println("114��°��>>>b"+ b);
		if(a==0) { // ���̺��� ������
			dao.insertUserLike(vo); // �� �ֱ� like_index 0 ����
			b = 0;
			System.out.println("118��°��>>>b"+ b);
			return b; 
		}else {// ���̺��� ������
				dao.updateLike2(vo);
				b = dao.selectLikeindex(vo);
				System.out.println("122��°��>>>b"+ b);
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
		list = dao.list(); // ArrayList�� DB�� ����
		
		Random r = new Random(); //������ü ����
		double s_y; // ��ȸ���� ���� ���� ����
		double s_x; // ���ƿ並 ���� ���� ����
		double s_f; // �Ÿ��� ���� ���� ����
		int[][] recommandArray= new int[100][2]; //������ �迭 ����
		
		System.out.print("ArrayList"); // index >>
		System.out.println();
		
		for (int i = 0; i < list.size(); i++) {
			vo=list.get(i);
			recommandArray[i][0] = i; // �ε����� �����ϴ� ����
			
			s_y = vo.getPlay_num()/10000;
			s_x = vo.getLike_num()/100;
			
			s_f = Math.pow((s_x*s_x)+(s_y*s_y),0.5);// �Ÿ��� ���Ѱ�.
			
			recommandArray[i][1] = Math.round((float)(s_f) + r.nextFloat()*45); //math.round : �Է°��� �ݿø��� ���� ���� ����� ������ �ǹ��մϴ�.
			//r.nextFloat()*45�� ���ѰŸ� + �������� �־� ���� �������� �������ʰ� ��
			System.out.printf("%5d",recommandArray[i][0]);
		}
		System.out.println();
		for(int i = 0; i < list.size(); i++) {
			System.out.printf("%5d",recommandArray[i][1]);
		}
		System.out.println();
		System.out.println("QuickSort�� ���");
		
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
		
		System.out.println("��µ� ���� ����");
		for (int i = 0; i < 99; i++) {
			System.out.println("(������µɼ���) s_id["+i+"]>>>>> �ε�����:"+s_id[i]+">>>>>>0���� �Ÿ�: "+recommandArray[99-i][1]);
		}
		
		int idx1 ;
		
		List<VideoVO> rcList = new ArrayList<>();
		for (int i = 5; i < 50; i++)// �迭
		{	
			idx1 = s_id[i];
			System.out.println("�ε���"+idx1);
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
	
