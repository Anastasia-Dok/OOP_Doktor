package database.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import database.entity.MathFunctionsEntity;
import database.entity.PointEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<PointEntity, Long> {
    List<PointEntity> findByFunction(MathFunctionsEntity function);
}