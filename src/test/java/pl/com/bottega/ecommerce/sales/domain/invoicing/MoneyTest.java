package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Test;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class MoneyTest {

    @Test(expected = IllegalArgumentException.class)
    public void add_differentCurrencyCodes(){

        Money eur = new Money(1f,"EUR");
        Money usd = new Money(1f, "USD");

        usd.add(eur);

    }


}
