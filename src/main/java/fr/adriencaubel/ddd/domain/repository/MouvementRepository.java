package fr.adriencaubel.ddd.domain.repository;

import fr.adriencaubel.ddd.domain.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MouvementRepository extends JpaRepository<Mouvement, Long> {
    @Query("""
        SELECT SUM(
            CASE 
                WHEN m.type = 'INPUT' THEN m.quantity 
                ELSE -m.quantity 
            END
        )
        FROM Mouvement m
        WHERE m.articleId = :articleId
        AND m.provider.id = :providerId
        """)
    Integer computeStock(Long articleId, Long providerId);
}
