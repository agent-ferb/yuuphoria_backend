package Yuu.yuuphoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
    // JpaRepository provides findAll(), findById(), save(), deleteById(), etc.
    // We don't need to add anything else here.
}