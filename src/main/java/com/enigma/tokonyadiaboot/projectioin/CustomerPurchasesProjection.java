package com.enigma.tokonyadiaboot.projectioin;

import java.math.BigDecimal;

public interface CustomerPurchasesProjection {
    String getCustomerName();
    BigDecimal getTotalPurchase();
}
