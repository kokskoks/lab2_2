package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;

public class MoneyTest {

    @Test(expected = IllegalArgumentException.class)
    public void add_differentCurrencyCodes(){

        Money eur = new Money(1f,"EUR");
        Money usd = new Money(1f, "USD");

        usd.add(eur);

    }

    @Test
    public void compare_compareMoneyFromDoubleAndMoneyFromBigDecimal(){

        Money doubleMoney = new Money(1.33f);
        Money bigDecimalMoney = new Money(new BigDecimal("1.33"));

        assertThat(doubleMoney.equals(bigDecimalMoney), is(true));

    }

}
