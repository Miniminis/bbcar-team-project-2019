package com.ycha.par.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.ReviewDao;
import com.ycha.par.domain.Review;

@Service("deleteReviewByPassenger")
public class DeleteReviewByPassengerService {
	
	@Autowired
	private SqlSessionTemplate template;
	
	private ReviewDao dao;
	
	public int deleteReview(int rv_idx, int p_idx) {
		dao = template.getMapper(ReviewDao.class);
		int rscnt = 0; //반환값 
		
		//1. rv_idx 에 해당하는 리뷰가 존재하는지 확인
		Review review = dao.selectByRvIdx(rv_idx);
		System.out.println("후기 삭제 03  "+review.toString());
		
		if(review == null) {
			rscnt = 0; //일치하는 리뷰 내역이 없음 
		} else if(review.getP_idx() == p_idx
						&& review.getDr_content() != null
						&& review.getDr_star() > 0){
			
			rscnt = dao.deleteOnlyPassengerReview(rv_idx);
			System.out.println("후기 삭제 04-1  "+rscnt);
			
		} else if(review.getP_idx() == p_idx
				&& review.getDr_content() == null
				&& !(review.getDr_star()>0)){
			
			rscnt = dao.deleteReview(rv_idx);
			System.out.println("후기 삭제 04-2  "+rscnt);
		} else { //그 밖의 예외상황들 
			rscnt = 0;
		}
		
		System.out.println("후기 삭제 05  "+rscnt);
		return rscnt;
	}
}
