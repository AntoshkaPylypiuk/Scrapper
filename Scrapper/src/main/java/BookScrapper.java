import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BookScrapper {
    public static void main(String[] args) {

        String url = "https://knigoland.com.ua/biznes-literatura";
        String titleText = ".knl-catalog-item__clipped-text";
        String priceText = ".knl-catalog-item__price";
        String newPriceText = ".knl-catalog-item__new-price";
        String listOfBooks = ".knl-catalog__products-list";
        String itemFromList =  ".knl-catalog-item__card";

        try {
            Document document = Jsoup.connect(url).get();
            Element productsList = document.selectFirst(listOfBooks);

            if (productsList != null) {
                Elements books = productsList.select(itemFromList);

                System.out.println("=====================================");
                for (Element book : books) {
                    Element titleElement = book.selectFirst(titleText);
                    Element priceElement = book.selectFirst(priceText);

                    if (priceElement == null) {
                        priceElement = book.selectFirst(newPriceText);
                    }

                    if (titleElement != null && priceElement != null) {
                        String title = titleElement.text();
                        String price = priceElement.text();
                        System.out.println(title + " - " + price);
                    } else {
                        System.out.println("Title or Price not found for a book.");
                    }
                }
                System.out.println("=====================================");
            } else {
                System.out.println("No product list found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

