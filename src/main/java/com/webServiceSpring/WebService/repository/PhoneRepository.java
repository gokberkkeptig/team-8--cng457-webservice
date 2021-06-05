package com.webServiceSpring.WebService.repository;

import com.webServiceSpring.WebService.entity.Computer;
import com.webServiceSpring.WebService.entity.Phone;
import com.webServiceSpring.WebService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone,Integer> {
    @Query("SELECT p,pr,b from Phone p inner join p.product pr inner join pr.brand b where b.name=?1")
    List<Phone> getPhoneByBrand(String brand);

    @Query("SELECT p,pr,b from Phone p inner join p.product pr inner join pr.brand b WHERE (:brandName is null or b.name = :brandName) and (:model is null or pr.model = :model) and (:screenSizeP is null or pr.screenSize = :screenSizeP) and (:internalMem is null or p.internalMemory = :internalMem) ")
    List<Phone> getPhoneByCriteria(@Param("brandName") String brand, @Param("model") String model,@Param("screenSizeP") Double screenSize, @Param("internalMem") Integer internalMemory);

}
