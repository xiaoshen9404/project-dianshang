package com.ds.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.ds.domain.Comment;
import com.ds.domain.ReturnStatus;
import com.ds.service.impl.CommentServiceBean;
/**
 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟揭拷锟斤拷锟斤拷Comment锟斤拷锟斤拷牟锟斤拷锟�
 * @author y949548818
 */

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentServiceBean commentService;
	
	@RequestMapping(value="/publish",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public void doPublish(@RequestParam(name="content",required=false,defaultValue="")String content,
			@RequestParam(name="postId",required=false,defaultValue="-1")int postId,
			@RequestParam(name="userId",required=false,defaultValue="-1")int userId,
			PrintWriter writer)
	{
		ReturnStatus status=new ReturnStatus();
		if("".equals(content)||userId==-1||postId==-1){
			status.addReason("锟斤拷锟斤拷锟斤拷全");
			status.setResultKey(ReturnStatus.ERROR);
			writer.write(JSON.toJSONString(status));
		}
		Comment comment=new Comment();
		comment.setContent(content);
		comment.setDate(new Timestamp(new Date().getTime()));
		comment.setPostId(postId);
		comment.setUserId(userId);
		if(commentService.Publish(comment)){
			status.setResultKey(ReturnStatus.SUCCESS);
		}
		else{
			status.setResultKey(ReturnStatus.ERROR);
		}
		writer.write(JSON.toJSONString(status));
		return;
	}
	
	
}
