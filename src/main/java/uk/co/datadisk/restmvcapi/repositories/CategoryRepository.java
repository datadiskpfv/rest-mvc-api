package uk.co.datadisk.restmvcapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.restmvcapi.domain.Category;

/**
 * Created by jt on 9/24/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
