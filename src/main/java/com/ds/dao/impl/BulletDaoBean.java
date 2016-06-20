package com.ds.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ds.dao.BulletDao;
import com.ds.domain.Bullet;
import com.ds.domain.Comment;
import com.ds.domain.Page;
import com.mysql.jdbc.Statement;

@Repository
public class BulletDaoBean extends BaseDao<Bullet> implements BulletDao{

	@Override
	public Bullet selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bullet> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Bullet bullet) {
		final String sql="insert into tb_bullet(user_id,video_id,content,publish_time,video_time,color,mode) values(?,?,?,?,?,?,?)";
		final Object[] params=new Object[]{
				bullet.getUserId(),
				bullet.getVideoId(),
				bullet.getContent(),
				bullet.getPublishTime(),
				bullet.getVideoTime(),
				bullet.getColor(),
				bullet.getMode()
		};
		KeyHolder keyHolder=new GeneratedKeyHolder();
		int rc=jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement st=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				for(int i=0;i<params.length;i++){
					st.setObject(i+1, params[i]);
				}
				return st;
			}
			
		}, keyHolder);
		if(rc>0){
			bullet.setBulletId(keyHolder.getKey().intValue());
		}
		return rc;
	}

	@Override
	public int update(int id, Bullet obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page<Bullet> page(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bullet> selectAllByVideoId(int videoId) {
		String sql="SELECT * FROM tb_bullet where video_id = ? order by video_time";
		
		return jdbcTemplate.query(sql,new Object[]{videoId}, new ResultSetExtractor<List<Bullet>>(){

			@Override
			public List<Bullet> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Bullet>bullets=new ArrayList<Bullet>();
				while(rs.next()){
					Bullet bullet=new Bullet();
					bullet.setBulletId(rs.getInt("bullet_id"));
					bullet.setColor(rs.getInt("color"));
					bullet.setContent(rs.getString("content"));
					bullet.setPublishTime(rs.getTimestamp("publish_time"));
					bullet.setVideoId(rs.getInt("video_id"));
					bullet.setVideoTime(rs.getInt("video_time"));
					bullet.setMode(rs.getInt("mode"));
					bullet.setUserId(rs.getInt("user_id"));
					bullets.add(bullet);
				}
				return bullets;
			}
			
		});
	}

	@Override
	public List<Bullet> selectAllByVideoIdAndStartTime(int videoId, int startTime) {
		String sql="SELECT * FROM tb_bullet where video_id = ? and video_time >= ? order by video_time";
		
		return jdbcTemplate.query(sql,new Object[]{videoId,startTime}, new ResultSetExtractor<List<Bullet>>(){

			@Override
			public List<Bullet> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Bullet>bullets=new ArrayList<Bullet>();
				while(rs.next()){
					Bullet bullet=new Bullet();
					bullet.setBulletId(rs.getInt("bullet_id"));
					bullet.setColor(rs.getInt("color"));
					bullet.setContent(rs.getString("content"));
					bullet.setPublishTime(rs.getTimestamp("publish_time"));
					bullet.setVideoId(rs.getInt("video_id"));
					bullet.setVideoTime(rs.getInt("video_time"));
					bullet.setMode(rs.getInt("mode"));
					bullet.setUserId(rs.getInt("user_id"));
					bullets.add(bullet);
				}
				return bullets;
			}
			
		});
	}

	@Override
	public List<Bullet> selectSomeByVideoIdAndStartTime(int videoId, int startTime, int amount) {
String sql="SELECT * FROM tb_bullet where video_id = ? and video_time >= ? order by video_time limit 0,?";
		
		return jdbcTemplate.query(sql,new Object[]{videoId,startTime,amount}, new ResultSetExtractor<List<Bullet>>(){

			@Override
			public List<Bullet> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Bullet>bullets=new ArrayList<Bullet>();
				while(rs.next()){
					Bullet bullet=new Bullet();
					bullet.setBulletId(rs.getInt("bullet_id"));
					bullet.setColor(rs.getInt("color"));
					bullet.setContent(rs.getString("content"));
					bullet.setPublishTime(rs.getTimestamp("publish_time"));
					bullet.setVideoId(rs.getInt("video_id"));
					bullet.setVideoTime(rs.getInt("video_time"));
					bullet.setMode(rs.getInt("mode"));
					bullet.setUserId(rs.getInt("user_id"));
					bullets.add(bullet);
				}
				return bullets;
			}
			
		});
	}


	
}
