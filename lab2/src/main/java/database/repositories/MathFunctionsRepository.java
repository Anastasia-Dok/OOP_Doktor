package database.repositories;

import database.entity.MathFunctionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MathFunctionsRepository extends JpaRepository<MathFunctionsEntity, Long> {
    List<MathFunctionsEntity>  findByFunctionName(String functionName);
}
