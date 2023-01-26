package com.athema.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.athema.dto.ReviewDTO;
import com.athema.service.ReviewService;


@SpringBootTest
class InsertTests {

	@Autowired
	ReviewService service;

	@Test
	void contextLoads() {								
		ReviewDTO review = new ReviewDTO();
			review.setReview_code(3);
			review.setMem_code(02);
			review.setItem_code(100);
			review.setTitle("여행");
			review.setContent("라라라라라");
			review.setScore(3);
			review.setReview_date(null);
			review.setReview_img("rphoto.jpg");

		try {
			service.register(review);
			System.out.println("[OK]");
		} catch (Exception e) {
			System.out.println("[ FAIL ]");
			e.printStackTrace();
		}

	}

}
