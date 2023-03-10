package kr.ac.kopo.starcafe.domain.category.repository;

import kr.ac.kopo.starcafe.domain.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MockCategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(final String name);
}
