package com.example.dongmyung.question;

import java.util.List;

import com.example.dongmyung.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	Question findBySubject(String subject);

	List<Question> findBySubjectLike(String subject);

	Question findBySubjectAndContent(String subject, String content);

	@Query("select q from Question q where q.author=:author")
	Page<Question> findByAuthor(@Param("author")SiteUser author, Pageable pageable);

	Page<Question> findAll(Pageable pageable);

	@Modifying
	@Query("update Question q set q.views = q.views + 1 where q.id = :id")
	int updateView(@Param("id") Long id);

	@Query("select "
			+ "distinct q "
			+ "from Question q "
			+ "left outer join SiteUser u1 on q.author=u1 "
			+ "left outer join Answer a on a.question=q "
			+ "left outer join SiteUser u2 on a.author=u2 "
			+ "where "
			+ "   q.subject like %:kw% "
			+ "   or q.content like %:kw% "
			+ "   or u1.username like %:kw% "
			+ "   or a.content like %:kw% "
			+ "   or u2.username like %:kw% ")
	Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

	@Query("select "
			+ "distinct q "
			+ "from Question q "
			+ "left outer join SiteUser u1 on q.author=u1 "
			+ "left outer join Answer a on a.question=q "
			+ "left outer join SiteUser u2 on a.author=u2 "
			+ "where "
			+ "   q.category =:category"
			+ "   and (q.subject like %:kw% "
			+ "   or q.content like %:kw% "
			+ "   or u1.username like %:kw% "
			+ "   or a.content like %:kw% "
			+ "   or u2.username like %:kw%)")
	Page<Question> findAllByKeywordAndCategory(@Param("kw") String kw, @Param("category") String category, Pageable pageable);

}
