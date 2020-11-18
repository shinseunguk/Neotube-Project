package com.mega.mvc01.sport;

import java.util.List;

import com.mega.mvc01.JoinVO;
import com.mega.mvc01.JoinVideoUserlikeVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;

public interface Interface {

	List<VideoVO> list();

	List<VideoVO> list(String video_id);

	List<ChannelVO> list2(String channel_id);

	List<JoinVO> join();

	List<JoinVO> popular();

	void delete();

	List<VideoVO> algorithm();

	List<JoinVideoUserlikeVO> likepage(String user_id);

	void user_recordInsert(UserRecordVO vo);

	void SubscribeInsert(SubscribeVO vo);

	List<VideoVO> record_view();

	List<VideoVO> subscribeList(String user_id);

	void viewnumUpdate(String video_id);

	SubscribeVO subscribeBoolean(SubscribeVO vo);

	int selectSubscribe2(SubscribeVO vo);

	int startSubscribe(SubscribeVO vo);

	int like(UserLikeVO vo);

	int dislike(UserLikeVO vo);

	int selectLikeindex(UserLikeVO vo);

	void insertUserLike(UserLikeVO vo);

	List<VideoVO> recommandAlgorithm() throws Exception;

}