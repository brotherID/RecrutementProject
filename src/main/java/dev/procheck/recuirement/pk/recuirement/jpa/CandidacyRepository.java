package dev.procheck.recuirement.pk.recuirement.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.procheck.recuirement.pk.recuirement.entity.Candidacy;

public interface CandidacyRepository extends JpaRepository<Candidacy, String> {
	@Query("SELECT candidacy from Candidacy candidacy where candidacy.town = :town and "
			+ "candidacy.borough =:borough and candidacy.skillsArea =:skills_area and"
			+ " candidacy.level =:level and candidacy.experience =:experience")
	public Page<Candidacy> findByTownAndByBoroughAndBySkills_areaAndByLevelAndByExperience(@Param("town")String town
			,@Param("borough")String borough ,@Param("skills_area")String skills_area,
			@Param("level")String level,@Param("experience")String experience,Pageable pageRequest );
}
