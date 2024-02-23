package lab2_2_1;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    private IStockMarketService stockmarket;
    private List<Stock> stocks;

    public StocksPortfolio(IStockMarketService stockmarket){
        this.stockmarket = stockmarket;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock){
        this.stocks.add(stock);
    }

    public double totalValue(){
        double ret = 0.0;
        for (Stock stock : this.stocks) {
            ret += stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }
        return ret;
    }
}
