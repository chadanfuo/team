package model;

import java.sql.Timestamp;

public class Minwon {

		private int num;
		private String subject;
		private String id;
		private Timestamp article_date;
		private int read_cnt;
		private String content;
		private String ans_content;
		private Timestamp ans_date;
		private String ans_check;
		private int like;
		
		
		
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Timestamp getArticle_date() {
			return article_date;
		}
		public void setArticle_date(Timestamp article_date) {
			this.article_date = article_date;
		}
		public int getRead_cnt() {
			return read_cnt;
		}
		public void setRead_cnt(int read_cnt) {
			this.read_cnt = read_cnt;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getAns_content() {
			return ans_content;
		}
		public void setAns_content(String ans_content) {
			this.ans_content = ans_content;
		}
		public Timestamp getAns_date() {
			return ans_date;
		}
		public void setAns_date(Timestamp ans_date) {
			this.ans_date = ans_date;
		}
			
		public String getAns_check() {
			return ans_check;
		}
		public void setAns_check(String ans_check) {
			this.ans_check = ans_check;
		}
		
		
		
		
		public int getLike() {
			return like;
		}
		public void setLike(int like) {
			this.like = like;
		}
		// 일반 사용자가 writeForm에서 작성된 MinwonController
		public Minwon( String subject, String id, String content) {
			
			
			this.subject = subject;
			this.id = id;
	
			this.content = content;
			
		}
		
		//list에 출력될 데이터 
		public Minwon(int num, String ans_check, String subject, String id, Timestamp article_date, int read_cnt, String content) {
			
			this.num = num;
			this.ans_check = ans_check;			
			this.subject = subject;
			this.id = id;
			this.article_date = article_date;
			this.read_cnt = read_cnt;
			this.content = content;
			
		}
		
		
		//content에 출력될 데이터
		public Minwon(int num, String subject, String id, Timestamp article_date, int read_cnt, String content,
				String ans_content, Timestamp ans_date, String ans_check) {
			
			this.num = num;
			this.subject = subject;
			this.id = id;
			this.article_date = article_date;
			this.read_cnt = read_cnt;
			this.content = content;
			this.ans_content = ans_content;
			this.ans_date = ans_date;
			this.ans_check = ans_check;
		}
		
		
		
		//수정된 민원데이터
		public Minwon(int num, String subject, String content) {
			this.num = num;
			this.subject = subject;
			this.content = content;
		}
		
		
		//답변
		public Minwon(int num, String ans_content) {
			
			this.num = num;
			this.ans_content = ans_content;
		
			
		}
	
		
		//list에 출력될 데이터 (좋아요 포함)
				public Minwon(int num, String ans_check, String subject, String id, Timestamp article_date, int read_cnt, String content, int like) {
					
					this.num = num;
					this.ans_check = ans_check;			
					this.subject = subject;
					this.id = id;
					this.article_date = article_date;
					this.read_cnt = read_cnt;
					this.content = content;
					this.like = like;
					
				}
		
		
	
		
		
		
		
 		
		
}
