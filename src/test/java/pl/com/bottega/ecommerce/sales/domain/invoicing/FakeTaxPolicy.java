package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.math.BigDecimal;

import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class FakeTaxPolicy implements TaxPolicy {

	public Tax calculateTax(ProductType productType, Money net) {
		
		Money taxAmount = null;
		
		switch(productType){
		
			case DRUG:
				taxAmount = net.multiplyBy(new BigDecimal("0.00"));
				break;
			case FOOD:
				taxAmount = net.multiplyBy(new BigDecimal("0.07"));
				break;
			case STANDARD:
				taxAmount = net.multiplyBy(new BigDecimal("0.23"));
				break;
			default:
				throw new IllegalArgumentException("Product type is"
						+ " not taken into consideration in this implementation"
						+ " of tax policy");
		}
		
		return new Tax(taxAmount, "Fake tax");
	}

}
