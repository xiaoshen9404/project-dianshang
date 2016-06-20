package com.ds.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ds.dao.VideoDao;
import com.ds.domain.Page;
import com.ds.domain.User;
import com.ds.domain.Video;

@Repository
public class VideoDaoBean extends BaseDao<Video> implements VideoDao{

	@Override
	public Video selectById(int id) {
		String sql="select * from tb_video where video_id=?";
		final Video video=new Video();
		jdbcTemplate.query(sql, new Object[]{id},new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				video.setVideoId(rs.getInt("video_id"));
				video.setUserId(rs.getInt("user_id"));
				video.setTitle(rs.getString("title"));
				video.setDate(new Timestamp(rs.getDate("date").getTime()));
				video.setUri(rs.getString("uri"));
			}
		});
		return video.getVideoId()==0?null:video;
	}

	@Override
	public List<Video> selectAll() {
		String sql="select * from tb_video order by video_id asc";
		return jdbcTemplate.query(sql,new ResultSetExtractor<List<Video>>(){
			@Override
			public List<Video> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
				List<Video>list=new ArrayList<Video>();
				while(rs.next()){
					Video video=new Video();
					video.setVideoId(rs.getInt("video_id"));
					video.setUserId(rs.getInt("user_id"));
					video.setTitle(rs.getString("title"));
					video.setDate(new Timestamp(rs.getDate("date").getTime()));
					video.setUri(rs.getString("uri"));
					list.add(video);
				}
				return list;
			}
		});
	}

	@Override
	public int insert(Video video) {
		final String sql="insert into tb_video(video_id,user_id,title,date,uri)"
				+ "values(?,?,?,?,?)";
		final Object[] params=new Object[]{
				video.getVideoId(),
				video.getUserId(),
				video.getTitle(),
				video.getDate(),
				video.getUri()
				
		};
		KeyHolder keyHolder=new GeneratedKeyHolder();
		int rc=jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement st=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

				for(int i=0;i<params.length;i++){
					st.setObject(i+1, params[i]);
				}
				return st;
			}
		},keyHolder);
		if(rc>0){
			video.setVideoId(keyHolder.getKey().intValue());
		}
		return rc;
	}

	@Override
	public int update(int id, Video video) {
		String sql="update tb_video set"
				+ "video_id=?,"
				+ "user_id=?,"
				+ "title=?,"
				+ "date=?,"
				+ "uri=?"
				+ "where video_id=?";
		
		Object[] params=new Object[]{
				video.getVideoId(),
				video.getUserId(),
				video.getTitle(),
				video.getDate(),
				video.getUri()
				
		};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int delete(int id) {
		String sql="delete from tb_video where video_id=?";
		Object[] params=new Object[]{id};
		return jdbcTemplate.update(sql,params);
	}
	private static final String SQL_COUNT = "SELECT count(*) FROM tb_video";
	private static final String SQL_SELECT = "SELECT * FROM tb_video";
	@Override
	public Page<Video> page(int pageNo, int pageSize) {
		return super.pagedQuery(SQL_SELECT, SQL_COUNT, pageNo, pageSize,new Object[]{},  new BeanPropertyRowMapper<Video>(Video.class));
	}

	

}
