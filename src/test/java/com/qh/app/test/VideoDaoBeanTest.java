package com.qh.app.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ds.dao.impl.VideoDaoBean;
import com.ds.domain.Page;
import com.ds.domain.User;
import com.ds.domain.Video;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("file:src/main/webapp/WEB-INF/springmvc-servlet.xml") 
public class VideoDaoBeanTest {

	@Autowired
	VideoDaoBean videoDao;
	//succeed!
	@Test
	public void selectById() {
		System.out.println(videoDao.selectById(5));
	}
	//succeed!
	@Test
	public void selectAll(){
		List<Video> list =new ArrayList<>();
		list=videoDao.selectAll();
		for (Video video : list) {
			System.out.println(video);
		}
		
	}
	//succeed!
	@Test
	public void insert(){
	Video video=new Video();
	video.setVideoId(11);
	video.setTitle("test3");
	video.setUserId(4);
	video.setDate(new Timestamp(new Date().getTime()));
	video.setUri("video/3/bbb.mp4");
	videoDao.insert(video);
	}
	//succeed!
	@Test
	public void update(){
		Video video=new Video();
		video.setVideoId(10);
		video.setTitle("aaaa");
		video.setUserId(4);
		video.setDate(new Timestamp(new Date().getTime()));
		video.setUri("video/2/aaa.mp4");
		videoDao.update(video.getVideoId(), video);
	}
	//succeed!
	@Test
	public void delete(){
		videoDao.delete(10);
	}
	//succeed!
	@Test
	public void page(){
		Page<Video> page=videoDao.page(1, 10);
		System.out.println(page.getResult());	
		
	}
}
