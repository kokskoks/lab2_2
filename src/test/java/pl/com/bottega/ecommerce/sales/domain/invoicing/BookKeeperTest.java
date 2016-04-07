package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class BookKeeperTest {

	@Test
	public void issuance_InvoiceRequestWithNoItems() {
		BookKeeper bookKeeper = new BookKeeper();
		
		ClientData clientData = new ClientData(Id.generate(), "Testo");
		InvoiceRequest invoiceRequest = new InvoiceRequest(clientData);

		TaxPolicy taxPolicy = new FakeTaxPolicy();
		
		Invoice testInvoice = bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		assertThat(testInvoice.getGros(), is(Money.ZERO));
		assertThat(testInvoice.getNet(), is(Money.ZERO));
		assertThat(testInvoice.getItems().isEmpty(), is(true));
		
	}
	

}
