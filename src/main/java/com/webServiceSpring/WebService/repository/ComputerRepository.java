package com.webServiceSpring.WebService.repository;

import com.webServiceSpring.WebService.entity.Computer;
import com.webServiceSpring.WebService.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;


public interface ComputerRepository extends JpaRepository<Computer,Integer> {
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT c,pr,b from Computer c inner join c.product pr inner join pr.brand b where b.name=?1")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout",value = "300")})
    List<Computer> getComputerByBrand(String brand);

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT c,pr,b from Computer c inner join c.product pr inner join pr.brand b WHERE (:brandName is null or b.name = :brandName) and (:modelp is null or pr.model = :modelp) and (:screenSizeChar is null or (pr.screenSize >= 15.0 and :screenSizeChar = '>') or (pr.screenSize < 15.0 and :screenSizeChar = '<')) and (:proc is null or c.processor = :proc) and (:memChar is null or (c.memory >= 16 and :memChar = '>') or (c.memory < 16 and :memChar = '<')) and (:storageChar is null or (c.storageCapacity >= 1 and :storageChar = '>') or (c.storageCapacity < 1 and :storageChar = '<'))")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout",value = "300")})
    List<Computer> getComputerByCriteria(@Param("brandName") String brand,@Param("modelp") String model,@Param("proc") String processor,@Param("mem") Integer memory,@Param("storage") Integer storageCapacity,@Param("screenSizeChar") String screenSizeString,@Param("storageChar") String storageString,@Param("memChar") String memString);
}
