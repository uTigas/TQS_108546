
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lab2_2_1.IStockMarketService;
import lab2_2_1.Stock;
import lab2_2_1.StocksPortfolio;

@ExtendWith(MockitoExtension.class)
public class TotalValueTest {
    @Mock
    IStockMarketService stockmarket;

    StocksPortfolio portfolio;

    @BeforeEach
    public void setUp(){
        this.portfolio = new StocksPortfolio(stockmarket);
    }
    @Test
    void testTotalValue() {
        assertEquals(portfolio.totalValue(),0,"Incorrect Total Value!");

        portfolio.addStock(new Stock("pao", 1));
        portfolio.addStock(new Stock("agua", 3));

        when(stockmarket.lookUpPrice("pao")).thenReturn(1.0);
        when(stockmarket.lookUpPrice("agua")).thenReturn(0.5);
        assertEquals(portfolio.totalValue(),2.5,"Incorrect Total Value!");
        verify(stockmarket, atLeast(1)).lookUpPrice("pao");
        verify(stockmarket, times(1)).lookUpPrice("pao");
        verify(stockmarket, times(1)).lookUpPrice("agua");

    }


}
