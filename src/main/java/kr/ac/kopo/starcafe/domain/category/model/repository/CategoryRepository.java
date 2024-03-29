package kr.ac.kopo.starcafe.domain.category.model.repository;

import kr.ac.kopo.starcafe.domain.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(final String name);
}
