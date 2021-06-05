package com.webServiceSpring.WebService.repository;

import com.webServiceSpring.WebService.entity.Computer;
import com.webServiceSpring.WebService.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Query("SELECT c,pr,b from Computer c inner join c.product pr inner join pr.brand b where b.name=?1")
//@Query("SELECT c,pr,b,ff from Computer c inner join c.product pr inner join pr.brand b where b.name=?1 inner join pr.productFeatures pf inner join pf.feature ff ")

/*@Query("SELECT c,pr,b from Computer c inner join c.product pr inner join pr.brand b WHERE (:brandName is null or b.name = :brandName)")
    List<Computer> getComputerByCriteria(@Param("brandName") String brand);*/

public interface ComputerRepository extends JpaRepository<Computer,Integer> {
    @Query("SELECT c,pr,b from Computer c inner join c.product pr inner join pr.brand b where b.name=?1")
    List<Computer> getComputerByBrand(String brand);


    @Query("SELECT c,pr,b from Computer c inner join c.product pr inner join pr.brand b WHERE (:brandName is null or b.name = :brandName) and (:modelp is null or pr.model = :modelp) and (:screenSizeP is null or pr.screenSize = :screenSizeP) and (:proc is null or c.processor = :proc) and (:mem is null or c.memory = :mem) and (:storage is null or c.storageCapacity = :storage)")
    List<Computer> getComputerByCriteria(@Param("brandName") String brand,@Param("modelp") String model,@Param("screenSizeP") Double screenSize,@Param("proc") String processor,@Param("mem") Integer memory,@Param("storage") Integer storageCapacity);
}
