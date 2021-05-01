package com.enigma.tokonyadiaboot.repository;

import com.enigma.tokonyadiaboot.entity.Merchant;
import com.enigma.tokonyadiaboot.entity.Purchase;
import com.enigma.tokonyadiaboot.projectioin.CustomerPurchasesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {
    @Query(value =
            "SELECT " +
                    "c.name as customerName," +
                    "SUM (P.price*T.quantity) as totalPurchase " +
                    "FROM " +
                    "t_purchases t " +
                    "JOIN m_customers c ON C.id = t.customer_id " +
                    "JOIN m_products p on P.id = t.product_id " +
                    "JOIN m_merchants m on M.id = p.merchant_id " +
                    "WHERE " +
                    "m.id = ?1 " +
                    "GROUP BY " +
                    "c.name, p.price",
            nativeQuery = true)
    List<CustomerPurchasesProjection> showAllCustomersByMerchantId(String merchantId);
}
