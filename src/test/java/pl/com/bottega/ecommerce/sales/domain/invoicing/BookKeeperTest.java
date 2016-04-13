package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.util.Date;

public class BookKeeperTest {

	private BookKeeper bookKeeper;
	private TaxPolicy fakeTaxPolicy;

	@Before
	public void setUp(){
		bookKeeper = new BookKeeper(new InvoiceFactory());
		fakeTaxPolicy = new FakeTaxPolicy();
	}

	@Test
	public void issuance_InvoiceRequestWithNoItems() {
		
		ClientData clientData = new ClientData(Id.generate(), "Test");
		InvoiceRequest invoiceRequest = new InvoiceRequest(clientData);
		
		Invoice testInvoice = bookKeeper.issuance(invoiceRequest, fakeTaxPolicy);

		assertThat(testInvoice.getItems().isEmpty(), is(true));
		
	}

	@Test(expected = NullPointerException.class)
	public void issuance_nullInvoiceRequest(){
		InvoiceRequest invoiceRequest = null;
		bookKeeper.issuance(invoiceRequest, fakeTaxPolicy);
	}


	@Test
	public void issuance_InvoiceRequestWithTwoItems(){
		ClientData clientData = new ClientData(Id.generate(), "Test");
		InvoiceRequest invoiceRequest = new InvoiceRequest(clientData);


		invoiceRequest.add(createDummyItem());
		invoiceRequest.add(createDummyItem());


		Invoice testInvoice = bookKeeper.issuance(invoiceRequest, fakeTaxPolicy);

		assertThat(testInvoice.getItems().size(), is(2));

	}

	private RequestItem createDummyItem(){

		ProductData productData = new ProductData(Id.generate(), Money.ZERO, "Dummy", ProductType.STANDARD,new Date());
		RequestItem item = new RequestItem(productData,  1, Money.ZERO);
		return item;
	}




}
