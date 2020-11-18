package com.mega.mvc01.sport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.JoinVO;
import com.mega.mvc01.JoinVideoUserlikeVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
//�̱�������
@Repository
public class VideoDAO {

	@Autowired
	SqlSessionTemplate mybatis;
	

	// db���� ������� list�� �̾ƿ�.
	public List<VideoVO> list() {
		List<VideoVO> list	= mybatis.selectList("video.select");
		return list;
	}

	//�α� arraylist
	public List<JoinVO> popular() {
		List<JoinVO> list = mybatis.selectList("video.popular");
		return list;
	}
	
	//Ȩ
	public List<JoinVO> join() {
		List<JoinVO> list= mybatis.selectList("video.join");
		return list;
	}
	
	//VideoVO�� list�� �̾ƿ�
	public List<VideoVO> list(String video_id) {
		 List<VideoVO> list= mybatis.selectList("video.list", video_id);
			/* System.out.println(list); */
		 return list;
	}
	
	//ChannelVO�� arraylist�� �̾ƿ�
	public List<ChannelVO> list2(String channel_id) {
		 List<ChannelVO> list= mybatis.selectList("video.list2", channel_id);
		 return list;
	}


	
	//�������̺� list select
	public List<JoinVideoUserlikeVO> likepage(String user_id) {
		List<JoinVideoUserlikeVO> list = mybatis.selectList("video.likeVideo", user_id);
		return list;
	}
	
	//record insert
	public void user_recordInsert(UserRecordVO vo) {
		 mybatis.insert("video.user_recordInsert", vo);
	}
	
	//record_view list select
	public List<VideoVO>  record_view() {
		List<VideoVO> list= mybatis.selectList("video.recordView");
			return list;
	}
	
	//���� list insert
	public void subscribeInsert(SubscribeVO vo) {
		 mybatis.insert("video.insertSubscribe", vo);
	}
	
	//���� list select
	public List<VideoVO>  subscribeList(String user_id){
		List<VideoVO> list= mybatis.selectList("video.subscribeList", user_id);
		System.out.println(user_id);
		return list;
	}
	
	public void viewnumUpdate(String video_id) {
		mybatis.update("video.updatePlaynum", video_id);
	}
	
	//���� ����Ʈ �̾Ƴ��� subscribe ���� ������
	public SubscribeVO subscribeBoolean(SubscribeVO vo) {
		SubscribeVO vo2 = mybatis.selectOne("video.subscribeBoolean", vo);
		return vo2;
	}
	
	
	// ������ ������ ���� �ش� ������ ä���� ���� ���θ� �ݺ��ؾ��ϴµ� db�� ����� ���� ������
	// ���� ������ ä�� ���� ���θ� db�� �����ϰ� �����ϱ� ����
	public void insertSubscribe(SubscribeVO vo) {
		System.out.println("dao: " + vo.getUser_id()+" "+vo.getChannel_id());
		mybatis.insert("video.insertSubscribe", vo);
	}
	
	// ������ �ߴ����� ���θ� �Ǵ��ϰ� ���� ��ȯ�ޱ� ����
	// �ش� ����� ���ڸ� �޾ƿͼ� �Ǵ�
	public int selectSubscribe(SubscribeVO vo) {
		return mybatis.selectOne("video.selectSubscribe", vo);
	}

	// ������ �ߴ����� ���θ� �Ǵ��ϰ� ���� ��ȯ�ޱ� ����
	// ������ ���� �޾Ƽ� �Ǵ�
	public int selectSubscribe2(SubscribeVO vo) {
		return mybatis.selectOne("video.selectSubscribe2", vo);
	}
	
	// ���� ��ư�� ������ ������ ������Ʈ ���� �ֱ� ����
	public void updateSubscribe0(SubscribeVO vo) {
		mybatis.update("video.updateSubscribe0", vo);
	}
	
	// ���� ��ư�� ������ ������ ������Ʈ ���� �ֱ� ����
	public void updateSubscribe1(SubscribeVO vo) {
		mybatis.update("video.updateSubscribe1", vo);
	}
	

	
	//������� �������� like_index���� ������ 0 �߰�
	public void insertUserLike(UserLikeVO vo) {
		int a = 1;
		List<UserLikeVO> list= mybatis.selectList("video.selectLike", vo);
		System.out.println("userlikeve ����Ʈ ������"+list.size());
		if(list.size()==0) {
			 a = 0; 
			 mybatis.insert("video.insertUserLike", vo);
		}
		System.out.println("Videodao�� a��" + a);
	}
	
	//like_index�� 1�� ������ ����
	public void updateLike1(UserLikeVO vo) {
		mybatis.update("video.updateLike1",vo);
	}
	
	//like_index�� 2�� ���� ����
	public void updateLike2(UserLikeVO vo) {
		mybatis.update("video.updateLike2",vo);
	}
	
	//������� �������� non/like/dislike ����
	public int selectLike(UserLikeVO vo) {
		UserLikeVO vo2 = mybatis.selectOne("video.selectLike", vo);
		System.out.println("like index�� : "+vo2.getLike_index());
			return vo2.getLike_index();
	}
	
	public int selectLikeindex(UserLikeVO vo) {
		UserLikeVO vo2 = mybatis.selectOne("video.selectLikeindex", vo);
		System.out.println("selectlikeindex ��"+vo2.getLike_index());
		int a = vo2.getLike_index();
		//likeindex�� �̾Ƴ���
		return a;
	}
	
	public void updatePlusLike(VideoVO vo) {
		mybatis.update("video.updatePlusLike", vo);
	}
	public void updateMinusLike(VideoVO vo) {
		mybatis.update("video.updateMinusLike", vo);
	}
	public void updatePlusDisLike(VideoVO vo) {
		mybatis.update("video.updatePlusDisLike", vo);
	}
	public void updateMinusDisLike(VideoVO vo) {
		mybatis.update("video.updateMinusDisLike", vo);
	}
	
	

}
