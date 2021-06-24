package com.webServiceSpring.WebService.repository;

import com.webServiceSpring.WebService.entity.Computer;
import com.webServiceSpring.WebService.entity.Phone;
import com.webServiceSpring.WebService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone,Integer> {
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout",value = "300")})
    @Query("SELECT p,pr,b from Phone p inner join p.product pr inner join pr.brand b where b.name=?1")
    List<Phone> getPhoneByBrand(String brand);

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout",value = "300")})
    @Query("SELECT p,pr,b from Phone p inner join p.product pr inner join pr.brand b WHERE (:brandName is null or b.name = :brandName) and (:model is null or pr.model = :model) and (:screenSizeChar is null or (pr.screenSize >= 6.0 and :screenSizeChar = '>') or (pr.screenSize < 6.0 and :screenSizeChar = '<')) and (:internalMemChar is null or (p.internalMemory >= 128 and :internalMemChar = '>') or (p.internalMemory < 128 and :internalMemChar = '<')) ")
    List<Phone> getPhoneByCriteria(@Param("brandName") String brand, @Param("model") String model, @Param("internalMem") Integer internalMemory,@Param("screenSizeChar") String screenSizeString,@Param("internalMemChar") String internalMemString);

}
