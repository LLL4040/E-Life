package notice.notice.repository;

import notice.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * postComments class
 *
 * @author 符永锐
 * @date 2019/07/05*/
public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
